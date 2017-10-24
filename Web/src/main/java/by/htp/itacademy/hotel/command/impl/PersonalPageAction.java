package by.htp.itacademy.hotel.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.htp.itacademy.hotel.command.Action;
import by.htp.itacademy.hotel.domain.entity.User;
import by.htp.itacademy.hotel.domain.vo.FillResponseInfo;
import by.htp.itacademy.hotel.service.UserService;
import by.htp.itacademy.hotel.service.exception.ServiceNoSuchUserException;
import by.htp.itacademy.hotel.service.factory.ServiceFactory;

import static by.htp.itacademy.hotel.util.AddressPage.*;
import static by.htp.itacademy.hotel.util.Parameter.*;

/**
 * The object of this class returns the address of the user's personal cabinet
 * and information about the user.
 * 
 * @author viktor
 *
 */
public class PersonalPageAction implements Action {

	private static final Action INSTANCE = new PersonalPageAction();
	private UserService userService;

	/**
	 * In this constructor, I get a reference to the factory object. And from her I
	 * get a link to the class object of the level - the service.
	 */
	private PersonalPageAction() {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		userService = serviceFactory.getUserService();
	}

	public static Action getInstance() {
		return INSTANCE;
	}

	@Override
	public FillResponseInfo execute(HttpServletRequest request) {
		System.out.println("command personalpage");
		FillResponseInfo responseData = new FillResponseInfo(PAGE_PERSONAL);
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(SESSION_PARAMETER_USER);
		try {
			userService.personalInfo(user);
			session.setAttribute(SESSION_PARAMETER_USER, user);
			session.setAttribute(SESSION_PARAMETER_PAGE, REQUEST_ACTION_PERSONALPAGE);
		} catch (ServiceNoSuchUserException e) {
			responseData.changeField(RESPONSE_ERROR, true);
			session.invalidate();
		}
		return responseData;
	}

}
