package com.github.joelws.release.tracker.common;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository public class GenericDaoImpl<T, PK> implements GenericDao<T, PK>
{
    @PersistenceContext private EntityManager entityManager;

    private Class<T> tClass;

    public GenericDaoImpl(Class<T> tClass)
    {
        this.tClass = tClass;
    }

    @Transactional @Override public T create(T type)
    {
        entityManager.persist(type);
        return type;
    }

    public GenericDaoImpl()
    {
    }

    @Transactional @Override public T read(PK primaryKey)
    {
        return entityManager.find(tClass, primaryKey);
    }

    @Transactional @Override public T update(T type)
    {
        entityManager.merge(type);
        return type;
    }

    @Transactional @Override public void delete(PK primaryKey)
    {
        entityManager.remove(entityManager.getReference(tClass, primaryKey));
    }

    protected EntityManager getEntityManager()
    {
        return entityManager;
    }

    protected void setEntityManager(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }
}
