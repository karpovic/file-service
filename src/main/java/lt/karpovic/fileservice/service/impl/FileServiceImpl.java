package lt.karpovic.fileservice.service.impl;

import lt.karpovic.fileservice.common.AppConfig;
import lt.karpovic.fileservice.common.MsgConst;
import lt.karpovic.fileservice.common.Validator;
import lt.karpovic.fileservice.model.ServiceResponse;
import lt.karpovic.fileservice.model.Word;
import lt.karpovic.fileservice.model.singletone.UploadFiles;
import lt.karpovic.fileservice.service.FileService;
import org.apache.log4j.Logger;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Service
public class FileServiceImpl implements FileService {

    private static final Logger logger = Logger.getLogger(FileService.class);

    @Override
    public ServiceResponse uploadFile(MultipartFile file) {
        ServiceResponse response = ServiceResponse.getSuccessStatus();
        String validation = Validator.validateInputFile(file);
        if (validation.isEmpty()) {
            UploadFiles.getInstance().addFileNameToList(file.getOriginalFilename());
            processUploadedFile(file);
        } else {
            response = ServiceResponse.getNotSuccessStatus();
            response.setResponseMsg(validation);
        }
        return response;
    }

    @Override
    public ServiceResponse getStatus() {
        int numberOfFile = UploadFiles.getInstance().getNumberOfFiles();
        int numberOfWords = UploadFiles.getInstance().getNumberOfWords();
        ServiceResponse response = ServiceResponse.getSuccessStatus();
        StringBuilder responseMsg = new StringBuilder();
        responseMsg.append(String.format(MsgConst.APP_STATUS_NUMBER_OF_UPLOAD_FILES, numberOfFile));
        responseMsg.append(String.format(MsgConst.APP_STATUS_NUMBER_OF_WORDS, numberOfWords));
        response.setResponseMsg(responseMsg.toString());
        return response;
    }

    @Override
    public ServiceResponse resetSystem() {
        ServiceResponse response = ServiceResponse.getSuccessStatus();
        UploadFiles.reset();
        return response;
    }

    @Override
    public ServiceResponse getWordsByInterval1() {
        ServiceResponse response = ServiceResponse.getSuccessStatus();
        response.setResponseMsg(getWordList(AppConfig.WORDS_1));
        return response;
    }

    @Override
    public ServiceResponse getWordsByInterval2() {
        ServiceResponse response = ServiceResponse.getSuccessStatus();
        response.setResponseMsg(getWordList(AppConfig.WORDS_2));
        return response;
    }

    @Override
    public ServiceResponse getWordsByInterval3() {
        ServiceResponse response = ServiceResponse.getSuccessStatus();
        response.setResponseMsg(getWordList(AppConfig.WORDS_3));
        return response;
    }

    @Override
    public ServiceResponse getWordsByInterval4() {
        ServiceResponse response = ServiceResponse.getSuccessStatus();
        response.setResponseMsg(getWordList(AppConfig.WORDS_4));
        return response;
    }

    @Override
    public ResponseEntity<InputStreamResource> getFileByIntervalNumber(int intervalNumber) {
        String validateMsg = Validator.validateIntervalNumber(intervalNumber);
        ResponseEntity response = null;
        if (validateMsg.isEmpty()) {
            response = getInputStreamResourceResponseEntity(getWordPartByIntervalNumber(intervalNumber));
        } else {
            response = ResponseEntity.status(HttpStatus.FORBIDDEN).body(validateMsg);
        }
        return response;
    }

    private String getWordPartByIntervalNumber(int intervalNumber) {
        String wordPart = "";
        switch (intervalNumber) {
            case 1:
                wordPart = AppConfig.WORDS_1;
                break;
            case 2:
                wordPart = AppConfig.WORDS_2;
                break;
            case 3:
                wordPart = AppConfig.WORDS_3;
                break;
            case 4:
                wordPart = AppConfig.WORDS_4;
                break;
            default:
                wordPart = "";
                break;
        }
        return wordPart;
    }

    private ResponseEntity<InputStreamResource> getInputStreamResourceResponseEntity(String wordPart) {
        String content = getWordList(wordPart, true);
        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(content.getBytes()));
        String fileName = AppConfig.FILE_NAME_PREFIX + wordPart + AppConfig.FILE_EXTENSION_SPLIT + AppConfig.TXT_FILE_EXTENSION;
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, AppConfig.ATTACHMENT_HEADER + fileName)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(resource);
    }

    private String getWordList(String wordPart) {
        return getWordList(wordPart, false);
    }

    private String getWordList(String wordPart, boolean splitByNewLine) {
        StringBuilder result = new StringBuilder();
        if (wordPart != null && !wordPart.isEmpty()) {
            for (Word word : UploadFiles.getInstance().getPartWordsMap(wordPart).values()) {
                result.append(word.getWordValue()).append(AppConfig.SYMBOL_FOR_SEPARATE_WORD_AND_NUMBER).append(word.getCounter())
                        .append(splitByNewLine ? AppConfig.SPLIT_WORDS_NEW_LINE : AppConfig.SPLIT_WORDS);
            }
        }
        return result.toString();
    }


    private void processUploadedFile(MultipartFile file) {
        try {
            Scanner sc = new Scanner(file.getInputStream(), AppConfig.FILE_ENCODING);
            Executor executor = Executors.newSingleThreadExecutor();
            executor.execute(() -> processFile(sc));
        } catch (IOException e) {
            logger.error(MsgConst.ERROR_WHILE_READING_FILE + e.getMessage());
        }
    }

    private void processFile(Scanner sc) {
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            for (String word : line.split(AppConfig.REG_EX_FOR_SPLITTING_WORDS)) {
                String w = word.replaceAll(AppConfig.REG_EX_FOR_LEAVE_ONLY_LETTERS, AppConfig.SYMBOL_FOR_REPLACING_NOT_LETTERS).toLowerCase();
                UploadFiles.getInstance().processWord(w);
            }
        }
        sc.close();
    }

}
