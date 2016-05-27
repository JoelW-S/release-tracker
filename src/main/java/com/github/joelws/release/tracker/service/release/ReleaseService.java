package com.github.joelws.release.tracker.service.release;

import com.github.joelws.release.tracker.interfaces.BusinessService;

import javax.ws.rs.core.Response;

public class ReleaseService implements BusinessService<String>
{
    private final CreateReleaseServiceOperation createReleaseServiceOperation;

    public ReleaseService(final CreateReleaseServiceOperation createReleaseServiceOperation)
    {
        this.createReleaseServiceOperation = createReleaseServiceOperation;
    }

    @Override public Response create(String json)
    {
        return createReleaseServiceOperation.delegate(json);
    }

    @Override public Response read(String identifier)
    {
        return null;
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
