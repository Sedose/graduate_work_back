package com.example.work.security;

import com.example.work.entity.Status;
import com.example.work.entity.UserEntity;
import com.example.work.entity.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class SecurityUser extends User {
    private UserRole userRole;
    private Long id;
    private boolean isActive;

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public SecurityUser(String username, UserRole userRole, Collection<? extends GrantedAuthority> authorities, Long id, boolean isActive) {
        super(username, "", authorities);
        this.userRole = userRole;
        this.id = id;
        this.isActive = isActive;
    }

    public static SecurityUser fromUser(UserEntity userEntity) {
        return new SecurityUser(
                userEntity.getEmail(),
                userEntity.getRole(),
                userEntity.getRole().getAuthorities(),
                userEntity.getId(),
                userEntity.getStatus().equals(Status.ACTIVE)
        );
    }

    public Long getId() {
        return this.id;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public UserRole getUserRole() {
        return this.userRole;
    }

    public String getEmail() {
        return super.getUsername();
    }
}
