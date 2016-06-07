package com.github.joelws.release.tracker.service.artifact.operation;


import com.github.joelws.release.tracker.conversion.ArtifactDtoToArtifactAdapter;
import com.github.joelws.release.tracker.dto.artifact.ArtifactDto;
import com.github.joelws.release.tracker.entity.artifact.Artifact;
import com.github.joelws.release.tracker.service.ServiceHelper;
import com.github.joelws.release.tracker.service.ServiceOperation;
import com.github.joelws.release.tracker.service.artifact.execution.DeleteArtifactServiceExecution;

import javax.ws.rs.core.Response;

public class DeleteArtifactServiceOperation extends ServiceOperation<String> {

    private final ServiceHelper helper;

    private final DeleteArtifactServiceExecution deleteArtifactServiceExecution;

    public DeleteArtifactServiceOperation(final ServiceHelper helper,
                                          final DeleteArtifactServiceExecution deleteArtifactServiceExecution) {
        this.helper = helper;
        this.deleteArtifactServiceExecution = deleteArtifactServiceExecution;
    }

    @Override
    protected Response delegate(String json) {

        ArtifactDtoToArtifactAdapter artifactDtoToArtifactAdapter = helper.getFactory().getImpl(ArtifactDtoToArtifactAdapter.class);

        Artifact artifactInQuestion = artifactDtoToArtifactAdapter.adapt(helper.getJsonAdapter().getObjectFromJson(json, ArtifactDto.class));

        deleteArtifactServiceExecution.execute(artifactInQuestion);

        return helper.getRestResponseBuilder().build(200);
    }
}
