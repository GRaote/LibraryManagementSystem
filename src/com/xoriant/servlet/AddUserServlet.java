package com.xoriant.servlet;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static com.xoriant.business.BusinessUserAdd.businessAddUser;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xoriant.business.BusinessUser;
import com.xoriant.filter.SessionFilter;
import com.xoriant.servlet.form.FormUser;

/**
 * Servlet implementation class AddUserServlet
 */
public class AddUserServlet extends HttpServlet {
	static final Logger logger = LogManager.getLogger(AddUserServlet.class
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
		} catch (NumberFormatException | SQLException e) {
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
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Adds user to database
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 * @throws NumberFormatException
	 */
	protected void doCommon(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			NumberFormatException, SQLException {
		logger.info("In AddUserServlet");
		logger.info(request.getParameter("region"));
		logger.info(request.getParameter("userType"));
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		RequestDispatcher requestDispatcher = null;
		HttpSession session = httpServletRequest.getSession(true);
		if (session.getAttribute("emailID") == null) {
			session.invalidate();
			response.sendRedirect("/LibraryManagementSystem/jsp/HomeTestLogin.jsp");
		} else {

			FormUser formUser = new FormUser();
			formUser.setEmailID(request.getParameter("emailID"));
			if (BusinessUser.businessCheckIfUserExists(formUser)) {
				String temp = "EmailID Already Exists";
				request.setAttribute("temp", temp);
				requestDispatcher = request
						.getRequestDispatcher("/jsp/AddUser.jsp");
				requestDispatcher.forward(request, response);

			} else {

				String fullName = request.getParameter("fullName");
				String userName = request.getParameter("userName");
				String password = request.getParameter("password");
				String startDate = request.getParameter("txtDate");

				java.util.Date date = null;
				Date dateOfBirth = null;
				try {
					SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
					date = sdf1.parse(startDate);

					java.sql.Date sqlStartDate = new Date(date.getTime());
					dateOfBirth = sqlStartDate;

				} catch (ParseException e) {

					requestDispatcher = request
							.getRequestDispatcher("/jsp/Error.jsp");
					requestDispatcher.forward(request, response);
				}
				String emailID = request.getParameter("emailID");

				String addressFirstLine = request
						.getParameter("addressFirstLine");

				String addressSecondLine = request
						.getParameter("addressSecondLine");

				String city = request.getParameter("region");

				String state = request.getParameter("city");

				int zipCode = Integer.parseInt(request.getParameter("zip"));
				String userType = request.getParameter("userType");

				formUser = new FormUser();
				formUser.setFullName(fullName);
				formUser.setUserName(userName);

				formUser.setPassword(password);
				formUser.setDateOfBirth(dateOfBirth);
				formUser.setEmailID(emailID);
				formUser.setAddressFirstLine(addressFirstLine);
				formUser.setAddressSecondLine(addressSecondLine);
				formUser.setCity(city);
				formUser.setState(state);
				formUser.setZipCode(zipCode);
				formUser.setUserType(userType);

				try {
					businessAddUser(formUser);
					request.setAttribute("emailID", emailID);
					request.setAttribute("formUser", formUser);
					String temp = "User Added Succesfully";
					request.setAttribute("temp", temp);
					requestDispatcher = request
							.getRequestDispatcher("/jsp/AdminPortal.jsp");
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
