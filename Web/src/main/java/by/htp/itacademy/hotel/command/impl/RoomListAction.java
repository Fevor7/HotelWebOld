package by.htp.itacademy.hotel.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.htp.itacademy.hotel.command.Action;
import by.htp.itacademy.hotel.command.exception.CommandInvalidParameterException;
import by.htp.itacademy.hotel.domain.entity.Room;
import by.htp.itacademy.hotel.domain.vo.FillResponseInfo;
import by.htp.itacademy.hotel.domain.vo.ListPage;
import by.htp.itacademy.hotel.service.RoomService;
import by.htp.itacademy.hotel.service.exception.ServiceNoRoomFoundException;
import by.htp.itacademy.hotel.service.factory.ServiceFactory;

import static by.htp.itacademy.hotel.util.Parameter.*;
import static by.htp.itacademy.hotel.util.AddressPage.*;

/**
 * The object of this class returns a page containing the list of rooms.
 * 
 * @author viktor
 *
 */
public class RoomListAction extends AbstractAction implements Action {

	private static final Action INSTANCE = new RoomListAction();
	private static final String ROOM_LIST = "roomList";
	private RoomService roomService;

	/**
	 * In this constructor, I get a reference to the factory object. And from her I
	 * get a link to the class object of the level - the service.
	 */
	private RoomListAction() {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		roomService = serviceFactory.getRoomService();
	}

	public static Action getInstance() {
		return INSTANCE;
	}

	@Override
	public FillResponseInfo execute(HttpServletRequest request) {
		System.out.println("command roomlist");
		FillResponseInfo responseInfo = new FillResponseInfo(PAGE_ROOM_LIST);
		String language = fetchLanguage(request);
		try {
			ListPage<Room> listPage = fetchPageNumber(request);
			listPage = roomService.roomList(listPage, language);
			request.setAttribute(LIST_WRAPPER, listPage);
			request.setAttribute(ROOM_LIST, listPage.getData());
		} catch (ServiceNoRoomFoundException | CommandInvalidParameterException e) {
			responseInfo.changeField(RESPONSE_ERROR, true);
		}
		return responseInfo;
	}

	/**
	 * The method generates an order for the parameters from the query.
	 * 
	 * @param request
	 * @return
	 * @throws CommandInvalidParameterException
	 */
	private ListPage<Room> fetchPageNumber(HttpServletRequest request) throws CommandInvalidParameterException {
		String pageNumber = request.getParameter(REQUEST_PARAMETER_PAGE_NUMBER);
		Long pageNumberFormat = null;
		try {
			pageNumberFormat = Long.parseLong(pageNumber);
		} catch (IllegalArgumentException e) {
			throw new CommandInvalidParameterException();
		}
		ListPage<Room> ListPage = new ListPage<Room>(pageNumberFormat, AMOUNT_ELEMENTS, ROOM_LIST);
		return ListPage;
	}

}
