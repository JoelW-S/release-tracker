package com.github.joelws.release.tracker.service.artifact.operation;

import com.github.joelws.release.tracker.conversion.ArtifactToArtifactDtoAdapter;
import com.github.joelws.release.tracker.dto.artifact.ArtifactDto;
import com.github.joelws.release.tracker.entity.artifact.Artifact;
import com.github.joelws.release.tracker.handler.JsonResponse;
import com.github.joelws.release.tracker.service.ServiceHelper;
import com.github.joelws.release.tracker.service.ServiceOperation;
import com.github.joelws.release.tracker.service.artifact.execution.ListArtifactServiceExecution;

import org.apache.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.core.Response;

public class ListArtifactServiceOperation extends ServiceOperation<Void> {
    private static final Logger LOGGER = Logger.getLogger(ListArtifactServiceOperation.class);

    private final ServiceHelper helper;
    private final ListArtifactServiceExecution listArtifactServiceExecution;

    public ListArtifactServiceOperation(final ServiceHelper helper,
                                        final ListArtifactServiceExecution listArtifactServiceExecution) {
        this.helper = helper;
        this.listArtifactServiceExecution = listArtifactServiceExecution;
    }

    @Override
    protected Response delegate(final Void param) {
        LOGGER.info("Starting List service operation:");
        final List<Artifact> result = listArtifactServiceExecution.execute(null);

        Response response;

        if (!result.isEmpty()) {
            LOGGER.info("Starting adaption process: ");
            final ArtifactToArtifactDtoAdapter adapter = helper.getFactory().getImpl(ArtifactToArtifactDtoAdapter.class);
            final List<ArtifactDto> adaptedResultList = result.stream().map(adapter::adapt).collect(Collectors.toList());
            response = helper.getRestResponseBuilder().build(200, adaptedResultList);
        } else {
            response = helper.getRestResponseBuilder().build(new JsonResponse(404, "No artifacts exist"));
        }
        LOGGER.info("Exiting with response: " + response.getEntity());
        return response;
    }
}
