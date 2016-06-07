package com.github.joelws.release.tracker.service.artifact.operation;

import com.github.joelws.release.tracker.handler.JsonResponse;
import com.github.joelws.release.tracker.service.ServiceHelper;
import com.github.joelws.release.tracker.service.ServiceOperation;

import org.apache.log4j.Logger;

import javax.ws.rs.core.Response;

public class UpdateArtifactServiceOperation extends ServiceOperation<String> {
    private static final Logger LOGGER = Logger.getLogger(UpdateArtifactServiceOperation.class);

    private final ServiceHelper helper;

    public UpdateArtifactServiceOperation(final ServiceHelper helper) {
        this.helper = helper;
    }

    @Override
    protected Response delegate(String param) {
        return helper.getRestResponseBuilder().build(new JsonResponse(400, "Unsupported operation"));
    }
}
