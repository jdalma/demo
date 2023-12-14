package com.wert.demo.service

import com.wert.demo.User
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.dao.EmptyResultDataAccessException

@SpringBootTest
class UserServiceTest{

    @Autowired
    private lateinit var userService: UserService

    @AfterEach
    fun tearDown() {
        userService.delete(10)
        userService.delete(11)
    }

    @Test
    @DisplayName("롤백되지 않음")
    fun case1() {
        assertThatThrownBy {
            userService.case1(USER1, EX_USER)
        }.isInstanceOf(IllegalArgumentException::class.java)

        assertThat(userService.findOneById(10).name).isEqualTo("테스트")
    }

    @Test
    @DisplayName("롤백됨")
    fun case2() {
        assertThatThrownBy {
            userService.case2(USER1, EX_USER)
        }.isInstanceOf(IllegalArgumentException::class.java)

        assertThatThrownBy {
            userService.findOneById(10)
        }.isInstanceOf(EmptyResultDataAccessException::class.java)
    }

    companion object {
        val USER1 = User(10, "테스트")
        val EX_USER = User(11, "ex")
    }
}
