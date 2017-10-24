package by.htp.itacademy.hotel.util;

/**
 * A class that includes a list of queries in the database.
 * 
 * @author viktor
 *
 */
public final class SqlCommand {

	private SqlCommand() {
	}

	public static final String SQL_SELECT_USER_INFO = "select * from user where login = ? ";
	public static final String SQL_SELECT_USER_LOGIN = "select * from user where (login = ? or email = ?) and password = ? ";
	public static final String SQL_SELECT_ORDER = "select s.value FROM hotel.order o JOIN hotel.status_order s ON o.id_status = s.id WHERE o.id = ? ";
	public static final String SQL_SELECT_ORDER_LIST = "select SQL_CALC_FOUND_ROWS o.id, number_room, u.login, date_start, date_end, bed, person, t.value, id_type_room, total_amount, s.value , o.id_status FROM hotel.order o JOIN hotel.user u ON o.id_user = u.id JOIN hotel.status_order s ON o.id_status = s.id JOIN hotel.type_room t ON o.id_type_room = t.id WHERE id_status = ? order by o.id desc  LIMIT ?,10";
	public static final String SQL_SELECT_ORDER_LIST_ALL = "select SQL_CALC_FOUND_ROWS o.id, number_room, u.login, date_start, date_end, bed, person, t.value, id_type_room, total_amount, s.value , o.id_status FROM hotel.order o JOIN hotel.user u ON o.id_user = u.id JOIN hotel.status_order s ON o.id_status = s.id JOIN hotel.type_room t ON o.id_type_room = t.id order by o.id desc LIMIT ?,10";
	public static final String SQL_SELECT_ORDER_LIST_USER = "select SQL_CALC_FOUND_ROWS o.id, number_room, u.login, date_start, date_end, bed, person, t.value, id_type_room, total_amount, s.value , o.id_status FROM hotel.order o JOIN hotel.user u ON o.id_user = u.id JOIN hotel.status_order s ON o.id_status = s.id JOIN hotel.type_room t ON o.id_type_room = t.id WHERE id_user = ? order by id LIMIT ?,10";
	public static final String SQL_SELECT_HOTEL_INFO = "select name, address, location, about ,star_rating FROM hotel.main_hotel ";
	public static final String SQL_SELECT_HOTEL_FACILITION = "select value FROM hotel.facilities_hotel ";
	public static final String SQL_SELECT_HOTEL_FOTO = "select address FROM hotel.foto_hotel ";
	public static final String SQL_SELECT_ROOM_TYPE = "SELECT id, value FROM hotel.type_room";
	public static final String SQL_SELECT_ID_ROOM_TYPE = "SELECT id FROM hotel.type_room WHERE value = ?";
	public static final String SQL_INSERT_USER = "INSERT INTO hotel.user (login, password, name, surname, email, role) VALUES (?, ?, ?, ?, ?, '0')";
	public static final String SQL_INSERT_ORDER = "INSERT INTO hotel.order (id_user, date_start, date_end, bed, person, id_type_room, id_status) VALUES (?, ?, ?, ?, ? , ?, ?);";
	public static final String SQL_DELETE_ORDER = "DELETE FROM hotel.order WHERE id=? ";
	public static final String SQL_UPDATE_ORDER = "UPDATE hotel.order SET date_start = ?, date_end = ?, bed = ?, person = ?, id_type_room = ? WHERE id = ?";
	public static final String SQL_UPDATE_ORDER_STATUS = "UPDATE hotel.order SET id_status=? WHERE id = ?";
	public static final String SQL_UPDATE_ORDER_ADMIN = "UPDATE hotel.order SET date_start = ?, date_end = ?, bed = ?, person = ?, id_type_room =?, total_amount= ?,id_status=?, number_room=? WHERE id = ?";
	public static final String SQL_SELECT_ROOM = "SELECT price, id_type FROM room WHERE number = ?";
	public static final String SQL_SELECT_ROOM_LIST_ALL = "SELECT SQL_CALC_FOUND_ROWS r.id, number, t.value, id_type,  size, foto_address, person, bed, price FROM room r JOIN type_room t ON r.id_type = t.id LIMIT ?,10";
	public static final String SQL_SELECT_ROOM_SEARCH = "SELECT SQL_CALC_FOUND_ROWS r.id, number, t.value, id_type,  size, foto_address, person, bed, price FROM room r JOIN type_room t ON r.id_type = t.id WHERE(number IN (SELECT number_room FROM hotel.order WHERE ((date_start NOT BETWEEN ? AND ?)AND(date_end NOT BETWEEN ? AND ?)) OR (id_status=4 or id_status=1 ) )  OR number NOT IN (SELECT number_room FROM hotel.order WHERE number_room!='' or number_room!=0)) AND (id_type=?) AND (price>=?) AND (price<=?) AND (person>=?) AND (bed>=?) LIMIT ?,10";
	public static final String SQL_SELECT_ROOM_SEARCH_ADMIN = "SELECT SQL_CALC_FOUND_ROWS r.id, number, t.value, id_type,  size, foto_address, person, bed, price FROM room r JOIN type_room t ON r.id_type = t.id WHERE(number IN (SELECT number_room FROM hotel.order WHERE ((date_start NOT BETWEEN ? AND ?)AND(date_end NOT BETWEEN ? AND ?)) OR (id_status=4 or id_status=1 ) )  OR number NOT IN (SELECT number_room FROM hotel.order WHERE number_room!='' or number_room!=0)) AND (id_type=?) AND (person>=?) AND (bed>=?) LIMIT ?,10";
	public static final String SQL_SELECT_ROOM_SEARCH_ALL_TYPE = "SELECT SQL_CALC_FOUND_ROWS r.id, number, t.value, id_type,  size, foto_address, person, bed, price FROM room r JOIN type_room t ON r.id_type = t.id WHERE(number IN (SELECT number_room FROM hotel.order WHERE ((date_start NOT BETWEEN ? AND ?)AND(date_end NOT BETWEEN ? AND ?)) OR (id_status=4 or id_status=1 ) )  OR number NOT IN (SELECT number_room FROM hotel.order WHERE number_room!='' or number_room!=0))AND (price>=?) AND (price<=?) AND (person>=?) AND (bed>=?) LIMIT ?,10";
	public static final String SQL_SELECT_ROOM_ALL_TYPE_ADMIN = "SELECT SQL_CALC_FOUND_ROWS r.id, number, t.value, id_type,  size, foto_address, person, bed, price FROM room r JOIN type_room t ON r.id_type = t.id WHERE(number IN (SELECT number_room FROM hotel.order WHERE ((date_start NOT BETWEEN ? AND ?)AND(date_end NOT BETWEEN ? AND ?)) OR (id_status=4 or id_status=1 ) )  OR number NOT IN (SELECT number_room FROM hotel.order WHERE number_room!='' or number_room!=0)) AND (person>=?) AND (bed>=?) LIMIT ?,10";
	public static final String SQL_SELECT_ID_STATUS_ORDER = "select id from hotel.status_order where value = ? ";
	public static final String SQL_SELECT_STATUS_ORDER = "select id, value from hotel.status_order ";
	public static final String SQL_SELECT_STATUS = "select id_status from hotel.order where id = ?";
	public static final String SQL_SIZE = "SELECT FOUND_ROWS()";
	
}