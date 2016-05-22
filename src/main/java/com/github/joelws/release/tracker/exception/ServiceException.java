package com.github.joelws.release.tracker.exception;


import javax.ws.rs.core.Response.Status;

public class ServiceException extends RuntimeException {
    private ResponseError responseError;

    public ServiceException() {
    }

    public ServiceException(Status status, Throwable cause) {
        super(cause);
        this.responseError = new ResponseError(status.getStatusCode(), status.getReasonPhrase());
    }

    public ServiceException(Status status) {
        this.responseError = new ResponseError(status.getStatusCode(), status.getReasonPhrase());
    }

    public ResponseError getResponseError() {
        return responseError;
    }

    public void setResponseError(ResponseError responseError) {
        this.responseError = responseError;
    }
}
