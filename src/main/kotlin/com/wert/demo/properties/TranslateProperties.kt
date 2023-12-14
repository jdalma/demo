package com.wert.demo.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties("translate")
data class TranslateProperties @ConstructorBinding constructor(
    private val v4: ServerInfo,
    private val v5: ServerInfo,
    private val tmp: String
) {
    data class ServerInfo(
        private val ip: String,
        private val port: String
    )
}
