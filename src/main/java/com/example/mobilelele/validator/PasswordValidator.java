package com.example.mobilelele.validator;

import com.example.mobilelele.model.dto.user.UserRegisterBindingModel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordMatcher, Object> {
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        UserRegisterBindingModel model = (UserRegisterBindingModel) value;
        return model.getPassword().equals(model.getConfirmPassword());
    }
}
