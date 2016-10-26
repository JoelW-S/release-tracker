package com.joelws.release.tracker.dao

import org.funktionale.option.Option
import org.funktionale.option.toOption
import java.lang.reflect.ParameterizedType
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext


abstract class GenericDaoImpl<T : Any, PK> : GenericDao<T, PK> {

    @PersistenceContext
    lateinit var entityManager: EntityManager

    private val klass: Class<T>

    init {
        val pt = javaClass.genericSuperclass as ParameterizedType
        @Suppress("UNCHECKED_CAST")
        val klass = pt.actualTypeArguments[0] as Class<T>
        this.klass = klass
    }

    override fun create(entity: T): Option<T> {
        entityManager.persist(entity)
        return entity.toOption()
    }

    override fun read(identifier: PK): Option<T> {
        return entityManager.find(klass, identifier).toOption()
    }

    override fun update(entity: T): Option<T> {
        return entityManager.merge(entity).toOption()
    }

    override fun delete(identifier: PK) {
        entityManager.remove(entityManager.getReference(klass, identifier))
    }

}
