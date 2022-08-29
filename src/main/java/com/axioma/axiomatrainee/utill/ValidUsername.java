package com.axioma.axiomatrainee.utill;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UsernameValidator.class)
@NotNull
public @interface ValidUsername {

    String message() default "Minimum length for password is 5, maximum is 20 " +
            "password must consist of letters, numbers and special characters. ('.', '-', '~') cannot be last and first characters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
