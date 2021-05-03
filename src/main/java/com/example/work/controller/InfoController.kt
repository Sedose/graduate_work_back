package com.example.work.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping

@RestController
@RequestMapping("/info")
class InfoController(
    val handlerMapping: RequestMappingHandlerMapping
) {

    @GetMapping("/health-check")
    fun healthCheck(): String {
        return "Health check"
    }

    @GetMapping("/all-endpoints")
    fun showAllEndpoints(): Map<*, HandlerMethod> {
        return handlerMapping.handlerMethods
    }
}
