package com.example.mobilelele.user;

import com.example.mobilelele.model.entity.enums.RoleType;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashSet;
import java.util.Set;

@Component
@SessionScope
public class CurrentUser {

    private boolean isLoggedIn;
    private String userName;
    private String firstName;
    private String lastName;
    private Set<RoleType> roles = new HashSet<>();

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public CurrentUser setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public CurrentUser setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public CurrentUser setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CurrentUser setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Set<RoleType> getRoles() {
        return roles;
    }

    public CurrentUser setRoles(Set<RoleType> roles) {
        this.roles = roles;
        return this;
    }

    public CurrentUser addRole(RoleType role) {
        this.roles.add(role);
        return this;
    }

    public CurrentUser clearRoles() {
        this.roles.clear();
        return this;
    }

    public boolean isAdmin() {
        return roles.contains(RoleType.ADMIN);
    }

    public void clear() {
        this.setFirstName(null).setLastName(null).setUserName(null).setLoggedIn(false).clearRoles();
    }

}
