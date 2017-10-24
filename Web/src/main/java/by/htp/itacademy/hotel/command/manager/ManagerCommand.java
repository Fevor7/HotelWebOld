package by.htp.itacademy.hotel.command.manager;

import static by.htp.itacademy.hotel.util.Parameter.*;

import java.util.HashMap;
import java.util.Map;

import by.htp.itacademy.hotel.command.Action;
import by.htp.itacademy.hotel.command.impl.*;

/**
 * The class in which the collection is populated with the values ​​of the
 * request attributes and references to the objects for processing the
 * attributes. And the output is a reference to the object, depending on the
 * attribute.
 * 
 * @author Viktor
 *
 */
public class ManagerCommand {

	private static Map<String, Action> map = new HashMap<String, Action>();

	/**
	 * Filling the collection with pairs of assignments: command - a reference to a
	 * specific object
	 */
	static {
		map.put(REQUEST_ACTION_LOGIN, LogInAction.getInstance());
		map.put(REQUEST_ACTION_LOGOUT, LogOutAction.getInstance());
		map.put(REQUEST_ACTION_SIGIIN, SignUpAction.getInstance());
		map.put(REQUEST_ACTION_SESSION_DESTROY, LogOutAction.getInstance());
		map.put(REQUEST_ACTION_CREATE_ORDER, OrderCreateAction.getInstance());
		map.put(REQUEST_ACTION_HOMEPAGE, HomePageAction.getInstance());
		map.put(REQUEST_ACTION_PERSONALPAGE, PersonalPageAction.getInstance());
		map.put(REQUEST_ACTION_FIRSTPAGE, FirstPageAction.getInstance());
		map.put(REQUEST_ACTION_ORDER_LIST, OrderListAction.getInstance());
		map.put(REQUEST_ACTION_ORDER_LIST_ADMIN, OrderListAdminAction.getInstance());
		map.put(REQUEST_ACTION_ORDER_CHOOSE_TYPE, OrderChooseTypeAction.getInstance());
		map.put(REQUEST_ACTION_ABOUT_PAGE, AboutPageAction.getInstance());
		map.put(REQUEST_ACTION_ORDER_LIST_USER, UserOrderListAction.getInstance());
		map.put(REQUEST_ACTION_ORDER_DELETE, OrderDeleteAction.getInstance());
		map.put(REQUEST_ACTION_ORDER_UPDATE, OrderUpdateAction.getInstance());
		map.put(REQUEST_ACTION_ORDER_UPDATE_ADMIN, OrderUpdateAdminAction.getInstance());
		map.put(REQUEST_ACTION_ROOM_PAGE, RoomPageAction.getInstance());
		map.put(REQUEST_ACTION_ROOM_LIST, RoomListAction.getInstance());
		map.put(REQUEST_ACTION_ROOM_SEARCH, RoomSearchAction.getInstance());
		map.put(REQUEST_ACTION_ROOM_SEARCH_ADMIN, RoomSearchAdminAction.getInstance());
		map.put(REQUEST_ACTION_ORDER_LIST_ALL, OrderListAllAction.getInstance());
		map.put(REQUEST_ACTION_LANGUAGE, SwitchLanguageAction.getInstance());

	}

	private ManagerCommand() {

	}

	/**
	 * A method that returns a reference to an object, depending on the command
	 * 
	 * @param action
	 * @return
	 */
	public static Action getAction(String action) {
		Action result = map.get(action.toLowerCase());
		if (result == null) {
			result = map.get(REQUEST_ACTION_HOMEPAGE);
		}
		return result;
	}
}