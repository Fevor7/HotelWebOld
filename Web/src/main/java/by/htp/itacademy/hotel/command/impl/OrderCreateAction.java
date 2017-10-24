package by.htp.itacademy.hotel.command.impl;

import javax.servlet.http.HttpServletRequest;

import java.sql.Date;

import by.htp.itacademy.hotel.command.Action;
import by.htp.itacademy.hotel.command.exception.CommandInvalidParameterException;
import by.htp.itacademy.hotel.domain.entity.Order;
import by.htp.itacademy.hotel.domain.entity.User;
import by.htp.itacademy.hotel.domain.vo.FillResponseInfo;
import by.htp.itacademy.hotel.service.OrderService;
import by.htp.itacademy.hotel.service.exception.ServiceException;
import by.htp.itacademy.hotel.service.factory.ServiceFactory;

import static by.htp.itacademy.hotel.util.Parameter.*;

/**
 * The object of this class selects gentle parameters from the query and sends
 * them to further processing
 * 
 * @author Viktor
 *
 */
public class OrderCreateAction implements Action {

	private static final Action INSTANCE = new OrderCreateAction();
	private static final Long ID_STATUS_WAIT = new Long(1);
	private OrderService orderService;

	/**
	 * In this constructor, I get a reference to the factory object. And from her I
	 * get a link to the class object of the level - the service.
	 */
	private OrderCreateAction() {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		orderService = serviceFactory.getOrderService();
	}

	public static Action getInstance() {
		return INSTANCE;
	}

	@Override
	public FillResponseInfo execute(HttpServletRequest request) {
		System.out.println("command createorder");
		FillResponseInfo responseInfo = new FillResponseInfo(RESPONSE_OK, true);
		User user = (User) request.getSession().getAttribute(SESSION_PARAMETER_USER);
		try {
			Order order = parseParameterRequest(request, user);
			orderService.createOrder(order, user);
		} catch (CommandInvalidParameterException e) {
			responseInfo.setData(RESPONSE_ERROR_DATA);
		} catch (ServiceException e) {
			responseInfo.setData(RESPONSE_ERROR);
		}
		return responseInfo;
	}

	/**
	 * Forming an Order object from query parameters
	 * 
	 * @param request
	 * @return
	 * @throws CommandInvalidParameterException
	 */
	private Order parseParameterRequest(HttpServletRequest request, User user) throws CommandInvalidParameterException {
		Long idUser = user.getId();
		String dateStart = request.getParameter(REQUEST_PARAMETER_DATE_START);
		String dateEnd = request.getParameter(REQUEST_PARAMETER_DATE_END);
		String bed = request.getParameter(REQUEST_PARAMETER_BED);
		String person = request.getParameter(REQUEST_PARAMETER_PERSON);
		String idTypeRoom = request.getParameter(REQUEST_PARAMETER_TYPE_ROOM_ID);
		Order order = formationOrder(dateStart, dateEnd, bed, person, idTypeRoom, idUser);
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
	private Order formationOrder(String dateStart, String dateEnd, String bed, String person, String idTypeRoom,
			Long idUser) throws CommandInvalidParameterException {
		Order order = null;
		try {
			Date dateStartFormat = Date.valueOf(dateStart);
			Date dateEndFormat = Date.valueOf(dateEnd);
			Byte bedNumber = Byte.parseByte(bed);
			Byte personNumber = Byte.parseByte(person);
			Long idTypeRoomFormat = Long.parseLong(idTypeRoom);
			order = new Order(dateStartFormat, dateEndFormat, bedNumber, personNumber, idTypeRoomFormat, idUser,
					ID_STATUS_WAIT);
		} catch (IllegalArgumentException e) {
			throw new CommandInvalidParameterException();
		}

		return order;
	}

}
