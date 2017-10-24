package by.htp.itacademy.hotel.filter;

import static by.htp.itacademy.hotel.util.Parameter.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.itacademy.hotel.domain.entity.User;

/**
 * A filter that checks whether the user is an administrator.
 * 
 * @author Viktor
 *
 */
public class AccessAdmin implements Filter {

	private List<String> actionList = Arrays.asList(REQUEST_ACTION_ORDER_LIST_ADMIN, REQUEST_ACTION_ORDER_UPDATE_ADMIN);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		String action = req.getParameter(REQUEST_PARAMETER_ACTION);
		User user = (User) session.getAttribute(SESSION_PARAMETER_USER);
		Boolean presenceAction = actionList.contains(action.toLowerCase());
		if (presenceAction && (!user.getRole())) {
			resp.getWriter().print(RESPONSE_ERROR);
			return;
		}
		chain.doFilter(request, response);
	}

}
