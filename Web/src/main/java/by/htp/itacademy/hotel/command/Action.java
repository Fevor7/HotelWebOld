package by.htp.itacademy.hotel.command;

import javax.servlet.http.HttpServletRequest;

import by.htp.itacademy.hotel.domain.vo.FillResponseInfo;

public interface Action {

	/**
	 * Processing Http-request and preparation of information for sending in the
	 * Http-response
	 * 
	 * @param Http-request
	 * @param Http-response
	 * @return page address
	 */

	public FillResponseInfo execute(HttpServletRequest request);

}
