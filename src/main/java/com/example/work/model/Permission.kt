package com.example.work.model

enum class Permission(val permission: String) {
    DEVELOPERS_READ("developers:read"),
    DEVELOPERS_WRITE("developers:write"),
    COURSES_READ("courses:read"),
}