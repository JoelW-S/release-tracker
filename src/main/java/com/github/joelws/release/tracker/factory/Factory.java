package com.github.joelws.release.tracker.factory;

/**
 * Created by a586240 on 27/05/2016.
 */
public interface Factory
{
    <T> T getImpl(final Class<T> klazz);
}
