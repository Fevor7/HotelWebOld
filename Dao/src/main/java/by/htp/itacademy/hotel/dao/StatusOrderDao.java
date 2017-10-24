package by.htp.itacademy.hotel.dao;

import java.util.List;

import by.htp.itacademy.hotel.dao.exception.DaoException;
import by.htp.itacademy.hotel.domain.entity.Order;
import by.htp.itacademy.hotel.domain.entity.Unit;

public interface StatusOrderDao {

	/**
	 * The method gets the status id of the value from the database.
	 * 
	 * @param order
	 * @throws DaoException
	 */
	void fetchIdStatus(Order order) throws DaoException;

	/**
	 * Method returns a list of order stats from the database
	 * 
	 * @param langua
	 * @throws DaoException
	 */
	List<Unit> fetchStatusList() throws DaoException;

}
