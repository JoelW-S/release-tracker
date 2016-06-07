package com.github.joelws.release.tracker.exception;


import com.github.joelws.release.tracker.handler.JsonResponse;

public class ReleaseTrackerException extends RuntimeException {

    private final JsonResponse errorResponse;

    public ReleaseTrackerException(final JsonResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public JsonResponse getErrorResponse() {
        return errorResponse;
    }
}
