package com.github.joelws.release.tracker.service;

import com.github.joelws.release.tracker.factory.Factory;
import com.github.joelws.release.tracker.factory.ReleaseTrackerFactory;
import com.github.joelws.release.tracker.interfaces.JsonAdapter;
import com.github.joelws.release.tracker.response.JsonResponse;

public class ServiceHelperImpl implements ServiceHelper
{
    private final JsonResponse responseBuilder;

    private final JsonAdapter jsonAdapter;

    private final Factory factory;

    public ServiceHelperImpl(JsonResponse responseBuilder, JsonAdapter jsonAdapter,
                             ReleaseTrackerFactory factory)
    {
        this.responseBuilder = responseBuilder;
        this.jsonAdapter = jsonAdapter;
        this.factory = factory;
    }

    @Override
    public JsonResponse getRestResponseBuilder()
    {
        return responseBuilder;
    }

    @Override public JsonAdapter getJsonAdapter()
    {
        return jsonAdapter;
    }

    @Override public Factory getFactory()
    {
        return factory;
    }

}
