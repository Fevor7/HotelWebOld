package by.htp.itacademy.hotel.command.impl;

import static by.htp.itacademy.hotel.util.AddressPage.*;
import static by.htp.itacademy.hotel.util.Parameter.*;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import by.htp.itacademy.hotel.command.Action;
import by.htp.itacademy.hotel.command.exception.CommandInvalidParameterException;
import by.htp.itacademy.hotel.domain.entity.Order;
import by.htp.itacademy.hotel.domain.entity.Room;
import by.htp.itacademy.hotel.domain.vo.FillResponseInfo;
import by.htp.itacademy.hotel.domain.vo.ListPage;
import by.htp.itacademy.hotel.service.RoomService;
import by.htp.itacademy.hotel.service.exception.ServiceNoRoomFoundException;
import by.htp.itacademy.hotel.service.factory.ServiceFactory;

/**
 * The object of this class reads the parameters of the room search and the list
 * of results from the administrator's request.s
 * 
 * @author viktor
 *
 */
public class RoomSearchAdminAction extends AbstractAction implements Action {

	private static final Action INSTANCE = new RoomSearchAdminAction();
	private static final String ROOM_LIST = "roomList";
	private static final String COMMAND = "roomsearchadmin";
	private RoomService roomService;

	/**
	 * In this constructor, I get a reference to the factory object. And from her I
	 * get a link to the class object of the level - the service.
	 */
	private RoomSearchAdminAction() {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		roomService = serviceFactory.getRoomService();
	}

	public static Action getInstance() {
		return INSTANCE;
	}

	@Override
	public FillResponseInfo execute(HttpServletRequest request) {
		System.out.println("command roomsearchadmin");
		FillResponseInfo responseInfo = new FillResponseInfo(PAGE_ROOM_LIST_ADMIN);
		String language = fetchLanguage(request);
		try {
			Order order = formationOrder(request);
			listPageCreate(request, order, language);
		} catch (CommandInvalidParameterException e) {
			responseInfo.changeField(RESPONSE_ERROR_DATA, true);
		} catch (ServiceNoRoomFoundException e) {
			responseInfo.changeField(RESPONSE_ERROR, true);
		}
		return responseInfo;
	}

	/**
	 * 
	 * @param request
	 * @param order
	 *            ModificationRequestObject(request, list, order);
	 * @param language
	 * @return
	 * @throws CommandInvalidParameterException
	 * @throws ServiceNoRoomFoundException
	 */
	private ListPage<Room> listPageCreate(HttpServletRequest request, Order order, String language)
			throws CommandInvalidParameterException, ServiceNoRoomFoundException {
		ListPage<Room> listPage = fetchPageNumber(request);
		listPage = roomService.searchRoomAdmin(listPage, order, language);
		request.setAttribute(LIST_WRAPPER, listPage);
		request.setAttribute(ROOM_LIST, listPage.getData());
		return listPage;
	}

	/**
	 * The method generates an order for the parameters from the query.
	 * 
	 * @param request
	 * @return
	 * @throws CommandInvalidParameterException
	 */
	private Order formationOrder(HttpServletRequest request) throws CommandInvalidParameterException {
		String dateStart = request.getParameter(REQUEST_PARAMETER_DATE_START);
		String dateEnd = request.getParameter(REQUEST_PARAMETER_DATE_END);
		String bed = request.getParameter(REQUEST_PARAMETER_BED);
		String person = request.getParameter(REQUEST_PARAMETER_PERSON);
		String idTypeRoom = request.getParameter(REQUEST_PARAMETER_TYPE_ROOM_ID);
		String pageNumber = request.getParameter(REQUEST_PARAMETER_PAGE_NUMBER);
		return orderObjectCreate(dateStart, dateEnd, bed, person, idTypeRoom, pageNumber);
	}

	/**
	 * Forming an Order object from query parameters
	 * 
	 * @param dateStart
	 * @param dateEnd
	 * @param bed
	 * @param person
	 * @param status
	 * @param minPrice
	 * @param maxPrice
	 * @param orderId
	 * @param roomId
	 * @return
	 * @throws CommandInvalidParameterException
	 */
	private Order orderObjectCreate(String dateStart, String dateEnd, String bed, String person, String typeRoom,
			String pageNumber) throws CommandInvalidParameterException {
		Order order = null;
		try {
			Date dateStartFormat = Date.valueOf(dateStart);
			Date dateEndFormat = Date.valueOf(dateEnd);
			Byte bedNumber = Byte.parseByte(bed);
			Byte personNumber = Byte.parseByte(person);
			Integer pageNumberFormat = Integer.parseInt(pageNumber);
			Long idType = Long.parseLong(typeRoom);
			order = new Order(dateStartFormat, dateEndFormat, bedNumber, personNumber, idType, pageNumberFormat);
		} catch (IllegalArgumentException e) {
			throw new CommandInvalidParameterException();
		}
		return order;
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
		ListPage<Room> ListPage = new ListPage<Room>(pageNumberFormat, AMOUNT_ELEMENTS, COMMAND);
		return ListPage;
	}

}
