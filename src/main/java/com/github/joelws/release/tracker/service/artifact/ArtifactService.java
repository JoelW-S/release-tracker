package com.github.joelws.release.tracker.service.artifact;

import com.github.joelws.release.tracker.interfaces.BusinessService;
import com.github.joelws.release.tracker.service.artifact.operation.CreateArtifactServiceOperation;
import com.github.joelws.release.tracker.service.artifact.operation.DeleteArtifactServiceOperation;
import com.github.joelws.release.tracker.service.artifact.operation.ReadArtifactServiceOperation;
import com.github.joelws.release.tracker.service.artifact.operation.UpdateArtifactServiceOperation;

import javax.ws.rs.core.Response;

public class ArtifactService implements BusinessService<String>
{
    private final CreateArtifactServiceOperation createArtifactServiceOperation;
    private final ReadArtifactServiceOperation readArtifactServiceOperation;
    private final DeleteArtifactServiceOperation deleteArtifactServiceOperation;
    private final UpdateArtifactServiceOperation updateArtifactServiceOperation;

    public ArtifactService(final CreateArtifactServiceOperation createArtifactServiceOperation,
                           final ReadArtifactServiceOperation readArtifactServiceOperation,
                           final DeleteArtifactServiceOperation deleteArtifactServiceOperation,
                           final UpdateArtifactServiceOperation updateArtifactServiceOperation)
    {
        this.createArtifactServiceOperation = createArtifactServiceOperation;
        this.readArtifactServiceOperation = readArtifactServiceOperation;
        this.deleteArtifactServiceOperation = deleteArtifactServiceOperation;
        this.updateArtifactServiceOperation = updateArtifactServiceOperation;
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
        return updateArtifactServiceOperation.execute(json);
    }

    @Override public Response delete(String identifier)
    {
        return deleteArtifactServiceOperation.execute(identifier);
    }
}
