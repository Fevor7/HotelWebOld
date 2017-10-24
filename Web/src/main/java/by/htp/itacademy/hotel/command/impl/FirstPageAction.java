package by.htp.itacademy.hotel.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.htp.itacademy.hotel.command.Action;
import by.htp.itacademy.hotel.domain.entity.Unit;
import by.htp.itacademy.hotel.domain.entity.User;
import by.htp.itacademy.hotel.domain.vo.FillResponseInfo;
import by.htp.itacademy.hotel.service.RoomService;
import by.htp.itacademy.hotel.service.UserService;
import by.htp.itacademy.hotel.service.exception.ServiceException;
import by.htp.itacademy.hotel.service.exception.ServiceNoSuchUserException;
import by.htp.itacademy.hotel.service.factory.ServiceFactory;

import static by.htp.itacademy.hotel.util.AddressPage.*;
import static by.htp.itacademy.hotel.util.Parameter.*;

import java.util.List;

/**
 * The object of this class returns the address of the main page, which contains
 * the creation window of the ordar and the slider. Writes data to the session.
 * 
 * @author Viktor
 *
 */
public class FirstPageAction extends AbstractAction implements Action {

	private static final Action INSTANCE = new FirstPageAction();
	private UserService userService;
	private RoomService roomService;

	/**
	 * In this constructor, I get a reference to the factory object. And from her I
	 * get a link to the class object of the level - the service.
	 */
	private FirstPageAction() {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		userService = serviceFactory.getUserService();
		roomService = serviceFactory.getRoomService();
	}

	public static Action getInstance() {
		return INSTANCE;
	}

	@Override
	public FillResponseInfo execute(HttpServletRequest request) {
		System.out.println("command firstpage");
		FillResponseInfo responseData = new FillResponseInfo(PAGE_FIRST);
		HttpSession session = request.getSession();
		session.setAttribute(SESSION_PARAMETER_PAGE, REQUEST_ACTION_FIRSTPAGE);
		User user = (User) session.getAttribute(SESSION_PARAMETER_USER);
		if (user != null) {
			try {
				userService.personalInfo(user);
				session.setAttribute(SESSION_PARAMETER_USER, user);
			} catch (ServiceNoSuchUserException e) {
				session.invalidate();
				request.setAttribute(REQUEST_PARAMETER_ERROR_MSG, e.getMessage());
			}
		}
		fetchAllTypeRoom(request);
		return responseData;

	}

	/**
	 * The method gets a list of room types and writes them to the query.
	 * 
	 * @param request
	 */
	private void fetchAllTypeRoom(HttpServletRequest request) {
		String language = fetchLanguage(request);
		try {
			List<Unit> list = roomService.typeRoomList(language);
			request.setAttribute(REQUEST_PARAMETER_TYPE_ROOM_LIST, list);
		} catch (ServiceException e) {
			request.setAttribute(REQUEST_PARAMETER_ERROR_MSG, e.getMessage());
		}

	}

}
