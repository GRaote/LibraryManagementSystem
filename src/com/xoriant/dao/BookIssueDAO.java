package com.xoriant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import com.xoriant.dao.contract.IBookIssueDAO;
import com.xoriant.dao.pojo.Book;
import com.xoriant.dao.pojo.BookRequestStatus;
import com.xoriant.dao.pojo.BookStatus;
import com.xoriant.dao.supp.ConnectionFactory;

/**
 * 
 * @author hegde_a
 * 
 */
public class BookIssueDAO implements IBookIssueDAO {
	/**
	 * retrieves the list of copies of a book
	 */
	@Override
	public ArrayList<Integer> getCopyID(int bookID) throws SQLException {
		ArrayList<Integer> copyIDList = new ArrayList<>();
		Connection connection = ConnectionFactory.getConnection();
		String query = "SELECT copyID from COPY_TABLE  WHERE bookID=?";
		PreparedStatement pStatement = connection.prepareStatement(query);
		pStatement.setInt(1, bookID);
		ResultSet resultSet = pStatement.executeQuery();
		while (resultSet.next()) {
			copyIDList.add(resultSet.getInt(1));
		}
		return copyIDList;
	}

	/**
	 * retrieves the list of issued books by the user
	 */
	@Override
	// done
	public ArrayList<Book> getIssuedBooks(String emailID) throws SQLException {
		ArrayList<Book> arrayList = new ArrayList<>();

		Connection connection = ConnectionFactory.getConnection();
		String query = "SELECT * from BOOK_DETAILS WHERE bookID in (SELECT bookID from COPY_TABLE WHERE copyID in (SELECT copyID FROM BOOK_STATUS WHERE bookStatus=? and emailID=?))";
		PreparedStatement pStatement = connection.prepareStatement(query);
		pStatement.setString(1, "ISSUED");
		pStatement.setString(2, emailID);
		ResultSet resultSet = pStatement.executeQuery();

		while (resultSet.next()) {
			arrayList.add(new Book(resultSet.getInt(1), resultSet.getString(2),
					resultSet.getString(3), resultSet.getDouble(4), resultSet
							.getString(5), resultSet.getInt(6)));
		}
		return arrayList;

	}

	public static Date addDays(Date d, int days) {
		d.setTime(d.getTime() + days * 1000 * 60 * 60 * 24);
		return d;
	}

	/**
	 * This unissues the book for the given emailID
	 */

	/**
	 * retrieves the record of the book issued by the user using the copy and
	 * emailID
	 */
	@Override
	public Book getIssuedBook(int copyID, String emailID) throws SQLException {
		Book book = new Book();
		Connection connection = ConnectionFactory.getConnection();
		String query = "SELECT * from BOOK_DETAILS WHERE bookID in (SELECT bookID FROM COPY_TABLE WHERE copyID in(SELECT copyID from BOOK_STATUS WHERE bookStatus=? and emailID=? and copyID=?))";
		PreparedStatement pStatement = connection.prepareStatement(query);
		pStatement.setString(1, "ISSUED");
		pStatement.setString(2, emailID);
		pStatement.setInt(3, copyID);
		ResultSet resultSet = pStatement.executeQuery();

		if (resultSet.next()) {
			book = new Book(resultSet.getInt(1), resultSet.getString(2),
					resultSet.getString(3), resultSet.getDouble(4),
					resultSet.getString(5), resultSet.getInt(6));
		}
		return book;

	}

	/**
	 * retrieves the list of books reserved by the user for the user <-- for the
	 * user only
	 * 
	 * @param emailID
	 * @return
	 * @throws SQLException
	 */
	// working
	public ArrayList<Book> getAllRequestedBooks(String emailID)
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
			pStatement1.setString(1, emailID);
			pStatement1.setInt(2, bookRequestStatus.getCopyID());
			pStatement1.setString(3, "ISSUED");
			ResultSet resultSet1 = pStatement1.executeQuery();
			if (resultSet1.next()
					&& bookRequestStatus.getRequestStatus().equals("NEW")) {
			} else {
				requestListFinal.add(bookRequestStatus);
			}
		}
		ArrayList<Book> book = new ArrayList<>();

		Iterator<BookRequestStatus> itr1 = requestListFinal.iterator();
		BookRequestStatus bookRequestStatus1 = null;
		while (itr1.hasNext()) {
			bookRequestStatus1 = itr1.next();
			String query3 = "SELECT * from BOOK_DETAILS where bookID in (SELECT bookID from copy_table where copyID in (SELECT copyID from book_request_status where copyID=? and emailID=?))";
			PreparedStatement pStatement3 = connection.prepareStatement(query3);
			pStatement3.setInt(1, bookRequestStatus1.getCopyID());
			pStatement3.setString(2, emailID);
			ResultSet resultSet3 = pStatement3.executeQuery();
			while (resultSet3.next()) {
				book.add(new Book(resultSet3.getInt(1),
						resultSet3.getString(2), resultSet3.getString(3),
						resultSet3.getDouble(4), resultSet3.getString(5),
						resultSet3.getInt(6)));
			}

		}
		return book;
	}

	/**
	 * retrieves the number of books reserved by the user
	 * 
	 * @param emailID
	 * @return
	 * @throws SQLException
	 */
	public int getNumberOfReservedBooks(String emailID) throws SQLException {
		return 0;

	}

	/**
	 * checks if the user can renew the book
	 */
	@Override
	public boolean checkRenew(int copyID, String emailID) throws SQLException {
		return false;

	}

	// ==========================================================================================================
	/**
	 * renews the book for the user
	 */
	@Override
	public void renewBook(int copyID, String emailID) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		String query = "UPDATE BOOK_REQUEST_STATUS SET bookRequestStatus=? where copyID=? and emailID=?";
		PreparedStatement pStatement = connection.prepareStatement(query);
		pStatement.setString(1, "RENEW");
		pStatement.setInt(2, copyID);
		pStatement.setString(3, emailID);
		pStatement.executeUpdate();

	}

	public void reserveBook1(int copyID, String emailID) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		String query2 = "SELECT * from BOOK_REQUEST_STATUS where copyid=? and emailID=?";
		PreparedStatement pStatement2 = connection.prepareStatement(query2);
		pStatement2.setInt(1, copyID);
		pStatement2.setString(2, emailID);
		ResultSet resultSet2 = pStatement2.executeQuery();
		if (resultSet2.next()) {
			String query = "UPDATE BOOK_REQUEST_STATUS SET bookrequestStatus=? where copyid=? and emailid=?";
			PreparedStatement pStatement = connection.prepareStatement(query);
			pStatement.setString(1, "NEW");
			pStatement.setInt(2, copyID);
			pStatement.setString(3, emailID);
			pStatement.executeUpdate();
		} else {

			String query = "INSERT INTO BOOK_REQUEST_STATUS (copyID,emailID,bookRequestStatus) values(?,?,?)";
			PreparedStatement pStatement = connection.prepareStatement(query);
			pStatement.setInt(1, copyID);
			pStatement.setString(2, emailID);
			pStatement.setString(3, "NEW");
			pStatement.executeUpdate();
		}
		String query6 = "SELECT * from BOOK_STATUS  WHERE copyID=?";
		PreparedStatement pStatement6 = connection.prepareStatement(query6);

		pStatement6.setInt(1, copyID);
		BookStatus bookStatus = null;
		ResultSet resultSet6 = pStatement6.executeQuery();
		if (resultSet6.next()) {
			bookStatus = new BookStatus(resultSet6.getString(1),
					resultSet6.getString(2), resultSet6.getDate(3),
					resultSet6.getDate(4), resultSet6.getInt(5));
		}
		if (bookStatus.getEmailID().equals("tehreem.ansari@xoriant.com")) {

			String query3 = "UPDATE BOOK_STATUS SET emailID=? WHERE copyID=?";
			PreparedStatement pStatement3 = connection.prepareStatement(query3);
			pStatement3.setString(1, emailID);
			pStatement3.setInt(2, copyID);
			pStatement3.executeUpdate();
		}

	}

	// use this method to get NEW RENEW request. to be changed
	public int getNumberOfReservedBooksByEmail(String emailID)
			throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		ArrayList<BookRequestStatus> bookReqStatus = new ArrayList<>();
		String query = "SELECT * from BOOK_REQUEST_STATUS where emailID=? and bookRequestStatus in(?,?)";
		PreparedStatement pStatement = connection.prepareStatement(query);
		pStatement.setString(1, emailID);
		pStatement.setString(2, "NEW");
		pStatement.setString(3, "RENEW");

		ResultSet resultSet = pStatement.executeQuery();

		while (resultSet.next()) {
			BookRequestStatus bookStatus1 = new BookRequestStatus(
					resultSet.getString(1), resultSet.getInt(2),
					resultSet.getString(3));
			bookReqStatus.add(bookStatus1);
		}
		Iterator<BookRequestStatus> itr = bookReqStatus.iterator();
		int count = 0;
		while (itr.hasNext()) {
			itr.next();
			count++;
		}
		return count;
	}

	// to be changed
	public int getNumberOfReservedBooksByCopyId(int copyID) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		ArrayList<BookRequestStatus> bookReqStatus = new ArrayList<>();
		String query = "SELECT * from BOOK_REQUEST_STATUS where copyID=? and bookRequestStatus in(?,?)";
		PreparedStatement pStatement = connection.prepareStatement(query);
		pStatement.setInt(1, copyID);
		pStatement.setString(2, "NEW");
		pStatement.setString(3, "RENEW");

		ResultSet resultSet = pStatement.executeQuery();

		while (resultSet.next()) {
			BookRequestStatus bookStatus1 = new BookRequestStatus(
					resultSet.getString(1), resultSet.getInt(2),
					resultSet.getString(3));
			bookReqStatus.add(bookStatus1);
		}
		Iterator<BookRequestStatus> itr = bookReqStatus.iterator();
		int count = 0;
		while (itr.hasNext()) {
			itr.next();
			count++;
		}
		return count;
	}

	public boolean checkReserveBook1(int copyID, String emailID)
			throws SQLException {
		BookRequestStatusDAO bookRequestStatusDAO = new BookRequestStatusDAO();
		int newCount = 0;
		int renewCount = 0;
		Connection connection = ConnectionFactory.getConnection();
		int countBooks = getNumberOfReservedBooksByEmail(emailID);
		if (countBooks == 2
				|| bookRequestStatusDAO.ifIssuedOrRequested(emailID, copyID)) {
			return false;
		}

		ArrayList<BookRequestStatus> list = new ArrayList<>();
		String query = "SELECT * from BOOK_REQUEST_STATUS  WHERE copyID=?";
		PreparedStatement pStatement = connection.prepareStatement(query);
		pStatement.setInt(1, copyID);
		ResultSet resultSet = pStatement.executeQuery();
		BookRequestStatus bookRequestStatus = null;
		while (resultSet.next()) {
			bookRequestStatus = new BookRequestStatus(resultSet.getString(1),
					resultSet.getInt(2), resultSet.getString(3));
			list.add(bookRequestStatus);
		}
		Iterator<BookRequestStatus> itr = list.iterator();
		while (itr.hasNext()) {
			BookRequestStatus bookRequestStatus2 = itr.next();

			if (bookRequestStatus2.getRequestStatus().equals("NEW")) {
				newCount++;
			}
			if (bookRequestStatus2.getRequestStatus().equals("RENEW")) {
				renewCount++;
			}
		}
		if (renewCount >= 1) {
			return false;
		}
		if (newCount == 1) {
			String query2 = "SELECT * FROM BOOK_STATUS where copyID=?";
			PreparedStatement pStatement2 = connection.prepareStatement(query2);
			pStatement2.setInt(1, copyID);
			ResultSet resultSet2 = pStatement2.executeQuery();
			BookStatus bookStatus = null;
			if (resultSet2.next()) {
				bookStatus = new BookStatus(resultSet2.getString(1),
						resultSet2.getString(2), resultSet2.getDate(3),
						resultSet2.getDate(4), resultSet2.getInt(5));
			}
			if (bookStatus.getBookStatus().equals("AVAILABLE")) {
				return false;
			}
			if (bookStatus.getBookStatus().equals("ISSUED")
					&& bookStatus.getEmailID().equals(emailID)) {
				return false;
			}
			if (bookStatus.getBookStatus().equals("ISSUED")
					&& !bookStatus.getEmailID().equals(emailID)) {
				return true;
			}
		}
		if (newCount >= 2) {
			return false;
		}

		return true;
	}

	public boolean checkRenew1(int copyID, String emailID) throws SQLException {

		boolean flag = false;
		int newCount = 0;
		int renewCount = 0;
		Connection connection = ConnectionFactory.getConnection();
		ArrayList<BookRequestStatus> list = new ArrayList<>();
		String query = "SELECT * from BOOK_REQUEST_STATUS  WHERE copyID=?";
		PreparedStatement pStatement = connection.prepareStatement(query);
		pStatement.setInt(1, copyID);
		ResultSet resultSet = pStatement.executeQuery();
		BookRequestStatus bookRequestStatus = null;
		while (resultSet.next()) {
			bookRequestStatus = new BookRequestStatus(resultSet.getString(1),
					resultSet.getInt(2), resultSet.getString(3));
			list.add(bookRequestStatus);
		}
		Iterator<BookRequestStatus> itr = list.iterator();
		while (itr.hasNext()) {
			BookRequestStatus bookRequestStatus2 = itr.next();

			if (bookRequestStatus2.getRequestStatus().equals("NEW")) {
				newCount++;
			}
			if (bookRequestStatus2.getRequestStatus().equals("RENEW")) {
				renewCount++;
			}
		}
		if (renewCount >= 1) {
			return false;
		}
		if (newCount == 1) {
			String query2 = "SELECT * FROM BOOK_STATUS where copyID=?";
			PreparedStatement pStatement2 = connection.prepareStatement(query2);
			pStatement2.setInt(1, copyID);
			ResultSet resultSet2 = pStatement2.executeQuery();
			BookStatus bookStatus = null;
			if (resultSet2.next()) {
				bookStatus = new BookStatus(resultSet2.getString(1),
						resultSet2.getString(2), resultSet2.getDate(3),
						resultSet2.getDate(4), resultSet2.getInt(5));
			}
			if (bookStatus.getBookStatus().equals("ISSUED")
					&& bookStatus.getEmailID().equals(emailID)) {
				return true;
			}
		}
		if (newCount >= 2) {
			return false;
		}

		return flag;

	}

	public void issueBook1(int copyID, String emailID) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();

		String query = "SELECT * from BOOK_STATUS  WHERE  copyID=? and emailID=?";
		PreparedStatement pStatement = connection.prepareStatement(query);
		pStatement.setInt(1, copyID);
		pStatement.setString(2, emailID);
		ResultSet resultSet = pStatement.executeQuery();
		BookStatus bookStatus = null;
		if (resultSet.next()) {
			bookStatus = new BookStatus(resultSet.getString(1),
					resultSet.getString(2), resultSet.getDate(3),
					resultSet.getDate(4), resultSet.getInt(5));
			if (bookStatus.getBookStatus().equals("AVAILABLE")) {
				String query2 = "UPDATE BOOK_STATUS SET bookStatus=?,issuedOn=?,dueDate=? where copyID=?";
				PreparedStatement pStatement2 = connection
						.prepareStatement(query2);
				java.util.Date now2 = new java.util.Date();
				java.util.Date today = new java.util.Date();
				java.util.Date addedDate1 = addDays(now2, 15);

				pStatement2.setString(1, "ISSUED");
				pStatement2.setDate(2, new java.sql.Date(today.getTime()));
				pStatement2.setDate(3, new java.sql.Date(addedDate1.getTime()));
				pStatement2.setInt(4, bookStatus.getCopyID());
				pStatement2.executeUpdate();

				BookDetailsDAO bookDetailsDAO = new BookDetailsDAO();
				Book book = bookDetailsDAO.getBookFromCopyCount(bookStatus
						.getCopyID());
				int count = book.getBookCount();
				count--;
				String query3 = "UPDATE BOOK_DETAILS SET bookCount=? where bookID=?";
				PreparedStatement pStatement3 = connection
						.prepareStatement(query3);
				pStatement3.setInt(1, count);
				pStatement3.setInt(2, book.getBookID());
				pStatement3.executeUpdate();

			}
		} else {

		}

	}

	public void returnBook(int copyID, String emailID) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		// get row from book status
		String query = "SELECT * from BOOK_STATUS  WHERE  copyID=? and emailID=?";
		PreparedStatement pStatement = connection.prepareStatement(query);
		pStatement.setInt(1, copyID);
		pStatement.setString(2, emailID);
		ResultSet resultSet = pStatement.executeQuery();
		BookStatus bookStatus = null;

		if (resultSet.next()) {
			bookStatus = new BookStatus(resultSet.getString(1),
					resultSet.getString(2), resultSet.getDate(3),
					resultSet.getDate(4), resultSet.getInt(5));

			// change book status to available
			if (bookStatus.getBookStatus().equals("ISSUED")) {
				String query2 = "UPDATE BOOK_STATUS SET emailID=?,bookStatus=?,issuedOn=?,dueDate=? where copyID=?";
				PreparedStatement pStatement2 = connection
						.prepareStatement(query2);
				pStatement2.setString(1, "tehreem.ansari@xoriant.com");
				pStatement2.setString(2, "AVAILABLE");
				pStatement2.setDate(3, null);
				pStatement2.setDate(4, null);
				pStatement2.setInt(5, bookStatus.getCopyID());
				pStatement2.executeUpdate();

				BookDetailsDAO bookDetailsDAO = new BookDetailsDAO();
				Book book = bookDetailsDAO.getBookFromCopyCount(bookStatus
						.getCopyID());
				int count = book.getBookCount();
				count++;
				// increase book count
				String query6 = "UPDATE BOOK_DETAILS SET bookCount=? where bookID=?";
				PreparedStatement pStatement6 = connection
						.prepareStatement(query6);
				pStatement6.setInt(1, count);
				pStatement6.setInt(2, book.getBookID());
				pStatement6.executeUpdate();
			}
			// get row from book request status
			String query3 = "SELECT * from BOOK_REQUEST_STATUS  WHERE copyID=? and emailID=?";
			PreparedStatement pStatement3 = connection.prepareStatement(query3);
			pStatement3.setInt(1, bookStatus.getCopyID());
			pStatement3.setString(2, emailID);
			ResultSet resultSet3 = pStatement3.executeQuery();

			if (resultSet3.next()) {
				BookRequestStatus bookRequestStatus = new BookRequestStatus(
						resultSet3.getString(1), resultSet3.getInt(2),
						resultSet3.getString(3));
				// change his/her renew to new
				if (bookRequestStatus.getRequestStatus().equals("RENEW")) {
					String query4 = "UPDATE BOOK_REQUEST_STATUS set bookRequestStatus=? where copyID=? and emailID=?";
					PreparedStatement pStatement4 = connection
							.prepareStatement(query4);
					pStatement4.setString(1, "NEW");
					pStatement4.setInt(2, bookStatus.getCopyID());
					pStatement4.setString(3, emailID);
					pStatement4.executeUpdate();
					// if renewed by same person , it is already issued , so
					// issue it again because
					// it was unissued before in this method
					String query2 = "UPDATE BOOK_STATUS SET bookStatus=?,issuedOn=?,dueDate=?,emailID=? where copyID=?";
					PreparedStatement pStatement2 = connection
							.prepareStatement(query2);
					java.util.Date now2 = new java.util.Date();
					java.util.Date today = new java.util.Date();
					java.util.Date addedDate1 = addDays(now2, 15);

					pStatement2.setString(1, "ISSUED");
					pStatement2.setDate(2, new java.sql.Date(today.getTime()));
					pStatement2.setDate(3,
							new java.sql.Date(addedDate1.getTime()));
					pStatement2.setString(4, emailID);
					pStatement2.setInt(5, bookStatus.getCopyID());
					pStatement2.executeUpdate();
					// decrease bookcount again
					BookDetailsDAO bookDetailsDAO = new BookDetailsDAO();
					Book book = bookDetailsDAO.getBookFromCopyCount(bookStatus
							.getCopyID());
					int count = book.getBookCount();
					count--;
					String query8 = "UPDATE BOOK_DETAILS SET bookCount=? where bookID=?";
					PreparedStatement pStatement8 = connection
							.prepareStatement(query8);
					pStatement8.setInt(1, count);
					pStatement8.setInt(2, book.getBookID());
					pStatement8.executeUpdate();

				}
				// change his/her new to close
				if (bookRequestStatus.getRequestStatus().equals("NEW")) {
					String query5 = "UPDATE BOOK_REQUEST_STATUS set bookRequestStatus=? where copyID=? and emailID=?";
					PreparedStatement pStatement5 = connection
							.prepareStatement(query5);
					pStatement5.setString(1, "CLOSED");
					pStatement5.setInt(2, bookStatus.getCopyID());
					pStatement5.setString(3, emailID);
					pStatement5.executeUpdate();

				}
				// get another row of another person
				String query4 = "SELECT * from BOOK_REQUEST_STATUS  WHERE copyID=? and emailID!=? and bookRequestStatus in(?,?)";
				PreparedStatement pStatement4 = connection
						.prepareStatement(query4);
				pStatement4.setInt(1, bookStatus.getCopyID());
				pStatement4.setString(2, emailID);
				pStatement4.setString(3, "NEW");
				pStatement4.setString(4, "RENEW");
				ResultSet resultSet4 = pStatement4.executeQuery();

				if (resultSet4.next()) {
					BookRequestStatus bookRequestStatus1 = new BookRequestStatus(
							resultSet4.getString(1), resultSet4.getInt(2),
							resultSet4.getString(3));

					// reserve book for another person
					String query6 = "UPDATE BOOK_STATUS set emailID=? where copyID=?";
					PreparedStatement pStatement6 = connection
							.prepareStatement(query6);
					pStatement6.setString(1, bookRequestStatus1.getEmailID());
					pStatement6.setInt(2, bookStatus.getCopyID());
					pStatement6.executeUpdate();

				}
			}

		}
	}

	@Override
	public int getBookID(int copyID) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		String query = "SELECT * from COPY_TABLE  WHERE  copyID=?";
		PreparedStatement pStatement = connection.prepareStatement(query);
		pStatement.setInt(1, copyID);
		ResultSet resultSet = pStatement.executeQuery();
		int bookID = 0;
		if (resultSet.next()) {
			bookID = resultSet.getInt("bookID");
		}
		return bookID;

	}

}
