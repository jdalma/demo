package com.wert.demo.profile

import com.wert.demo.User
import com.wert.demo.properties.TranslateProperties
import com.wert.demo.properties.TranslateProperties.*
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.Profile
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@Import(ProfileConfig::class)
@ActiveProfiles("test")
class ProfileTest {

    @Autowired
    private lateinit var translateProperties: TranslateProperties

    @Autowired
    private lateinit var user: User

    @Test
    fun profileTest() {
        println(translateProperties)

        assertThat(translateProperties).isEqualTo(
            TranslateProperties(
                ServerInfo("127.0.0.1", "1000"),
                ServerInfo("127.0.0.2", "2000"),
                "test"
            )
        )
    }

    @Test
    fun beanProfileTest() {
        assertThat(user).isEqualTo(
            User(1000, "test")
        )
    }
}

@Configuration
class ProfileConfig {

    @Bean
    @Profile("test")
    fun testUser(): User = User(1000, "test")

    @Bean
    @Profile("real")
    fun realUser(): User = User(2000, "real")
}
