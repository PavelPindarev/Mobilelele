package com.example.mobilelele.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UsernameExistValidator.class)
public @interface UsernameExist {

    String message() default "Username is don't exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
