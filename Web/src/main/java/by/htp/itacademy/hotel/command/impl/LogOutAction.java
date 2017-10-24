package by.htp.itacademy.hotel.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.htp.itacademy.hotel.command.Action;
import by.htp.itacademy.hotel.domain.vo.FillResponseInfo;

import static by.htp.itacademy.hotel.util.Parameter.*;

/**
 * The object of this class provides the logout of the user.
 * 
 * @author Viktor
 *
 */
public class LogOutAction implements Action {

	private static final LogOutAction INSTANCE = new LogOutAction();

	private LogOutAction() {
	}

	public static LogOutAction getInstance() {
		return INSTANCE;
	}

	@Override
	public FillResponseInfo execute(HttpServletRequest request) {
		System.out.println("command logout");
		HttpSession session = request.getSession();
		session.invalidate();
		return new FillResponseInfo(RESPONSE_OK, true);
	}

}
