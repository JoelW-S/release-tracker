package com.github.joelws.release.tracker.service.release.operation;

import com.github.joelws.release.tracker.service.ServiceHelper;
import com.github.joelws.release.tracker.service.ServiceOperation;
import com.github.joelws.release.tracker.service.release.execution.DeleteReleaseServiceExecution;

import javax.ws.rs.core.Response;

public class DeleteReleaseServiceOperation extends ServiceOperation<String> {
    private final ServiceHelper helper;
    private final DeleteReleaseServiceExecution deleteReleaseServiceExecution;

    public DeleteReleaseServiceOperation(final ServiceHelper helper,
                                         final DeleteReleaseServiceExecution deleteReleaseServiceExecution) {
        this.helper = helper;
        this.deleteReleaseServiceExecution = deleteReleaseServiceExecution;
    }

    @Override
    protected Response delegate(String identifier) {

        deleteReleaseServiceExecution
                .execute(identifier);

        return helper.getRestResponseBuilder().build(200);
    }
}
