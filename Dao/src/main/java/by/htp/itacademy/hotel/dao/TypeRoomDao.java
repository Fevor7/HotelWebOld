package by.htp.itacademy.hotel.dao;

import java.util.List;

import by.htp.itacademy.hotel.dao.exception.DaoException;
import by.htp.itacademy.hotel.domain.entity.Order;
import by.htp.itacademy.hotel.domain.entity.Unit;

public interface TypeRoomDao {

	/**
	 * The method returns the list of the room type from the database.
	 * 
	 * @param language
	 * 
	 * @param typeRoom
	 * @return
	 * @throws DaoException
	 */
	List<Unit> fetchAllTypeRoom() throws DaoException;

	/**
	 * The method returns the id of the room type by the name of the type from the
	 * database.
	 * 
	 * @param order
	 * @throws DaoException
	 */
	void fetchIdTypeRoom(Order order) throws DaoException;
}
