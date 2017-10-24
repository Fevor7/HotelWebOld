package by.htp.itacademy.hotel.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.htp.itacademy.hotel.dao.HotelDao;
import by.htp.itacademy.hotel.dao.databaseconnect.ConnectionDataBase;
import by.htp.itacademy.hotel.dao.exception.DaoException;
import by.htp.itacademy.hotel.domain.entity.Hotel;

import static by.htp.itacademy.hotel.util.SqlCommand.*;

/**
 * In the object of this class, reads the table with the parameters of the hotel
 * from the database.
 * 
 * @author Viktor
 *
 */
public class HotelDaoImpl implements HotelDao {

	private static final String READ_INFO_EXCEPTION = "Could not get information from the MySql database about the hotel. ";
	private static final HotelDao INSTANCE = new HotelDaoImpl();
	private ConnectionDataBase connectionDataBase;
	private Connection connection;
	private PreparedStatement ps;

	/**
	 * In this constructor, I get a reference to the connection class object to the
	 * MySQL database.
	 */
	private HotelDaoImpl() {
		connectionDataBase = ConnectionDataBase.getInstance();
	}

	public static HotelDao getInstance() {
		return INSTANCE;
	}

	@Override
	public Hotel fetchHotelParamener() throws DaoException {
		System.out.println("Dao fetchHotelParamener");
		ResultSet rs = null;
		Hotel hotel = null;
		try {
			connection = connectionDataBase.getConnection();
			ps = connection.prepareStatement(SQL_SELECT_HOTEL_INFO);
			rs = ps.executeQuery();
			if (rs.next()) {
				hotel = readHotelParameter(rs);
			}
		} catch (SQLException e) {
			throw new DaoException(READ_INFO_EXCEPTION + e.getMessage());
		} finally {
			connectionDataBase.closeConnection(connection, ps, rs);
		}
		return hotel;
	}

	/**
	 * The method in which the hotel object is created by the parameters from the
	 * database.
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Hotel readHotelParameter(ResultSet rs) throws SQLException {
		String name = rs.getString(1);
		String address = rs.getString(2);
		String location = rs.getString(3);
		String about = rs.getString(4);
		String starRating = rs.getString(5);
		Hotel hotel = new Hotel(name, address, location, about, starRating);
		return hotel;
	}

}
