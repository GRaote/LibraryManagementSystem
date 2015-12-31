package com.xoriant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.xoriant.dao.contract.IBookStatusDAO;
import com.xoriant.dao.pojo.BookStatus;
import com.xoriant.dao.supp.ConnectionFactory;

/**
 * 
 * @author hegde_a
 * 
 */
public class BookStatusDAO implements IBookStatusDAO {
	/**
	 * retrieves the list of records which have pending status i.e they have
	 * crossed the duedate
	 */
	@Override
	public ArrayList<BookStatus> getPendingReturn() throws SQLException {
		ArrayList<BookStatus> arrayList = new ArrayList<>();
		Connection connection = ConnectionFactory.getConnection();
		String query = "select * from BOOK_STATUS WHERE dueDate < curdate()";
		PreparedStatement pStatement = connection.prepareStatement(query);
		ResultSet resultset = pStatement.executeQuery();
		while (resultset.next()) {
			arrayList.add(new BookStatus(resultset.getString(1), resultset
					.getString(2), resultset.getDate(3), resultset.getDate(4),
					resultset.getInt(5)));
		}
		return arrayList;
	}

	/**
	 * retrieves the status record of the book issued by the user
	 * 
	 * @param copyID
	 * @param emailID
	 * @return
	 * @throws SQLException
	 */
	public BookStatus getIssuedBookStatus(int copyID, String emailID)
			throws SQLException {
		BookStatus bookStatus = null;
		Connection connection = ConnectionFactory.getConnection();
		String query = "select * from BOOK_STATUS WHERE emailID=? and copyID=?";
		PreparedStatement pStatement = connection.prepareStatement(query);
		pStatement.setString(1, emailID);
		pStatement.setInt(2, copyID);
		ResultSet resultset = pStatement.executeQuery();
		if (resultset.next()) {
			bookStatus = new BookStatus(resultset.getString(1),
					resultset.getString(2), resultset.getDate(3),
					resultset.getDate(4), resultset.getInt(5));
		}
		return bookStatus;
	}

	/**
	 * retrieves the list of status records issued by the user
	 * 
	 * @param emailID
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<BookStatus> getAllIssuedBookStatus(String emailID)
			throws SQLException {
		ArrayList<BookStatus> arrayList = new ArrayList<>();
		Connection connection = ConnectionFactory.getConnection();
		String query = "select * from BOOK_STATUS WHERE emailID=? and bookStatus=?";
		PreparedStatement pStatement = connection.prepareStatement(query);
		pStatement.setString(1, emailID);
		pStatement.setString(2, "ISSUED");
		ResultSet resultset = pStatement.executeQuery();
		while (resultset.next()) {
			arrayList.add(new BookStatus(resultset.getString(1), resultset
					.getString(2), resultset.getDate(3), resultset.getDate(4),
					resultset.getInt(5)));
		}
		return arrayList;
	}

	/**
	 * retrieves the book status from the copyID of the book
	 * 
	 * @param copyID
	 * @return
	 * @throws SQLException
	 */
	public String getBookStatusFromCopyID(int copyID) throws SQLException {
		String bookStatus = "";
		Connection connection = ConnectionFactory.getConnection();
		String query = "select bookStatus from BOOK_STATUS WHERE copyID=?";
		PreparedStatement pStatement = connection.prepareStatement(query);
		pStatement.setInt(1, copyID);
		ResultSet resultset = pStatement.executeQuery();
		bookStatus = resultset.getString("bookStatus");
		return bookStatus;
	}
}
