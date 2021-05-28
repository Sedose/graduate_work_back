package com.example.work.controller

import com.example.work.service.TrainingRepresentativeService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/student-groups")
class UserGroupController(
    private val trainingRepresentativeService: TrainingRepresentativeService,
) {

    @GetMapping
    fun getStudentGroups() = trainingRepresentativeService.findAllStudentGroups()
}
