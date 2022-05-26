package com.example.mobilelele.web;

import com.example.mobilelele.model.dto.binding.UserLoginBindingModel;
import com.example.mobilelele.model.dto.service.UserLoginServiceModel;
import com.example.mobilelele.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserLoginController {

    private final UserService userService;
    private final ModelMapper mapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginController.class);

    @Autowired
    public UserLoginController(UserService userService) {
        this.userService = userService;
        this.mapper = new ModelMapper();
    }

    @ModelAttribute("loginModel")
    public UserLoginBindingModel userLoginBindingModel() {
        return new UserLoginBindingModel();
    }

    @ModelAttribute("hasErrors")
    public boolean hasErrors() {
        return false;
    }

    @GetMapping("/login")
    public String login() {
        return "auth-login";
    }

    @PostMapping("/login")
    public String login(@Valid UserLoginBindingModel loginModel,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        boolean hasErrors = bindingResult.hasErrors();
        LOGGER.info("User tried to login. User with username {} tried to login. Success {}?",
                loginModel.getUsername(),
                !hasErrors);

        if (hasErrors) {
            redirectAttributes.addFlashAttribute("loginModel", loginModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.loginModel", bindingResult)
                    .addFlashAttribute("hasErrors", true);

            return "redirect:/users/login";
        }

        userService.login(mapper.map(loginModel, UserLoginServiceModel.class));

        return "redirect:/";
    }
}
