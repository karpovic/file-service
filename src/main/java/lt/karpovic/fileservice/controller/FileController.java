package lt.karpovic.fileservice.controller;

import lt.karpovic.fileservice.model.ServiceResponse;
import lt.karpovic.fileservice.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
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




}
