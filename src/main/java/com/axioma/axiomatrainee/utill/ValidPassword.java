package com.axioma.axiomatrainee.utill;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
@NotNull
public @interface ValidPassword {
    String message() default "Minimum length for password is 8, " +
            "password must consist of at least one upper case letter, one lower case and one digit";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
