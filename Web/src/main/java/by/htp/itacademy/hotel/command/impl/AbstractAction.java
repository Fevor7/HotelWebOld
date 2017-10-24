package by.htp.itacademy.hotel.command.impl;

import static by.htp.itacademy.hotel.util.Parameter.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

abstract class AbstractAction {

	/**
	 * The method takes the meaning of the language
	 * 
	 * @param request
	 * @return
	 */
	String fetchLanguage(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String language = (String) session.getAttribute(REQUEST_ACTION_LANGUAGE);
		if (!LANGLIST.contains(language)) {
			language = LANGUAGE_RU;
		}
		return language;
	}

}
