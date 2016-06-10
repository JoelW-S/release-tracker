package com.github.joelws.release.tracker.interfaces;


import javax.ws.rs.core.Response;

public abstract class ResourceEndpoint<I> {

    protected final BusinessService<I> service;

    public ResourceEndpoint(final BusinessService<I> service) {
        this.service = service;
    }

    public abstract Response method(final I param);
}
