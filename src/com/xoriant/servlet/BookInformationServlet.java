package com.xoriant.servlet;

import static com.xoriant.business.BusinessBookDetails.businessGetBookDetails;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.xoriant.business.BusinessUtil;
import com.xoriant.servlet.form.FormBook;

/**
 * Servlet implementation class BookInformationServlet
 */
public class BookInformationServlet extends HttpServlet {
	static final Logger logger = LogManager
			.getLogger(BookInformationServlet.class.getName());
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
		int page = 1;
		int recordsPerPage = 4;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		RequestDispatcher requestDispatcher = null;
		HttpSession session = httpServletRequest.getSession(true);
		if (session.getAttribute("emailID") == null) {
			session.invalidate();
			response.sendRedirect("/LibraryManagementSystem/jsp/HomeTestLogin.jsp");
		} else {
			logger.info("In BookInformationServlet");
			String emailID = request.getParameter("emailID");

			ArrayList<FormBook> formBook = businessGetBookDetails((page - 1)
					* recordsPerPage, recordsPerPage);
			BusinessUtil businessUtil = new BusinessUtil();
			int noOfRecords = businessUtil.businessGetNoOfBookRecords();
			int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

			request.setAttribute("noOfPages", noOfPages);
			request.setAttribute("currentPage", page);
			logger.info(formBook);
			request.setAttribute("emailID", emailID);
			request.setAttribute("formBook", formBook);

			requestDispatcher = request
					.getRequestDispatcher("/jsp/BookInformation.jsp");
			requestDispatcher.forward(request, response);

		}
	}
}
