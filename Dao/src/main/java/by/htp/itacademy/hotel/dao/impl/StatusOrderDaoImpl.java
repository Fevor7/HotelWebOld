package by.htp.itacademy.hotel.dao.impl;

import static by.htp.itacademy.hotel.util.SqlCommand.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.htp.itacademy.hotel.dao.StatusOrderDao;
import by.htp.itacademy.hotel.dao.databaseconnect.ConnectionDataBase;
import by.htp.itacademy.hotel.dao.exception.DaoException;
import by.htp.itacademy.hotel.domain.entity.Order;
import by.htp.itacademy.hotel.domain.entity.Unit;

/**
 * The object of this class works with the table - status_order in the MySql
 * database.
 * 
 * @author Viktor
 *
 */
public class StatusOrderDaoImpl implements StatusOrderDao {

	private static final String READ_ID_STATUS_EXCEPTION = "Failed to get status id from MySql database.";
	private static final String READ_STATUS_EXCEPTION = "Failed to get status from MySql database.";
	private static final StatusOrderDao INSTANCE = new StatusOrderDaoImpl();
	private ConnectionDataBase connectionDataBase;
	private Connection connection;
	private PreparedStatement ps;

	/**
	 * In this constructor, I get a reference to the connection class object to the
	 * MySQL database.
	 */
	private StatusOrderDaoImpl() {
		connectionDataBase = ConnectionDataBase.getInstance();
	}

	public static StatusOrderDao getInstance() {
		return INSTANCE;
	}

	@Override
	public void fetchIdStatus(Order order) throws DaoException {
		System.out.println("Dao fetchIdStatus");
		ResultSet rs = null;
		Long idStatus = null;
		try {
			ps = connectionDataBase.getConnection().prepareStatement(SQL_SELECT_ID_STATUS_ORDER);
			ps.setString(1, order.getOrderStatus().getValue());
			rs = ps.executeQuery();
			idStatus = readIdResult(rs);
		} catch (SQLException e) {
			throw new DaoException(READ_ID_STATUS_EXCEPTION + e.getMessage());
		} finally {
			connectionDataBase.closeConnection(connection, ps, rs);
		}
		order.getOrderStatus().setId(idStatus);
	}

	@Override
	public List<Unit> fetchStatusList() throws DaoException {
		System.out.println("Dao fetchStatusList");
		ResultSet rs = null;
		List<Unit> list = null;
		try {
			connection = connectionDataBase.getConnection();
			ps = connection.prepareStatement(SQL_SELECT_STATUS_ORDER);
			rs = ps.executeQuery();
			list = readResultListStatus(rs);
		} catch (SQLException e) {
			throw new DaoException(READ_STATUS_EXCEPTION + e.getMessage());
		} finally {
			connectionDataBase.closeConnection(connection, ps, rs);
		}
		return list;
	}

	/**
	 * The method processes the database response in which there is an id of the
	 * status.
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Long readIdResult(ResultSet rs) throws SQLException {
		Long result = null;
		if (rs.next()) {
			result = rs.getLong(1);
		}
		return result;
	}

	/**
	 * The method processes the database response in which there is a list of
	 * status.
	 * 
	 * @param rs
	 * @param list
	 * @throws SQLException
	 */
	private List<Unit> readResultListStatus(ResultSet rs) throws SQLException {
		List<Unit> list = new ArrayList<Unit>();
		while (rs.next()) {
			Unit status = new Unit();
			status.setId(rs.getLong(1));
			status.setValue(rs.getString(2));
			list.add(status);
		}
		return list;
	}

}
