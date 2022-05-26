package com.example.mobilelele.model.dto.binding;

import com.example.mobilelele.validator.UsernameExist;
import com.example.mobilelele.validator.ValidPassword;

@ValidPassword
public class UserLoginBindingModel {

    @UsernameExist
    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public UserLoginBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserLoginBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }
}
