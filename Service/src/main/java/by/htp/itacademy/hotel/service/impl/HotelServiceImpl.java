package by.htp.itacademy.hotel.service.impl;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import by.htp.itacademy.hotel.dao.FacilitiesHotelDao;
import by.htp.itacademy.hotel.dao.FotoHotelDao;
import by.htp.itacademy.hotel.dao.HotelDao;
import by.htp.itacademy.hotel.dao.exception.DaoException;
import by.htp.itacademy.hotel.dao.factory.DaoFactory;
import by.htp.itacademy.hotel.domain.entity.FacilitiesHotel;
import by.htp.itacademy.hotel.domain.entity.FotoAddressHotel;
import by.htp.itacademy.hotel.domain.entity.Hotel;
import by.htp.itacademy.hotel.service.HotelService;
import by.htp.itacademy.hotel.service.exception.ServiceException;

/**
 * The object of this class forms the object of the hotel with a full
 * description.
 * 
 * @author viktor
 *
 */
public class HotelServiceImpl implements HotelService {

	private static final HotelService INSTANCE = new HotelServiceImpl();
	private static final Logger LOG = Logger.getLogger(HotelServiceImpl.class);
	private static final String LOG_ERROR = " ERROR: ";
	private static final String PAGE_CONTENT = "pagecontent";
	private FacilitiesHotelDao facilitiesHotelDao;
	private FotoHotelDao fotoHotelDao;
	private HotelDao hotelDao;

	/**
	 * In this constructor, reference variables are assigned references to DAO level
	 * objects.
	 */
	private HotelServiceImpl() {
		DaoFactory daoFactory = DaoFactory.getInstance();
		facilitiesHotelDao = daoFactory.getFacilitiesHotelDao();
		fotoHotelDao = daoFactory.getFotoHotelDao();
		hotelDao = daoFactory.getHotelDao();
	}

	public static HotelService getInstance() {
		return INSTANCE;
	}

	@Override
	public Hotel hotelInfo(String language) throws ServiceException {
		Hotel hotel = null;
		try {
			hotel = hotelDao.fetchHotelParamener();
			List<FacilitiesHotel> listFacilities = facilitiesHotelDao.fetchHotelFacilities();
			List<FotoAddressHotel> listFoto = fotoHotelDao.fetchHotelFoto();
			setFacilities(hotel, listFacilities, language);
			loadingDundle(hotel, language);
			hotel.setFotoAddress(listFoto);
		} catch (DaoException e) {
			LOG.error(LOG_ERROR + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
		return hotel;
	}

	private void loadingDundle(Hotel hotel, String language) {
		Locale currentLocale = new Locale(language);
		ResourceBundle bundle = ResourceBundle.getBundle(PAGE_CONTENT, currentLocale);
		String name = hotel.getName();
		hotel.setName(bundle.getString(name));
		String address = hotel.getAddress();
		hotel.setAddress(bundle.getString(address));
		String about = hotel.getAbout();
		hotel.setAbout(bundle.getString(about));
	}

	private void setFacilities(Hotel hotel, List<FacilitiesHotel> listFacilities, String language) {
		Locale currentLocale = new Locale(language);
		ResourceBundle bundle = ResourceBundle.getBundle(PAGE_CONTENT, currentLocale);
		for (FacilitiesHotel facilitiesHotel : listFacilities) {
			String value = facilitiesHotel.getValue();
			facilitiesHotel.setValue(bundle.getString(value));
		}
		hotel.setFacilities(listFacilities);
	}

}
