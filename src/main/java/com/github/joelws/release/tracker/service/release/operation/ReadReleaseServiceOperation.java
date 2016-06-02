package com.github.joelws.release.tracker.service.release.operation;

import com.github.joelws.release.tracker.entity.release.Release;
import com.github.joelws.release.tracker.handler.JsonResponse;
import com.github.joelws.release.tracker.service.ServiceHelper;
import com.github.joelws.release.tracker.service.ServiceOperation;
import com.github.joelws.release.tracker.service.release.execution.ReadReleaseServiceExecution;

import javax.ws.rs.core.Response;

public class ReadReleaseServiceOperation extends ServiceOperation<String> {
    private final ServiceHelper helper;

    private final ReadReleaseServiceExecution readReleaseServiceExecution;

    public ReadReleaseServiceOperation(final ServiceHelper helper,
                                       final ReadReleaseServiceExecution readReleaseServiceExecution) {
        this.helper = helper;
        this.readReleaseServiceExecution = readReleaseServiceExecution;
    }

    @Override
    protected Response delegate(String identifier) {
        Release result = readReleaseServiceExecution.execute(identifier);

        Response response = null;
        if (result != null) {
            response = helper.getRestResponseBuilder().build(200, result);
        } else {
            response = helper.getRestResponseBuilder().build(new JsonResponse(404, "Release doesn't exist"));
        }
        return response;
    }
}
