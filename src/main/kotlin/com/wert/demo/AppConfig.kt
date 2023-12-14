package com.wert.demo

import com.wert.demo.controller.UserController
import com.wert.demo.repository.UserRepository
import com.wert.demo.service.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate

@Configuration
class AppConfig {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass.name)

    @Bean
    fun userRepository(template: JdbcTemplate): UserRepository {
        log.info("[@Configuration] call userRepository")
        return UserRepository(template)
    }

    @Bean
    fun userService(userRepository: UserRepository): UserService {
        log.info("[@Configuration] call userService")
        return UserService(userRepository)
    }

    @Bean
    fun userController(userService: UserService): UserController {
        log.info("[@Configuration] call userController")
        return UserController(userService)
    }
}
