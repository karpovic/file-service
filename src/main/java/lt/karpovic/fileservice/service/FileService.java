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
     * method will return number of loaded files and number of words
     *
     * @return
     */
    ServiceResponse getStatus();

    /**
     * method will reset uploaded files and found words
     *
     * @return
     */
    ServiceResponse resetSystem();
}
