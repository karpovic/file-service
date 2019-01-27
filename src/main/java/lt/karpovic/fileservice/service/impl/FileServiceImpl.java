package lt.karpovic.fileservice.service.impl;

import lt.karpovic.fileservice.common.AppConfig;
import lt.karpovic.fileservice.common.MsgConst;
import lt.karpovic.fileservice.common.Validator;
import lt.karpovic.fileservice.model.ServiceResponse;
import lt.karpovic.fileservice.model.Word;
import lt.karpovic.fileservice.model.singletone.UploadFiles;
import lt.karpovic.fileservice.service.FileService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Scanner;

@Service
public class FileServiceImpl implements FileService {

    private static final Logger logger = Logger.getLogger(FileService.class);

    @Override
    public ServiceResponse uploadFile(MultipartFile file) {
        ServiceResponse response = null;
        String validation = Validator.validateInputFile(file);
        if (validation.isEmpty()) {
            UploadFiles.getInstance().addFileNameToList(file.getOriginalFilename());

            response = processUploadedFile(file);

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


    private ServiceResponse processUploadedFile(MultipartFile file) {
        ServiceResponse response = ServiceResponse.getSuccessStatus();
        try (Scanner sc = new Scanner(file.getInputStream(), AppConfig.FILE_ENCODING)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                for (String word : line.split(AppConfig.REG_EX_FOR_SPLITTING_WORDS)) {
                    String w = word.replaceAll(AppConfig.REG_EX_FOR_LEAVE_ONLY_LETTERS, AppConfig.SYMBOL_FOR_REPLACING_NOT_LETTERS).toLowerCase();
                    if (!w.isEmpty()) {
                        if (UploadFiles.getInstance().getWordsMap().get(w) != null) {
                            UploadFiles.getInstance().getWordsMap().get(w).incrementCounter();
                        } else {
                            UploadFiles.getInstance().addWord(new Word(w));
                        }
                    }
                }
            }
        } catch (IOException e) {
            logger.error(MsgConst.ERROR_WHILE_READING_FILE + e.getMessage());
            response = ServiceResponse.getNotSuccessStatus();
            response.setResponseMsg(MsgConst.ERROR_WHILE_READING_FILE);
        }
        return response;
    }

}
