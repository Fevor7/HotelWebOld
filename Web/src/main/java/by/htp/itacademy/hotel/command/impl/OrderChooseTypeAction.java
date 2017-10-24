package by.htp.itacademy.hotel.command.impl;

import static by.htp.itacademy.hotel.util.AddressPage.*;
import static by.htp.itacademy.hotel.util.Parameter.RESPONSE_ERROR;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.htp.itacademy.hotel.command.Action;
import by.htp.itacademy.hotel.domain.entity.Unit;
import by.htp.itacademy.hotel.domain.vo.FillResponseInfo;
import by.htp.itacademy.hotel.service.OrderService;
import by.htp.itacademy.hotel.service.exception.ServiceException;
import by.htp.itacademy.hotel.service.factory.ServiceFactory;

/**
 * The object of this class returns the address of the page with the choice of
 * the type of order.
 * 
 * @author viktor
 *
 */
public class OrderChooseTypeAction extends AbstractAction implements Action {

	private static final String STATUS_LIST = "statusList";
	private static final OrderChooseTypeAction INSTANCE = new OrderChooseTypeAction();
	private OrderService orderService;
	
	/**
	 * In this constructor, I get a reference to the factory object. And from her I
	 * get a link to the class object of the level - the service.
	 */
	private OrderChooseTypeAction() {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		orderService = serviceFactory.getOrderService();
	}
	public static OrderChooseTypeAction getInstance() {
		return INSTANCE;
	}

	@Override
	public FillResponseInfo execute(HttpServletRequest request) {
		System.out.println("command pagechoosetypeorder");
		FillResponseInfo responseInfo = new FillResponseInfo(PAGE_ORDER_CHOOSE);
		String language = fetchLanguage(request);
		try {
			List<Unit> list = orderService.fetchStatusList(language);
			request.setAttribute(STATUS_LIST, list);
		} catch (ServiceException e) {
			responseInfo.changeField(RESPONSE_ERROR, true);
		}
		return responseInfo;
	}
	

}
