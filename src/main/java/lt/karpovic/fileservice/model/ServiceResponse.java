package lt.karpovic.fileservice.model;

import lt.karpovic.fileservice.common.ResponseStatusEnum;

public class ServiceResponse {

    private String responseMsg = "";

    private ResponseStatusEnum status;

    private int statusCode;

    public ServiceResponse(ResponseStatusEnum status, int statusCode) {
        this.status = status;
        this.statusCode = statusCode;
    }

    public ServiceResponse(String responseMsg, ResponseStatusEnum status, int statusCode) {
        this.responseMsg = responseMsg;
        this.status = status;
        this.statusCode = statusCode;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    public ResponseStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ResponseStatusEnum status) {
        this.status = status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public static ServiceResponse getSuccessStatus() {
        return new ServiceResponse(ResponseStatusEnum.COMPLETED_SUCCESS, ResponseStatusEnum.COMPLETED_SUCCESS.getStatusCode());
    }

    public static ServiceResponse getNotSuccessStatus() {
        return new ServiceResponse(ResponseStatusEnum.CLIENT_ERROR, ResponseStatusEnum.CLIENT_ERROR.getStatusCode());
    }
}
