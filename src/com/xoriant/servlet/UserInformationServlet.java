package com.xoriant.servlet;

import static com.xoriant.business.BusinessUser.businessGetUserDetails;

import java.io.IOException;
import java.sql.SQLException;

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
 * Servlet implementation class UserDetailsServlet
 */
public class UserInformationServlet extends HttpServlet {
	static final Logger logger = LogManager
			.getLogger(UserInformationServlet.class.getName());
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
	}

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
			logger.info("In UserInformationServlet");
			String emailID = request.getParameter("emailID");
			FormUser formUser = new FormUser();
			formUser.setEmailID(emailID);
			formUser = businessGetUserDetails(formUser);
			logger.info(formUser);
			request.setAttribute("emailID", emailID);
			request.setAttribute("formUser", formUser);
			/**
			 * Displaying information according to user type
			 */
			if (formUser.getUserType().equalsIgnoreCase("USER")) {
				requestDispatcher = request
						.getRequestDispatcher("/jsp/UserInformation.jsp");
				requestDispatcher.forward(request, response);
			} else if (formUser.getUserType().equalsIgnoreCase("LIBRARIAN")) {
				requestDispatcher = request
						.getRequestDispatcher("/jsp/LibrarianInfo.jsp");
				requestDispatcher.forward(request, response);
			} else if (formUser.getUserType().equalsIgnoreCase("ADMIN")) {
				requestDispatcher = request
						.getRequestDispatcher("/jsp/AdminInformation.jsp");
				requestDispatcher.forward(request, response);
			}

		}
	}
}
