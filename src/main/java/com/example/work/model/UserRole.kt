package com.example.work.model

import org.springframework.security.core.authority.SimpleGrantedAuthority

enum class UserRole(private val permissions: Set<Permission>) {
    TRAINING_REPRESENTATIVE(setOf(Permission.DEVELOPERS_READ)),
    LECTURER(setOf(
            Permission.DEVELOPERS_READ,
            Permission.DEVELOPERS_WRITE,
            Permission.COURSES_READ,
    )),
    STUDENT(setOf(
            Permission.DEVELOPERS_READ,
    ));

    val authorities: Set<SimpleGrantedAuthority>
        get() = permissions
            .map { SimpleGrantedAuthority(it.permission) }
            .toSet()
}
