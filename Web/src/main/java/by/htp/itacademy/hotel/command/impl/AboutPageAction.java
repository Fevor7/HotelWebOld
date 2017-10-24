package by.htp.itacademy.hotel.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.htp.itacademy.hotel.command.Action;
import by.htp.itacademy.hotel.command.exception.CommandInvalidParameterException;
import by.htp.itacademy.hotel.domain.entity.Hotel;
import by.htp.itacademy.hotel.domain.vo.FillResponseInfo;
import by.htp.itacademy.hotel.service.HotelService;
import by.htp.itacademy.hotel.service.exception.ServiceException;
import by.htp.itacademy.hotel.service.factory.ServiceFactory;

import static by.htp.itacademy.hotel.util.Parameter.*;
import static by.htp.itacademy.hotel.util.AddressPage.*;

/**
 * The object of this class returns the address of the hotel description page
 * and the data about this hotel.
 * 
 * @author viktor
 *
 */
public class AboutPageAction extends AbstractAction implements Action {

	private static final String REQUEST_ATTRIBUTE_HOTEL = "hotel";
	private static final String REQUEST_FACILITION_HOTEL = "listFacil";
	private static final String REQUEST_FOTO_HOTEL = "listFoto";
	private static final Action INSTANCE = new AboutPageAction();
	private HotelService hotelService;

	/**
	 * In this constructor, I get a reference to the factory object. And from her I
	 * get a link to the class object of the level - the service.
	 */
	private AboutPageAction() {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		hotelService = serviceFactory.getHotelService();
	}

	public static Action getInstance() {
		return INSTANCE;
	}

	@Override
	public FillResponseInfo execute(HttpServletRequest request) {
		System.out.println("command aboutpage");
		FillResponseInfo responseInfo = new FillResponseInfo(PAGE_ABOUT);
		String language = fetchLanguage(request);
		try {
			Hotel hotel = hotelService.hotelInfo(language);
			ModificationRequestObject(request, hotel);
		} catch (ServiceException e) {
			responseInfo.changeField(RESPONSE_ERROR, true);
		}
		return responseInfo;
	}

	/**
	 * Modification of the object request
	 * 
	 * @param request
	 * @param list
	 * @param order
	 * @throws CommandInvalidParameterException
	 */
	private void ModificationRequestObject(HttpServletRequest request, Hotel hotel) {
		request.getSession().setAttribute(SESSION_PARAMETER_PAGE, REQUEST_ACTION_ABOUT_PAGE);
		request.setAttribute(REQUEST_ATTRIBUTE_HOTEL, hotel);
		request.setAttribute(REQUEST_FACILITION_HOTEL, hotel.getFacilities());
		request.setAttribute(REQUEST_FOTO_HOTEL, hotel.getFotoAddress());
	}

}
