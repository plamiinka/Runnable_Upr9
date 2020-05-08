package com.example.runnable_upr9;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidator {
    public boolean isValidEmail(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^.+@.{2,}\\\\..{2,}$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);

        return matcher.matches();
    }
    public boolean isValidPassword(String passwod) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "\\\\w{2}\\\\d\\\\w{2}\\\\d\\\\p[A-Z][!@#$%&*]\\\\d\\\\w{2}\\\\d\\\\w\\\\p[A-Z]\\\\d[!@#$%&*]";
                //"^(.{2}[0-9].{2}[0-9][A-Z][!@#$%&*]){2}$"
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(passwod);

        return matcher.matches();
    }
}
