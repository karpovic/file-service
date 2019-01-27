package lt.karpovic.fileservice.controller;

import lt.karpovic.fileservice.model.ServiceResponse;
import lt.karpovic.fileservice.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class FileController {

    @Autowired
    FileService fileService;


    @PostMapping(value = "/uploadFile")
    public ServiceResponse uploadFile(@RequestParam("file") MultipartFile file) {
        return fileService.uploadFile(file);
    }

    @GetMapping(value = "/getStatus")
    public ServiceResponse getStatus() {
        return fileService.getStatus();
    }

    @PostMapping(value = "/resetSystem")
    public ServiceResponse resetSystem() {
        return fileService.resetSystem();
    }

    @GetMapping(value = "/getWordsByInterval1")
    public ServiceResponse getWordsByInterval1() {
        return fileService.getWordsByInterval1();
    }

    @GetMapping(value = "/getWordsByInterval2")
    public ServiceResponse getWordsByInterval2() {
        return fileService.getWordsByInterval2();
    }

    @GetMapping(value = "/getWordsByInterval3")
    public ServiceResponse getWordsByInterval3() {
        return fileService.getWordsByInterval3();
    }

    @GetMapping(value = "/getWordsByInterval4")
    public ServiceResponse getWordsByInterval4() {
        return fileService.getWordsByInterval4();
    }

    @GetMapping(value = "/getFileByIntervalNumber")
    public ResponseEntity<InputStreamResource> getFileByIntervalNumber(@RequestParam("intervalNumber") int intervalNumber) {
        return fileService.getFileByIntervalNumber(intervalNumber);
    }



}
