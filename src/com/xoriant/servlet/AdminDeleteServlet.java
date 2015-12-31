package com.xoriant.servlet;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

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

import static com.xoriant.business.BusinessUserAdd.businessDeleteUser;
import static com.xoriant.business.BusinessIssueBook.businessGetDatesList;

import com.xoriant.business.BusinessUser;
import com.xoriant.filter.SessionFilter;
import com.xoriant.servlet.form.FormBookStatus;
import com.xoriant.servlet.form.FormUser;

/**
 * Servlet implementation class AdminDeleteServlet
 */
public class AdminDeleteServlet extends HttpServlet {
	static final Logger logger = LogManager.getLogger(AdminDeleteServlet.class
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
	 * Deletes user from the database
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
			logger.info("In AdminDeleteServlet");
			int count = 0;

			String emailID = request.getParameter("emailID");
			FormUser formUser = new FormUser();
			formUser.setEmailID(emailID);
			if (emailID.equalsIgnoreCase("adarsh.hegde@xoriant.com")
					|| emailID.equalsIgnoreCase("tehreem.ansari@xoriant.com")) {
				String admin = "You cannot delete this particular user";
				request.setAttribute("admin", admin);
				requestDispatcher = request
						.getRequestDispatcher("/jsp/AdminDeleteUser.jsp");
				requestDispatcher.forward(request, response);
			} else {

				FormUser formDelUser = new FormUser();

				formDelUser.setEmailID(emailID);

				ArrayList<FormBookStatus> formBookStatus = null;
				try {
					formBookStatus = businessGetDatesList(formDelUser);
				} catch (SQLException e) {
					logger.error(e);
					requestDispatcher = request
							.getRequestDispatcher("/jsp/Error.jsp");
					requestDispatcher.forward(request, response);
				}
				Iterator<FormBookStatus> itr = formBookStatus.iterator();
				while (itr.hasNext()) {
					itr.next();
					count++;
				}
				if (count >= 1) {
					String admin = "This user has issued books , you cannot delete him";
					request.setAttribute("admin", admin);
					requestDispatcher = request
							.getRequestDispatcher("/jsp/AdminDeleteUser.jsp");
					requestDispatcher.forward(request, response);
				} else if (BusinessUser.businessCheckIfUserExists(formUser)) {
					try {
						businessDeleteUser(formDelUser);
					} catch (SQLException e) {
						logger.error(e);
						requestDispatcher = request
								.getRequestDispatcher("/jsp/Error.jsp");
						requestDispatcher.forward(request, response);
					}
					String temp = "Deleted the User Succesfully";
					request.setAttribute("temp", temp);
					requestDispatcher = request
							.getRequestDispatcher("/jsp/AdminDeleteUser.jsp");
					requestDispatcher.forward(request, response);
				} else {
					String admin = "No user exists with this emailID";
					request.setAttribute("admin", admin);
					requestDispatcher = request
							.getRequestDispatcher("/jsp/AdminDeleteUser.jsp");
					requestDispatcher.forward(request, response);
				}
			}

		}
	}
}
