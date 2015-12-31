package com.xoriant.business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import com.xoriant.business.BusinessValidate;
import com.xoriant.dao.BookDetailsDAO;
import com.xoriant.dao.BookIssueDAO;
import com.xoriant.dao.contract.IBookDetailsDAO;
import com.xoriant.dao.contract.IBookIssueDAO;
import com.xoriant.dao.pojo.Book;
import com.xoriant.servlet.form.FormBook;

/**
 * 
 * @author hegde_a
 * 
 */
public class BusinessBookDetails {

	/**
	 * This returns a list of books with text the user has entered
	 * 
	 * @param formBook
	 * @return
	 * @throws SQLException
	 */

	public Set<FormBook> businessGetBookDetailsByAll(String searchString)
			throws SQLException {
		BookDetailsDAO bookDetails = new BookDetailsDAO();
		Set<Book> books;
		if (BusinessValidate.businessIsNumeric(searchString)) {
			double searchedPrice = Double.parseDouble(searchString);
			books = bookDetails.getBookDetailsByPrice(searchedPrice);
		} else {
			books = bookDetails.getBookDetailsByAll(searchString);
		}

		Set<FormBook> formBooks = new HashSet<>();
		Iterator<Book> itr = books.iterator();
		while (itr.hasNext()) {
			Book book = itr.next();
			formBooks.add(new FormBook(book.getBookID(), book.getTitle(), book
					.getAuthor(), book.getPrice(), book.getPublicationName(),
					book.getBookCount()));
		}
		return formBooks;

	}

	/**
	 * Returns all the details of all the books
	 * 
	 * @param formBook
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<FormBook> businessGetBookDetails(int page,
			int noOfRecords) throws SQLException {
		IBookDetailsDAO bookDetails = new BookDetailsDAO();
		ArrayList<Book> bookListDetails = bookDetails.getBookDetails(page,
				noOfRecords);
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
	 * Returns the list of copies of a book
	 * 
	 * @param bookID
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<Integer> businessGetCopyList(int bookID)
			throws SQLException {
		IBookIssueDAO iBookIssueDAO = new BookIssueDAO();
		ArrayList<Integer> arrayList = iBookIssueDAO.getCopyID(bookID);
		return arrayList;
	}

	public static boolean businessCheckIfBookExists(FormBook formBook)
			throws SQLException {
		IBookDetailsDAO bookDetailsDAO = new BookDetailsDAO();
		if (bookDetailsDAO.checkIfBookExists(formBook.getBookID())) {
			return true;
		}
		return false;
	}

}
