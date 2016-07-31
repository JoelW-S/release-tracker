package com.github.joelws.release.tracker.interfaces

import org.springframework.transaction.annotation.Transactional

import javax.ws.rs.core.Response

interface BusinessService<in I> {

    @Transactional
    fun create(json: String): Response

    @Transactional
    fun read(identifier: I): Response

    @Transactional
    fun update(json: String): Response

    @Transactional
    fun delete(identifier: I): Response

    @Transactional
    fun list(): Response
}
