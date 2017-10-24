package by.htp.itacademy.hotel.service;

import by.htp.itacademy.hotel.domain.entity.Hotel;
import by.htp.itacademy.hotel.service.exception.ServiceException;

public interface HotelService {

	/**
	 * The method returns the hotel object with the filled-in fields.
	 * 
	 * @param language
	 * 
	 * @return
	 * @throws ServiceException
	 */
	Hotel hotelInfo(String language) throws ServiceException;
}
