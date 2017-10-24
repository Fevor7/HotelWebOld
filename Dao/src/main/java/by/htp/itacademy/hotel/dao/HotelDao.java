package by.htp.itacademy.hotel.dao;

import by.htp.itacademy.hotel.dao.exception.DaoException;
import by.htp.itacademy.hotel.domain.entity.Hotel;

public interface HotelDao {

	/**
	 * The method returns the hotel object created with the values ​​read from the
	 * database.
	 * 
	 * @param language
	 * 
	 * @return
	 * @throws DaoException
	 */
	Hotel fetchHotelParamener() throws DaoException;

}
