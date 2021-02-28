package com.example.work.security

import com.example.work.exception.JwtAuthException
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
@RequiredArgsConstructor
class JwtTokenFilter(private val jwtTokenProvider: JwtTokenProvider) : GenericFilterBean() {

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        jwtTokenProvider.resolveToken(request as HttpServletRequest)?.let { token ->
            runCatching {
                jwtTokenProvider.getAuthentication(token)?.let {
                    SecurityContextHolder.getContext().authentication = it
                }
            }.onFailure {
                SecurityContextHolder.clearContext()
                sendError(response)
            }
        } ?: sendError(response)
        chain.doFilter(request, response)
    }

    private fun sendError(response: ServletResponse) {
        (response as HttpServletResponse).sendError(HttpStatus.FORBIDDEN.value())
        throw JwtAuthException("JWT token is expired or invalid")
    }
}
