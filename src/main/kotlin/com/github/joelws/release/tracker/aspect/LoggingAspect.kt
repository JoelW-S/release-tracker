package com.github.joelws.release.tracker.aspect

import org.apache.log4j.Logger
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut

/*
Copyright 2016 Joel Whittaker-Smith

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/@Aspect class LoggingAspect {

    private val logger = Logger.getLogger(LoggingAspect::class.java)

    @Pointcut("execution(* com.github.joelws.release.tracker.service..*Execution.execute(..))")
    fun executionMethods() {
    }

    @Pointcut("execution(* com.github.joelws.release.tracker.service..*Operation.execute(..))")
    fun operationMethods() {
    }

    @Before("operationMethods() || executionMethods()")
    fun startLogging(joinPoint: JoinPoint) = logger.info("Starting ${joinPoint.target.javaClass.simpleName}.${joinPoint.signature.name}, with: ${joinPoint.args.map { it }.joinToString(" ")}")

    @AfterReturning("operationMethods() || executionMethods()", returning = "result")
    fun exitLogging(joinPoint: JoinPoint, result: Any?) = logger.info("Exiting ${joinPoint.target.javaClass.simpleName}.${joinPoint.signature.name}, with: $result")
}
