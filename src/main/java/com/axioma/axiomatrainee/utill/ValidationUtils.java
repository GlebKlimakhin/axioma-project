package com.axioma.axiomatrainee.utill;

public class ValidationUtils {
    public static final String USERNAME_PATTERN = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";
    public static final String EMAIL_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    public static final String PASSWORD_PATTERN = "^(?=.*[0-9])"
            + "(?=.*[a-z])"
            + "(?=.*[A-Z])"
            + "(?=\\S+$).{8,20}$";
}
