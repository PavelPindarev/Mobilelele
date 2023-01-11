package com.example.mobilelele.service;

import com.example.mobilelele.model.dto.user.UserLoginBindingModel;
import com.example.mobilelele.model.dto.user.UserRegisterBindingModel;

import java.util.Locale;

public interface UserService {

    void registerAndLogin(UserRegisterBindingModel userRegisterDTO, Locale preferredLocale);

    void login(String email);

    boolean isEmailFree(String email);

    void initializeUsersAndRoles();

    boolean passwordsCheck(UserLoginBindingModel userLoginDTO);

    void createUserIfNotExist(String userEmail);
}
