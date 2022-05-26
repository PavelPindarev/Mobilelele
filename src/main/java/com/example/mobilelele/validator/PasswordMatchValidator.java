package com.example.mobilelele.validator;

import com.example.mobilelele.model.dto.binding.UserLoginBindingModel;
import com.example.mobilelele.model.dto.service.UserLoginServiceModel;
import com.example.mobilelele.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<ValidPassword, Object> {

    private final UserService userService;
    private final ModelMapper mapper;

    @Autowired
    public PasswordMatchValidator(UserService userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        UserLoginBindingModel loginModel = (UserLoginBindingModel) value;

        return userService.passwordsCheck(mapper.map(loginModel, UserLoginServiceModel.class));
    }
}
