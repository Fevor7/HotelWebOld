package by.htp.itacademy.hotel.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.htp.itacademy.hotel.util.AddressPage.*;

/**
 * The object of this filter checks whether the request contains the desired
 * header.
 * 
 * @author viktor
 *
 */
public class VerificationHeader implements Filter {

	private static final String SECURITY_HEADER_NAME = "SecurityHeader";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String header = req.getHeader(SECURITY_HEADER_NAME);
		if (header == null) {
			RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(PAGE_ERROR);
			dispatcher.forward(req, resp);
			return;
		}
		chain.doFilter(request, response);

	}

}
