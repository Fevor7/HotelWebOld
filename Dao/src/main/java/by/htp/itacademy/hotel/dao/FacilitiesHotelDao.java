package by.htp.itacademy.hotel.dao;

import java.util.List;

import by.htp.itacademy.hotel.dao.exception.DaoException;
import by.htp.itacademy.hotel.domain.entity.FacilitiesHotel;

public interface FacilitiesHotelDao {

	/**
	 * The method returns a list of hotel amenities read from the database.
	 * 
	 * @param language
	 * 
	 * @return
	 * @throws DaoException
	 */
	List<FacilitiesHotel> fetchHotelFacilities() throws DaoException;

}
