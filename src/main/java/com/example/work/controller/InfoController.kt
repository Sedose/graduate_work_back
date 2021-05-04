package com.example.work.controller

import com.example.work.repository.UserRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping

@RestController
@RequestMapping("/info")
class InfoController(
    val handlerMapping: RequestMappingHandlerMapping,
    val userRepository: UserRepository,
) {

    @GetMapping("/health-check")
    fun healthCheck(): String {
        return "Health check"
    }

    @GetMapping("/db-check")
    fun dbCheck() = userRepository.count()
}
