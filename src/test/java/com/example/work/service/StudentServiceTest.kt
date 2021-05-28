package com.example.work.service

import com.example.work.controller.request.body.Attendance
import com.example.work.controller.request.body.AttendancesRequestBody
import com.example.work.controller.request.body.UserSettings
import com.example.work.controller.request.body.UserSettingsRequestBody
import com.example.work.controller.response.body.UserSettingResponseBodyPart
import com.example.work.controller.response.body.UserSettingsResponseBody
import com.example.work.entity.UserSettingsEntity
import com.example.work.mapper.CommonMapper
import com.example.work.model.Permission
import com.example.work.model.UserRole
import com.example.work.repository.StudentAttendancesRepository
import com.example.work.repository.UserRepository
import com.example.work.repository.UserSettingsRepository
import com.example.work.security.SecurityUser
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.*

class StudentServiceTest : StringSpec() {

    init {
        val studentAttendancesRepository = mockk<StudentAttendancesRepository>()
        val userSettingsRepository = mockk<UserSettingsRepository>(
            relaxUnitFun = true
        )
        val userRepository = mockk<UserRepository>()
        val target = StudentService(
            studentAttendancesRepository,
            userSettingsRepository,
            userRepository,
        )

        "test registerAttendanceUsingFile()" {
            //given
            val lecturerIdRegisteredBy = 1


            //when
            val actual = target.registerAttendanceUsingFile(
                attendancesRequestBodyTestData,
                lecturerIdRegisteredBy
            )

            //then
//        val expected = UserSettingsResponseBody(userSettingsResponseBodiesTestData)

//        actual shouldBeEqualToComparingFields expected
        }
    }


    val attendancesRequestBodyTestData = AttendancesRequestBody(
        attendances = listOf(
            Attendance(
                fullName = "Name11 Name12 Name13",
                userAction = "userAction1",
                timestamp = "18.11.2020, 14:01:15"
            ),
            Attendance(
                fullName = "Name21 Name22 Name23",
                userAction = "userAction2",
                timestamp = "18.11.2020, 14:03:40"
            ),
            Attendance(
                fullName = "Name31 Name32 Name33",
                userAction = "userAction3",
                timestamp = "18.11.2020, 14:02:41"
            ),
        ),
        courseId = 1,
        registeredTimestamp = Date(1622177300)
    )


    val securityUserTestData = SecurityUser(
        "username",
        UserRole.LECTURER,
        listOf(SimpleGrantedAuthority(Permission.USER_SETTINGS_UPDATE.name)),
        1,
        true,
    )
}
