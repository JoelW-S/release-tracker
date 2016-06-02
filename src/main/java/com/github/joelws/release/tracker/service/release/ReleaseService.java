package com.github.joelws.release.tracker.service.release;

import com.github.joelws.release.tracker.interfaces.BusinessService;
import com.github.joelws.release.tracker.service.release.operation.CreateReleaseServiceOperation;
import com.github.joelws.release.tracker.service.release.operation.DeleteReleaseServiceOperation;
import com.github.joelws.release.tracker.service.release.operation.ReadReleaseServiceOperation;
import com.github.joelws.release.tracker.service.release.operation.UpdateReleaseServiceOperation;

import javax.ws.rs.core.Response;

public class ReleaseService implements BusinessService<String> {
    private final CreateReleaseServiceOperation createReleaseServiceOperation;
    private final UpdateReleaseServiceOperation updateReleaseServiceOperation;
    private final ReadReleaseServiceOperation readReleaseServiceOperation;
    private final DeleteReleaseServiceOperation deleteReleaseServiceOperation;

    public ReleaseService(final CreateReleaseServiceOperation createReleaseServiceOperation,
                          final UpdateReleaseServiceOperation updateReleaseServiceOperation,
                          final ReadReleaseServiceOperation readReleaseServiceOperation,
                          final DeleteReleaseServiceOperation deleteReleaseServiceOperation) {
        this.createReleaseServiceOperation = createReleaseServiceOperation;
        this.updateReleaseServiceOperation = updateReleaseServiceOperation;
        this.readReleaseServiceOperation = readReleaseServiceOperation;
        this.deleteReleaseServiceOperation = deleteReleaseServiceOperation;
    }

    @Override
    public Response create(String json) {
        return createReleaseServiceOperation.execute(json);
    }

    @Override
    public Response read(String identifier) {
        return readReleaseServiceOperation.execute(identifier);
    }

    @Override
    public Response update(String json) {
        return updateReleaseServiceOperation.execute(json);
    }

    @Override
    public Response delete(String identifier) {
        return deleteReleaseServiceOperation.execute(identifier);
    }
}
