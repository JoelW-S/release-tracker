package com.github.joelws.release.tracker.service.artifact;

import com.github.joelws.release.tracker.interfaces.BusinessService;
import com.github.joelws.release.tracker.service.artifact.operation.CreateArtifactServiceOperation;
import com.github.joelws.release.tracker.service.artifact.operation.DeleteArtifactServiceOperation;
import com.github.joelws.release.tracker.service.artifact.operation.ReadArtifactServiceOperation;

import javax.ws.rs.core.Response;

public class ArtifactService implements BusinessService<String>
{
    private final CreateArtifactServiceOperation createArtifactServiceOperation;
    private final ReadArtifactServiceOperation readArtifactServiceOperation;
    private final DeleteArtifactServiceOperation deleteArtifactServiceOperation;

    public ArtifactService(final CreateArtifactServiceOperation createArtifactServiceOperation,
                           final ReadArtifactServiceOperation readArtifactServiceOperation,
                           final DeleteArtifactServiceOperation deleteArtifactServiceOperation)
    {
        this.createArtifactServiceOperation = createArtifactServiceOperation;
        this.readArtifactServiceOperation = readArtifactServiceOperation;
        this.deleteArtifactServiceOperation = deleteArtifactServiceOperation;
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
        return deleteArtifactServiceOperation.execute(identifier);
    }
}
