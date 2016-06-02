package com.github.joelws.release.tracker.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class GenericDaoImpl<T, PK> implements GenericDao<T, PK>
{
    @PersistenceContext private EntityManager entityManager;

    private Class<T> klass;

    public GenericDaoImpl()
    {
        final Type klass = getClass().getGenericSuperclass();
        final ParameterizedType pt = (ParameterizedType) klass;
        this.klass = (Class<T>) pt.getActualTypeArguments()[0];
    }

    @Override public T create(T entity)
    {
        entityManager.persist(entity);
        return entity;
    }

    @Override public T read(PK identifier)
    {
        return entityManager.find(klass, identifier);
    }

    @Override public T update(T entity)
    {
        return entityManager.merge(entity);
    }

    @Override public void delete(PK identifier)
    {
        entityManager.remove(entityManager.getReference(klass, identifier));
    }

    public EntityManager getEntityManager()
    {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    public Class<T> getKlass()
    {
        return klass;
    }

    public void setKlass(Class<T> klass)
    {
        this.klass = klass;
    }
}
