package com.joelws.release.tracker.interfaces

import org.springframework.transaction.annotation.Transactional
import ratpack.handling.Context

interface BusinessService<in I> {

    @Transactional
    fun create(json: String): (Context) -> Unit

    @Transactional
    fun read(identifier: I): (Context) -> Unit

    @Transactional
    fun update(json: String): (Context) -> Unit

    @Transactional
    fun delete(identifier: I): (Context) -> Unit

    @Transactional
    fun list(): (Context) -> Unit
}
