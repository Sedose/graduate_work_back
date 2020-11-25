package com.example.work.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table("users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private UserRole role;
    private Status status;
}
