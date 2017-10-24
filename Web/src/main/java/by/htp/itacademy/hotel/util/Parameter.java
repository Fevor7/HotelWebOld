package by.htp.itacademy.hotel.util;

import java.util.Arrays;
import java.util.List;

/**
 * List of possible attributes parameters of the http-request "action"
 * 
 * @author Viktor
 *
 */
public final class Parameter {

	private Parameter() {
	}

	public static final String REQUEST_ACTION_LOGIN = "login";
	public static final String REQUEST_ACTION_HOMEPAGE = "homepage";
	public static final String REQUEST_ACTION_LOGOUT = "logout";
	public static final String REQUEST_ACTION_SIGIIN = "newuser";
	public static final String REQUEST_ACTION_PERSONALPAGE = "personalpage";
	public static final String REQUEST_ACTION_SESSION_DESTROY = "sessiondestoy";
	public static final String REQUEST_ACTION_FIRSTPAGE = "firstpage";
	public static final String REQUEST_ACTION_CREATE_ORDER = "createorder";
	public static final String REQUEST_ACTION_ORDER_LIST = "orderlist";
	public static final String REQUEST_ACTION_ORDER_LIST_ALL = "allorderlist";
	public static final String REQUEST_ACTION_ORDER_LIST_ADMIN = "orderlistadmin";
	public static final String REQUEST_ACTION_ORDER_CHOOSE_TYPE = "pagechoosetypeorder";
	public static final String REQUEST_ACTION_ABOUT_PAGE = "aboutpage";
	public static final String REQUEST_ACTION_ORDER_LIST_USER = "orderlistuser";
	public static final String REQUEST_ACTION_ORDER_DELETE = "orderdelete";
	public static final String REQUEST_ACTION_ORDER_UPDATE = "orderupdate";
	public static final String REQUEST_ACTION_ORDER_UPDATE_ADMIN = "orderupdateadmin";
	public static final String REQUEST_ACTION_ROOM_PAGE = "roompage";
	public static final String REQUEST_ACTION_ROOM_LIST = "roomlist";
	public static final String REQUEST_ACTION_ROOM_SEARCH = "roomsearch";
	public static final String REQUEST_ACTION_ROOM_SEARCH_ADMIN = "roomsearchadmin";
	public static final String REQUEST_ACTION_ROOM_PAYMENT = "payment";
	public static final String REQUEST_ACTION_LANGUAGE = "language";
	public static final String REQUEST_ACTION_LANGUAGE_RU = "ru";
	public static final String REQUEST_ACTION_LANGUAGE_VALUE = "value";

	public static final String REQUEST_PARAMETER_ACTION = "action";
	public static final String REQUEST_PARAMETER_ERROR_MSG = "error_msg";
	public static final String REQUEST_PARAMETER_LOGIN = "login";
	public static final String REQUEST_PARAMETER_PASSWORD = "pass";
	public static final String REQUEST_PARAMETER_NAME = "name";
	public static final String REQUEST_PARAMETER_SURNAME = "surname";
	public static final String REQUEST_PARAMETER_EMAIL = "email";

	public static final String REQUEST_PARAMETER_ORDER_ID = "id";
	public static final String REQUEST_PARAMETER_ORDER_TYPE = "type";
	public static final String REQUEST_PARAMETER_DATE_START = "dateStart";
	public static final String REQUEST_PARAMETER_DATE_END = "dateEnd";
	public static final String REQUEST_PARAMETER_BED = "bed";
	public static final String REQUEST_PARAMETER_PERSON = "person";
	public static final String REQUEST_PARAMETER_TYPE_ROOM = "typeRoom";
	public static final String REQUEST_PARAMETER_TYPE_ROOM_ID = "idTypeRoom";
	public static final String REQUEST_PARAMETER_TYPE_ROOM_LIST = "typeRoomList";
	public static final String REQUEST_PARAMETER_MAX_PRICE = "maxPrice";
	public static final String REQUEST_PARAMETER_MIN_PRICE = "minPrice";
	public static final String REQUEST_PARAMETER_ROOM_ID = "roomId";
	public static final String REQUEST_PARAMETER_STATUS = "status";
	public static final String REQUEST_PARAMETER_TOTAL = "totalAmount";
	public static final String REQUEST_PARAMETER_PAGE_NUMBER = "pagenumber";

	public static final String LIST_WRAPPER = "listWrapper";

	public static final String LANGUAGE = "LANG";
	public static final String LANGUAGE_RU = "ru";
	public static final String LANGUAGE_EN = "en";

	public static final String RESPONSE_OK = "OK";
	public static final String RESPONSE_ERROR = "error";
	public static final String RESPONSE_ERROR_DATA = "errordata";

	public static final String SESSION_PARAMETER_PAGE = "page";
	public static final String SESSION_PARAMETER_USER = "user";

	public static final List<String> LANGLIST = Arrays.asList(LANGUAGE_RU, LANGUAGE_EN);

	public static final Long AMOUNT_ELEMENTS = new Long(10);

}
