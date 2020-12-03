package com.example.work.security;

import com.example.work.entity.Status;
import com.example.work.entity.UserEntity;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class SecurityUser extends User {

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

    public SecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities, Long id, boolean isActive) {
        super(username, password, authorities);
        this.id = id;
        this.isActive = isActive;
    }

    public static SecurityUser fromUser(UserEntity userEntity) {
        return new SecurityUser(
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getRole().getAuthorities(),
                userEntity.getId(),
                userEntity.getStatus().equals(Status.ACTIVE)
        );
    }
}
