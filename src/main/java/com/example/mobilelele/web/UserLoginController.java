package com.example.mobilelele.web;

import com.example.mobilelele.model.dto.binding.UserLoginBindingModel;
import com.example.mobilelele.model.dto.service.UserLoginServiceModel;
import com.example.mobilelele.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserLoginController {

    private final UserService userService;
    private final ModelMapper mapper;

    private static Logger LOGGER = LoggerFactory.getLogger(UserLoginController.class);


    @Autowired
    public UserLoginController(UserService userService) {
        this.userService = userService;
        this.mapper = new ModelMapper();
    }

    @GetMapping("/users/login")
    public String login() {
        return "auth-login";
    }

    @PostMapping("/users/login")
    public String login(UserLoginBindingModel userLoginBindingModel) {

        boolean loginSuccessful = userService
                .login(mapper.map(userLoginBindingModel, UserLoginServiceModel.class));

        LOGGER.info("User tried to login. User with username {} tried to login. Success {}?",
                userLoginBindingModel.getUsername(),
                loginSuccessful);

        if (loginSuccessful) {
            return "redirect:/";
        }

        return "redirect:/users/login";
    }
}
