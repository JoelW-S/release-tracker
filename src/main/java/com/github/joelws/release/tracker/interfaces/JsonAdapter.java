package com.github.joelws.release.tracker.interfaces;

public interface JsonAdapter
{
    String getJsonFromObject(final Object obj);

    <T> T getObjectFromJson(final String jsonString, final Class<T> klazz);

}
