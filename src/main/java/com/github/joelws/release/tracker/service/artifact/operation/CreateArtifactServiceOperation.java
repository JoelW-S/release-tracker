package com.github.joelws.release.tracker.service.artifact.operation;

import com.github.joelws.release.tracker.conversion.ArtifactAdapter;
import com.github.joelws.release.tracker.conversion.ArtifactDtoAdapter;
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
    protected Response delegate(String json) {
        ArtifactAdapter toArtifactAdapter = helper.getFactory().getImpl(ArtifactAdapter.class);

        Artifact result = createArtifactServiceExecution
                .execute(toArtifactAdapter.adapt(helper.getJsonAdapter().getObjectFromJson(json, ArtifactDto.class)));


        Response response = null;

        if (result != null) {
            ArtifactDtoAdapter toArtifactDtoAdapter = helper.getFactory().getImpl(ArtifactDtoAdapter.class);
            ArtifactDto adaptedResult = toArtifactDtoAdapter.adapt(result);
            response = helper.getRestResponseBuilder().build(200, adaptedResult);
        } else {
            response = helper.getRestResponseBuilder().build(new JsonResponse(422, "Artifact already exists"));
        }
        return response;
    }
}
