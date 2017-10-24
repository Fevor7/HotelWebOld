package by.htp.itacademy.hotel.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.htp.itacademy.hotel.command.Action;
import by.htp.itacademy.hotel.domain.entity.User;
import by.htp.itacademy.hotel.domain.vo.FillResponseInfo;
import by.htp.itacademy.hotel.service.UserService;
import by.htp.itacademy.hotel.service.exception.ServiceNoSuchUserException;
import by.htp.itacademy.hotel.service.factory.ServiceFactory;

import static by.htp.itacademy.hotel.util.Parameter.*;

/**
 * The object of this class retrieves the required parameters for authorization
 * from the query. Creates an object based on these parameters. And passes the
 * reference to the object to the service layer.
 * 
 * @author Viktor
 *
 */
public class LogInAction implements Action {

	private static Action INSTANCE = new LogInAction();
	private UserService userService;

	/**
	 * In this constructor, I get a reference to the factory object. And from her I
	 * get a link to the class object of the level - the service.
	 */
	private LogInAction() {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		userService = serviceFactory.getUserService();
	}

	public static Action getInstance() {
		return INSTANCE;
	}

	@Override
	public FillResponseInfo execute(HttpServletRequest request) {
		System.out.println("command login");
		FillResponseInfo responseData = new FillResponseInfo(true);
		User user = createUser(request);
		try {
			userService.logIn(user);
			request.getSession().setAttribute(SESSION_PARAMETER_USER, user);
			responseData.setData(user.getRole() + user.getName());
		} catch (ServiceNoSuchUserException e) {
			responseData.setData(RESPONSE_ERROR);
		}
		return responseData;
	}

	/**
	 * Creating a user object by query's query
	 * 
	 * @param request
	 * @return
	 */
	private User createUser(HttpServletRequest request) {
		String login = request.getParameter(REQUEST_PARAMETER_LOGIN);
		String password = request.getParameter(REQUEST_PARAMETER_PASSWORD);
		Integer hashCodeInt = password.hashCode();
		Long hashLong = hashCodeInt.longValue();
		return new User(login, hashLong);
	}

}
