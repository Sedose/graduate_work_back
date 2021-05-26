package com.example.work.repository

import com.example.work.entity.AttendanceEntity
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import java.time.Instant
import java.util.*

interface StudentAttendancesRepository : CrudRepository<AttendanceEntity, Int> {

    @Query(
        """
            SELECT MAX(`user_attendances`.`registered_timestamp`) 
            FROM `user_attendances` WHERE `user_attendances`.`registered_by` = :lecturerIdRegisteredBy
        """
    )
    fun findMaxByRegisteredTimestampAndRegisteredBy(
        lecturerIdRegisteredBy: Int,
    ): Instant?
}
