package com.example.work.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

/**
 * registeredBy - id of lecturer
 */
@Table("user_attendances")
public final class AttendanceEntity {
    @Id
    private Integer id;
    private Integer userId;
    private Integer courseId;
    private Integer registeredBy;
    private Date registeredTimestamp;

    public AttendanceEntity(Integer id, Integer userId, Integer courseId, Integer registeredBy, Date registeredTimestamp) {
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
        this.registeredBy = registeredBy;
        this.registeredTimestamp = registeredTimestamp;
    }

    public Integer getId() {
        return this.id;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public Integer getCourseId() {
        return this.courseId;
    }

    public Integer getRegisteredBy() {
        return this.registeredBy;
    }

    public Date getRegisteredTimestamp() {
        return this.registeredTimestamp;
    }
}
