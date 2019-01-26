package lt.karpovic.fileservice.service;

import lt.karpovic.fileservice.model.ServiceResponse;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    /**
     * method will validate upload file and add to UploadFile.class uploaded file list
     *
     * @param file
     * @return
     */
    ServiceResponse uploadFile(MultipartFile file);

    /**
     * method will check number of uploaded files in UploadFile.class and return response message with number
     *
     * @return
     */
    ServiceResponse getNumberOfFiles();
}
