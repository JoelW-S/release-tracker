package com.github.joelws.release.tracker.service.release.operation;

import com.github.joelws.release.tracker.conversion.ReleaseDtoToReleaseAdapter;
import com.github.joelws.release.tracker.conversion.ReleaseToReleaseDtoAdapter;
import com.github.joelws.release.tracker.dto.release.ReleaseDto;
import com.github.joelws.release.tracker.entity.release.Release;
import com.github.joelws.release.tracker.handler.JsonResponse;
import com.github.joelws.release.tracker.service.ServiceHelper;
import com.github.joelws.release.tracker.service.ServiceOperation;
import com.github.joelws.release.tracker.service.release.execution.CreateReleaseServiceExecution;

import javax.ws.rs.core.Response;

public class CreateReleaseServiceOperation extends ServiceOperation<String> {
    private final ServiceHelper helper;

    private final CreateReleaseServiceExecution createReleaseServiceExecution;

    public CreateReleaseServiceOperation(ServiceHelper helper,
                                         CreateReleaseServiceExecution createReleaseServiceExecution) {
        this.helper = helper;
        this.createReleaseServiceExecution = createReleaseServiceExecution;
    }

    @Override
    protected Response delegate(String json) {

        final ReleaseDtoToReleaseAdapter adapter = helper.getFactory().getImpl(ReleaseDtoToReleaseAdapter.class);

        final Release result = createReleaseServiceExecution
                .execute(adapter.adapt(helper.getJsonAdapter().getObjectFromJson(json, ReleaseDto.class)));

        Response response;

        if (result != null) {
            final ReleaseToReleaseDtoAdapter adapterForResponse = helper.getFactory().getImpl(ReleaseToReleaseDtoAdapter.class);
            final ReleaseDto adaptedResult = adapterForResponse.adapt(result);
            response = helper.getRestResponseBuilder().build(200, adaptedResult);
        } else {
            response = helper.getRestResponseBuilder().build(new JsonResponse(442, "Release already exists"));
        }
        return response;
    }
}
