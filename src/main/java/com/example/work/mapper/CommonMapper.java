package com.example.work.mapper;

import com.example.work.entity.UserEntity;
import com.example.work.response.body.Course;
import com.example.work.response.body.LecturerRegisteredBy;
import com.example.work.response.body.UserSettingResponseBodyPart;
import com.example.work.entity.CourseEntity;
import com.example.work.entity.UserSettingsEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface CommonMapper {

    List<Course> map(List<CourseEntity> courseEntities);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "id", target = "id")
    Course map(CourseEntity entity);

    UserSettingResponseBodyPart map(UserSettingsEntity entity);

    List<UserSettingResponseBodyPart> mapToUserSettingResponseBodyPartList(List<UserSettingsEntity> entity);

    @NotNull LecturerRegisteredBy map(@NotNull UserEntity get);
}
