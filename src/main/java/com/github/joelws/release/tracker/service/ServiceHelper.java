package com.github.joelws.release.tracker.service;

import com.github.joelws.release.tracker.factory.Factory;
import com.github.joelws.release.tracker.interfaces.JsonAdapter;
import com.github.joelws.release.tracker.response.JsonResponse;

public interface ServiceHelper
{
    JsonResponse getRestResponseBuilder();

    JsonAdapter getJsonAdapter();

    Factory getFactory();
}
