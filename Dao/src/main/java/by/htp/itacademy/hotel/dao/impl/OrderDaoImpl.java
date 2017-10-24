package by.htp.itacademy.hotel.dao.impl;

import static by.htp.itacademy.hotel.util.SqlCommand.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import by.htp.itacademy.hotel.dao.OrderDao;
import by.htp.itacademy.hotel.dao.databaseconnect.ConnectionDataBase;
import by.htp.itacademy.hotel.dao.exception.DaoException;
import by.htp.itacademy.hotel.domain.entity.Order;
import by.htp.itacademy.hotel.domain.entity.User;
import by.htp.itacademy.hotel.domain.vo.ListPage;

/**
 * The object of this class performs actions with the table of orders in the
 * database.
 * 
 * @author Viktor
 *
 */
public class OrderDaoImpl implements OrderDao {

	private static final String CREATE_ORDER_EXCEPTION = "Failed to create a new order in MySql database.";
	private static final String DELETE_ORDER_EXCEPTION = "It was not possible to remove the order from the MySql database.";
	private static final String LIST_ORDER_EXCEPTION = "Failed to get list of orders from MySql database.";
	private static final String UPDATE_ORDER_EXCEPTION = "The order could not be changed.";
	private static final String UPDATE_ORDER_STATUS_EXCEPTION = "Could not change the order status in the database.";
	private static final OrderDao INSTANCE = new OrderDaoImpl();
	private ConnectionDataBase connectionDataBase;
	private Connection connection;
	private PreparedStatement ps;

	/**
	 * In this constructor, I get a reference to the connection class object to the
	 * MySQL database.
	 */
	private OrderDaoImpl() {
		connectionDataBase = ConnectionDataBase.getInstance();
	}

	public static OrderDao getInstance() {
		return INSTANCE;
	}

	@Override
	public void updateOrder(Order order) throws DaoException {
		System.out.println("Dao updateOrder");
		try {
			connection = connectionDataBase.getConnection();
			ps = connection.prepareStatement(SQL_UPDATE_ORDER);
			queryUpdateOrder(ps, order);
		} catch (SQLException e) {
			throw new DaoException(UPDATE_ORDER_EXCEPTION + e.getMessage());
		} finally {
			connectionDataBase.closeConnection(connection, ps);
		}

	}

	@Override
	public void updateOrderAdmin(Order order) throws DaoException {
		System.out.println("Dao updateOrderAdmin");
		try {
			connection = connectionDataBase.getConnection();
			ps = connection.prepareStatement(SQL_UPDATE_ORDER_ADMIN);
			queryUpdateOrderAdmin(ps, order);
		} catch (SQLException e) {
			throw new DaoException(UPDATE_ORDER_EXCEPTION + e.getMessage());
		} finally {
			connectionDataBase.closeConnection(connection, ps);
		}

	}

	@Override
	public void orderDelete(Order order) throws DaoException {
		System.out.println("Dao orderDelete");
		try {
			connection = connectionDataBase.getConnection();
			ps = connection.prepareStatement(SQL_DELETE_ORDER);
			ps.setLong(1, order.getOrderId());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(DELETE_ORDER_EXCEPTION + e.getMessage());
		} finally {
			connectionDataBase.closeConnection(connection, ps);
		}

	}

	@Override
	public void createOrder(Order order) throws DaoException {
		System.out.println("Dao createOrder");
		try {
			connection = connectionDataBase.getConnection();
			ps = connection.prepareStatement(SQL_INSERT_ORDER);
			queryNewOrder(ps, order);
		} catch (SQLException e) {
			throw new DaoException(CREATE_ORDER_EXCEPTION + e.getMessage());
		} finally {
			connectionDataBase.closeConnection(connection, ps);
		}
	}

	@Override
	public ListPage<Order> fetchAllOrder(ListPage<Order> listPage) throws DaoException {
		System.out.println("Dao fetchAllOrder");
		ResultSet rs = null;
		Long totalPage = listPage.getMaxPerPage() * listPage.getPage();
		try {
			connection = connectionDataBase.getConnection();
			ps = connection.prepareStatement(SQL_SELECT_ORDER_LIST_ALL);
			ps.setLong(1, totalPage);
			rs = ps.executeQuery();
			listPage = readQuery(listPage, rs);
		} catch (SQLException e) {
			throw new DaoException(LIST_ORDER_EXCEPTION + e.getMessage());
		} finally {
			connectionDataBase.closeConnection(connection, ps, rs);
		}
		return fetchSizeList(listPage);
	}

	@Override
	public ListPage<Order> fetchSomethingOrder(ListPage<Order> listPage, Order order)
			throws DaoException {
		System.out.println("Dao fetchSomethingOrder");
		ResultSet rs = null;
		Long totalPage = listPage.getMaxPerPage() * listPage.getPage();
		try {
			connection = connectionDataBase.getConnection();
			ps = connection.prepareStatement(SQL_SELECT_ORDER_LIST);
			rs = sendQuarySomethingOrder(ps, order, totalPage);
			listPage = readQuery(listPage, rs);
		} catch (SQLException e) {
			throw new DaoException(LIST_ORDER_EXCEPTION + e.getMessage());
		} finally {
			connectionDataBase.closeConnection(connection, ps, rs);
		}
		return fetchSizeList(listPage);
	}

	@Override
	public ListPage<Order> fetchUserOrder(ListPage<Order> listPage, User user) throws DaoException {
		System.out.println("Dao fetchUserOrder");
		ResultSet rs = null;
		try {
			connection = connectionDataBase.getConnection();
			ps = connection.prepareStatement(SQL_SELECT_ORDER_LIST_USER);
			rs = sendQuaryUserOrder(ps, user, listPage);
			listPage = readQuery(listPage, rs);
		} catch (SQLException e) {
			throw new DaoException(LIST_ORDER_EXCEPTION + e.getMessage());
		} finally {
			connectionDataBase.closeConnection(connection, ps, rs);
		}
		return fetchSizeList(listPage);
	}

	@Override
	public void updateOrderStatus(Order order) throws DaoException {
		System.out.println("Dao updateOrderStatus");
		try {
			connection = connectionDataBase.getConnection();
			ps = connection.prepareStatement(SQL_UPDATE_ORDER_STATUS);
			queryUpdateOrderStatus(ps, order);
		} catch (SQLException e) {
			throw new DaoException(UPDATE_ORDER_STATUS_EXCEPTION + e.getMessage());
		} finally {
			connectionDataBase.closeConnection(connection, ps);
		}
	}

	@Override
	public Long fetchStatusOrder(Order order) throws DaoException {
		System.out.println("Dao fetchStatusOrder");
		ResultSet rs = null;
		Long idStatus = null;
		try {
			connection = connectionDataBase.getConnection();
			ps = connection.prepareStatement(SQL_SELECT_STATUS);
			ps.setLong(1, order.getOrderId());
			rs = ps.executeQuery();
			idStatus = rs.next() ? rs.getLong(1) : null;
		} catch (SQLException e) {
			throw new DaoException(LIST_ORDER_EXCEPTION + e.getMessage());
		} finally {
			connectionDataBase.closeConnection(connection, ps, rs);
		}
		return idStatus;
	}

	/**
	 * Method of parsing the string query to update the status of the order in the
	 * database.
	 * 
	 * @param ps
	 * @param order
	 * @throws SQLException
	 */
	private void queryUpdateOrderStatus(PreparedStatement ps, Order order) throws SQLException {
		ps.setLong(1, order.getOrderStatus().getId());
		ps.setLong(2, order.getOrderId());
		ps.executeUpdate();
	}

	/**
	 * The method forms a query string from the fields of the order object.
	 * 
	 * @param ps
	 * @param order
	 * @throws SQLException
	 */
	private void queryNewOrder(PreparedStatement ps, Order order) throws SQLException {
		ps.setLong(1, order.getUser().getId());
		ps.setDate(2, order.getDateStart());
		ps.setDate(3, order.getDateEnd());
		ps.setByte(4, order.getBedNumber());
		ps.setByte(5, order.getPersonNumber());
		ps.setLong(6, order.getTypeRoom().getId());
		ps.setLong(7, order.getOrderStatus().getId());
		ps.executeUpdate();
	}

	/**
	 * This method reads the values of the order fields from the database response
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Order readQueryOrder(ResultSet rs) throws SQLException {
		Long id = rs.getLong(1);
		Long numberRoom = rs.getLong(2);
		String userLogin = rs.getString(3);
		Date dateStrart = rs.getDate(4);
		Date dateEnd = rs.getDate(5);
		Byte bed = rs.getByte(6);
		Byte person = rs.getByte(7);
		String typeRoom = rs.getString(8);
		Long idTypeRoom = rs.getLong(9);
		BigDecimal totalAmount = rs.getBigDecimal(10);
		String status = rs.getString(11);
		Long idStatus = rs.getLong(12);
		Order order = new Order(id, numberRoom, userLogin, dateStrart, dateEnd, bed, person, typeRoom, idTypeRoom,
				totalAmount, status, idStatus);
		return order;
	}

	/**
	 * The method reads user orders from the database
	 * 
	 * @param listPage
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private ListPage<Order> readQuery(ListPage<Order> listPage, ResultSet rs) throws SQLException {
		List<Order> list = new ArrayList<Order>();
		while (rs.next()) {
			Order order = readQueryOrder(rs);
			list.add(order);
		}
		listPage.setData(list);
		return listPage;
	}

	/**
	 * The method sends the request to the database to obtain a list of user orders.
	 * 
	 * @param ps
	 * @param user
	 * @param totalPage
	 * @throws SQLException
	 */
	private ResultSet sendQuaryUserOrder(PreparedStatement ps, User user, ListPage<Order> listPage) throws SQLException {
		Long totalPage = listPage.getMaxPerPage() * listPage.getPage();
		ps.setLong(1, user.getId());
		ps.setLong(2, totalPage);
		return ps.executeQuery();
	}

	/**
	 * The method sends a request to the database to obtain a list of orders with a
	 * specific status.
	 * 
	 * @param connection
	 * @param user
	 * @param totalPage
	 * @throws SQLException
	 */
	private ResultSet sendQuarySomethingOrder(PreparedStatement ps, Order order, Long totalPage) throws SQLException {
		ps.setLong(1, order.getOrderStatus().getId());
		ps.setLong(2, totalPage);
		return ps.executeQuery();
	}

	/**
	 * The method forms the term of the request to the database to update the value
	 * of the order by the user.
	 * 
	 * @param ps
	 * @param order
	 * @throws SQLException
	 */
	private void queryUpdateOrder(PreparedStatement ps, Order order) throws SQLException {
		ps.setDate(1, order.getDateStart());
		ps.setDate(2, order.getDateEnd());
		ps.setByte(3, order.getBedNumber());
		ps.setByte(4, order.getPersonNumber());
		ps.setLong(5, order.getTypeRoom().getId());
		ps.setLong(6, order.getOrderId());
		ps.executeUpdate();
	}

	/**
	 * The method generates a query term in the database to update the order value
	 * by the administrator.
	 * 
	 * @param ps
	 * @param order
	 * @throws SQLException
	 */
	private void queryUpdateOrderAdmin(PreparedStatement ps, Order order) throws SQLException {
		ps.setDate(1, order.getDateStart());
		ps.setDate(2, order.getDateEnd());
		ps.setByte(3, order.getBedNumber());
		ps.setByte(4, order.getPersonNumber());
		ps.setLong(5, order.getTypeRoom().getId());
		ps.setBigDecimal(6, order.getTotalAmount());
		ps.setLong(7, order.getOrderStatus().getId());
		ps.setLong(8, order.getRoom().getId());
		ps.setLong(9, order.getOrderId());
		ps.executeUpdate();
	}

	/**
	 * The method fetches the number of lines from the database.
	 * 
	 * @param connection
	 * @return
	 * @throws DaoException
	 */
	private ListPage<Order> fetchSizeList(ListPage<Order> listPage) throws DaoException {
		ResultSet rs = null;
		try {
			connection = connectionDataBase.getConnection();
			ps = connection.prepareStatement(SQL_SIZE);
			rs = ps.executeQuery();
			if (rs.next()) {
				listPage.setTotal(rs.getLong(1));
			}
		} catch (SQLException e) {
			throw new DaoException(LIST_ORDER_EXCEPTION + e.getMessage());
		} finally {
			connectionDataBase.closeConnection(connection, ps, rs);
		}
		return listPage;
	}

}
