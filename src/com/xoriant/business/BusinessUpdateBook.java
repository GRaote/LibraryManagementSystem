package com.xoriant.business;

import java.sql.SQLException;

import com.xoriant.dao.BookDetailsDAO;
import com.xoriant.dao.contract.IBookDetailsDAO;
import com.xoriant.dao.pojo.Book;
import com.xoriant.servlet.form.FormBook;

/**
 * 
 * @author hegde_a
 * 
 */
public class BusinessUpdateBook {
	/**
	 * this is used to add a book to the database
	 * 
	 * @param formBook
	 * @throws SQLException
	 */
	public static void businessAddBook(FormBook formBook) throws SQLException {
		Book book = new Book(1, formBook.getTitle(), formBook.getAuthor(),
				formBook.getPrice(), formBook.getPublicationName(),
				formBook.getBookCount());
		IBookDetailsDAO bookDetailsDAO = new BookDetailsDAO();
		bookDetailsDAO.addBook(book);
	}

	/**
	 * this deletes a book from the database
	 * 
	 * @param formBook
	 * @throws SQLException
	 */
	public static void businessDeleteBook(FormBook formBook)
			throws SQLException {
		IBookDetailsDAO bookDetailsDAO = new BookDetailsDAO();
		bookDetailsDAO.deleteBook(formBook.getBookID());
	}

	/**
	 * this retrieves a book record from the database for a particular bookID
	 * 
	 * @param formBook
	 * @return
	 * @throws SQLException
	 */
	public static FormBook businessGetBook(FormBook formBook)
			throws SQLException {
		IBookDetailsDAO bookDetailsDAO = new BookDetailsDAO();
		Book book = bookDetailsDAO.getBookFromID(formBook.getBookID());
		FormBook formBook2 = new FormBook(book.getBookID(), book.getTitle(),
				book.getAuthor(), book.getPrice(), book.getPublicationName(),
				book.getBookCount());
		return formBook2;
	}
/**
 * this modifies the book record in the database
 * @param formBook
 * @throws SQLException
 */
	public static void businessModifyBook(FormBook formBook)
			throws SQLException {
		IBookDetailsDAO bookDetailsDAO = new BookDetailsDAO();
		Book book = new Book(formBook.getBookID(), formBook.getTitle(),
				formBook.getAuthor(), formBook.getPrice(),
				formBook.getPublicationName(), formBook.getBookCount());
		bookDetailsDAO.modifyBook(book);
	}
}
