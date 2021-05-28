package com.example.work.service

import com.example.work.entity.StudentGroupEntity
import com.example.work.repository.StudentGroupsRepository
import org.springframework.stereotype.Service

@Service
class TrainingRepresentativeService(
    private val studentGroupsRepository: StudentGroupsRepository,
) {

    fun findAllStudentGroups(): MutableIterable<StudentGroupEntity> =
        studentGroupsRepository.findAll()
}
