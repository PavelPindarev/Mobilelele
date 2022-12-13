package com.example.mobilelele.model.dto.user;

import com.example.mobilelele.model.validation.EmailExist;
import com.example.mobilelele.model.validation.ValidPassword;

@ValidPassword
public class UserLoginBindingModel {

    @EmailExist
    private String email;

    private String password;

    public String getEmail() {
        return email;
    }

    public UserLoginBindingModel setEmail(String email) {
        this.email = email;
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
