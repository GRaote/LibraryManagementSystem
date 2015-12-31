package com.xoriant.servlet;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static com.xoriant.business.BusinessUpdateBook.businessAddBook;
import static com.xoriant.business.BusinessUpdateBook.businessDeleteBook;
import static com.xoriant.business.BusinessUpdateBook.businessGetBook;
import static com.xoriant.business.BusinessUpdateBook.businessModifyBook;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xoriant.business.BusinessBookDetails;
import com.xoriant.filter.SessionFilter;
import com.xoriant.servlet.form.FormBook;

/**
 * Servlet implementation class UpdateBookServlet
 */
public class UpdateBookServlet extends HttpServlet {
	static final Logger logger = LogManager.getLogger(UpdateBookServlet.class
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
	 * Add/Delete/Modifies the book
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
		String action = request.getParameter("action");
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		RequestDispatcher requestDispatcher = null;
		HttpSession session = httpServletRequest.getSession(true);
		if (session.getAttribute("emailID") == null) {
			session.invalidate();
			response.sendRedirect("/LibraryManagementSystem/jsp/HomeTestLogin.jsp");
		} else {
			logger.info("In UpdateBookServlet");
			if ("ADD".equals(action)) {
				FormBook formBook = new FormBook(1,
						request.getParameter("title"),
						request.getParameter("author"),
						Double.parseDouble(request.getParameter("price")),
						request.getParameter("publicationName"),
						Integer.parseInt(request.getParameter("bookCount")));

				try {
					businessAddBook(formBook);

					requestDispatcher = request
							.getRequestDispatcher("/jsp/AddNewBook.jsp");
					request.setAttribute("op", "added");
					requestDispatcher.forward(request, response);
				} catch (Exception e) {
					String temp = "Cannot add the book, Please Enter Valid Information";
					request.setAttribute("temp", temp);
					requestDispatcher = request
							.getRequestDispatcher("/jsp/AddNewBook.jsp");
					requestDispatcher.forward(request, response);
				}

			} else if ("DELETE".equals(action)) {
				FormBook formBook = new FormBook();
				formBook.setBookID(Integer.parseInt(request
						.getParameter("bookID")));
				if (BusinessBookDetails.businessCheckIfBookExists(formBook)) {
					try {
						businessDeleteBook(formBook);
					} catch (Exception e) {
						String temp = "Cannot delete the book";
						request.setAttribute("temp", temp);
						requestDispatcher = request
								.getRequestDispatcher("/jsp/AddNewBook.jsp");
						request.setAttribute("op", "deleted");
						requestDispatcher.forward(request, response);
					}
					request.setAttribute("op", "deleted");
					requestDispatcher = request
							.getRequestDispatcher("/BookInformation");
					requestDispatcher.forward(request, response);
				} else {
					String temp = "Book with this ID doesn't exist";
					request.setAttribute("temp", temp);
					requestDispatcher = request
							.getRequestDispatcher("/jsp/AddNewBook.jsp");
					requestDispatcher.forward(request, response);
				}

			} else if ("UPDATE".equals(action)) {
				FormBook formBook = new FormBook();
				formBook.setBookID(Integer.parseInt(request
						.getParameter("bookID")));
				if (BusinessBookDetails.businessCheckIfBookExists(formBook)) {

					FormBook formBook2 = businessGetBook(formBook);
					request.setAttribute("formBook", formBook2);

					requestDispatcher = request
							.getRequestDispatcher("/jsp/UpdateBook.jsp");
					requestDispatcher.forward(request, response);
				} else {
					String temp = "Book with this ID doesn't exist";
					request.setAttribute("temp", temp);
					requestDispatcher = request
							.getRequestDispatcher("/jsp/UpdateBook.jsp");
					requestDispatcher.forward(request, response);

				}

			} else if ("MODIFY".equals(action)) {
				if (request.getParameter("bookID") == "") {
					String temp = "Cannot Modify Directly , Update Via Book Information Tab";
					request.setAttribute("temp", temp);
					requestDispatcher = request
							.getRequestDispatcher("/jsp/UpdateBook.jsp");
					requestDispatcher.forward(request, response);
				} else {
					FormBook formBook = new FormBook(Integer.parseInt(request
							.getParameter("bookID")),
							request.getParameter("title"),
							request.getParameter("author"),
							Double.parseDouble(request.getParameter("price")),
							request.getParameter("publicationName"),
							Integer.parseInt(request.getParameter("bookCount")));
					try {
						businessModifyBook(formBook);
						requestDispatcher = request
								.getRequestDispatcher("/jsp/UpdateBook.jsp");
						request.setAttribute("op", "modified");
						requestDispatcher.forward(request, response);
					} catch (Exception e) {
						requestDispatcher = request
								.getRequestDispatcher("/jsp/Error.jsp");
						requestDispatcher.forward(request, response);
					}

				}
			}
		}
	}
}
