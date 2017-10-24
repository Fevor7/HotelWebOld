package by.htp.itacademy.hotel.dao.impl;

import static by.htp.itacademy.hotel.util.SqlCommand.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.htp.itacademy.hotel.dao.UserDao;
import by.htp.itacademy.hotel.dao.databaseconnect.ConnectionDataBase;
import by.htp.itacademy.hotel.dao.exception.DaoException;
import by.htp.itacademy.hotel.domain.entity.User;

/**
 * The object of this class sends a database query and processes the result. In
 * order to check the presence of a client with specific identification data.
 * 
 * @author Viktor
 *
 */
public class UserDaoImpl implements UserDao {

	private static final String LIST_USER_EXCEPTION = "Could not get a list of users from the MySql database. ";
	private static final String CREATE_USER_EXCEPTION = "Failed to create a new user in the MySql database. ";
	private static final UserDao INSTANCE = new UserDaoImpl();
	private ConnectionDataBase connectionDataBase;
	private Connection connection;
	private PreparedStatement ps;

	/**
	 * In this constructor, I get a reference to the connection class object to the
	 * MySQL database.
	 */
	private UserDaoImpl() {
		connectionDataBase = ConnectionDataBase.getInstance();
	}

	public static UserDao getInstance() {
		return INSTANCE;
	}

	@Override
	public void logIn(User user) throws DaoException {
		System.out.println("Dao logIn");
		ResultSet rs = null;
		try {
			connection = connectionDataBase.getConnection();
			ps = queryUserLogin(connection, user);
			rs = ps.executeQuery();
			if (rs.next()) {
				readQueryUser(rs, user);
			}
		} catch (SQLException e) {
			throw new DaoException(LIST_USER_EXCEPTION + e.getMessage());
		} finally {
			connectionDataBase.closeConnection(connection, ps, rs);
		}
	}

	@Override
	public void createUser(User user) throws DaoException {
		System.out.println("Dao createUser");
		try {
			connection = connectionDataBase.getConnection();
			ps = connection.prepareStatement(SQL_INSERT_USER);
			writeQueryUser(ps, user);
		} catch (SQLException e) {
			throw new DaoException(CREATE_USER_EXCEPTION + e.getMessage());
		} finally {
			connectionDataBase.closeConnection(connection, ps);
		}
	}

	@Override
	public void personalInfo(User user) throws DaoException {
		System.out.println("Dao personalInfo");
		ResultSet rs = null;
		try {
			connection = connectionDataBase.getConnection();
			ps = connection.prepareStatement(SQL_SELECT_USER_INFO);
			ps.setString(1, user.getLogin());
			rs = ps.executeQuery();
			if (rs.next()) {
				readQueryUser(rs, user);
			}
		} catch (SQLException e) {
			throw new DaoException(LIST_USER_EXCEPTION + e.getMessage());
		} finally {
			connectionDataBase.closeConnection(connection, ps, rs);
		}
	}

	/**
	 * Reading and converting user fields from a response from the database.
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private void readQueryUser(ResultSet rs, User user) throws SQLException {
		user.setId(rs.getLong(1));
		user.setLogin(rs.getString(2));
		user.setName(rs.getString(4));
		user.setSurname(rs.getString(5));
		user.setEmail(rs.getString(6));
		user.setRole(rs.getBoolean(7));
	}

	/**
	 * Forming a query string for the database.
	 * 
	 * @param ps
	 * @param user
	 * @throws SQLException
	 */
	private void writeQueryUser(PreparedStatement ps, User user) throws SQLException {
		ps.setString(1, user.getLogin());
		ps.setLong(2, user.getHashCodePass());
		ps.setString(3, user.getName());
		ps.setString(4, user.getSurname());
		ps.setString(5, user.getEmail());
		ps.executeUpdate();
	}

	/**
	 * The method sends a request to the database for authorization
	 * 
	 * @param connection
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	private PreparedStatement queryUserLogin(Connection connection, User user) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(SQL_SELECT_USER_LOGIN);
		ps.setString(1, user.getLogin());
		ps.setString(2, user.getLogin());
		ps.setLong(3, user.getHashCodePass());
		user.setLogin(null);
		return ps;
	}
}
