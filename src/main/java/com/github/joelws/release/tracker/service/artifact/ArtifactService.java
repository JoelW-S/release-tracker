package com.github.joelws.release.tracker.service.artifact;

import com.github.joelws.release.tracker.interfaces.BusinessService;
import com.github.joelws.release.tracker.service.artifact.operation.CreateArtifactServiceOperation;
import com.github.joelws.release.tracker.service.artifact.operation.DeleteArtifactServiceOperation;
import com.github.joelws.release.tracker.service.artifact.operation.ListArtifactServiceOperation;
import com.github.joelws.release.tracker.service.artifact.operation.ReadArtifactServiceOperation;
import com.github.joelws.release.tracker.service.artifact.operation.UpdateArtifactServiceOperation;

import javax.ws.rs.core.Response;

public class ArtifactService implements BusinessService<String> {
    private final CreateArtifactServiceOperation createArtifactServiceOperation;
    private final ReadArtifactServiceOperation readArtifactServiceOperation;
    private final DeleteArtifactServiceOperation deleteArtifactServiceOperation;
    private final UpdateArtifactServiceOperation updateArtifactServiceOperation;
    private final ListArtifactServiceOperation listArtifactServiceOperation;

    public ArtifactService(final CreateArtifactServiceOperation createArtifactServiceOperation,
                           final ReadArtifactServiceOperation readArtifactServiceOperation,
                           final DeleteArtifactServiceOperation deleteArtifactServiceOperation,
                           final UpdateArtifactServiceOperation updateArtifactServiceOperation,
                           final ListArtifactServiceOperation listArtifactServiceOperation) {
        this.createArtifactServiceOperation = createArtifactServiceOperation;
        this.readArtifactServiceOperation = readArtifactServiceOperation;
        this.deleteArtifactServiceOperation = deleteArtifactServiceOperation;
        this.updateArtifactServiceOperation = updateArtifactServiceOperation;
        this.listArtifactServiceOperation = listArtifactServiceOperation;
    }

    @Override
    public Response create(final String json) {
        return createArtifactServiceOperation.execute(json);
    }

    @Override
    public Response read(final String identifier) {
        return readArtifactServiceOperation.execute(identifier);
    }

    @Override
    public Response update(final String json) {
        return updateArtifactServiceOperation.execute(json);
    }

    @Override
    public Response delete(final String identifier) {
        return deleteArtifactServiceOperation.execute(identifier);
    }

    @Override
    public Response list() {
        return listArtifactServiceOperation.execute(null);
    }
}
