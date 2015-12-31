package com.xoriant.servlet;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.xoriant.business.BusinessModifyUserByAdmin.businessGetUserDetails;

import com.xoriant.filter.SessionFilter;
import com.xoriant.servlet.form.FormUser;

/**
 * Servlet implementation class AdminGetUserInfoServlet
 */
public class AdminGetUserInfoServlet extends HttpServlet {
	static final Logger logger = LogManager.getLogger(SessionFilter.class
			.getName());
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doCommon(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doCommon(request, response);
	}

	/**
	 * Get user information for the admin for that emailID
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doCommon(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		RequestDispatcher requestDispatcher = null;
		HttpSession session = httpServletRequest.getSession(true);
		if (session.getAttribute("emailID") == null) {
			session.invalidate();
			response.sendRedirect("/LibraryManagementSystem/jsp/HomeTestLogin.jsp");
		} else {
			logger.info("In AdminGetUserInfoServlet");
			String emailID = request.getParameter("emailID");

			FormUser formUser2 = new FormUser();
			formUser2.setEmailID(emailID);

			FormUser formUser;
			try {
				formUser = businessGetUserDetails(formUser2);
				request.setAttribute("formUser", formUser);
				requestDispatcher = request
						.getRequestDispatcher("/jsp/AdminUserInfo.jsp");
				requestDispatcher.forward(request, response);
			} catch (Exception e) {
				logger.error(e);
				String temp = "No User by this emailID found";
				request.setAttribute("temp", temp);
				requestDispatcher = request
						.getRequestDispatcher("/jsp/AdminUserInfo.jsp");
				requestDispatcher.forward(request, response);
			}

		}
	}
}
