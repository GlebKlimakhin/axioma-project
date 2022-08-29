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
@Constraint(validatedBy = EmailValidator.class)
@NotNull
public @interface ValidEmail {
    String message() default "Email pattern is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
