package by.htp.itacademy.hotel.service;

import java.util.List;

import by.htp.itacademy.hotel.domain.entity.Order;
import by.htp.itacademy.hotel.domain.entity.Unit;
import by.htp.itacademy.hotel.domain.entity.User;
import by.htp.itacademy.hotel.domain.vo.ListPage;
import by.htp.itacademy.hotel.service.exception.ServiceException;
import by.htp.itacademy.hotel.service.exception.ServiceNoOrderFoundException;

public interface OrderService {

	/**
	 * A method that compiles a query string to create an order in the database
	 * 
	 * @param order
	 * @param user
	 * @return
	 */
	void createOrder(Order order, User user) throws ServiceException;

	/**
	 * A service method that forms a query string into a database to obtain a list
	 * of orders
	 * 
	 * @param listPage
	 * 
	 * @param order
	 * @param user
	 * 
	 * @return
	 */
	ListPage<Order> orderList(ListPage<Order> listPage, Order order, User user, String language)
			throws ServiceNoOrderFoundException;

	/**
	 * The method returns a list of all orders.
	 * 
	 * @param listPage
	 * 
	 * @param user
	 * @param language
	 * @return
	 * @throws ServiceNoOrderFoundException
	 */

	ListPage<Order> orderListAll(ListPage<Order> listPage, User user, String language)
			throws ServiceNoOrderFoundException;

	/**
	 * The method returns a list of user orders.
	 * 
	 * @param listPage
	 * 
	 * @param user
	 * @param language
	 * @return
	 * @throws ServiceNoOrderFoundException
	 */
	ListPage<Order> orderListUser(ListPage<Order> listPage, User user, String language)
			throws ServiceNoOrderFoundException;

	/**
	 * The method deletes the order.
	 * 
	 * @param order
	 * @param user
	 * @throws ServiceException
	 */
	void orderDelete(Order order, User user) throws ServiceException;

	/**
	 * The method updates the user's order.
	 * 
	 * @param order
	 * @param user
	 * @throws ServiceException
	 */
	void updateOrder(Order order, User user) throws ServiceException;

	/**
	 * The method updates the order fields by the administrator.
	 * 
	 * @param order
	 * @param user
	 * @throws ServiceException
	 */
	void updateOrderAdmin(Order order, User user) throws ServiceException;

	/**
	 * Update the status of the order.
	 * 
	 * @param order
	 * @param idOrder
	 * @throws ServiceException
	 */
	void updateStatusOrder(Order order, User user) throws ServiceException;

	/**
	 * The method returns a list of stats orders
	 * 
	 * @param language
	 * @return
	 * @throws ServiceException
	 */
	List<Unit> fetchStatusList(String language) throws ServiceException;

}
