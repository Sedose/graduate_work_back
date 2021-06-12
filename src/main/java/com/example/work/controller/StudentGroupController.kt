package com.example.work.controller

import com.example.work.entity.StudentGroupEntity
import com.example.work.service.TrainingRepresentativeService
import lombok.RequiredArgsConstructor
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/student-groups")
class StudentGroupController (
    private val trainingRepresentativeService: TrainingRepresentativeService
) {

    @PreAuthorize("hasAuthority('student-groups:read')")
    @GetMapping
    fun studentGroups() : List<StudentGroupEntity>
        = trainingRepresentativeService.findAllStudentGroups()

    @GetMapping("{groupId}")
    @PreAuthorize("hasAuthority('student-groups:read')")
    fun getStudentGroupById(
        @PathVariable groupId: Int
    ): StudentGroupEntity? {
        return trainingRepresentativeService.findStudentGroupById(groupId)
    }
}