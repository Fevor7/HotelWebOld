package by.htp.itacademy.hotel.dao.impl;

import static by.htp.itacademy.hotel.util.SqlCommand.SQL_SELECT_HOTEL_FOTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.htp.itacademy.hotel.dao.FotoHotelDao;
import by.htp.itacademy.hotel.dao.databaseconnect.ConnectionDataBase;
import by.htp.itacademy.hotel.dao.exception.DaoException;
import by.htp.itacademy.hotel.domain.entity.FotoAddressHotel;

/**
 * In the object of this class, reads the table with the names of photos of the
 * hotel from the database.
 * 
 * @author viktor
 *
 */
public class FotoHotelDaoImpl implements FotoHotelDao {

	private static final String READ_INFO_EXCEPTION = "Could not get foto from the MySql database about the hotel. ";
	private static final FotoHotelDao INSTANCE = new FotoHotelDaoImpl();
	private ConnectionDataBase connectionDataBase;
	private Connection connection;
	private PreparedStatement ps;

	/**
	 * In this constructor, I get a reference to the connection class object to the
	 * MySQL database.
	 */
	private FotoHotelDaoImpl() {
		connectionDataBase = ConnectionDataBase.getInstance();
	}

	public static FotoHotelDao getInstance() {
		return INSTANCE;
	}

	@Override
	public List<FotoAddressHotel> fetchHotelFoto() throws DaoException {
		System.out.println("Dao fetchHotelFoto");
		ResultSet rs = null;
		List<FotoAddressHotel> list = new ArrayList<>();
		try {
			connection = connectionDataBase.getConnection();
			ps = connection.prepareStatement(SQL_SELECT_HOTEL_FOTO);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new FotoAddressHotel(rs.getString(1)));
			}
		} catch (SQLException e) {
			throw new DaoException(READ_INFO_EXCEPTION + e.getMessage());
		} finally {
			connectionDataBase.closeConnection(connection, ps, rs);
		}
		return list;
	}

}
