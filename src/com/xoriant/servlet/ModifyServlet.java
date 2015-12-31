package com.xoriant.servlet;

import static com.xoriant.business.BusinessUser.businessGetUserDetails;
import static com.xoriant.business.BusinessUser.businessModifyUser;

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
 * Servlet implementation class ModifyServlet
 */
public class ModifyServlet extends HttpServlet {
	static final Logger logger = LogManager.getLogger(ModifyServlet.class
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
	 * Modifies the user information
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
			String emailID = request.getParameter("emailID");
			String fullName = request.getParameter("fullName");
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			String addressFirstLine = request.getParameter("addressFirstLine");
			String addressSecondLine = request
					.getParameter("addressSecondLine");
			int zipCode = Integer.parseInt(request.getParameter("zip"));
			String city = request.getParameter("city");
			String state = request.getParameter("region");

			FormUser formUser = new FormUser();
			formUser.setEmailID(emailID);
			formUser.setFullName(fullName);
			formUser.setUserName(userName);
			formUser.setPassword(password);
			formUser.setAddressFirstLine(addressFirstLine);
			formUser.setAddressSecondLine(addressSecondLine);
			formUser.setCity(city);
			formUser.setState(state);
			formUser.setZipCode(zipCode);
			logger.info(formUser);
			try {
				businessModifyUser(formUser);
				session.setAttribute("name", formUser.getUserName());
			} catch (Exception e) {
				logger.error(e);
				requestDispatcher = request
						.getRequestDispatcher("/jsp/Error.jsp");
				requestDispatcher.forward(request, response);
			}
			FormUser formUser2 = businessGetUserDetails(formUser);
			request.setAttribute("emailID", emailID);
			request.setAttribute("formUser", formUser2);

			requestDispatcher = request
					.getRequestDispatcher("/UserInformation");
			requestDispatcher.forward(request, response);

		}
	}
}
