package by.htp.itacademy.hotel.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.htp.itacademy.hotel.command.Action;
import by.htp.itacademy.hotel.domain.entity.Unit;
import by.htp.itacademy.hotel.domain.vo.FillResponseInfo;
import by.htp.itacademy.hotel.service.RoomService;
import by.htp.itacademy.hotel.service.exception.ServiceException;
import by.htp.itacademy.hotel.service.factory.ServiceFactory;

import static by.htp.itacademy.hotel.util.AddressPage.*;
import static by.htp.itacademy.hotel.util.Parameter.*;

import java.util.List;

/**
 * The object of this class returns a page with a filter for the list of rooms.
 * 
 * @author Viktor
 *
 */
public class RoomPageAction extends AbstractAction implements Action {

	private static final Action INSTANCE = new RoomPageAction();
	private RoomService roomService;

	private RoomPageAction() {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		roomService = serviceFactory.getRoomService();
	}

	public static Action getInstance() {
		return INSTANCE;
	}

	@Override
	public FillResponseInfo execute(HttpServletRequest request) {
		System.out.println("command roompage");
		FillResponseInfo responseInfo = new FillResponseInfo(PAGE_ROOM);
		request.getSession().setAttribute(SESSION_PARAMETER_PAGE, REQUEST_ACTION_ROOM_PAGE);
		String language = fetchLanguage(request);
		try {
			List<Unit> list = roomService.typeRoomList(language);
			request.setAttribute(REQUEST_PARAMETER_TYPE_ROOM_LIST, list);
		} catch (ServiceException e) {
			request.setAttribute(REQUEST_PARAMETER_ERROR_MSG, e.getMessage());
		}
		return responseInfo;
	}

}
