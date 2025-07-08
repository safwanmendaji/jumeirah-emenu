package com.jumeirah.jwt;

import com.jumeirah.model.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserInfoDetails implements UserDetails {

    private String username;
    private String password;
    private GrantedAuthority authority;

    public UserInfoDetails(UserInfo userInfo) {
        this.username = userInfo.getEmail(); // Username is typically the email
        this.password = userInfo.getPassword();

        // Convert the single role to GrantedAuthority
        this.authority = new SimpleGrantedAuthority(userInfo.getRole().getRoleName());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
