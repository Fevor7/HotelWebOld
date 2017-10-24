package by.htp.itacademy.hotel.service.factory;

import by.htp.itacademy.hotel.service.HotelService;
import by.htp.itacademy.hotel.service.OrderService;
import by.htp.itacademy.hotel.service.RoomService;
import by.htp.itacademy.hotel.service.UserService;
import by.htp.itacademy.hotel.service.impl.*;

/**
 * The class implements the Factory template, which provides links to the
 * Service layer objects
 * 
 * @author viktor
 *
 */
public class ServiceFactory {

	private static final ServiceFactory INSTANCE = new ServiceFactory();

	private ServiceFactory() {
	}

	public static ServiceFactory getInstance() {
		return INSTANCE;
	}

	public HotelService getHotelService() {
		return HotelServiceImpl.getInstance();
	}

	public UserService getUserService() {
		return UserServiceImpl.getInstance();
	}

	public OrderService getOrderService() {
		return OrderServiceImpl.getInstance();
	}

	public RoomService getRoomService() {
		return RoomServiceImpl.getInstance();
	}
}
