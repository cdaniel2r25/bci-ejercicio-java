package com.example.bci.demoApi.config.annotations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordConstraintValidator implements ConstraintValidator<PasswordValid, String> {

	@Override
	public void initialize(PasswordValid arg0) {
	}

	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {

		String regex = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$";

		Pattern p = Pattern.compile(regex);

		if (password == null) {
	
			return false;
		}

		Matcher m = p.matcher(password);
		return m.matches();

	}

}
