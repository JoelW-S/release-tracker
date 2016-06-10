package com.github.joelws.release.tracker.boot

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean
import org.springframework.context.support.ClassPathXmlApplicationContext


open class ApplicationKt

fun main(args: Array<String>) {
    val ctx = ClassPathXmlApplicationContext("META-INF/beans.xml")
    val sf = ctx.getBean("releaseTracker") as JAXRSServerFactoryBean
    sf.init()
}

