package com.github.joelws.release.tracker.dao;

public interface GenericDao<T, PK> {

    T create(final T entity);

    T read(final PK identifier);

    T update(final T entity);

    void delete(final PK identifier);

}
