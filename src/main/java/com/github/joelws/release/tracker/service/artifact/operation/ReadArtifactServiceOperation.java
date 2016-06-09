package com.github.joelws.release.tracker.service.artifact.operation;

import com.github.joelws.release.tracker.conversion.ArtifactDtoToArtifactAdapter;
import com.github.joelws.release.tracker.conversion.ArtifactToArtifactDtoAdapter;
import com.github.joelws.release.tracker.dto.artifact.ArtifactDto;
import com.github.joelws.release.tracker.entity.artifact.Artifact;
import com.github.joelws.release.tracker.handler.JsonResponse;
import com.github.joelws.release.tracker.service.ServiceHelper;
import com.github.joelws.release.tracker.service.ServiceOperation;
import com.github.joelws.release.tracker.service.artifact.execution.ReadArtifactServiceExecution;

import org.apache.log4j.Logger;

import javax.ws.rs.core.Response;

public class ReadArtifactServiceOperation extends ServiceOperation<String> {
    private static final Logger LOGGER = Logger.getLogger(ReadArtifactServiceOperation.class);

    private final ServiceHelper helper;

    private final ReadArtifactServiceExecution readArtifactServiceExecution;

    public ReadArtifactServiceOperation(final ServiceHelper helper,
                                        final ReadArtifactServiceExecution readArtifactServiceExecution) {
        this.helper = helper;
        this.readArtifactServiceExecution = readArtifactServiceExecution;
    }

    @Override
    protected Response delegate(final String param) {
        final ArtifactDtoToArtifactAdapter toArtifactDtoToArtifactAdapter = helper.getFactory().getImpl(ArtifactDtoToArtifactAdapter.class);

        final ArtifactDto artifactDto = helper.getJsonAdapter().getObjectFromJson(param, ArtifactDto.class);

        final Artifact result = readArtifactServiceExecution.execute(toArtifactDtoToArtifactAdapter.adapt(artifactDto));

        Response response;

        if (result != null) {
            final ArtifactToArtifactDtoAdapter toArtifactToArtifactDtoAdapter = helper.getFactory().getImpl(ArtifactToArtifactDtoAdapter.class);
            final ArtifactDto adaptedResult = toArtifactToArtifactDtoAdapter.adapt(result);
            response = helper.getRestResponseBuilder().build(200, adaptedResult);
        } else {
            response = helper.getRestResponseBuilder().build(new JsonResponse(404, "Artifact doesn't exist"));
        }
        return response;
    }
}
