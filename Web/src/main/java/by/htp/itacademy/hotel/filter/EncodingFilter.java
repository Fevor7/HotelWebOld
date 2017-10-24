package by.htp.itacademy.hotel.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Filter that corrects the encoding of the request and response.
 * 
 * @author Viktor
 *
 */
public class EncodingFilter implements Filter {

	private static final String ENCODING_PARAMETER = "encoding";
	private String code;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		code = filterConfig.getInitParameter(ENCODING_PARAMETER);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String codeRequest = request.getCharacterEncoding();
		if (code != null && !code.equalsIgnoreCase(codeRequest)) {
			request.setCharacterEncoding(code);
			response.setCharacterEncoding(code);
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		code = null;
	}

}
