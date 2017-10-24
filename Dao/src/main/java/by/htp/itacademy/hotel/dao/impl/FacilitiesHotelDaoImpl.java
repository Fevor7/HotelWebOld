package by.htp.itacademy.hotel.dao.impl;

import static by.htp.itacademy.hotel.util.SqlCommand.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.htp.itacademy.hotel.dao.FacilitiesHotelDao;
import by.htp.itacademy.hotel.dao.databaseconnect.ConnectionDataBase;
import by.htp.itacademy.hotel.dao.exception.DaoException;
import by.htp.itacademy.hotel.domain.entity.FacilitiesHotel;

/**
 * In the object of this class, a table of the facilities in the hotel from the
 * database is read.
 * 
 * @author Viktor
 *
 */
public class FacilitiesHotelDaoImpl implements FacilitiesHotelDao {

	private static final String READ_INFO_EXCEPTION = "Could not get facilities from the MySql database about the hotel.";
	private static final FacilitiesHotelDao INSTANCE = new FacilitiesHotelDaoImpl();
	private ConnectionDataBase connectionDataBase;
	private Connection connection;
	private PreparedStatement ps;

	/**
	 * In this constructor, I get a reference to the connection class object to the
	 * MySQL database.
	 */
	private FacilitiesHotelDaoImpl() {
		connectionDataBase = ConnectionDataBase.getInstance();
	}

	public static FacilitiesHotelDao getInstance() {
		return INSTANCE;
	}

	@Override
	public List<FacilitiesHotel> fetchHotelFacilities() throws DaoException {
		System.out.println("Dao fetchHotelFacilities");
		ResultSet rs = null;
		List<FacilitiesHotel> list = new ArrayList<>();
		try {
			connection = connectionDataBase.getConnection();
			ps = connection.prepareStatement(SQL_SELECT_HOTEL_FACILITION);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new FacilitiesHotel(rs.getString(1)));
			}
		} catch (SQLException e) {
			throw new DaoException(READ_INFO_EXCEPTION + e.getMessage());
		} finally {
			connectionDataBase.closeConnection(connection, ps, rs);
		}
		return list;
	}

}
