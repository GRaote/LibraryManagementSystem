package com.xoriant.servlet;

import static com.xoriant.business.BusinessReserveBook.businessReserveBook;

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

import com.xoriant.servlet.form.FormBook;
import com.xoriant.servlet.form.FormUser;

/**
 * Servlet implementation class ReserveBookServlet
 */
public class ReserveBookServlet extends HttpServlet {
	static final Logger logger = LogManager.getLogger(ReserveBookServlet.class
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
	 * Reserving/Renewing Book.
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
			logger.info("In ReserveBookServlet");
			String emailID = request.getParameter("emailID");
			int bookID = Integer.parseInt(request.getParameter("bookID"));
			String getAction = request.getParameter("getAction");
			FormUser formUser = new FormUser();
			formUser.setEmailID(emailID);
			FormBook formBook = new FormBook();
			formBook.setBookID(bookID);
			businessReserveBook(formUser, formBook, getAction);
			request.setAttribute("emailID", emailID);
			request.setAttribute("action", getAction);
			requestDispatcher = request
					.getRequestDispatcher("/jsp/ReserveBook.jsp");
			requestDispatcher.forward(request, response);
		}
	}
}
