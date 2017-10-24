package by.htp.itacademy.hotel.dao.impl;

import static by.htp.itacademy.hotel.util.SqlCommand.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.htp.itacademy.hotel.dao.TypeRoomDao;
import by.htp.itacademy.hotel.dao.databaseconnect.ConnectionDataBase;
import by.htp.itacademy.hotel.dao.exception.DaoException;
import by.htp.itacademy.hotel.domain.entity.Order;
import by.htp.itacademy.hotel.domain.entity.TypeRoom;
import by.htp.itacademy.hotel.domain.entity.Unit;

public class TypeRoomDaoImpl implements TypeRoomDao {

	private static final String READ_TYPE_EXCEPTION = "Could not read the room data types from the database.";
	private static final String READ_ID_TYPE_EXCEPTION = "Could not read the id type from the database.";
	private static final TypeRoomDao INSTANCE = new TypeRoomDaoImpl();
	private ConnectionDataBase connectionDataBase;
	private Connection connection;
	private PreparedStatement ps;

	/**
	 * In this constructor, I get references to a factory object that returns links
	 * to a DAO level object.
	 */
	private TypeRoomDaoImpl() {
		connectionDataBase = ConnectionDataBase.getInstance();
	}

	public static TypeRoomDao getInstance() {
		return INSTANCE;
	}

	@Override
	public List<Unit> fetchAllTypeRoom() throws DaoException {
		System.out.println("Dao fetchAllTypeRoom");
		ResultSet rs = null;
		List<Unit> list = new ArrayList<Unit>();
		try {
			connection = connectionDataBase.getConnection();
			ps = connection.prepareStatement(SQL_SELECT_ROOM_TYPE);
			rs = ps.executeQuery();
			readResultListType(rs, list);
		} catch (SQLException e) {
			throw new DaoException(READ_TYPE_EXCEPTION + e.getMessage());
		} finally {
			connectionDataBase.closeConnection(connection, ps, rs);
		}
		return list;
	}

	@Override
	public void fetchIdTypeRoom(Order order) throws DaoException {
		System.out.println("Dao fetchIdTypeRoom");
		ResultSet rs = null;
		Long idTypeRoom = null;
		try {
			connection = connectionDataBase.getConnection();
			ps = connection.prepareStatement(SQL_SELECT_ID_ROOM_TYPE);
			ps.setString(1, order.getTypeRoom().getValue());
			rs = ps.executeQuery();
			idTypeRoom = readIdResult(rs);
		} catch (SQLException e) {
			throw new DaoException(READ_ID_TYPE_EXCEPTION + e.getMessage());
		} finally {
			connectionDataBase.closeConnection(connection, ps, rs);
		}
		order.setTypeRoom(new TypeRoom(idTypeRoom));
	}

	/**
	 * The method processes the database response in which there is an id of the
	 * type.
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Long readIdResult(ResultSet rs) throws SQLException {
		Long id = null;
		while (rs.next()) {
			id = rs.getLong(1);
		}
		return id;
	}

	/**
	 * The method processes the database response in which there is a list of types.
	 * 
	 * @param rs
	 * @param list
	 * @throws SQLException
	 */
	private void readResultListType(ResultSet rs, List<Unit> list) throws SQLException {
		while (rs.next()) {
			Unit typeRoom = new Unit();
			typeRoom.setId(rs.getLong(1));
			typeRoom.setValue(rs.getString(2));
			list.add(typeRoom);
		}
	}

}
