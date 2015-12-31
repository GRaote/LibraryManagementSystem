package com.xoriant.servlet;

import static com.xoriant.business.BusinessIssueBook.businessGetBookFromCopy;
import static com.xoriant.business.BusinessIssueBook.businessGetDatesList;
import static com.xoriant.business.BusinessIssueBook.businessGetRequestedBooks;

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

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.xoriant.servlet.form.FormBook;
import com.xoriant.servlet.form.FormBookStatus;
import com.xoriant.servlet.form.FormBookWithStatus;
import com.xoriant.servlet.form.FormUser;

/**
 * Servlet implementation class BookDetailsServlet
 */
public class BookDetailsServlet extends HttpServlet {
	static final Logger logger = LogManager.getLogger(BookDetailsServlet.class
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
	 * Finds all the Books Issued and Requested By the User
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
			logger.info("In BookDetailsServlet");
			String emailID = request.getParameter("emailID");
			FormUser formUser = new FormUser();
			formUser.setEmailID(emailID);
			logger.info(formUser);
			ArrayList<FormBookWithStatus> formBookWithStatus = new ArrayList<FormBookWithStatus>();
			ArrayList<FormBookStatus> formBookStatus = businessGetDatesList(formUser);
			Iterator<FormBookStatus> itr7 = formBookStatus.iterator();
			while (itr7.hasNext()) {
				FormBookStatus bookStatus = itr7.next();
				FormBook formBook = businessGetBookFromCopy(bookStatus
						.getCopyID());
				formBookWithStatus.add(new FormBookWithStatus(formBook
						.getTitle(), formBook.getAuthor(), formBook.getPrice(),
						formBook.getPublicationName(),
						bookStatus.getIssuedOn(), bookStatus.getDueDate()));

			}
			ArrayList<FormBook> arrayList = businessGetRequestedBooks(formUser);
			request.setAttribute("formBookWithStatus", formBookWithStatus);
			request.setAttribute("requestedBooks", arrayList);
			requestDispatcher = request
					.getRequestDispatcher("/jsp/BookDetails.jsp");
			requestDispatcher.forward(request, response);

		}
	}
}
