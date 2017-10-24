package by.htp.itacademy.hotel.command.impl;

import static by.htp.itacademy.hotel.util.AddressPage.*;
import static by.htp.itacademy.hotel.util.Parameter.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.htp.itacademy.hotel.command.Action;
import by.htp.itacademy.hotel.command.exception.CommandInvalidParameterException;
import by.htp.itacademy.hotel.domain.entity.Order;
import by.htp.itacademy.hotel.domain.entity.Unit;
import by.htp.itacademy.hotel.domain.entity.User;
import by.htp.itacademy.hotel.domain.vo.FillResponseInfo;
import by.htp.itacademy.hotel.domain.vo.ListPage;
import by.htp.itacademy.hotel.service.OrderService;
import by.htp.itacademy.hotel.service.RoomService;
import by.htp.itacademy.hotel.service.exception.ServiceException;
import by.htp.itacademy.hotel.service.exception.ServiceNoOrderFoundException;
import by.htp.itacademy.hotel.service.exception.ServiceNoRoomFoundException;
import by.htp.itacademy.hotel.service.factory.ServiceFactory;

/**
 * The object of this class returns a page with user's orders.
 * 
 * @author viktor
 *
 */
public class UserOrderListAction extends AbstractAction implements Action {

	private static final Action INSTANCE = new UserOrderListAction();
	private static final String COMMAND = "orderListUser";
	private OrderService orderService;
	private RoomService roomService;

	/**
	 * In this constructor, I get a reference to the factory object. And from her I
	 * get a link to the class object of the level - the service.
	 */
	private UserOrderListAction() {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		orderService = serviceFactory.getOrderService();
		roomService = serviceFactory.getRoomService();

	}

	public static Action getInstance() {
		return INSTANCE;
	}

	@Override
	public FillResponseInfo execute(HttpServletRequest request) {
		System.out.println("command orderlistuser");
		FillResponseInfo responseData = new FillResponseInfo(PAGE_USER_ORDER_LIST);
		String language = fetchLanguage(request);
		try {
			listPageCreate(request, language);
		} catch (ServiceNoOrderFoundException | CommandInvalidParameterException e) {
			responseData.changeField(RESPONSE_ERROR, true);
		}
		fetchAllTypeRoom(request);
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
	private ListPage<Order> listPageCreate(HttpServletRequest request, String language)
			throws CommandInvalidParameterException, ServiceNoOrderFoundException {
		User user = (User) request.getSession().getAttribute(SESSION_PARAMETER_USER);
		ListPage<Order> listPage = fetchPageNumber(request);
		listPage = orderService.orderListUser(listPage, user, language);
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
		return new ListPage<Order>(pageNumberFormat, AMOUNT_ELEMENTS, COMMAND);
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
