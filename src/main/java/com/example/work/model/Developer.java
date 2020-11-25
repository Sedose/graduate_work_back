package com.example.work.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Developer {
    Long id;
    String firstName;
    String lastName;
}
