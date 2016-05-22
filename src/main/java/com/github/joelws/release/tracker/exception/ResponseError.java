package com.github.joelws.release.tracker.exception;


public class ResponseError {

    private Integer errorCode;

    private String reason;

    public ResponseError() {
    }

    public ResponseError(Integer errorCode, String reason) {
        this.errorCode = errorCode;
        this.reason = reason;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
