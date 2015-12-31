package com.xoriant.servlet;

import static com.xoriant.business.BusinessModifyUserByAdmin.businessGetUserDetails;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xoriant.servlet.form.FormUser;

/**
 * Servlet implementation class AdminGetSelIinfoServlet
 */
public class AdminGetSelfInformationServlet extends HttpServlet {
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

	protected void doCommon(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		RequestDispatcher requestDispatcher = null;
		HttpSession session = httpServletRequest.getSession(true);
		if (session.getAttribute("emailID") == null) {
			session.invalidate();
			response.sendRedirect("/LibraryManagementSystem/jsp/HomeTestLogin.jsp");
		} else {
			String emailID = request.getParameter("emailID");

			FormUser formUser2 = new FormUser();
			formUser2.setEmailID(emailID);

			FormUser formUser;

			try {
				formUser = businessGetUserDetails(formUser2);

				request.setAttribute("formUser", formUser);
				requestDispatcher = request
						.getRequestDispatcher("/jsp/AdminInformation.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e) {
				requestDispatcher = request
						.getRequestDispatcher("/jsp/Error.jsp");
				requestDispatcher.forward(request, response);
			}
		}
	}
}
