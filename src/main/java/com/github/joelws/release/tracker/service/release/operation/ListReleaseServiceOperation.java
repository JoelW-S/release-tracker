package com.github.joelws.release.tracker.service.release.operation;

import com.github.joelws.release.tracker.conversion.ReleaseToReleaseDtoAdapter;
import com.github.joelws.release.tracker.dto.release.ReleaseDto;
import com.github.joelws.release.tracker.entity.release.Release;
import com.github.joelws.release.tracker.handler.JsonResponse;
import com.github.joelws.release.tracker.service.ServiceHelper;
import com.github.joelws.release.tracker.service.ServiceOperation;
import com.github.joelws.release.tracker.service.release.execution.ListReleaseServiceExecution;

import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.core.Response;


public class ListReleaseServiceOperation extends ServiceOperation<Void> {

    private final ServiceHelper helper;

    private final ListReleaseServiceExecution listReleaseServiceExecution;

    public ListReleaseServiceOperation(final ServiceHelper helper,
                                       final ListReleaseServiceExecution listReleaseServiceExecution) {
        this.helper = helper;
        this.listReleaseServiceExecution = listReleaseServiceExecution;
    }

    @Override
    protected Response delegate(final Void param) {
        final List<Release> result = listReleaseServiceExecution.execute(null);

        Response response;

        if (!result.isEmpty()) {
            final ReleaseToReleaseDtoAdapter adapter = helper.getFactory().getImpl(ReleaseToReleaseDtoAdapter.class);
            final List<ReleaseDto> adaptedResultList = result.stream().map(adapter::adapt).collect(Collectors.toList());
            response = helper.getRestResponseBuilder().build(200, adaptedResultList);
        } else {
            response = helper.getRestResponseBuilder().build(new JsonResponse(404, "No releases exist"));
        }
        return response;
    }
}
