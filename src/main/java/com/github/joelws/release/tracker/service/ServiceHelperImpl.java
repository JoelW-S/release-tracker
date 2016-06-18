package com.github.joelws.release.tracker.service;

import com.github.joelws.release.tracker.factory.Factory;
import com.github.joelws.release.tracker.factory.ReleaseTrackerFactory;
import com.github.joelws.release.tracker.interfaces.JsonAdapter;
import com.github.joelws.release.tracker.response.RestResponseImpl;

public class ServiceHelperImpl implements ServiceHelper {
    private final RestResponseImpl responseBuilder;

    private final JsonAdapter jsonAdapter;

    private final Factory factory;

    public ServiceHelperImpl(final RestResponseImpl responseBuilder, final JsonAdapter jsonAdapter,
                             final ReleaseTrackerFactory factory) {
        this.responseBuilder = responseBuilder;
        this.jsonAdapter = jsonAdapter;
        this.factory = factory;
    }

    @Override
    public RestResponseImpl getRestResponseBuilder() {
        return responseBuilder;
    }

    @Override
    public JsonAdapter getJsonAdapter() {
        return jsonAdapter;
    }

    @Override
    public Factory getFactory() {
        return factory;
    }

}
