package by.htp.itacademy.hotel.service.impl;

import by.htp.itacademy.hotel.dao.UserDao;
import by.htp.itacademy.hotel.dao.exception.DaoException;
import by.htp.itacademy.hotel.dao.factory.DaoFactory;
import by.htp.itacademy.hotel.domain.entity.User;
import by.htp.itacademy.hotel.service.UserService;
import by.htp.itacademy.hotel.service.exception.ServiceException;
import by.htp.itacademy.hotel.service.exception.ServiceNoSuchUserException;

import org.apache.log4j.Logger;

/**
 * The class of this class is designed to execute business logic with user.
 * 
 * @author viktor
 *
 */
public class UserServiceImpl implements UserService {

	private static final UserService INSTANCE = new UserServiceImpl();
	private static final Logger LOG = Logger.getLogger(OrderServiceImpl.class);
	private static final String LOG_ERROR = " ERROR: ";
	private static final String LOG_USER_SIGNUP = "USER SIGNUP. Login: ";
	private static final String LOG_USER_LOGIN = "USER AUTHORIZED, id: ";
	private static final String ACCESS_USER_EXCEPTION = "Access is denied";
	private UserDao dao;

	/**
	 * In this constructor, I get references to a factory object that returns links
	 * to a DAO level object.
	 */
	private UserServiceImpl() {
		DaoFactory daoFactory = DaoFactory.getInstance();
		dao = daoFactory.getUserDao();
	}

	public static UserService getInstance() {
		return INSTANCE;
	}

	@Override
	public void logIn(User user) throws ServiceNoSuchUserException {
		try {
			dao.logIn(user);
		} catch (DaoException e) {
			LOG.error(LOG_ERROR + e.getMessage());
		}
		if (user.getLogin() == null) {
			throw new ServiceNoSuchUserException(ACCESS_USER_EXCEPTION);
		} else {
			LOG.info(LOG_USER_LOGIN + user.getId());
		}
	}

	@Override
	public void signUp(User user) throws ServiceException {
		try {
			dao.createUser(user);
			LOG.info(LOG_USER_SIGNUP + user.getLogin());
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void personalInfo(User user) throws ServiceNoSuchUserException {
		try {
			dao.personalInfo(user);
		} catch (DaoException e) {
			LOG.error(LOG_ERROR + e.getMessage());
		}
		if (user == null) {
			throw new ServiceNoSuchUserException(ACCESS_USER_EXCEPTION);
		}
	}

}
