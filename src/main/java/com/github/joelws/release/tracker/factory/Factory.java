package com.github.joelws.release.tracker.factory;

public interface Factory
{
    <T> T getImpl(final Class<T> klazz);
}
