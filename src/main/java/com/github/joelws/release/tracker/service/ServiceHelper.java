package com.github.joelws.release.tracker.service;

import com.github.joelws.release.tracker.factory.Factory;
import com.github.joelws.release.tracker.interfaces.JsonAdapter;
import com.github.joelws.release.tracker.response.RestResponseImpl;

public interface ServiceHelper
{
    RestResponseImpl getRestResponseBuilder();

    JsonAdapter getJsonAdapter();

    Factory getFactory();
}
