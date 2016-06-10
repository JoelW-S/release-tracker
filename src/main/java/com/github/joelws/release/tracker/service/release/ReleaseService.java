package com.github.joelws.release.tracker.service.release;


import com.github.joelws.release.tracker.interfaces.BusinessService;
import com.github.joelws.release.tracker.service.release.operation.CreateReleaseServiceOperation;
import com.github.joelws.release.tracker.service.release.operation.DeleteReleaseServiceOperation;
import com.github.joelws.release.tracker.service.release.operation.ListReleaseServiceOperation;
import com.github.joelws.release.tracker.service.release.operation.ReadReleaseServiceOperation;
import com.github.joelws.release.tracker.service.release.operation.UpdateReleaseServiceOperation;

import javax.ws.rs.core.Response;

public class ReleaseService implements BusinessService<String> {
    private final CreateReleaseServiceOperation createReleaseServiceOperation;
    private final UpdateReleaseServiceOperation updateReleaseServiceOperation;
    private final ReadReleaseServiceOperation readReleaseServiceOperation;
    private final DeleteReleaseServiceOperation deleteReleaseServiceOperation;
    private final ListReleaseServiceOperation listReleaseServiceOperation;

    public ReleaseService(final CreateReleaseServiceOperation createReleaseServiceOperation,
                          final UpdateReleaseServiceOperation updateReleaseServiceOperation,
                          final ReadReleaseServiceOperation readReleaseServiceOperation,
                          final DeleteReleaseServiceOperation deleteReleaseServiceOperation,
                          final ListReleaseServiceOperation listReleaseServiceOperation) {
        this.createReleaseServiceOperation = createReleaseServiceOperation;
        this.updateReleaseServiceOperation = updateReleaseServiceOperation;
        this.readReleaseServiceOperation = readReleaseServiceOperation;
        this.deleteReleaseServiceOperation = deleteReleaseServiceOperation;
        this.listReleaseServiceOperation = listReleaseServiceOperation;
    }

    @Override
    public Response create(final String json) {
        return createReleaseServiceOperation.execute(json);
    }

    @Override
    public Response read(final String identifier) {
        return readReleaseServiceOperation.execute(identifier);
    }

    @Override
    public Response update(final String json) {
        return updateReleaseServiceOperation.execute(json);
    }

    @Override
    public Response delete(final String identifier) {
        return deleteReleaseServiceOperation.execute(identifier);
    }

    @Override
    public Response list() {
        return listReleaseServiceOperation.execute(null);
    }
}
