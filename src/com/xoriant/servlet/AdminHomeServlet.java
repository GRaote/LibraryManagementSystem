package com.xoriant.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xoriant.business.BusinessUser;
import com.xoriant.business.BusinessUtil;
import com.xoriant.servlet.form.FormUser;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class AdminHomeServlet
 */
public class AdminHomeServlet extends HttpServlet {
	static final Logger logger = LogManager.getLogger(AdminHomeServlet.class
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
			logger.info("In AdminHomeServlet");
			int page = 1;
			int recordsPerPage = 4;
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}

			BusinessUser businessUser = new BusinessUser();
			List<FormUser> formUsers = businessUser.businessGetAllUsers(
					(page - 1) * recordsPerPage, recordsPerPage);

			BusinessUtil businessUtil = new BusinessUtil();
			int noOfRecords = businessUtil.businessGetNoOfRecords();
			int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

			request.setAttribute("noOfPages", noOfPages);
			request.setAttribute("currentPage", page);
			request.setAttribute("formUsers", formUsers);
			requestDispatcher = request
					.getRequestDispatcher("/jsp/AdminAllUserInformation.jsp");
			requestDispatcher.forward(request, response);
		}
	}

}
