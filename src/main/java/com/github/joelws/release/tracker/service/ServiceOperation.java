package com.github.joelws.release.tracker.service;

import javax.ws.rs.core.Response;

public abstract class ServiceOperation<I>
{
    public Response execute(final I param)
    {
        return delegate(param);
    }

    protected abstract Response delegate(final I param);
}
