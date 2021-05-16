package com.example.work.controller.response.body

class UserSettingsResponseBody (
    val userSettings: List<UserSettingResponseBodyPart>
)

class UserSettingResponseBodyPart(
    val code: String,
    val description: String,
    val value: String,
    val defaultValue: String,
)
