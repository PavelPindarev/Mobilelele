package com.example.mobilelele.model.userdetails;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class MobileleUserDetails implements UserDetails {
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Collection<GrantedAuthority> authorities;

    public MobileleUserDetails(Long id,
                               String username,
                               String password,
                               String firstName,
                               String lastName,
                               Collection<GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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

    public String getFullName() {
        StringBuilder builder = new StringBuilder();

        if (!this.firstName.isEmpty()) {
            builder.append(this.firstName);
        }

        if (!this.lastName.isEmpty()) {
            if (!builder.isEmpty()) {
                builder.append(" ");
            }
            builder.append(this.lastName);
        }
        return builder.toString();
    }
}
