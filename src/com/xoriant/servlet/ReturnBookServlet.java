package com.xoriant.servlet;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static com.xoriant.business.BusinessIssueBook.businessUnIssueBook;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xoriant.filter.SessionFilter;
import com.xoriant.servlet.form.FormUser;

/**
 * Servlet implementation class ReturnBookServlet
 */
public class ReturnBookServlet extends HttpServlet {
	static final Logger logger = LogManager.getLogger(ReturnBookServlet.class
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
	 * Returns the book (UnIssue)
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
			logger.info("In ReturnBookServlet");
			String emailID = request.getParameter("emailID");
			int copyID = Integer.parseInt(request.getParameter("copyID"));
			FormUser formUser = new FormUser();
			formUser.setEmailID(emailID);
			boolean returnSuccess = false;
			try {
				returnSuccess = businessUnIssueBook(copyID, formUser);

				request.setAttribute("returnSuccess", returnSuccess);
				requestDispatcher = request
						.getRequestDispatcher("/jsp/ReturnBook2.jsp");
				requestDispatcher.forward(request, response);
			} catch (Exception e) {
				logger.error(e);
				requestDispatcher = request
						.getRequestDispatcher("/jsp/ReturnBookError.jsp");
				requestDispatcher.forward(request, response);
			}

		}
	}

}
