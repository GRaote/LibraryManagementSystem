package com.xoriant.business;

import java.sql.SQLException;

import com.xoriant.dao.BookDetailsDAO;
import com.xoriant.dao.UserDAO;

public class BusinessUtil {
	public int businessGetNoOfRecords() throws SQLException {
		UserDAO user = new UserDAO();
		int noOfRecords = user.getNoOfRecords();
		return noOfRecords;
	}

	public int businessGetNoOfBookRecords() throws SQLException {
		BookDetailsDAO book = new BookDetailsDAO();
		int noOfRecords = book.getNoOfBookRecords();
		return noOfRecords;
	}
}
