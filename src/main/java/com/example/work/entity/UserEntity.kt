package com.example.work.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("users")
class UserEntity (
    @Id
    val id: Long,
    val email: String,
    val role: UserRole,
    val status: Status
)
