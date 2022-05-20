package com.example.mobilelele.model.dto.service;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRegisterServiceModel {
    private String firstName;

    private String lastName;

    private String username;

    private String password;


    public String getFirstName() {
        return firstName;
    }

    public UserRegisterServiceModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegisterServiceModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getUsername() {
        return username != null ? username.trim() : null;
    }

    public UserRegisterServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }


}
