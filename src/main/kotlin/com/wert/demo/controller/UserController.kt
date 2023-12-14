package com.wert.demo.controller

import com.wert.demo.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseBody


@Controller
class UserController(
    private val userService: UserService
) {

    @GetMapping(value = ["/hello/{userId}/{userCode}"])
    fun hello(
        @PathVariable("userId") data: String?,
        @PathVariable userCode: String?
    ): String? {
        return data
    }

    @GetMapping("/sleep/{millis}")
    @ResponseBody
    fun sleep(
        @PathVariable millis: Long
    ): String {
        userService.sleep(millis)
        return "success"
    }
}
