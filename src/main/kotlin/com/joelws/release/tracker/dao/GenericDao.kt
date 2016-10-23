package com.joelws.release.tracker.dao

import org.funktionale.option.Option

interface GenericDao<T : Any, PK> {

    fun create(entity: T): Option<T>

    fun read(identifier: PK): Option<T>

    fun update(entity: T): Option<T>

    fun delete(identifier: PK)

}
