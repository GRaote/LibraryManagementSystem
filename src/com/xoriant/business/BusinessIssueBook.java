package com.xoriant.business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import com.xoriant.dao.BookDetailsDAO;
import com.xoriant.dao.BookIssueDAO;
import com.xoriant.dao.BookStatusDAO;
import com.xoriant.dao.contract.IBookDetailsDAO;
import com.xoriant.dao.contract.IBookIssueDAO;
import com.xoriant.dao.contract.IBookStatusDAO;
import com.xoriant.dao.pojo.Book;
import com.xoriant.dao.pojo.BookStatus;
import com.xoriant.servlet.form.FormBook;
import com.xoriant.servlet.form.FormBookStatus;
import com.xoriant.servlet.form.FormUser;

/**
 * 
 * @author raote_g
 * 
 */
public class BusinessIssueBook {
	/**
	 * Issues the book for the given copy and user
	 * 
	 * @param copyID
	 * @param formUser
	 * @throws SQLException
	 */
	public static void businessIssueBook(int copyID, FormUser formUser)
			throws SQLException {
		String emailID = formUser.getEmailID();
		IBookIssueDAO bookIssueDAO = new BookIssueDAO();
		bookIssueDAO.issueBook1(copyID, emailID);
	}

	/**
	 * Un-issues the book for the given copy and the user
	 * 
	 * @param copyID
	 * @param formUser
	 * @return
	 * @throws SQLException
	 * @throws NullPointerException
	 */
	public static boolean businessUnIssueBook(int copyID, FormUser formUser)
			throws SQLException, NullPointerException {
		String emailID = formUser.getEmailID();
		IBookIssueDAO bookIssueDAO = new BookIssueDAO();
		if (businessGetIssuedBook(copyID, formUser).getAuthor().equals(null)) {
			return false;
		} else {
			bookIssueDAO.returnBook(copyID, emailID);
			return true;
		}
	}

	/**
	 * Gets the record of the book for a copy of the book issued by the user
	 * 
	 * @param copyID
	 * @param formUser
	 * @return
	 * @throws SQLException
	 */
	public static FormBook businessGetIssuedBook(int copyID, FormUser formUser)
			throws SQLException {
		String emailID = formUser.getEmailID();
		IBookIssueDAO bookIssueDAO = new BookIssueDAO();
		Book book = bookIssueDAO.getIssuedBook(copyID, emailID);

		FormBook formBook = new FormBook(book.getBookID(), book.getTitle(),
				book.getAuthor(), book.getPrice(), book.getPublicationName(),
				book.getBookCount());

		return formBook;
	}

	/**
	 * Returns a record containing the issue and due dates for the given copy
	 * issued by the given user
	 * 
	 * @param copyID
	 * @param formUser
	 * @return
	 * @throws SQLException
	 * @throws NullPointerException
	 */
	public static FormBookStatus businessGetDates(int copyID, FormUser formUser)
			throws SQLException, NullPointerException {
		String emailID = formUser.getEmailID();
		IBookStatusDAO bookStatusDAO = new BookStatusDAO();
		BookStatus bookStatus = bookStatusDAO.getIssuedBookStatus(copyID,
				emailID);

		FormBookStatus formBookStatus = new FormBookStatus(
				bookStatus.getBookStatus(), bookStatus.getEmailID(),
				bookStatus.getIssuedOn(), bookStatus.getDueDate(),
				bookStatus.getCopyID());
		return formBookStatus;
	}

	/**
	 * Returns a list of records containing the details of those issues that
	 * have crossed their due dates
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<FormBookStatus> businessGetPendingRequests()
			throws SQLException {
		IBookStatusDAO bookStatusDAO = new BookStatusDAO();
		ArrayList<BookStatus> arrayList = bookStatusDAO.getPendingReturn();
		ArrayList<FormBookStatus> arrayList2 = new ArrayList<>();
		Iterator<BookStatus> itr = arrayList.iterator();
		while (itr.hasNext()) {
			BookStatus bookStatus = itr.next();
			arrayList2.add(new FormBookStatus(bookStatus.getBookStatus(),
					bookStatus.getEmailID(), bookStatus.getIssuedOn(),
					bookStatus.getDueDate(), bookStatus.getCopyID()));
		}

		return arrayList2;
	}

	/**
	 * This retrieves the book record of which the given copyID is a copy
	 * 
	 * @param copyID
	 * @return
	 * @throws SQLException
	 */
	public static FormBook businessGetBookFromCopy(int copyID)
			throws SQLException {
		IBookDetailsDAO bookDetailsDAO = new BookDetailsDAO();
		Book book = bookDetailsDAO.getBookFromCopyCount(copyID);
		FormBook formBook = new FormBook(book.getBookID(), book.getTitle(),
				book.getAuthor(), book.getPrice(), book.getPublicationName(),
				book.getBookCount());
		return formBook;
	}

	/**
	 * Gives the list of books issued by the user
	 * 
	 * @param formUser
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<FormBook> businessGetAllUserBookList(
			FormUser formUser) throws SQLException {
		IBookIssueDAO bookIssue = new BookIssueDAO();
		ArrayList<Book> bookListDetails = bookIssue
				.getAllRequestedBooks(formUser.getEmailID());
		ArrayList<FormBook> formBookList = new ArrayList<FormBook>();
		Iterator<Book> itr = bookListDetails.iterator();
		while (itr.hasNext()) {
			Book book = itr.next();
			formBookList.add(new FormBook(book.getBookID(), book.getTitle(),
					book.getAuthor(), book.getPrice(), book
							.getPublicationName(), book.getBookCount()));
		}
		return formBookList;
	}

	/**
	 * gets the list of records containing the issue and due dates of the books
	 * issued by the user
	 * 
	 * @param formUser
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<FormBookStatus> businessGetDatesList(
			FormUser formUser) throws SQLException {
		String emailID = formUser.getEmailID();
		IBookStatusDAO bookStatusDAO = new BookStatusDAO();
		ArrayList<BookStatus> bookStatusList = bookStatusDAO
				.getAllIssuedBookStatus(emailID);

		ArrayList<FormBookStatus> formBookStatusList = new ArrayList<FormBookStatus>();
		Iterator<BookStatus> itr = bookStatusList.iterator();
		while (itr.hasNext()) {
			BookStatus bookStatus = itr.next();
			formBookStatusList.add(new FormBookStatus(bookStatus
					.getBookStatus(), bookStatus.getEmailID(), bookStatus
					.getIssuedOn(), bookStatus.getDueDate(), bookStatus
					.getCopyID()));
		}
		return formBookStatusList;

	}

	/**
	 * returns a list of all the books requested by the user
	 * 
	 * @param formUser
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<FormBook> businessGetRequestedBooks(
			FormUser formUser) throws SQLException {
		IBookIssueDAO bookIssueDAO = new BookIssueDAO();
		ArrayList<Book> book = bookIssueDAO.getAllRequestedBooks(formUser
				.getEmailID());
		ArrayList<FormBook> formBooks = new ArrayList<>();
		Iterator<Book> itr = book.iterator();
		while (itr.hasNext()) {
			Book book2 = itr.next();
			formBooks.add(new FormBook(book2.getBookID(), book2.getTitle(),
					book2.getAuthor(), book2.getPrice(), book2
							.getPublicationName(), book2.getBookCount()));
		}
		return formBooks;

	}

	/**
	 * returns the list of copies of the book
	 * 
	 * @param bookID
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<Integer> businessGetCopyID(int bookID)
			throws SQLException {
		IBookIssueDAO bookIssueDAO = new BookIssueDAO();
		ArrayList<Integer> copyID = bookIssueDAO.getCopyID(bookID);
		return copyID;

	}

	/**
	 * Gets the book status of the book the copy belongs to
	 * 
	 * @param copyID
	 * @return
	 * @throws SQLException
	 */
	public static String businessGetBookStatusFromCopyID(int copyID)
			throws SQLException {
		IBookStatusDAO bookStatusDAO = new BookStatusDAO();
		String bookStatus = bookStatusDAO.getBookStatusFromCopyID(copyID);
		return bookStatus;
	}
}
