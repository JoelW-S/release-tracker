package com.github.joelws.release.tracker.common;

public interface GenericDao<T, PK>
{
    T create(final T type);

    T read(final PK primaryKey);

    T update(final T type);

    void delete(final PK primaryKey);
}
