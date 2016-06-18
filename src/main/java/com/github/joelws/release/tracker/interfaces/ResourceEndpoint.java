package com.github.joelws.release.tracker.interfaces;


public abstract class ResourceEndpoint<I> {

    protected final BusinessService<I> service;

    public ResourceEndpoint(final BusinessService<I> service) {
        this.service = service;
    }

}
