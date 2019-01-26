package lt.karpovic.fileservice.service.impl;

import lt.karpovic.fileservice.common.MsgConst;
import lt.karpovic.fileservice.common.Validator;
import lt.karpovic.fileservice.model.ServiceResponse;
import lt.karpovic.fileservice.model.singletone.UploadFiles;
import lt.karpovic.fileservice.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {


    @Override
    public ServiceResponse uploadFile(MultipartFile file) {
        ServiceResponse response = null;
        String validation = Validator.validateInputFile(file);
        if (validation.isEmpty()) {
            UploadFiles.getInstance().addFile(file);
            response = ServiceResponse.getSuccessStatus();
        } else {
            response = ServiceResponse.getNotSuccessStatus();
            response.setResponseMsg(validation);
        }
        return response;
    }

    @Override
    public ServiceResponse getNumberOfFiles() {
        int numberOfFile = UploadFiles.getInstance().getNumberOfFiles();
        ServiceResponse response = ServiceResponse.getSuccessStatus();
        response.setResponseMsg(String.format(MsgConst.APP_STATUS_NUMBER_OF_UPLOAD_FILES, numberOfFile));
        return response;
    }
}
