package com.github.joelws.release.tracker.service.artifact.operation;

import com.github.joelws.release.tracker.conversion.ArtifactDtoToArtifactAdapter;
import com.github.joelws.release.tracker.conversion.ArtifactToArtifactDtoAdapter;
import com.github.joelws.release.tracker.dto.artifact.ArtifactDto;
import com.github.joelws.release.tracker.entity.artifact.Artifact;
import com.github.joelws.release.tracker.handler.JsonResponse;
import com.github.joelws.release.tracker.service.ServiceHelper;
import com.github.joelws.release.tracker.service.ServiceOperation;
import com.github.joelws.release.tracker.service.artifact.execution.CreateArtifactServiceExecution;

import javax.ws.rs.core.Response;


public class CreateArtifactServiceOperation extends ServiceOperation<String> {
    private final ServiceHelper helper;

    private final CreateArtifactServiceExecution createArtifactServiceExecution;

    public CreateArtifactServiceOperation(final ServiceHelper helper,
                                          final CreateArtifactServiceExecution createArtifactServiceExecution) {
        this.helper = helper;
        this.createArtifactServiceExecution = createArtifactServiceExecution;
    }

    @Override
    protected Response delegate(final String json) {
        final ArtifactDtoToArtifactAdapter toArtifactDtoToArtifactAdapter = helper.getFactory().getImpl(ArtifactDtoToArtifactAdapter.class);

        final Artifact result = createArtifactServiceExecution
                .execute(toArtifactDtoToArtifactAdapter.adapt(helper.getJsonAdapter().getObjectFromJson(json, ArtifactDto.class)));


        Response response;

        if (result != null) {
            final ArtifactToArtifactDtoAdapter toArtifactToArtifactDtoAdapter = helper.getFactory().getImpl(ArtifactToArtifactDtoAdapter.class);
            final ArtifactDto adaptedResult = toArtifactToArtifactDtoAdapter.adapt(result);
            response = helper.getRestResponseBuilder().build(200, adaptedResult);
        } else {
            response = helper.getRestResponseBuilder().build(new JsonResponse(422, "Artifact already exists"));
        }
        return response;
    }
}
