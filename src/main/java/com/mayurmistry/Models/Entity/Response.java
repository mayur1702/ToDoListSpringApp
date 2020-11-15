package com.mayurmistry.Models.Entity;

import com.fasterxml.jackson.databind.util.JSONWrappedObject;

public class Response {
    private boolean status;
    private String body;
    private int errorCode;
    private JSONWrappedObject data;

    public JSONWrappedObject getData() {
        return data;
    }

    public void setData(JSONWrappedObject data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    private String exceptionMessage;

    public Response() {}

}
