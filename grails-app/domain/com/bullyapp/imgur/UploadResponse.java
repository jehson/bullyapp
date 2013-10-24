package com.bullyapp.imgur;

public class UploadResponse {
    private UploadResponseData data;
    private Integer status;
    private Boolean success;
    
    public UploadResponseData getData() {
        return data;
    }
    public void setData(UploadResponseData data) {
        this.data = data;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Boolean getSuccess() {
        return success;
    }
    public void setSuccess(Boolean success) {
        this.success = success;
    }
    
}
