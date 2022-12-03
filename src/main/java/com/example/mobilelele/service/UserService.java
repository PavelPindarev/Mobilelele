package com.example.mobilelele.service;

import com.example.mobilelele.model.dto.binding.UserRegisterBindingModel;
import com.example.mobilelele.model.dto.service.UserLoginServiceModel;

public interface UserService {

    void registerAndLogin(UserRegisterBindingModel userRegisterDTO);

    void login(String userName);

    boolean isUsernameFree(String username);

    void initializeUsersAndRoles();

    boolean passwordsCheck(UserLoginServiceModel loginServiceModel);
}
