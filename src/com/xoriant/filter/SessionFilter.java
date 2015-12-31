package com.xoriant.filter;

import static com.xoriant.business.BusinessUser.businessGetUserDetails;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.xoriant.servlet.form.FormUser;

/**
 * Servlet Filter implementation class CreateSessionFilter
 */
public class SessionFilter implements Filter {
	static final Logger logger = LogManager.getLogger(SessionFilter.class
			.getName());

	/**
	 * Default constructor.
	 */
	public SessionFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 *      Checking valid credentials
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		logger.info("In SessionFilter");
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String emailID = httpServletRequest.getParameter("emailID");
		String password = httpServletRequest.getParameter("password");
		HttpSession session = httpServletRequest.getSession(true);
		session.setMaxInactiveInterval(300);
		FormUser formUser = new FormUser();
		formUser.setEmailID(emailID);
		formUser.setPassword(password);
		try {

			formUser = businessGetUserDetails(formUser);
			logger.info(formUser);
			String valid = formUser.getUserType();
			if (valid.equalsIgnoreCase("USER")
					|| valid.equalsIgnoreCase("ADMIN")
					|| valid.equalsIgnoreCase("LIBRARIAN")) {
				session.setAttribute("emailID", emailID);
				session.setAttribute("name", formUser.getUserName());
				session.setAttribute("password", password);
				session.setAttribute("userType", formUser.getUserType());

			}

			chain.doFilter(request, response);
			/**
			 * Handling incorrect credentials
			 */
		} catch (Exception e) {
			logger.error(e);
			String temp = "Please enter valid login details";
			request.setAttribute("temp", temp);
			RequestDispatcher requestDispatcher = null;
			requestDispatcher = request.getRequestDispatcher("/jsp/Home.jsp");
			requestDispatcher.forward(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
