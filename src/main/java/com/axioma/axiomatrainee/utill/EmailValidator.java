package com.axioma.axiomatrainee.utill;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    private Pattern pattern;
    private Matcher matcher;

    @Override
    public void initialize(ValidEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return verifyEmail(email);
    }

    private boolean verifyEmail(String email) {
        pattern = Pattern.compile(ValidationUtils.EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
