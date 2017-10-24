package by.htp.itacademy.hotel.command.impl;

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

import static by.htp.itacademy.hotel.util.Parameter.*;

/**
 * The object of this class deletes the order.
 * 
 * @author viktor
 *
 */
public class OrderDeleteAction implements Action {

	private static final OrderDeleteAction INSTANCE = new OrderDeleteAction();
	private static final String ERROR_ORDER_DELETE = "errordelete";
	private static final String ORDER_ID = "id";
	private OrderService orderService;

	/**
	 * In this constructor, I get a reference to the factory object. And from her I
	 * get a link to the class object of the level - the service.
	 */
	private OrderDeleteAction() {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		orderService = serviceFactory.getOrderService();
	}

	public static OrderDeleteAction getInstance() {
		return INSTANCE;
	}

	@Override
	public FillResponseInfo execute(HttpServletRequest request) {
		System.out.println("command orderdelete");
		FillResponseInfo responseInfo = new FillResponseInfo(RESPONSE_OK, true);
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(SESSION_PARAMETER_USER);
		try {
			Order order = createOrder(request);
			orderService.orderDelete(order, user);
		} catch (CommandInvalidParameterException e) {
			responseInfo.setData(RESPONSE_ERROR_DATA);
		} catch (ServiceException e) {
			responseInfo.setData(ERROR_ORDER_DELETE);
		}
		return responseInfo;
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
	private Order createOrder(HttpServletRequest request) throws CommandInvalidParameterException {
		Order order = null;
		try {
			Long id = Long.parseLong(request.getParameter(ORDER_ID));
			order = new Order(id);
		} catch (IllegalArgumentException e) {
			throw new CommandInvalidParameterException();
		}
		return order;
	}

}
