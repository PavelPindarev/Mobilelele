package com.example.mobilelele.service;

import com.example.mobilelele.model.dto.service.UserLoginServiceModel;
import com.example.mobilelele.model.dto.service.UserRegisterServiceModel;

public interface UserService {

    boolean login(UserLoginServiceModel userLoginServiceModel);

    void logout();

    void register(UserRegisterServiceModel userRegisterServiceModel);

    boolean isUsernameFree(String username);

    void initializeUsersAndRoles();
}
