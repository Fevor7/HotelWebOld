package by.htp.itacademy.hotel.service.impl;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import by.htp.itacademy.hotel.dao.RoomDao;
import by.htp.itacademy.hotel.dao.TypeRoomDao;
import by.htp.itacademy.hotel.dao.exception.DaoException;
import by.htp.itacademy.hotel.dao.factory.DaoFactory;
import by.htp.itacademy.hotel.domain.entity.Order;
import by.htp.itacademy.hotel.domain.entity.Room;
import by.htp.itacademy.hotel.domain.entity.Unit;
import by.htp.itacademy.hotel.domain.vo.ListPage;
import by.htp.itacademy.hotel.service.RoomService;
import by.htp.itacademy.hotel.service.exception.ServiceException;
import by.htp.itacademy.hotel.service.exception.ServiceNoRoomFoundException;

/**
 * The class of this class performs the function of business logic over the room
 * object.
 * 
 * @author viktor
 *
 */
public class RoomServiceImpl implements RoomService {

	private static final RoomService INSTANCE = new RoomServiceImpl();
	private static final Logger LOG = Logger.getLogger(RoomServiceImpl.class);
	private static final String TYPE_ROOM_EXCEPTION = "The list of room types is empty.";
	private static final String ROOM_LIST_EXCEPTION = "No rooms found";
	private static final String LOG_ERROR = " ERROR: ";
	private static final String PAGE_CONTENT = "pagecontent";
	private TypeRoomDao typeRoomDao;
	private RoomDao roomDao;

	/**
	 * In this constructor, I get references to a factory object that returns links
	 * to a DAO level object.
	 */
	private RoomServiceImpl() {
		DaoFactory daoFactory = DaoFactory.getInstance();
		roomDao = daoFactory.getRoomDaoImpl();
		typeRoomDao = daoFactory.getTypeRoomDaoImpl();
	}

	public static RoomService getInstance() {
		return INSTANCE;
	}

	@Override
	public ListPage<Room> roomList(ListPage<Room> listPage, String language) throws ServiceNoRoomFoundException {
		try {
			roomDao.fetchAllRoom(listPage);
			loadingDundle(listPage.getData(), language);
		} catch (DaoException e) {
			LOG.error(LOG_ERROR + e.getMessage());
		}
		if (listPage.getData().isEmpty()) {
			throw new ServiceNoRoomFoundException(ROOM_LIST_EXCEPTION);
		}
		return listPage;
	}

	@Override
	public ListPage<Room> searchRoom(ListPage<Room> listPage, Order order, String language)
			throws ServiceNoRoomFoundException {
		try {
			roomDao.roomListSearch(listPage, order);
			loadingDundle(listPage.getData(), language);
		} catch (DaoException e) {
			LOG.error(LOG_ERROR + e.getMessage());
		}
		if (listPage.getData().isEmpty()) {
			throw new ServiceNoRoomFoundException(ROOM_LIST_EXCEPTION);
		}
		return listPage;
	}

	@Override
	public ListPage<Room> searchRoomAdmin(ListPage<Room> listPage, Order order, String language)
			throws ServiceNoRoomFoundException {
		try {
			listPage = roomDao.roomListSearchAdmin(listPage, order);
			loadingDundle(listPage.getData(), language);
		} catch (DaoException e) {
			LOG.error(LOG_ERROR + e.getMessage());
		}
		if (listPage.getData().isEmpty()) {
			throw new ServiceNoRoomFoundException(ROOM_LIST_EXCEPTION);
		}
		return listPage;
	}

	@Override
	public List<Unit> typeRoomList(String language) throws ServiceException {
		List<Unit> list = null;
		try {
			list = typeRoomDao.fetchAllTypeRoom();
			setValue(list, language);
		} catch (DaoException e) {
			LOG.error(LOG_ERROR + e.getMessage());
		}
		if (list.isEmpty()) {
			throw new ServiceException(TYPE_ROOM_EXCEPTION);
		}
		return list;
	}
	
	private void loadingDundle(List<Room> list, String language) {
		Locale currentLocale = new Locale(language);
		ResourceBundle bundle = ResourceBundle.getBundle(PAGE_CONTENT, currentLocale);
		for (int i = 0; i < list.size(); i++) {
			Room room = list.get(i);
			String type = room.getTypeRoom().getValue();
			room.getTypeRoom().setValue(bundle.getString(type));
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
