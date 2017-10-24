package by.htp.itacademy.hotel.dao;

import by.htp.itacademy.hotel.dao.exception.DaoException;
import by.htp.itacademy.hotel.domain.entity.*;
import by.htp.itacademy.hotel.domain.vo.ListPage;

public interface RoomDao {

	/**
	 * The method returns a list of all rooms from the database.
	 * 
	 * @param language
	 * @param order
	 * 
	 * @return
	 */
	void fetchAllRoom(ListPage<Room> listPage) throws DaoException;

	/**
	 * The method returns a list of rooms corresponding to the parameters of the
	 * order from the database.
	 * 
	 * @param listPage
	 * 
	 * @param order
	 * @param language
	 * @return
	 */
	ListPage<Room> roomListSearch(ListPage<Room> listPage, Order order) throws DaoException;

	/**
	 * The method returns a list of numbers that match the order parameters from the
	 * database for the administrator.
	 * 
	 * @param listPage
	 * 
	 * @param order
	 * @param language
	 * @return
	 * @throws DaoException
	 */
	ListPage<Room> roomListSearchAdmin(ListPage<Room> listPage, Order order) throws DaoException;

	/**
	 * The method returns the Room object from the MySql database.
	 * 
	 * @param order
	 * @return
	 * @throws DaoException
	 */
	Room fetchRoom(Order order) throws DaoException;

}
