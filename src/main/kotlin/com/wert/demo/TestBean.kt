package com.wert.demo

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.slf4j.Logger
import org.slf4j.LoggerFactory

data class TestBean(
    val message: String
) {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass.name)

    init {
        log.info("[TestBean] 생성자 호출 , url = $message")
    }

    @Throws(Exception::class)
    fun init() {
        log.info("[TestBean] init : $message")
    }

    @Throws(Exception::class)
    fun close() {
        log.info("[TestBean] close : $message")
    }

    @PostConstruct
    @Throws(Exception::class)
    fun customInitMethod() {
        log.info("[TestBean] customInitMethod : $message")
    }

    @PreDestroy
    @Throws(Exception::class)
    fun customCloseMethod() {
        log.info("[TestBean] customCloseMethod : $message")
    }
}

