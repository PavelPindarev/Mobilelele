package com.example.mobilelele.service;

import com.example.mobilelele.model.dto.user.UserLoginBindingModel;
import com.example.mobilelele.model.dto.user.UserRegisterBindingModel;

public interface UserService {

    void registerAndLogin(UserRegisterBindingModel userRegisterDTO);

    void login(String userName);

    boolean isUsernameFree(String username);

    void initializeUsersAndRoles();

    boolean passwordsCheck(UserLoginBindingModel loginServiceModel);
}
