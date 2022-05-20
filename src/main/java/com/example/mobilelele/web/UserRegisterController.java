package com.example.mobilelele.web;

import com.example.mobilelele.model.dto.binding.UserRegisterBindingModel;
import com.example.mobilelele.model.dto.service.UserRegisterServiceModel;
import com.example.mobilelele.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserRegisterController {

    private final UserService userService;
    private final ModelMapper mapper;

    @Autowired
    public UserRegisterController(UserService userService) {
        this.userService = userService;
        this.mapper = new ModelMapper();
    }

    @ModelAttribute(name = "userModel")
    public UserRegisterBindingModel userModel() {
        return new UserRegisterBindingModel();
    }

    @GetMapping("/register")
    public String getRegisterView() {
        return "auth-register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegisterBindingModel userModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userModel", userModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel", bindingResult);

            return "redirect:/users/register";
        }

        UserRegisterServiceModel serviceModel =
                mapper.map(userModel, UserRegisterServiceModel.class);


        if (!userService.isUsernameFree(serviceModel.getUsername())) {
            redirectAttributes.addFlashAttribute("userModel", userModel);
            redirectAttributes.addFlashAttribute("userNameOccupied", true);

            return "redirect:/users/register";
        } else {
            userService.register(serviceModel);
        }

        return "redirect:/";

    }
}
