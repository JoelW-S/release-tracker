package com.github.joelws.release.tracker.service.release.operation;

import com.github.joelws.release.tracker.conversion.ReleaseAdapter;
import com.github.joelws.release.tracker.dto.release.ReleaseDto;
import com.github.joelws.release.tracker.entity.release.Release;
import com.github.joelws.release.tracker.handler.JsonResponse;
import com.github.joelws.release.tracker.service.ServiceHelper;
import com.github.joelws.release.tracker.service.ServiceOperation;
import com.github.joelws.release.tracker.service.release.execution.UpdateReleaseServiceExecution;

import javax.ws.rs.core.Response;

public class UpdateReleaseServiceOperation extends ServiceOperation<String> {
    private final ServiceHelper helper;
    private final UpdateReleaseServiceExecution updateReleaseServiceExecution;

    public UpdateReleaseServiceOperation(final ServiceHelper helper,
                                         final UpdateReleaseServiceExecution updateReleaseServiceExecution) {
        this.helper = helper;
        this.updateReleaseServiceExecution = updateReleaseServiceExecution;
    }

    @Override
    protected Response delegate(String json) {
        ReleaseAdapter adapter = helper.getFactory().getImpl(ReleaseAdapter.class);

        Release result = updateReleaseServiceExecution
                .execute(adapter.adapt(helper.getJsonAdapter().getObjectFromJson(json, ReleaseDto.class)));

        Response response = null;

        if (result != null) {
            response = helper.getRestResponseBuilder().build(200, result);
        } else {
            response = helper.getRestResponseBuilder().build(new JsonResponse(404, "Release doesn't exist"));
        }
        return response;
    }
}
