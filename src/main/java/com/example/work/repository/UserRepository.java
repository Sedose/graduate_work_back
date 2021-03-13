package com.example.work.repository;

import com.example.work.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByFirstNameAndMiddleNameAndLastName(String firstName, String middleName, String lastName);
}
