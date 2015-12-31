package com.xoriant.servlet;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static com.xoriant.business.BusinessIssueBook.businessGetDates;
import static com.xoriant.business.BusinessIssueBook.businessGetIssuedBook;
import static com.xoriant.business.BusinessIssueBook.businessIssueBook;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xoriant.filter.SessionFilter;
import com.xoriant.servlet.form.FormBook;
import com.xoriant.servlet.form.FormBookStatus;
import com.xoriant.servlet.form.FormUser;

/**
 * Servlet implementation class IssueBookServlet This is used to issue a book to
 * a user
 */
public class IssueBookServlet extends HttpServlet {
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
	 * Issuing book
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
			logger.info("In IssueBooKServlet");
			String emailID = request.getParameter("emailID");
			int copyID = Integer.parseInt(request.getParameter("copyID"));
			FormUser formUser = new FormUser();
			formUser.setEmailID(emailID);

			try {
				businessIssueBook(copyID, formUser);
				FormBook formBook = businessGetIssuedBook(copyID, formUser);
				FormBookStatus formBookStatus = businessGetDates(copyID,
						formUser);
				request.setAttribute("formBook", formBook);
				request.setAttribute("formBookStatus", formBookStatus);
				requestDispatcher = request
						.getRequestDispatcher("/jsp/GetIssuedBook.jsp");
				requestDispatcher.forward(request, response);
			} catch (Exception e) {
				logger.error(e);
				requestDispatcher = request
						.getRequestDispatcher("/jsp/IssueBookError.jsp");
				requestDispatcher.forward(request, response);
			}

		}
	}
}
