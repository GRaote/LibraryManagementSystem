package com.xoriant.servlet;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static com.xoriant.business.BusinessModifyUser.businessModifyUserByAdmin;

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
 * Servlet implementation class ModifyServlet
 */
public class ModifyAdminServlet extends HttpServlet {
	static final Logger logger = LogManager.getLogger(ModifyAdminServlet.class
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

	protected void doCommon(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			NumberFormatException, SQLException {
		String userType="";
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		RequestDispatcher requestDispatcher = null;
		HttpSession session = httpServletRequest.getSession(true);
		if (session.getAttribute("emailID") == null) {
			session.invalidate();
			response.sendRedirect("/LibraryManagementSystem/jsp/HomeTestLogin.jsp");
		} else {
			FormUser formUser1 = new FormUser();
			formUser1.setEmailID(request.getParameter("emailID"));
			if (!BusinessUser.businessCheckIfUserExists(formUser1)) {
				String temp1 = "User doesn't Exist, please get user details by emailID first";
				request.setAttribute("temp1", temp1);
				requestDispatcher = request
						.getRequestDispatcher("/jsp/AdminUserInfo.jsp");
				requestDispatcher.forward(request, response);
			} else {
				logger.info("In ModifyAdminServlet");
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
					logger.error(e);
					requestDispatcher = request
							.getRequestDispatcher("/jsp/Error.jsp");
					requestDispatcher.forward(request, response);
				}
				String emailID = request.getParameter("emailID");
	
				String addressFirstLine = request
						.getParameter("addressFirstLine");

				String addressSecondLine = request
						.getParameter("addressSecondLine");

				String city = request.getParameter("city");

				String state = request.getParameter("region");

				int zipCode = Integer.parseInt(request.getParameter("zip"));
				userType = request.getParameter("userType");
				if("tehreem.ansari@xoriant.com".equals(emailID))
				{
					userType = "LIBRARIAN";
				}
				if("adarsh.hegde@xoriant.com".equals(emailID))
				{
					userType = "ADMIN";
				}
				
				
			
				FormUser formUser = new FormUser();
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

				request.setAttribute("emailID", emailID);
				try {
					businessModifyUserByAdmin(formUser);
					session.setAttribute("name", formUser.getUserName());
				} catch (SQLException e) {
					logger.error(e);
					requestDispatcher = request
							.getRequestDispatcher("/jsp/Error.jsp");
					requestDispatcher.forward(request, response);
				}
				String temp = "User Modified";
				request.setAttribute("temp", temp);
				request.setAttribute("formUser", formUser);
				requestDispatcher = request
						.getRequestDispatcher("/jsp/AdminPortal.jsp");
				requestDispatcher.forward(request, response);

			}
		}
	}

}
