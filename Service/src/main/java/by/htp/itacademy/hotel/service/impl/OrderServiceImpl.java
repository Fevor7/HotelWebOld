package by.htp.itacademy.hotel.service.impl;

import by.htp.itacademy.hotel.dao.OrderDao;
import by.htp.itacademy.hotel.dao.RoomDao;
import by.htp.itacademy.hotel.dao.StatusOrderDao;
import by.htp.itacademy.hotel.dao.exception.DaoException;
import by.htp.itacademy.hotel.dao.factory.DaoFactory;
import by.htp.itacademy.hotel.domain.entity.Order;
import by.htp.itacademy.hotel.domain.entity.Room;
import by.htp.itacademy.hotel.domain.entity.Unit;
import by.htp.itacademy.hotel.domain.entity.User;
import by.htp.itacademy.hotel.domain.vo.ListPage;
import by.htp.itacademy.hotel.service.OrderService;
import by.htp.itacademy.hotel.service.exception.ServiceException;
import by.htp.itacademy.hotel.service.exception.ServiceNoOrderFoundException;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

/**
 * The class of this class is designed to execute business logic with order.
 * 
 * @author Viktor
 *
 */
public class OrderServiceImpl implements OrderService {

	private static final OrderService INSTANCE = new OrderServiceImpl();
	private static final Logger LOG = Logger.getLogger(OrderServiceImpl.class);
	private static final String LOG_ORDER_STATU_UPDATE = "ORDER STATUS UPDATE: ";
	private static final String LOG_ORDER_UPDATE = "ORDER UPDATE: ";
	private static final String LOG_ORDER_CREATE = "ORDER CREATE: ";
	private static final String LOG_ORDER_DELETE = "ORDER DELETE: ";
	private static final String LOG_ADMIN = " Admin ID: ";
	private static final String LOG_USER = " User ID: ";
	private static final String LOG_ERROR = " ERROR: ";

	private static final Long STATUS_WAIT =   1L;
	private static final Long STATUS_CONFIRMED = 2L;

	private static final String ERROR_ORDER_FOUND = "No orders found.";
	private static final String ERROR_ORDER = "You do not have orders.";
	private static final String STATUS_LIST_ERROR = "Could not get status list";
	private static final String PAGE_CONTENT = "pagecontent";

	private static final Integer NUMBER_SECOND_IN_A_DAY = 86400000;
	private static final Byte CORRECTION_FACTOR = 1;

	private StatusOrderDao statusOrderDao;
	private OrderDao orderDao;
	private RoomDao roomDao;

	/**
	 * In this constructor, I get references to a factory object that returns links
	 * to a DAO level object.
	 */
	private OrderServiceImpl() {
		DaoFactory daoFactory = DaoFactory.getInstance();
		statusOrderDao = daoFactory.getStatusOrderDao();
		orderDao = daoFactory.getOrderDao();
		roomDao = daoFactory.getRoomDaoImpl();

	}

	public static OrderService getInstance() {
		return INSTANCE;
	}

	@Override
	public void updateOrder(Order order, User user) throws ServiceException {
		try {
			orderDao.updateOrder(order);
			LOG.info(LOG_ORDER_UPDATE + order + LOG_USER + user.getId());
		} catch (DaoException e) {
			LOG.error(LOG_ERROR + e.getMessage() + LOG_USER + user.getId());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void updateOrderAdmin(Order order, User user) throws ServiceException {
		try {
			statusUpdate(order);
			orderDao.updateOrderAdmin(order);
			LOG.info(LOG_ORDER_UPDATE + order + LOG_ADMIN + user.getId());
		} catch (DaoException e) {
			LOG.error(LOG_ERROR + e.getMessage() + LOG_USER + user.getId());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void createOrder(Order order, User user) throws ServiceException {
		try {
			orderDao.createOrder(order);
			LOG.info(LOG_ORDER_CREATE + order + LOG_USER + user.getId());
		} catch (DaoException e) {
			LOG.error(LOG_ERROR + e.getMessage() + LOG_USER + user.getId());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public ListPage<Order> orderListAll(ListPage<Order> listPage, User user, String language)
			throws ServiceNoOrderFoundException {
		try {
			listPage = orderDao.fetchAllOrder(listPage);
			loadingDundle(listPage.getData(), language);
		} catch (DaoException e) {
			LOG.error(LOG_ERROR + e.getMessage() + LOG_USER + user.getId());
		}
		if (listPage.getData().isEmpty()) {
			throw new ServiceNoOrderFoundException(ERROR_ORDER_FOUND);
		}
		return listPage;
	}

	@Override
	public ListPage<Order> orderListUser(ListPage<Order> listPage, User user, String language)
			throws ServiceNoOrderFoundException {
		try {
			listPage = orderDao.fetchUserOrder(listPage, user);
			loadingDundle(listPage.getData(), language);
		} catch (DaoException e) {
			LOG.error(LOG_ERROR + e.getMessage() + LOG_USER + user.getId());
		}
		if (listPage.getData().isEmpty()) {
			throw new ServiceNoOrderFoundException(ERROR_ORDER);
		}
		return listPage;
	}

	@Override
	public ListPage<Order> orderList(ListPage<Order> listPage, Order order, User user, String language)
			throws ServiceNoOrderFoundException {
		try {
			listPage = orderDao.fetchSomethingOrder(listPage, order);
			loadingDundle(listPage.getData(), language);
		} catch (DaoException e) {
			LOG.error(LOG_ERROR + e.getMessage() + LOG_USER + user.getId());
		}
		if (listPage.getData().isEmpty()) {
			throw new ServiceNoOrderFoundException(ERROR_ORDER_FOUND);
		}
		return listPage;
	}

	@Override
	public void orderDelete(Order order, User user) throws ServiceException {
		try {
			orderDao.orderDelete(order);
			LOG.info(LOG_ORDER_DELETE + order + LOG_USER + user.getId());
		} catch (DaoException e) {
			LOG.error(LOG_ERROR + e.getMessage() + LOG_USER + user.getId());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void updateStatusOrder(Order order, User user) throws ServiceException {
		try {
			statusOrderDao.fetchIdStatus(order);
			orderDao.updateOrderStatus(order);
			LOG.info(LOG_ORDER_STATU_UPDATE + order + LOG_USER + user.getId());
		} catch (DaoException e) {
			LOG.error(LOG_ERROR + e.getMessage() + LOG_USER + user.getId());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<Unit> fetchStatusList(String language) throws ServiceException {
		List<Unit> list = null;
		try {
			list = statusOrderDao.fetchStatusList();
			setValue(list,language);
		} catch (DaoException e) {
			LOG.error(LOG_ERROR + e.getMessage());
		}
		if (list == null) {
			throw new ServiceException(STATUS_LIST_ERROR);
		}
		return list;
	}

	/**
	 * Method for updating the order status.
	 * 
	 * @param order
	 * @throws DaoException
	 */
	private void statusUpdate(Order order) throws DaoException {
		if ((order.getRoom().getId()!= 0) && (order.getOrderStatus().getId() == 1)) {
			BigDecimal totalAmount = calculationTotalCost(order);
			if (orderDao.fetchStatusOrder(order).equals(STATUS_WAIT)) {
				order.getOrderStatus().setId(STATUS_CONFIRMED);
			}
			order.setTotalAmount(totalAmount);
		}
	}

	/**
	 * The method calculates the total value of the order.
	 * 
	 * @param order
	 * @return
	 * @throws DaoException
	 */
	private BigDecimal calculationTotalCost(Order order) throws DaoException {
		Room room = roomDao.fetchRoom(order);
		BigDecimal totalCost = null;
		if (room != null) {
			BigDecimal roomPrice = room.getPrice();
			BigDecimal numberDays = calculationNumberDays(order);
			totalCost = roomPrice.multiply(numberDays);
			order.getTypeRoom().setId(room.getTypeRoom().getId());
		}
		return totalCost;
	}

	/**
	 * The method calculates the number of nights in a hotel.
	 * 
	 * @param order
	 * @return
	 */
	private BigDecimal calculationNumberDays(Order order) {
		Date dateStart = order.getDateStart();
		Date dateEnd = order.getDateEnd();
		Long differenceDay = (dateEnd.getTime() - dateStart.getTime()) / NUMBER_SECOND_IN_A_DAY + CORRECTION_FACTOR;
		BigDecimal numberDays = new BigDecimal(differenceDay);
		return numberDays;
	}
	
	private void loadingDundle(List<Order> list, String language) {
		Locale currentLocale = new Locale(language);
		ResourceBundle bundle = ResourceBundle.getBundle(PAGE_CONTENT, currentLocale);
		for (int i = 0; i < list.size(); i++) {
			Order order = list.get(i);
			String status = order.getOrderStatus().getValue();
			String typeRoom = order.getTypeRoom().getValue();
			order.getOrderStatus().setValue(bundle.getString(status));
			order.getTypeRoom().setValue(bundle.getString(typeRoom));
		} 
		
		
	}

	private void setValue(List<Unit> list, String language) {
		Locale currentLocale = new Locale(language);
		ResourceBundle bundle = ResourceBundle.getBundle(PAGE_CONTENT, currentLocale);
		for (int i = 0; i < list.size(); i++) {
			String value = list.get(i).getValue();
			list.get(i).setValue(bundle.getString(value));
		}
	}
	
}
