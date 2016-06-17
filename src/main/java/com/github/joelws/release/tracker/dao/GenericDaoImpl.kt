package com.github.joelws.release.tracker.dao

import java.lang.reflect.ParameterizedType
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext


open class GenericDaoImpl<T, PK> : GenericDao<T, PK> {

    @PersistenceContext
    var entityManager: EntityManager? = null

    val klass: Class<T>

    init {
        val klass = javaClass.genericSuperclass
        val pt = klass as ParameterizedType
        this.klass = pt.actualTypeArguments[0] as Class<T>
    }

    override fun create(entity: T?): T? {
        entityManager?.persist(entity)
        return entity
    }

    override fun read(identifier: PK?) = entityManager?.find(klass, identifier)

    override fun update(entity: T?) = entityManager?.merge(entity)

    override fun delete(identifier: PK?) {
        entityManager?.remove(entityManager?.getReference(klass, identifier))
    }

}
