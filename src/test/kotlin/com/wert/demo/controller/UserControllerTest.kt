package com.wert.demo.controller

import com.wert.demo.DemoApplication
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate

@SpringBootTest(
    classes = arrayOf(DemoApplication::class),
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate
    @Test
    fun sleep() {
        val forEntity = restTemplate.getForEntity("/sleep/3000", String::class.java)
        println(forEntity)

        Assertions.assertThat(forEntity.statusCode.is2xxSuccessful).isTrue()
    }
}
