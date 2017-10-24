package by.htp.itacademy.hotel.service;

import by.htp.itacademy.hotel.domain.entity.User;
import by.htp.itacademy.hotel.service.exception.ServiceException;
import by.htp.itacademy.hotel.service.exception.ServiceNoSuchUserException;

public interface UserService {

	/**
	 * Forming a query string in the database for user authentication. Creation of
	 * an object User.
	 * 
	 * @param user
	 * @return
	 * @throws ServiceNoSuchUserException
	 */
	void logIn(User user) throws ServiceNoSuchUserException;

	/**
	 * Forming a query string in the database to create a new user.
	 * 
	 * @param user
	 * @return
	 */
	void signUp(User user) throws ServiceException;

	/**
	 * Formation of a query string to the database for obtaining personal data.
	 * 
	 * @param user
	 * @return
	 */
	void personalInfo(User user) throws ServiceNoSuchUserException;

}
