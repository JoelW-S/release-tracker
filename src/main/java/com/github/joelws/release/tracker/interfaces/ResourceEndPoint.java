package com.github.joelws.release.tracker.interfaces;

import javax.ws.rs.core.Response;

abstract public class ResourceEndPoint<I>
{
    protected final BusinessService<I> service;

    public ResourceEndPoint(BusinessService<I> service)
    {
        this.service = service;
    }

    public abstract Response create(String json);

    public abstract Response read(I identifier);

    public abstract Response update(String json);

    public abstract Response delete(I identifier);

}
