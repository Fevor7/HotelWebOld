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
 * The object of this class returns the address of the home page. And save some
 * parameters to the session object.
 * 
 * @author Viktor
 *
 */
public class HomePageAction implements Action {

	private static final Action INSTANCE = new HomePageAction();
	private UserService userService;

	/**
	 * In this constructor, I get a reference to the factory object. And from her I
	 * get a link to the class object of the level - the service.
	 */
	private HomePageAction() {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		userService = serviceFactory.getUserService();
	}

	public static Action getInstance() {
		return INSTANCE;
	}

	@Override
	public FillResponseInfo execute(HttpServletRequest request) {
		System.out.println("command homepage");
		FillResponseInfo responseData = new FillResponseInfo(PAGE_HOME);
		HttpSession session = request.getSession();
		settingLanguage(session);
		User user = (User) session.getAttribute(SESSION_PARAMETER_USER);
		if (user != null) {
			try {
				userService.personalInfo(user);
				session.setAttribute(SESSION_PARAMETER_USER, user);
			} catch (ServiceNoSuchUserException e) {
				session.invalidate();
			}
		}
		return responseData;
	}

	/**
	 * The method checks the record of the current language of the page.
	 * 
	 * @param session
	 * @return
	 */
	private void settingLanguage(HttpSession session) {
		Object languageValue = session.getAttribute(REQUEST_ACTION_LANGUAGE);
		if (languageValue == null) {
			session.setAttribute(REQUEST_ACTION_LANGUAGE, REQUEST_ACTION_LANGUAGE_RU);
		}
	}

}
