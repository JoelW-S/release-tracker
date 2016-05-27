package com.github.joelws.release.tracker.interfaces;

/**
 * Created by a586240 on 27/05/2016.
 */
public interface JsonAdapter
{
    String getJsonFromObject(final Object obj);

    <T> T getObjectFromJson(final String jsonString, final Class<T> klazz);

}
