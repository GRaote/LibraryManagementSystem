package com.xoriant.servlet;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static com.xoriant.business.BusinessIssueBook.businessGetBookFromCopy;
import static com.xoriant.business.BusinessIssueBook.businessGetPendingRequests;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xoriant.filter.SessionFilter;
import com.xoriant.servlet.form.FormBook;
import com.xoriant.servlet.form.FormBookStatus;
import com.xoriant.servlet.form.FormPendingRequest;

/**
 * Servlet implementation class PendingServlet
 */
public class PendingServlet extends HttpServlet {
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
	 * Provides the list of books which are issued and are not returned before
	 * the due date
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
			logger.info("In PendingServlet");

			try {
				ArrayList<FormBookStatus> arrayList = businessGetPendingRequests();
				Iterator<FormBookStatus> itr = arrayList.iterator();
				ArrayList<FormPendingRequest> arrayList2 = new ArrayList<>();

				while (itr.hasNext()) {
					FormBookStatus bookStatus = itr.next();
					FormBook formBook = businessGetBookFromCopy(bookStatus
							.getCopyID());
					arrayList2.add(new FormPendingRequest(bookStatus
							.getEmailID(), formBook.getBookID(), formBook
							.getTitle(), bookStatus.getDueDate()));
				}

				request.setAttribute("pendingList", arrayList2);

				requestDispatcher = request
						.getRequestDispatcher("/jsp/PendingBook.jsp");
				requestDispatcher.forward(request, response);

			} catch (SQLException e) {
				logger.error(e);
				requestDispatcher = request
						.getRequestDispatcher("/jsp/Error.jsp");
				requestDispatcher.forward(request, response);
			}
		}
	}

}
