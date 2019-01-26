package lt.karpovic.fileservice.controller;

import lt.karpovic.fileservice.common.ResponseStatusEnum;
import lt.karpovic.fileservice.model.ServiceResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    @GetMapping("/ping")
    public ServiceResponse ping() {
        return new ServiceResponse(ResponseStatusEnum.COMPLETED_SUCCESS, ResponseStatusEnum.COMPLETED_SUCCESS.getStatusCode());
    }


}
