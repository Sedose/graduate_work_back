package com.example.work.service

import com.example.work.entity.StudentGroupEntity
import com.example.work.repository.AttendancesRepository
import com.example.work.repository.StudentGroupsRepository
import com.example.work.response.body.ReportItem
import com.example.work.response.body.StudentAttendancesReport
import org.springframework.stereotype.Service

@Service
open class TrainingRepresentativeService(
    private val studentGroupsRepository: StudentGroupsRepository,
    private val attendancesRepository: AttendancesRepository,
) {

    fun findAllStudentGroups(): MutableIterable<StudentGroupEntity> =
        studentGroupsRepository.findAll()

    fun formStudentAttendancesReportByGroupIdAndCourseId(
        studentGroupId: Int,
        courseId: Int,
    ): StudentAttendancesReport {
        val studentsByGroup =
            studentGroupsRepository.findGroupStudents(studentGroupId)
        val maxAttendances = attendancesRepository.findMaxAttendances(
            studentGroupId,
            courseId,
        )
        return StudentAttendancesReport(
            studentsByGroup.map {
                ReportItem(
                    it.email,
                    it.firstName,
                    it.lastName,
                    it.middleName,
                    findAttendancesPercent(
                        it.id,
                        courseId,
                        maxAttendances,
                    )
                )
            }
        )
    }

    private fun findAttendancesPercent(
        studentId: Int,
        courseId: Int,
        maxAttendances: Int,
    ): Int = (
            attendancesRepository.findNumberOfAttendances(
                studentId,
                courseId,
            ) / maxAttendances.toDouble() * 100
    ).toInt()
}
