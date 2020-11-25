package com.example.work.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum UserRole {
    TRAINING_REPRESENTATIVE(Set.of(Permission.DEVELOPERS_READ))
    , LECTURER(Set.of(Permission.DEVELOPERS_READ, Permission.DEVELOPERS_WRITE))
    , STUDENT(Set.of(Permission.DEVELOPERS_READ));

    private final Set<Permission> permissions;

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}

