package com.github.joelws.release.tracker.factory

interface Factory {
    fun <T> getImpl(klazz: Class<T>): T
}
