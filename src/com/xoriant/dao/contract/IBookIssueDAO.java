package com.xoriant.dao.contract;

import java.sql.SQLException;
import java.util.ArrayList;

import com.xoriant.dao.pojo.Book;

public interface IBookIssueDAO {
	public ArrayList<Integer> getCopyID(int bookID) throws SQLException;
	
	public int getBookID(int copyID) throws SQLException;

	public ArrayList<Book> getIssuedBooks(String emailID) throws SQLException;

	public Book getIssuedBook(int copyID, String emailID) throws SQLException;

	public void renewBook(int copyID,String emailID) throws SQLException;

	public boolean checkRenew(int copyID, String emailID) throws SQLException;

	public ArrayList<Book> getAllRequestedBooks(String emailID)
			throws SQLException;
	
	public int getNumberOfReservedBooks(String emailID) throws SQLException;
	
	public int getNumberOfReservedBooksByEmail(String emailID)
			throws SQLException;
	
	public int getNumberOfReservedBooksByCopyId(int copyID) throws SQLException;
	
	public boolean checkReserveBook1(int copyID, String emailID)
			throws SQLException;
	
	public boolean checkRenew1(int copyID, String emailID) throws SQLException;
	
	public void issueBook1(int copyID, String emailID) throws SQLException;
	
	public void returnBook(int copyID, String emailID) throws SQLException;
	
	public void reserveBook1(int copyID, String emailID) throws SQLException;

}
