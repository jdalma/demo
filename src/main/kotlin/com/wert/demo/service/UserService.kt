package com.wert.demo.service

import com.wert.demo.User
import com.wert.demo.aop.TraceTest
import com.wert.demo.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository
) {

    @TraceTest
    fun sleep(millis: Long) {
        Thread.sleep(millis)
    }

    /**
     * 첫 번째 사용자 가입 후 두 번째 사용자 가입 시 런타임 예외 발생
     * 첫 번째 사용자는 롤백이 보장되지 않는다.
     */
    fun case1(user1: User, user2: User) {
        save(user1)
        save(user2)
    }

    /**
     * 첫 번째 사용자 가입 후 두 번째 사용자 가입 시 런타임 예외 발생
     * 첫 번째 사용자는 롤백이 보장된다
     */
    @Transactional
    fun case2(user1: User, user2: User) {
        save(user1)
        save(user2)
    }

    fun save(user: User) {
        require(user.name != "ex") {
            "런타임 예외 발생 ${user.name}"
        }
        userRepository.save(user)
    }

    fun delete(id: Int) {
        userRepository.delete(id)
    }

    fun findOneById(id: Int) = userRepository.findOneById(id)
}
