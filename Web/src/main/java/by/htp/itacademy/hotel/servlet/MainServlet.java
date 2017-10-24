package by.htp.itacademy.hotel.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.itacademy.hotel.command.manager.ManagerCommand;
import by.htp.itacademy.hotel.domain.vo.FillResponseInfo;

import static by.htp.itacademy.hotel.util.Parameter.*;

@WebServlet("/Servlet")
public class MainServlet extends HttpServlet {

	private static final long serialVersionUID = 1398961966955835496L;

	/**
	 * Method of processing the GET-request
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);

	}

	/**
	 * Method of processing the POST-request
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Receiving an object reference depending on the request.
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter(REQUEST_PARAMETER_ACTION);
		FillResponseInfo responseInfo = ManagerCommand.getAction(action).execute(request);
		if (responseInfo.isStatusMessage()) {
			response.getWriter().print(responseInfo.getData());
		} else {
			request.getRequestDispatcher(responseInfo.getData()).forward(request, response);
		}

	}

}
