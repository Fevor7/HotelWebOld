package by.htp.itacademy.hotel.command.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.htp.itacademy.hotel.command.exception.CommandInvalidParameterException;

/**
 * Class for validating data.
 * 
 * @author viktor
 *
 */
public class Validator {

	private static final String EMAIL_PATTERN = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
	private static final String NAME_PATTERN = "^[а-яА-ЯёЁa-zA-Z]+$";
	private static final String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$";

	private Validator() {
	}

	/**
	 * Method for validating e-mail.
	 * 
	 * @param email
	 * @throws CommandInvalidParameterException
	 */
	public static void emailValidation(String email) throws CommandInvalidParameterException {
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		if (!matcher.matches()) {
			throw new CommandInvalidParameterException();
		}
	}

	/**
	 * Method for validation name.
	 * 
	 * @param name
	 * @throws CommandInvalidParameterException
	 */
	public static void nameValidation(String name) throws CommandInvalidParameterException {
		Pattern pattern = Pattern.compile(NAME_PATTERN);
		Matcher matcher = pattern.matcher(name);
		if (!matcher.matches()) {
			throw new CommandInvalidParameterException();
		}
	}

	/**
	 * Method for password validation.
	 * 
	 * @param password
	 * @throws CommandInvalidParameterException
	 */
	public static void passwordValidation(String password) throws CommandInvalidParameterException {
		Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
		Matcher matcher = pattern.matcher(password);
		if (!matcher.matches()) {
			throw new CommandInvalidParameterException();
		}
	}
}
