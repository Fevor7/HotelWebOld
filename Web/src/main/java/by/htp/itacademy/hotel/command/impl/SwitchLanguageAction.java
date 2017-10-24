package by.htp.itacademy.hotel.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.htp.itacademy.hotel.command.Action;
import by.htp.itacademy.hotel.domain.vo.FillResponseInfo;

import static by.htp.itacademy.hotel.util.Parameter.*;

public class SwitchLanguageAction implements Action {

	private static final Action INSTANCE = new SwitchLanguageAction();

	private SwitchLanguageAction() {
	}

	public static Action getInstance() {
		return INSTANCE;
	}

	@Override
	public FillResponseInfo execute(HttpServletRequest request) {
		System.out.println("command language");
		String languageValue = request.getParameter(REQUEST_ACTION_LANGUAGE_VALUE);
		request.getSession().setAttribute(REQUEST_ACTION_LANGUAGE, languageValue);
		return new FillResponseInfo(RESPONSE_OK, true);
	}

}
