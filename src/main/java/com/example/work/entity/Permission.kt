package com.example.work.entity

enum class Permission(val permission: String) {
    DEVELOPERS_READ("developers:read"),
    DEVELOPERS_WRITE("developers:write"),
    COURSES_READ("courses:read"),
}