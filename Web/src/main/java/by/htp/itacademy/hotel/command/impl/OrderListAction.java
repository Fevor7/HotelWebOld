package by.htp.itacademy.hotel.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.htp.itacademy.hotel.command.Action;
import by.htp.itacademy.hotel.command.exception.CommandInvalidParameterException;
import by.htp.itacademy.hotel.domain.entity.Order;
import by.htp.itacademy.hotel.domain.entity.OrderStatus;
import by.htp.itacademy.hotel.domain.entity.User;
import by.htp.itacademy.hotel.domain.vo.FillResponseInfo;
import by.htp.itacademy.hotel.domain.vo.ListPage;
import by.htp.itacademy.hotel.service.OrderService;
import by.htp.itacademy.hotel.service.exception.ServiceNoOrderFoundException;
import by.htp.itacademy.hotel.service.exception.ServiceNoRoomFoundException;
import by.htp.itacademy.hotel.service.factory.ServiceFactory;

import static by.htp.itacademy.hotel.util.AddressPage.*;
import static by.htp.itacademy.hotel.util.Parameter.*;

/**
 * The object of this class checks whether the user is an administrator and
 * writes a list of orders in response.
 * 
 * @author Viktor
 *
 */
public class OrderListAction extends AbstractAction implements Action {

	private static final Action INSTANCE = new OrderListAction();
	private static final Long AMOUNT_ELEMENTS = new Long(10);
	private static final String LIST_WRAPPER = "listWrapper";
	private static final String ORDER_LIST_STATUS = "orderStatus";
	private static final String COMMAND = "orderList";

	private OrderService orderService;

	/**
	 * In this constructor, I get a reference to the factory object. And from her I
	 * get a link to the class object of the level - the service.
	 */
	private OrderListAction() {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		orderService = serviceFactory.getOrderService();
	}

	public static Action getInstance() {
		return INSTANCE;
	}

	@Override
	public FillResponseInfo execute(HttpServletRequest request) {
		System.out.println("command orderlist");
		FillResponseInfo responseData = new FillResponseInfo(PAGE_ORDER_LIST);
		String language = fetchLanguage(request);
		try {
			Order order = formationOrder(request);
			request.getSession().setAttribute(ORDER_LIST_STATUS, order.getOrderStatus().getId());
			listPageCreate(request, order, language);
		} catch (CommandInvalidParameterException e) {
			responseData.changeField(RESPONSE_ERROR_DATA, true);
		} catch (ServiceNoOrderFoundException e) {
			responseData.changeField(RESPONSE_ERROR, true);
		}
		return responseData;
	}

	/**
	 * 
	 * @param request
	 * @param order
	 * @param language
	 * @return
	 * @throws CommandInvalidParameterException
	 * @throws ServiceNoRoomFoundException
	 * @throws ServiceNoOrderFoundException
	 */
	private ListPage<Order> listPageCreate(HttpServletRequest request, Order order, String language)
			throws CommandInvalidParameterException, ServiceNoOrderFoundException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(SESSION_PARAMETER_USER);
		ListPage<Order> listPage = fetchPageNumber(request);
		listPage = orderService.orderList(listPage, order, user, language);
		request.setAttribute(LIST_WRAPPER, listPage);
		request.setAttribute(REQUEST_ACTION_ORDER_LIST, listPage.getData());
		return listPage;
	}

	/**
	 * The method generates an order for the parameters from the query.
	 * 
	 * @param request
	 * @return
	 * @throws CommandInvalidParameterException
	 */
	private ListPage<Order> fetchPageNumber(HttpServletRequest request) throws CommandInvalidParameterException {
		String pageNumber = request.getParameter(REQUEST_PARAMETER_PAGE_NUMBER);
		Long pageNumberFormat = null;
		try {
			pageNumberFormat = Long.parseLong(pageNumber);
		} catch (IllegalArgumentException e) {
			throw new CommandInvalidParameterException();
		}
		ListPage<Order> ListPage = new ListPage<Order>(pageNumberFormat, AMOUNT_ELEMENTS, COMMAND);
		return ListPage;
	}

	/**
	 * The method generates an order and query parameters.
	 * 
	 * @param request
	 * @return
	 * @throws CommandInvalidParameterException
	 */
	private Order formationOrder(HttpServletRequest request) throws CommandInvalidParameterException {
		Order order = new Order();
		try {
			String orderType = request.getParameter(REQUEST_PARAMETER_ORDER_TYPE);
			Long idStatus = Long.parseLong(orderType);
			order.setOrderStatus(new OrderStatus(idStatus));
		} catch (IllegalArgumentException e) {
			throw new CommandInvalidParameterException();
		}
		return order;
	}

}
