package com.github.joelws.release.tracker.service.artifact;

import com.github.joelws.release.tracker.interfaces.BusinessService;
import com.github.joelws.release.tracker.service.artifact.operation.ReadArtifactServiceOperation;

import javax.ws.rs.core.Response;

public class ArtifactService implements BusinessService<String>
{

    private final ReadArtifactServiceOperation readArtifactServiceOperation;

    public ArtifactService(final ReadArtifactServiceOperation readArtifactServiceOperation)
    {
        this.readArtifactServiceOperation = readArtifactServiceOperation;
    }

    @Override public Response create(String json)
    {
        return null;
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
