package com.github.joelws.release.tracker.interfaces

interface JsonAdapter {

    fun getJsonFromObject(any: Any): String?

    fun <T> getObjectFromJson(jsonString: String?, klazz: Class<T>): T

}
