package com.example.work.response.body

class StudentAttendancesReport (
    val reportItems: List<ReportItem>
)

class ReportItem(
    val email: String,
    val firstName: String,
    val middleName: String?,
    val lastName: String?,
    val attendancesPercent: Int,
)
