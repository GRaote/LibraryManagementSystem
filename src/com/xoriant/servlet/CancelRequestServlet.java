package com.xoriant.servlet;

import static com.xoriant.business.BusinessReserve.businessCancelBookRequest;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xoriant.servlet.form.FormBookRequestStatus;

/**
 * Servlet implementation class CheckBookRequestServlet
 */
public class CancelRequestServlet extends HttpServlet {

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
			int copyID = Integer.parseInt(request.getParameter("copyID"));
			String emailID = request.getParameter("emailID");
			FormBookRequestStatus formBookRequestStatus = new FormBookRequestStatus();
			formBookRequestStatus.setCopyID(copyID);
			formBookRequestStatus.setEmailID(emailID);
			businessCancelBookRequest(formBookRequestStatus);
			requestDispatcher = request
					.getRequestDispatcher("/jsp/CheckBookRequest2.jsp");
			requestDispatcher.forward(request, response);

		}
	}
}
