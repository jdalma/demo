package com.wert.demo

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import org.springframework.stereotype.Component
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import javax.sql.DataSource

@Configuration
class Config {

    @Bean(destroyMethod = "shutdown")
    fun dataSource(): DataSource =
        EmbeddedDatabaseBuilder()
            .setName("testDb")
            .setType(EmbeddedDatabaseType.H2)
            .setScriptEncoding("UTF-8")
            .addScript("classpath:/init.sql")
            .build()

    @Bean(initMethod = "init", destroyMethod = "close")
//    @Bean
    fun networkClient(): TestBean {
        return TestBean("빈 수동 등록 테스트")
    }
}

@Component
class WebConfig: WebMvcConfigurer {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass.name)

    override fun addInterceptors(registry: InterceptorRegistry) {
        log.info("[WebMvcConfigurer] addInterceptors")
        registry.addInterceptor(TestInterceptor())
    }
}
