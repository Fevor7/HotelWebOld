package by.htp.itacademy.hotel.command.impl;

import static by.htp.itacademy.hotel.util.Parameter.*;

import javax.servlet.http.HttpServletRequest;

import by.htp.itacademy.hotel.command.Action;
import by.htp.itacademy.hotel.command.exception.CommandInvalidParameterException;
import by.htp.itacademy.hotel.command.validator.Validator;
import by.htp.itacademy.hotel.domain.entity.User;
import by.htp.itacademy.hotel.domain.vo.FillResponseInfo;
import by.htp.itacademy.hotel.service.UserService;
import by.htp.itacademy.hotel.service.exception.ServiceException;
import by.htp.itacademy.hotel.service.factory.ServiceFactory;

/**
 * An object of this class registers a new user.
 * 
 * @author Viktor
 *
 */
public class SignUpAction implements Action {

	private static final Action INSTANCE = new SignUpAction();
	private UserService userService;

	/**
	 * In this constructor, I get a reference to the factory object. And from her I
	 * get a link to the class object of the level - the service.
	 */
	private SignUpAction() {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		userService = serviceFactory.getUserService();
	}

	public static Action getInstance() {
		return INSTANCE;
	}

	@Override
	public FillResponseInfo execute(HttpServletRequest request) {
		System.out.println("command newuser");
		FillResponseInfo responseData = new FillResponseInfo(RESPONSE_OK, true);
		try {
			User user = createUserObject(request);
			userService.signUp(user);
		} catch (CommandInvalidParameterException e) {
			responseData.setData(RESPONSE_ERROR_DATA);
		} catch (ServiceException e) {
			responseData.setData(RESPONSE_ERROR);
		}
		return responseData;
	}

	/**
	 * Create a User object by query parameters
	 * 
	 * @param request
	 * @return
	 * @throws CommandInvalidParameterException
	 */
	private User createUserObject(HttpServletRequest request) throws CommandInvalidParameterException {
		String name = request.getParameter(REQUEST_PARAMETER_NAME);
		String surname = request.getParameter(REQUEST_PARAMETER_SURNAME);
		String email = request.getParameter(REQUEST_PARAMETER_EMAIL);
		String login = request.getParameter(REQUEST_PARAMETER_LOGIN);
		String password = request.getParameter(REQUEST_PARAMETER_PASSWORD);
		Integer hashCodePass = password.hashCode();
		Long hashPassUser = hashCodePass.longValue();
		userValidation(password, name, surname, email);
		return new User(login, hashPassUser, name, surname, email);
	}

	/**
	 * Validation of User fields.
	 * 
	 * @param user
	 * @throws CommandInvalidParameterException
	 */
	private void userValidation(String password, String name, String surname, String email)
			throws CommandInvalidParameterException {
		Validator.nameValidation(name);
		Validator.nameValidation(surname);
		Validator.emailValidation(email);
		Validator.passwordValidation(password);
	}

}
