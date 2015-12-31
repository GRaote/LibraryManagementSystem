package com.xoriant.servlet;

import static com.xoriant.business.BusinessUser.businessGetUserType;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.xoriant.servlet.form.FormUser;

/**
 * Servlet implementation class UserTypeServlet
 */
public class UserTypeServlet extends HttpServlet {
	static final Logger logger = LogManager.getLogger(UserTypeServlet.class
			.getName());
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			doCommon(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			doCommon(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * checking valid credentials with usertype
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	protected void doCommon(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		RequestDispatcher requestDispatcher = null;
		HttpSession session = httpServletRequest.getSession(true);
		if (session.getAttribute("emailID") == null) {
			session.invalidate();
			response.sendRedirect("/LibraryManagementSystem/jsp/HomeTestLogin.jsp");
		} else {
			logger.info("In UserTypeServlet");
			String emailID = request.getParameter("emailID");
			String password = request.getParameter("password");
			String userType1 = request.getParameter("userType");
			FormUser formUser = new FormUser();
			formUser.setEmailID(emailID);
			formUser.setPassword(password);

			formUser.setUserType(businessGetUserType(formUser));
			logger.info(formUser);
			if (!userType1.equalsIgnoreCase(formUser.getUserType())) {
				String temp = "Please enter correct User Type";
				request.setAttribute("temp", temp);
				requestDispatcher = request
						.getRequestDispatcher("/jsp/Home.jsp");
				requestDispatcher.forward(request, response);

			}
			/**
			 * Navigating according to usertypes
			 */
			else if (formUser.getUserType().equalsIgnoreCase("ADMIN")) {
				requestDispatcher = request
						.getRequestDispatcher("/jsp/AdminPortal.jsp");
				requestDispatcher.forward(request, response);

			} else if (formUser.getUserType().equalsIgnoreCase("User")) {

				requestDispatcher = request
						.getRequestDispatcher("/jsp/User.jsp");
				requestDispatcher.forward(request, response);

			} else if (formUser.getUserType().equalsIgnoreCase("LIBRARIAN")) {

				requestDispatcher = request
						.getRequestDispatcher("/jsp/Librarian.jsp");
				requestDispatcher.forward(request, response);

			}

		}
	}
}
