package com.example.work.mapper

import com.example.work.entity.CourseEntity
import com.example.work.response.body.Course
import com.example.work.entity.UserSettingsEntity
import com.example.work.response.body.UserSettingResponseBodyPart
import com.example.work.entity.UserEntity
import com.example.work.response.body.LecturerRegisteredBy
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface CommonMapper {
    fun map(courseEntities: List<CourseEntity>): List<Course>
    fun map(entity: CourseEntity): Course
    fun map(entity: UserSettingsEntity): UserSettingResponseBodyPart
    fun mapToUserSettingResponseBodyPartList(entity: List<UserSettingsEntity>): List<UserSettingResponseBodyPart>
    fun map(userEntity: UserEntity): LecturerRegisteredBy
}
