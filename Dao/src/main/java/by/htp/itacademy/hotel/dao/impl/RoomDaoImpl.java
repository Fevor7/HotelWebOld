package by.htp.itacademy.hotel.dao.impl;

import static by.htp.itacademy.hotel.util.SqlCommand.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.htp.itacademy.hotel.dao.RoomDao;
import by.htp.itacademy.hotel.dao.databaseconnect.ConnectionDataBase;
import by.htp.itacademy.hotel.dao.exception.DaoException;
import by.htp.itacademy.hotel.domain.entity.Order;
import by.htp.itacademy.hotel.domain.entity.Room;
import by.htp.itacademy.hotel.domain.vo.ListPage;

/**
 * The object of this class performs operations on the table of rooms in the
 * database.
 * 
 * @author viktor
 *
 */
public class RoomDaoImpl implements RoomDao {

	private static final String LIST_ROOM_EXCEPTION = "Failed to get list of rooms from MySql database. ";
	private static final String ADDRESS_FOTO = "web/images/room/";
	private static final Long ID_ANY_TYPE = new Long(1);
	private static final RoomDao INSTANCE = new RoomDaoImpl();
	private ConnectionDataBase connectionDataBase;
	private Connection connection;
	private PreparedStatement ps;

	/**
	 * In this constructor, I get a reference to the connection class object to the
	 * MySQL database.
	 */
	private RoomDaoImpl() {
		connectionDataBase = ConnectionDataBase.getInstance();
	}

	public static RoomDao getInstance() {
		return INSTANCE;
	}

	@Override
	public Room fetchRoom(Order order) throws DaoException {
		System.out.println("Dao fetchRoom");
		ResultSet rs = null;
		Room room = null;
		try {
			connection = connectionDataBase.getConnection();
			ps = connection.prepareStatement(SQL_SELECT_ROOM);
			ps.setLong(1, order.getRoom().getId());
			rs = ps.executeQuery();
			if (rs.next()) {
				room = new Room(null, rs.getBigDecimal(1), rs.getLong(2));
			}
		} catch (SQLException e) {
			throw new DaoException(LIST_ROOM_EXCEPTION + e.getMessage());
		} finally {
			connectionDataBase.closeConnection(connection, ps, rs);
		}
		return room;
	}

	@Override
	public void fetchAllRoom(ListPage<Room> listPage) throws DaoException {
		System.out.println("Dao fetchAllRoom");
		ResultSet rs = null;
		Long totalPage = listPage.getMaxPerPage() * listPage.getPage();
		try {
			connection = connectionDataBase.getConnection();
			ps = connection.prepareStatement(SQL_SELECT_ROOM_LIST_ALL);
			ps.setLong(1, totalPage);
			rs = ps.executeQuery();
			listPage = readQuery(rs, listPage);
		} catch (SQLException e) {
			throw new DaoException(LIST_ROOM_EXCEPTION + e.getMessage());
		} finally {
			connectionDataBase.closeConnection(connection, ps, rs);
		}
		fetchSizeList(listPage);
	}

	@Override
	public ListPage<Room> roomListSearch(ListPage<Room> listPage, Order order) throws DaoException {
		System.out.println("Dao roomListSearch");
		ResultSet rs = null;
		try {
			connection = connectionDataBase.getConnection();
			rs = sendQuery(order, listPage, connection);
			listPage = readQuery(rs, listPage);
		} catch (SQLException e) {
			throw new DaoException(LIST_ROOM_EXCEPTION + e.getMessage());
		} finally {
			connectionDataBase.closeConnection(connection, ps, rs);
		}
		return fetchSizeList(listPage);
	}

	@Override
	public ListPage<Room> roomListSearchAdmin(ListPage<Room> listPage, Order order)
			throws DaoException {
		System.out.println("Dao roomListSearchAdmin");
		ResultSet rs = null;
		try {
			connection = connectionDataBase.getConnection();
			rs = sendQueryAdmin(order, connection, listPage);
			listPage = readQueryRoomAdmin(rs, listPage);
		} catch (SQLException e) {
			throw new DaoException(LIST_ROOM_EXCEPTION + e.getMessage());
		} finally {
			connectionDataBase.closeConnection(connection, ps, rs);
		}
		return fetchSizeList(listPage);
	}

	/**
	 * This method writes a list of rooms into a wrapper object
	 * 
	 * @param rs
	 * @param listPage
	 * @return
	 * @throws SQLException
	 */
	private ListPage<Room> readQueryRoomAdmin(ResultSet rs, ListPage<Room> listPage) throws SQLException {
		List<Room> list = new ArrayList<Room>();
		while (rs.next()) {
			Room room = readQueryRoom(rs);
			list.add(room);
		}
		listPage.setData(list);
		return listPage;
	}

	/**
	 * Sends the request to the database.
	 * 
	 * @param order
	 * @param language
	 * @return
	 * @throws SQLException
	 */
	private ResultSet sendQuery(Order order, ListPage<Room> listPage, Connection connection)
			throws SQLException {
		Long totalPage = listPage.getMaxPerPage() * listPage.getPage();
		Long idType = order.getTypeRoom().getId();
		String query = SQL_SELECT_ROOM_SEARCH;
		if (idType.equals(ID_ANY_TYPE)) {
			query = SQL_SELECT_ROOM_SEARCH_ALL_TYPE;
		}
		ps = connection.prepareStatement(query);
		queryRoomSearch(ps, order, totalPage);
		return ps.executeQuery();
	}

	/**
	 * Sends the request to the database for the administration.
	 * 
	 * @param order
	 * @return
	 * @throws SQLException
	 */
	private ResultSet sendQueryAdmin(Order order, Connection connection, ListPage<Room> listPage)
			throws SQLException {
		Long totalPage = listPage.getMaxPerPage() * listPage.getPage();
		Long idType = order.getTypeRoom().getId();
		String query = SQL_SELECT_ROOM_SEARCH_ADMIN;
		if (idType.equals(ID_ANY_TYPE)) {
			query = SQL_SELECT_ROOM_ALL_TYPE_ADMIN;
		}
		ps = connection.prepareStatement(query);
		queryRoomSearchAdmin(ps, order, totalPage);
		return ps.executeQuery();
	}

	/**
	 * The method reads the room parameters from the database response result
	 * collection and creates a room object.
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Room readQueryRoom(ResultSet rs) throws SQLException {
		Long id = rs.getLong(1);
		Long number = rs.getLong(2);
		String typeRoom = rs.getString(3);
		Long idType = rs.getLong(4);
		Byte size = rs.getByte(5);
		String foto = ADDRESS_FOTO + rs.getString(6);
		Byte person = rs.getByte(7);
		Byte bed = rs.getByte(8);
		BigDecimal price = rs.getBigDecimal(9);
		Room room = new Room(id, number, typeRoom, idType, size, foto, person, bed, price);
		return room;
	}

	/**
	 * The method forms a query string in the database to search for rooms by
	 * parameters.
	 * 
	 * @param ps
	 * @param order
	 * @throws SQLException
	 */
	private void queryRoomSearch(PreparedStatement ps, Order order, Long totalPage) throws SQLException {
		int insertionNumber = 5;
		Long idType = order.getTypeRoom().getId();
		if (!idType.equals(ID_ANY_TYPE)) {
			ps.setLong(5, order.getTypeRoom().getId());
			insertionNumber++;
		}
		ps.setDate(1, order.getDateStart());
		ps.setDate(2, order.getDateEnd());
		ps.setDate(3, order.getDateStart());
		ps.setDate(4, order.getDateEnd());
		ps.setBigDecimal(insertionNumber, order.getMinPrice());
		ps.setBigDecimal(++insertionNumber, order.getMaxPrice());
		ps.setByte(++insertionNumber, order.getPersonNumber());
		ps.setByte(++insertionNumber, order.getBedNumber());
		ps.setLong(++insertionNumber, totalPage);
	}

	/**
	 * The method forms a query string in the database to search for rooms by
	 * parameters, for the administration.
	 * 
	 * @param ps
	 * @param order
	 * @throws SQLException
	 */
	private void queryRoomSearchAdmin(PreparedStatement ps, Order order, Long totalPage) throws SQLException {
		int insertionNumber = 5;
		Long idType = order.getTypeRoom().getId();
		if (!idType.equals(ID_ANY_TYPE)) {
			ps.setLong(5, order.getTypeRoom().getId());
			insertionNumber++;
		}
		ps.setDate(1, order.getDateStart());
		ps.setDate(2, order.getDateEnd());
		ps.setDate(3, order.getDateStart());
		ps.setDate(4, order.getDateEnd());
		ps.setByte(insertionNumber, order.getPersonNumber());
		ps.setByte(++insertionNumber, order.getBedNumber());
		ps.setLong(++insertionNumber, totalPage);
	}

	/**
	 * The method reads the list of rooms from the database response array
	 * 
	 * @param rs
	 * @param listPage
	 * @return
	 * @throws SQLException
	 */
	private ListPage<Room> readQuery(ResultSet rs, ListPage<Room> listPage) throws SQLException {
		List<Room> list = new ArrayList<Room>();
		while (rs.next()) {
			Room room = readQueryRoom(rs);
			list.add(room);
		}
		listPage.setData(list);
		return listPage;
	}

	/**
	 * The method fetches the number of lines from the database.
	 * 
	 * @param connection
	 * @return
	 * @throws DaoException
	 */
	private ListPage<Room> fetchSizeList(ListPage<Room> listPage) throws DaoException {
		ResultSet rs = null;
		try {
			connection = connectionDataBase.getConnection();
			ps = connection.prepareStatement(SQL_SIZE);
			rs = ps.executeQuery();
			if (rs.next()) {
				listPage.setTotal(rs.getLong(1));
			}
		} catch (SQLException e) {
			throw new DaoException(LIST_ROOM_EXCEPTION + e.getMessage());
		} finally {
			connectionDataBase.closeConnection(connection, ps, rs);
		}
		return listPage;
	}

}
