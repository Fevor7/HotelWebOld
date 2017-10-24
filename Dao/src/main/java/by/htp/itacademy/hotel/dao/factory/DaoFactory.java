package by.htp.itacademy.hotel.dao.factory;

import by.htp.itacademy.hotel.dao.*;
import by.htp.itacademy.hotel.dao.impl.*;

/**
 * This class implements the Factory template. The methods of this class return
 * references to objects. Which work with the data store.
 * 
 * @author Viktor
 *
 */
public class DaoFactory {

	private static final DaoFactory INSTANCE = new DaoFactory();

	private DaoFactory() {
	}

	public static DaoFactory getInstance() {
		return INSTANCE;
	}

	public UserDao getUserDao() {
		return UserDaoImpl.getInstance();
	}

	public OrderDao getOrderDao() {
		return OrderDaoImpl.getInstance();
	}

	public HotelDao getHotelDao() {
		return HotelDaoImpl.getInstance();
	}

	public FacilitiesHotelDao getFacilitiesHotelDao() {
		return FacilitiesHotelDaoImpl.getInstance();
	}

	public FotoHotelDao getFotoHotelDao() {
		return FotoHotelDaoImpl.getInstance();
	}

	public RoomDao getRoomDaoImpl() {
		return RoomDaoImpl.getInstance();
	}

	public TypeRoomDao getTypeRoomDaoImpl() {
		return TypeRoomDaoImpl.getInstance();
	}

	public StatusOrderDao getStatusOrderDao() {
		return StatusOrderDaoImpl.getInstance();
	}
}
