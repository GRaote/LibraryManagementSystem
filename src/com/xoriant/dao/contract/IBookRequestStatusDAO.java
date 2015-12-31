package com.xoriant.dao.contract;

import java.sql.SQLException;
import java.util.ArrayList;

import com.xoriant.dao.pojo.BookRequestStatus;

public interface IBookRequestStatusDAO {
	public String getBookRequestStatus(String emailID, int copyID)
			throws SQLException;

	public ArrayList<BookRequestStatus> getAllRequestedBooks()
			throws SQLException;

	public void cancelBookRequest(int copyID, String emailID)
			throws SQLException;
	
	public boolean ifIssuedOrRequested(String emailID,int copyID) throws SQLException;
	
	
}
