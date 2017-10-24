package by.htp.itacademy.hotel.dao;

import by.htp.itacademy.hotel.dao.exception.DaoException;
import by.htp.itacademy.hotel.domain.entity.User;

public interface UserDao {
	/**
	 * The method that organizes access
	 * 
	 * @param user
	 * @return
	 */
	void logIn(User user) throws DaoException;

	/**
	 * Write a new user to the database
	 * 
	 * @param sqlRequest
	 * @return
	 */
	void createUser(User user) throws DaoException;

	/**
	 * Receiving personal user data from the database.
	 * 
	 * @param user
	 * @return
	 * @throws ServiceNoSuchUserException
	 */
	void personalInfo(User user) throws DaoException;
}
