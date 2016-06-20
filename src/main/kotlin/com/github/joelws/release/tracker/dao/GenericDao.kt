package com.github.joelws.release.tracker.dao

interface GenericDao<T, PK> {

    fun create(entity: T?): T?

    fun read(identifier: PK?): T?

    fun update(entity: T?): T?

    fun delete(identifier: PK?)

}
