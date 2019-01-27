package lt.karpovic.fileservice.service;

import lt.karpovic.fileservice.model.ServiceResponse;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    /**
     * method will validate upload file and process in thread uploaded file
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

    /**
     * return words from 1 interval by AppConfig.WORDS_1
     *
     * @return
     */
    ServiceResponse getWordsByInterval1();

    /**
     * return words from 2 interval by AppConfig.WORDS_2
     *
     * @return
     */
    ServiceResponse getWordsByInterval2();

    /**
     * return words from 3 interval by AppConfig.WORDS_3
     *
     * @return
     */
    ServiceResponse getWordsByInterval3();

    /**
     * return words from 4 interval by AppConfig.WORDS_4
     *
     * @return
     */
    ServiceResponse getWordsByInterval4();

}
