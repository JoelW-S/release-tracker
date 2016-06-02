package com.github.joelws.release.tracker.service.artifact;

import com.github.joelws.release.tracker.interfaces.BusinessService;
import com.github.joelws.release.tracker.service.artifact.operation.CreateArtifactServiceOperation;
import com.github.joelws.release.tracker.service.artifact.operation.ReadArtifactServiceOperation;

import javax.ws.rs.core.Response;

public class ArtifactService implements BusinessService<String>
{
    private final CreateArtifactServiceOperation createArtifactServiceOperation;
    private final ReadArtifactServiceOperation readArtifactServiceOperation;

    public ArtifactService(final CreateArtifactServiceOperation createArtifactServiceOperation,
                           final ReadArtifactServiceOperation readArtifactServiceOperation)
    {
        this.createArtifactServiceOperation = createArtifactServiceOperation;
        this.readArtifactServiceOperation = readArtifactServiceOperation;
    }

    @Override public Response create(String json)
    {
        return createArtifactServiceOperation.execute(json);
    }

    @Override public Response read(String identifier)
    {
        return readArtifactServiceOperation.execute(identifier);
    }

    @Override public Response update(String json)
    {
        return null;
    }

    @Override public Response delete(String identifier)
    {
        return null;
    }
}
