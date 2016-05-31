package com.github.joelws.release.tracker.service.artifact.operation;

import com.github.joelws.release.tracker.conversion.ArtifactAdapter;
import com.github.joelws.release.tracker.conversion.ArtifactDTOAdapter;
import com.github.joelws.release.tracker.dto.artifact.ArtifactDTO;
import com.github.joelws.release.tracker.entity.artifact.Artifact;
import com.github.joelws.release.tracker.handler.JsonResponse;
import com.github.joelws.release.tracker.service.ServiceHelper;
import com.github.joelws.release.tracker.service.ServiceOperation;
import com.github.joelws.release.tracker.service.artifact.execution.ReadArtifactServiceExecution;
import org.apache.log4j.Logger;

import javax.ws.rs.core.Response;

public class ReadArtifactServiceOperation extends ServiceOperation<String>
{
    private static final Logger LOGGER = Logger.getLogger(ReadArtifactServiceOperation.class);

    private final ServiceHelper helper;

    private final ReadArtifactServiceExecution readArtifactServiceExecution;

    public ReadArtifactServiceOperation(final ServiceHelper helper,
            final ReadArtifactServiceExecution readArtifactServiceExecution)
    {
        this.helper = helper;
        this.readArtifactServiceExecution = readArtifactServiceExecution;
    }

    @Override protected Response delegate(String param)
    {
        ArtifactAdapter toArtifactAdapter = helper.getFactory().getImpl(ArtifactAdapter.class);

        ArtifactDTO artifactDTO = helper.getJsonAdapter().getObjectFromJson(param, ArtifactDTO.class);

        Artifact result = readArtifactServiceExecution.execute(toArtifactAdapter.adapt(artifactDTO));

        Response response = null;

        if (result != null)
        {
            ArtifactDTOAdapter toArtifactDTOAdapter = helper.getFactory().getImpl(ArtifactDTOAdapter.class);
            ArtifactDTO adaptedResult = toArtifactDTOAdapter.adapt(result);
            response = helper.getRestResponseBuilder().build(200, adaptedResult);
        }
        else
        {
            response = helper.getRestResponseBuilder().build(new JsonResponse(422, "Artifact doesn't exist."));
        }
        return response;
    }
}
