package com.xoriant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import com.xoriant.dao.contract.IBookRequestStatusDAO;
import com.xoriant.dao.pojo.BookRequestStatus;
import com.xoriant.dao.supp.ConnectionFactory;

/**
 * 
 * @author raote_g
 * 
 */
public class BookRequestStatusDAO implements IBookRequestStatusDAO {
	/**
	 * Retrieves the request status for the book raised by the user
	 */
	// NOT REQUIRED
	@Override
	public String getBookRequestStatus(String emailID, int copyID)
			throws SQLException {
		String requestStatus = "";
		Connection connection = ConnectionFactory.getConnection();
		String query = "SELECT * from BOOK_REQUEST_STATUS  WHERE emailID=? and copyID=?";
		PreparedStatement pStatement = connection.prepareStatement(query);
		pStatement.setString(1, emailID);
		pStatement.setInt(2, copyID);
		ResultSet resultSet = pStatement.executeQuery();
		if (resultSet.next()) {
			requestStatus = resultSet.getString(3);
		}
		return requestStatus;

	}

	/**
	 * retrieves all the books requested by the user for the librarian
	 */
	// MODIFIED AND WORKING AS EXPECTED
	// Create method to get books details from copyID of this returned list
	@Override
	public ArrayList<BookRequestStatus> getAllRequestedBooks()
			throws SQLException {
		ArrayList<BookRequestStatus> requestList = new ArrayList<>();
		ArrayList<BookRequestStatus> requestListFinal = new ArrayList<>();
		Connection connection = ConnectionFactory.getConnection();
		String query = "SELECT * from BOOK_REQUEST_STATUS where bookRequestStatus in(?,?)";
		PreparedStatement pStatement = connection.prepareStatement(query);
		pStatement.setString(1, "NEW");
		pStatement.setString(2, "RENEW");
		ResultSet resultSet = pStatement.executeQuery();
		while (resultSet.next()) {
			requestList.add(new BookRequestStatus(resultSet.getString(1),
					resultSet.getInt(2), resultSet.getString(3)));
		}
		Iterator<BookRequestStatus> itr = requestList.iterator();
		BookRequestStatus bookRequestStatus = null;
		while (itr.hasNext()) {
			bookRequestStatus = itr.next();
			String query1 = "SELECT * from BOOK_STATUS where emailID=? and copyID=? and bookStatus=?";
			PreparedStatement pStatement1 = connection.prepareStatement(query1);
			pStatement1.setString(1, bookRequestStatus.getEmailID());
			pStatement1.setInt(2, bookRequestStatus.getCopyID());
			pStatement1.setString(3, "ISSUED");
			ResultSet resultSet1 = pStatement1.executeQuery();
			if (resultSet1.next()
					&& bookRequestStatus.getRequestStatus().equals("NEW")) {
			} else {
				requestListFinal.add(bookRequestStatus);
			}
		}
		return requestListFinal;

	}

	/**
	 * cancels the book request status for the given copy of the book
	 */

	public void cancelBookRequest(int copyID, String emailID)
			throws SQLException {

		Connection connection = ConnectionFactory.getConnection();
		String query = "SELECT * FROM BOOK_REQUEST_STATUS WHERE copyID=? and emailID=?";
		PreparedStatement pStatement = connection.prepareStatement(query);
		pStatement.setInt(1, copyID);
		pStatement.setString(2, emailID);
		ResultSet resultSet = pStatement.executeQuery();
		if (resultSet.next()) {
			if (resultSet.getString("bookRequestStatus").equals("RENEW")) {
				String query2 = "UPDATE BOOK_REQUEST_STATUS set bookRequestStatus=? where copyID=? and emailId=?";
				PreparedStatement pStatement2 = connection
						.prepareStatement(query2);
				pStatement2.setString(1, "NEW");
				pStatement2.setInt(2, copyID);
				pStatement2.setString(3, emailID);
				pStatement2.executeUpdate();
			} else if (resultSet.getString("bookRequestStatus").equals("NEW")) {
				String query3 = "SELECT * FROM BOOK_STATUS WHERE bookStatus=? and copyID=? and emailID=?";
				PreparedStatement pStatement3 = connection
						.prepareStatement(query3);
				pStatement3.setString(1, "AVAILABLE");
				pStatement3.setInt(2, copyID);
				pStatement3.setString(3, emailID);
				ResultSet resultSet2 = pStatement3.executeQuery();
				if (resultSet2.next()) {
					String query4 = "UPDATE BOOK_REQUEST_STATUS set bookRequestStatus=? where emailID=? and copyID=?";
					PreparedStatement pStatement4 = connection
							.prepareStatement(query4);
					pStatement4.setString(1, "CLOSED");
					pStatement4.setString(2, emailID);
					pStatement4.setInt(3, copyID);
					pStatement4.executeUpdate();

					String query5 = "UPDATE BOOK_STATUS set emailID=? where copyID=?";
					PreparedStatement pStatement5 = connection
							.prepareStatement(query5);
					pStatement5.setString(1, "tehreem.ansari@xoriant.com");
					pStatement5.setInt(2, copyID);
					pStatement5.executeUpdate();
				}
			}
			if (resultSet.getString("bookRequestStatus").equals("NEW")) {
				String query3 = "SELECT * FROM BOOK_STATUS WHERE bookStatus=? and copyID=? and emailID!=?";
				PreparedStatement pStatement3 = connection
						.prepareStatement(query3);
				pStatement3.setString(1, "ISSUED");
				pStatement3.setInt(2, copyID);
				pStatement3.setString(3, emailID);
				ResultSet resultSet2 = pStatement3.executeQuery();
				if (resultSet2.next()) {
					String query4 = "UPDATE BOOK_REQUEST_STATUS set bookRequestStatus=? where emailID=? and copyID=?";
					PreparedStatement pStatement4 = connection
							.prepareStatement(query4);
					pStatement4.setString(1, "CLOSED");
					pStatement4.setString(2, emailID);
					pStatement4.setInt(3, copyID);
					pStatement4.executeUpdate();
				}
			}
		}

	}

	@Override
	public boolean ifIssuedOrRequested(String emailID, int copyID)
			throws SQLException {
		BookIssueDAO bookIssueDAO = new BookIssueDAO();
		int bookID = bookIssueDAO.getBookID(copyID);
		Connection connection = ConnectionFactory.getConnection();
		String query = "select * from book_request_status where emailID=? and bookRequestStatus not in (?)";
		PreparedStatement preparedStatement = connection
				.prepareStatement(query);
		preparedStatement.setString(1, emailID);
		preparedStatement.setString(2, "CLOSED");
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			int userBook = bookIssueDAO.getBookID(resultSet.getInt("copyID"));
			if (userBook == bookID) {
				return true;
			}
		}
		return false;
	}
}
