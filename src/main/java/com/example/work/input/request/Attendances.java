package com.example.work.input.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Attendances {
    List<Attendance> attendances;
}
