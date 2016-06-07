package com.github.joelws.release.tracker.service.release.operation;

import com.github.joelws.release.tracker.conversion.ReleaseToReleaseDtoAdapter;
import com.github.joelws.release.tracker.dto.release.ReleaseDto;
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
        final Release result = readReleaseServiceExecution.execute(identifier);

        Response response = null;

        if (result != null) {
            final ReleaseToReleaseDtoAdapter adapter = helper.getFactory().getImpl(ReleaseToReleaseDtoAdapter.class);
            final ReleaseDto adaptedResult = adapter.adapt(result);
            response = helper.getRestResponseBuilder().build(200, adaptedResult);
        } else {
            response = helper.getRestResponseBuilder().build(new JsonResponse(404, "Release doesn't exist"));
        }
        return response;
    }
}
