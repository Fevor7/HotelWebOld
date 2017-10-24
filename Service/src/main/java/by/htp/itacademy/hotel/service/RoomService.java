package by.htp.itacademy.hotel.service;

import java.util.List;

import by.htp.itacademy.hotel.domain.entity.Order;
import by.htp.itacademy.hotel.domain.entity.Room;
import by.htp.itacademy.hotel.domain.entity.Unit;
import by.htp.itacademy.hotel.domain.vo.ListPage;
import by.htp.itacademy.hotel.service.exception.ServiceException;
import by.htp.itacademy.hotel.service.exception.ServiceNoRoomFoundException;

public interface RoomService {

	/**
	 * The method takes a list of all rooms in the hotel.
	 * 
	 * @param listPage
	 * @param language
	 * @param order
	 * 
	 * @return
	 * @throws ServiceNoRoomFoundException
	 */
	ListPage<Room> roomList(ListPage<Room> listPage, String language) throws ServiceNoRoomFoundException;

	/**
	 * The method returns a list of rooms found by parameters.
	 * 
	 * @param listPage
	 * @param order
	 * @param language
	 * @return
	 * @throws ServiceNoRoomFoundException
	 */
	ListPage<Room> searchRoom(ListPage<Room> listPage, Order order, String language) throws ServiceNoRoomFoundException;

	/**
	 * The method returns a list of rooms found by parameters for the administrator.
	 * 
	 * @param listPage
	 * 
	 * @param order
	 * @param language
	 * @return
	 * @throws ServiceNoRoomFoundException
	 */
	ListPage<Room> searchRoomAdmin(ListPage<Room> listPage, Order order, String language)
			throws ServiceNoRoomFoundException;

	/**
	 * The method returns a list of room types in the hotel.
	 * 
	 * @param language
	 * 
	 * @return
	 * @throws ServiceException
	 */
	List<Unit> typeRoomList(String language) throws ServiceException;

}
