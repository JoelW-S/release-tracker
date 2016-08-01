package com.joelws.release.tracker.dao

import java.lang.reflect.ParameterizedType
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext


abstract class GenericDaoImpl<T, PK> : GenericDao<T, PK> {

    @PersistenceContext
    lateinit var entityManager: EntityManager

    private val klass: Class<T>

    init {
        val pt = javaClass.genericSuperclass as ParameterizedType
        @Suppress("UNCHECKED_CAST")
        val klass = pt.actualTypeArguments[0] as Class<T>
        this.klass = klass
    }

    override fun create(entity: T?): T? {
        entityManager.persist(entity)
        return entity
    }

    override fun read(identifier: PK?) = entityManager.find(klass, identifier)

    override fun update(entity: T?) = entityManager.merge(entity)

    override fun delete(identifier: PK?) {
        entityManager.remove(entityManager.getReference(klass, identifier))
    }

}
