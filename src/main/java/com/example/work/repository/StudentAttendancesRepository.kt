package com.example.work.repository;

import com.example.work.entity.AttendanceEntity;
import org.springframework.data.repository.CrudRepository;

public interface StudentAttendancesRepository extends CrudRepository<AttendanceEntity, Integer> {

}
