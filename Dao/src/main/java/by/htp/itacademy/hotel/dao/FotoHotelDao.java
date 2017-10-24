package by.htp.itacademy.hotel.dao;

import java.util.List;

import by.htp.itacademy.hotel.dao.exception.DaoException;
import by.htp.itacademy.hotel.domain.entity.FotoAddressHotel;

public interface FotoHotelDao {

	/**
	 * The method returns a list of photos of hotel photos read from the database.
	 * 
	 * @return
	 * @throws DaoException
	 */
	List<FotoAddressHotel> fetchHotelFoto() throws DaoException;

}
