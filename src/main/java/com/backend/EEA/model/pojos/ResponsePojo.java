package com.backend.EEA.model.pojos;

public class ResponsePojo {
    private boolean success;
    private String exceptionMessage;
    private Object content;

    public ResponsePojo(boolean success, String exceptionMessage, Object content) {
        this.success = success;
        this.exceptionMessage = exceptionMessage;
        this.content = content;
    }

    public ResponsePojo() {
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static ResponsePojo error(String exceptionMessage, String content){
        return new ResponsePojo(false, exceptionMessage, content);
    }

}
