package com.wert.demo.config

import com.wert.demo.AppConfig
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Import
import org.springframework.jdbc.core.JdbcTemplate


@SpringBootTest
@Import(AppConfig::class)
class ConfigurationTest {

    @Autowired
    private lateinit var applicationContext: ApplicationContext

    @Autowired
    private lateinit var appConfig: AppConfig

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    @Test
    fun singletonTest() {
        println(appConfig)
        val userRepository1 = appConfig.userRepository(jdbcTemplate)
        val userRepository2 = appConfig.userRepository(jdbcTemplate)

        Assertions.assertThat(userRepository1).isEqualTo(userRepository2)
    }

    @Test
    fun allBeans() {
        val bean = applicationContext.getBeansOfType(Object::class.java)
        bean.forEach { k, v ->
            println("[key] : $k , [value] : $v")
        }
    }
}
