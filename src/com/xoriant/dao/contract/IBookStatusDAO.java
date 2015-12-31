package com.xoriant.dao.contract;

import java.sql.SQLException;
import java.util.ArrayList;

import com.xoriant.dao.pojo.BookStatus;

public interface IBookStatusDAO {
	public ArrayList<BookStatus> getPendingReturn() throws SQLException;
	
	public BookStatus getIssuedBookStatus(int copyID, String emailID)
			throws SQLException;
	
	public ArrayList<BookStatus> getAllIssuedBookStatus(String emailID)
			throws SQLException;
	
	public String getBookStatusFromCopyID(int copyID) throws SQLException;
}
