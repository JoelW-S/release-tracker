package com.github.joelws.release.tracker.factory;

import com.github.joelws.release.tracker.exception.ReleaseTrackerException;
import com.github.joelws.release.tracker.handler.JsonResponse;

import org.apache.log4j.Logger;

public class ReleaseTrackerFactory implements Factory
{
    private final static Logger LOGGER = Logger.getLogger(ReleaseTrackerFactory.class);

    @Override public <T> T getImpl(Class<T> klazz)
    {
        try
        {
            return klazz.newInstance();
        }
        catch (InstantiationException | IllegalAccessException e)
        {
            LOGGER.error("Failed to instantiate object: " + klazz);
            throw new ReleaseTrackerException(new JsonResponse(500, "Failed to instantiate object"));
        }
    }
}
