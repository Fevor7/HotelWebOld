package by.htp.itacademy.hotel.command.impl;

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

import static by.htp.itacademy.hotel.util.AddressPage.*;
import static by.htp.itacademy.hotel.util.Parameter.*;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * The object of this class reads the room search parameters and the result list
 * from the user's query.
 * 
 * @author viktor
 *
 */
public class RoomSearchAction extends AbstractAction implements Action {

	private static final Action INSTANCE = new RoomSearchAction();
	private static final String ROOM_LIST = "roomList";
	private static final String LIST_WRAPPER = "listWrapper";
	private static final String COMMAND = "roomsearch";
	private RoomService roomService;

	/**
	 * In this constructor, I get a reference to the factory object. And from her I
	 * get a link to the class object of the level - the service.
	 */
	private RoomSearchAction() {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		roomService = serviceFactory.getRoomService();
	}

	public static Action getInstance() {
		return INSTANCE;
	}

	@Override
	public FillResponseInfo execute(HttpServletRequest request) {
		System.out.println("command roomsearch");
		FillResponseInfo responseInfo = new FillResponseInfo(PAGE_ROOM_LIST);
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
	 * @param language
	 * @return
	 * @throws CommandInvalidParameterException
	 * @throws ServiceNoRoomFoundException
	 */
	private ListPage<Room> listPageCreate(HttpServletRequest request, Order order, String language)
			throws CommandInvalidParameterException, ServiceNoRoomFoundException {
		ListPage<Room> listPage = fetchPageNumber(request);
		listPage = roomService.searchRoom(listPage, order, language);
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
		String minPrice = request.getParameter(REQUEST_PARAMETER_MIN_PRICE);
		String maxPrice = request.getParameter(REQUEST_PARAMETER_MAX_PRICE);
		String idTypeRoom = request.getParameter(REQUEST_PARAMETER_TYPE_ROOM_ID);
		Order order = orderObjectCreate(dateStart, dateEnd, bed, person, minPrice, maxPrice, idTypeRoom);
		return order;
	}

	/**
	 * Forming an Order object from query parameters
	 * 
	 * @param dateStart
	 * @param dateEnd
	 * @param bed
	 * @param person
	 * @param minPrice
	 * @param maxPrice
	 * @param orderId
	 * @param roomId
	 * @return
	 * @throws CommandInvalidParameterException
	 */
	private Order orderObjectCreate(String dateStart, String dateEnd, String bed, String person, String minPrice,
			String maxPrice, String typeRoom) throws CommandInvalidParameterException {
		Order order = null;
		try {
			Date dateStartFormat = Date.valueOf(dateStart);
			Date dateEndFormat = Date.valueOf(dateEnd);
			Byte bedNumber = Byte.parseByte(bed);
			Byte personNumber = Byte.parseByte(person);
			BigDecimal minPriceFormat = new BigDecimal(minPrice);
			BigDecimal maxPriceFormat = new BigDecimal(maxPrice);
			Long idType = Long.parseLong(typeRoom);
			order = new Order(dateStartFormat, dateEndFormat, bedNumber, personNumber, minPriceFormat, maxPriceFormat,
					idType);
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
