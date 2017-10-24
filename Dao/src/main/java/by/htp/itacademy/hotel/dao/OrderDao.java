package by.htp.itacademy.hotel.dao;

import by.htp.itacademy.hotel.dao.exception.DaoException;
import by.htp.itacademy.hotel.domain.entity.Order;
import by.htp.itacademy.hotel.domain.entity.User;
import by.htp.itacademy.hotel.domain.vo.ListPage;

public interface OrderDao {

	/**
	 * A method that ensures the creation of a new order in the database.
	 * 
	 * @param sqlRequest
	 * @return
	 */
	void createOrder(Order order) throws DaoException;

	/**
	 * The method returns a list of all orders.
	 * 
	 * @param listPage
	 * @param language
	 * 
	 * @param order
	 * 
	 * @param sqlRequest
	 * @return
	 */
	ListPage<Order> fetchAllOrder(ListPage<Order> listPage) throws DaoException;

	/**
	 * The method that returns the list of orders received from the database.
	 * 
	 * @param listPage
	 * 
	 * @param order
	 * 
	 * @param sqlRequest
	 * @return
	 */
	ListPage<Order> fetchSomethingOrder(ListPage<Order> listPage, Order order) throws DaoException;

	/**
	 * The method returns a list of user orders read from the database.
	 * 
	 * @param listPage
	 * 
	 * @param user
	 * @param language
	 * @return
	 */
	ListPage<Order> fetchUserOrder(ListPage<Order> listPage, User user) throws DaoException;

	/**
	 * The method deletes the order from the database.
	 * 
	 * @param order
	 * @throws DaoException
	 */
	void orderDelete(Order order) throws DaoException;

	/**
	 * The method saves the change in the warrant made by the user.
	 * 
	 * @param order
	 * @throws DaoException
	 */
	void updateOrder(Order order) throws DaoException;

	/**
	 * The method saves the change in the warrant made by the administrator.
	 * 
	 * @param order
	 * @throws DaoException
	 */
	void updateOrderAdmin(Order order) throws DaoException;

	/**
	 * Method for updating the order status.
	 * 
	 * @param order
	 * @throws DaoException
	 */
	void updateOrderStatus(Order order) throws DaoException;

	/**
	 * The method gets the status id of the id order from the database.
	 * 
	 * @param order
	 * 
	 * @return
	 * @throws DaoException
	 */
	Long fetchStatusOrder(Order order) throws DaoException;

}
