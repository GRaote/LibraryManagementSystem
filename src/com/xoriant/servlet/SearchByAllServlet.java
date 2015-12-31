package com.xoriant.servlet;

import static com.xoriant.business.BusinessBookDetails.businessGetCopyList;
import static com.xoriant.business.BusinessReserveBook.businessGetAction;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xoriant.business.BusinessBookDetails;
import com.xoriant.servlet.form.FormBook;
import com.xoriant.servlet.form.FormUser;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class SearchByAllServlet extends HttpServlet {
	static final Logger logger = LogManager.getLogger(SearchByAllServlet.class
			.getName());
	private static final long serialVersionUID = 1L;
	FormBook formBook = new FormBook();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			doCommon(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			doCommon(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doCommon(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {

		logger.info("In SearchByAllServlet");
		String emailID = request.getParameter("emailID");
		FormUser formUser = new FormUser();
		RequestDispatcher requestDispatcher = null;
		formUser.setEmailID(emailID);

		ArrayList<FormBook> formBookList = new ArrayList<>();
		Set<FormBook> formBooks = new HashSet<>();
		BusinessBookDetails businessBookDetails = new BusinessBookDetails();
		formBooks = businessBookDetails.businessGetBookDetailsByAll(request
				.getParameter("search"));

		Iterator<FormBook> itr = formBooks.iterator();

		Set<FormBook> renewList = new HashSet<>();
		Set<FormBook> reserveList = new HashSet<>();
		Set<FormBook> noActionList = new HashSet<>();
		while (itr.hasNext()) {
			FormBook formBook2 = itr.next();
			int bookID = formBook2.getBookID();
			ArrayList<Integer> arrayList = businessGetCopyList(bookID);
			Iterator<Integer> itr1 = arrayList.iterator();

			boolean renew = false;
			boolean reserve = false;
			while (itr1.hasNext()) {
				String action = businessGetAction(itr1.next(), emailID);
				if (action.equalsIgnoreCase("RENEW")) {
					renew = true;
					break;
				} else if (action.equalsIgnoreCase("RESERVE")) {
					reserve = true;
				}
			}
			if (renew) {
				renewList.add(formBook2);
			} else if (reserve) {
				reserveList.add(formBook2);
			} else {
				noActionList.add(formBook2);
			}
		}
		request.setAttribute("renewList", renewList);
		request.setAttribute("reserveList", reserveList);
		request.setAttribute("noActionList", noActionList);
		request.setAttribute("emailID", emailID);
		request.setAttribute("formBookList", formBookList);
		request.setAttribute("formBooks", formBooks);

		requestDispatcher = request
				.getRequestDispatcher("/jsp/SearchInformation.jsp");
		requestDispatcher.forward(request, response);
	}

}
