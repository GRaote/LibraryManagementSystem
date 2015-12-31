package com.xoriant.business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import com.xoriant.dao.BookIssueDAO;
import com.xoriant.dao.contract.IBookIssueDAO;
import com.xoriant.servlet.form.FormBook;
import com.xoriant.servlet.form.FormUser;

public class BusinessReserveBook {
	public static String businessGetAction(int copyID, String emailID)
			throws SQLException {
		IBookIssueDAO bookIssueDAO = new BookIssueDAO();
		boolean reserve = bookIssueDAO.checkReserveBook1(copyID, emailID);
		boolean renew = bookIssueDAO.checkRenew1(copyID, emailID);
		if (reserve) {
			return "RESERVE";
		} else if (renew) {
			return "RENEW";
		} else {
			return "NOACTION";
		}
	}

	// change this to disallow reserving different copy of same book
	public static void businessReserveBook(FormUser formUser,
			FormBook formBook, String action) throws SQLException {

		IBookIssueDAO bookIssueDAO = new BookIssueDAO();
		String emailID = formUser.getEmailID();
		boolean check = false;
		int copyID = 0;
		int bookID = formBook.getBookID();

		ArrayList<Integer> arrayList = bookIssueDAO.getCopyID(bookID);
		Iterator<Integer> itr = arrayList.iterator();
		while (itr.hasNext()) {
			copyID = itr.next();
			if (bookIssueDAO.checkRenew1(copyID, emailID)) {
				bookIssueDAO.renewBook(copyID, emailID);
				break;
			} else if (bookIssueDAO.checkReserveBook1(copyID, emailID)) {
				check = true;
				break;
			}
		}
		if (check) {
			bookIssueDAO.reserveBook1(copyID, emailID);
		}

	}
}
