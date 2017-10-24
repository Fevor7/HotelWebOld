package by.htp.itacademy.hotel.command.impl;

import static by.htp.itacademy.hotel.util.Parameter.*;

import java.math.BigDecimal;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.htp.itacademy.hotel.command.Action;
import by.htp.itacademy.hotel.command.exception.CommandInvalidParameterException;
import by.htp.itacademy.hotel.domain.entity.Order;
import by.htp.itacademy.hotel.domain.entity.User;
import by.htp.itacademy.hotel.domain.vo.FillResponseInfo;
import by.htp.itacademy.hotel.service.OrderService;
import by.htp.itacademy.hotel.service.exception.ServiceException;
import by.htp.itacademy.hotel.service.factory.ServiceFactory;

/**
 * The object of this class saves the changes in the order that came in the
 * request from the administrator.
 * 
 * @author viktor
 *
 */
public class OrderUpdateAdminAction implements Action {

	private static final Action INSTANCE = new OrderUpdateAdminAction();
	private static final String ZERO_VALUE = "0";
	private static final String EMPTY_LINE = "";
	private OrderService orderService;

	/**
	 * In this constructor, I get a reference to the factory object. And from her I
	 * get a link to the class object of the level - the service.
	 */
	private OrderUpdateAdminAction() {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		orderService = serviceFactory.getOrderService();
	}

	public static Action getInstance() {
		return INSTANCE;
	}

	@Override
	public FillResponseInfo execute(HttpServletRequest request) {
		System.out.println("command orderupdateadmin");
		FillResponseInfo responseInfo = new FillResponseInfo(RESPONSE_OK, true);
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(SESSION_PARAMETER_USER);
		try {
			Order order = formationOrder(request);
			orderService.updateOrderAdmin(order, user);
		} catch (CommandInvalidParameterException e) {
			responseInfo.setData(RESPONSE_ERROR_DATA);
		} catch (ServiceException e) {
			responseInfo.setData(RESPONSE_ERROR);
		}
		return responseInfo;
	}

	/**
	 * This method retrieves parameters from the query and creates an order object.
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
		String orderId = request.getParameter(REQUEST_PARAMETER_ORDER_ID);
		String roomId = request.getParameter(REQUEST_PARAMETER_ROOM_ID);
		String idStatus = request.getParameter(REQUEST_PARAMETER_STATUS);
		String total = request.getParameter(REQUEST_PARAMETER_TOTAL);
		roomId = (EMPTY_LINE.equals(roomId)) ? ZERO_VALUE : roomId;
		return orderObjectCreate(dateStart, dateEnd, bed, person, idTypeRoom, orderId, roomId, idStatus, total);
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
	 * @param total
	 * @return
	 * @throws CommandInvalidParameterException
	 */
	private Order orderObjectCreate(String dateStart, String dateEnd, String bed, String person, String idTypeRoom,
			String orderId, String roomId, String idStatus, String total) throws CommandInvalidParameterException {
		Order order = null;
		try {
			Long id = Long.parseLong(orderId);
			Date dateStartFormat = Date.valueOf(dateStart);
			Date dateEndFormat = Date.valueOf(dateEnd);
			Byte bedNumber = Byte.parseByte(bed);
			Byte personNumber = Byte.parseByte(person);
			Long roomIdFormat = Long.parseLong(roomId);
			Long idType = Long.parseLong(idTypeRoom);
			Long idStatusFormat = Long.parseLong(idStatus);
			BigDecimal totalFormat = BigDecimal.valueOf(Long.parseLong(total));
			order = new Order(id, roomIdFormat, dateStartFormat, dateEndFormat, bedNumber, personNumber, idType,
					idStatusFormat, totalFormat);
		} catch (IllegalArgumentException e) {
			throw new CommandInvalidParameterException();
		}
		return order;
	}

}
