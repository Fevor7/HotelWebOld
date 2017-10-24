package by.htp.itacademy.hotel.command.impl;

import static by.htp.itacademy.hotel.util.AddressPage.*;
import static by.htp.itacademy.hotel.util.Parameter.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.htp.itacademy.hotel.command.Action;
import by.htp.itacademy.hotel.domain.entity.Unit;
import by.htp.itacademy.hotel.domain.vo.FillResponseInfo;
import by.htp.itacademy.hotel.service.OrderService;
import by.htp.itacademy.hotel.service.RoomService;
import by.htp.itacademy.hotel.service.exception.ServiceException;
import by.htp.itacademy.hotel.service.factory.ServiceFactory;

/**
 * The object of this class returns the address of the page where I will place
 * the orders for editing by the administrator.
 * 
 * @author viktor
 *
 */
public class OrderListAdminAction extends AbstractAction implements Action {

	private static final Action INSTANCE = new OrderListAdminAction();
	private static final String STATUS_LIST = "statusList";
	private RoomService roomService;
	private OrderService orderService;

	private OrderListAdminAction() {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		roomService = serviceFactory.getRoomService();
		orderService = serviceFactory.getOrderService();
	}

	public static Action getInstance() {
		return INSTANCE;
	}

	@Override
	public FillResponseInfo execute(HttpServletRequest request) {
		System.out.println("command orderlistadmin");
		request.getSession().setAttribute(SESSION_PARAMETER_PAGE, REQUEST_ACTION_ORDER_LIST_ADMIN);
		String language = fetchLanguage(request);
		try {
			List<Unit> listType = roomService.typeRoomList(language);
			List<Unit> listStatus = orderService.fetchStatusList(language);
			request.setAttribute(REQUEST_PARAMETER_TYPE_ROOM_LIST, listType);
			request.setAttribute(STATUS_LIST, listStatus);
		} catch (ServiceException e) {
			request.setAttribute(REQUEST_PARAMETER_ERROR_MSG, e.getMessage());
		}
		return new FillResponseInfo(PAGE_ORDER_LIST_ADMIN);
	}

}