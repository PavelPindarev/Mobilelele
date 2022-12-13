package com.example.mobilelele.web;

import com.example.mobilelele.model.dto.user.UserRegisterBindingModel;
import com.example.mobilelele.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
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
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //REGISTER
    @ModelAttribute(name = "passMatch")
    public boolean match() {
        return true;
    }

    @GetMapping("/register")
    public String getRegisterView(Model model) {
        if (!model.containsAttribute("userModel")) {
            model.addAttribute("userModel", new UserRegisterBindingModel());
        }
        return "auth-register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegisterBindingModel userModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userModel", userModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userModel", bindingResult);

            bindingResult.getAllErrors().forEach(error -> {
                String defaultMessage = error.getDefaultMessage();
                if (defaultMessage != null && defaultMessage.equals("Password don't match")) {
                    redirectAttributes.addFlashAttribute("passMatch", false);
                }
            });
            return "redirect:/users/register";
        }

        userService.registerAndLogin(userModel);

        return "redirect:/";

    }

    //LOGIN
    @GetMapping("/login")
    public String login() {
        return "auth-login";
    }

    //    ON ERROR
    @PostMapping("/login-error")
    public String loginError(@ModelAttribute(UsernamePasswordAuthenticationFilter
            .SPRING_SECURITY_FORM_USERNAME_KEY) String username,
                             RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username)
                .addFlashAttribute("bad_credentials", true);

        return "redirect:/users/login";
    }

}
