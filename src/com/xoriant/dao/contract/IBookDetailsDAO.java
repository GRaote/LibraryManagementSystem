package com.xoriant.dao.contract;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;

import java.sql.Connection;
import com.xoriant.dao.pojo.Book;

public interface IBookDetailsDAO {

	public void addBook(Book book) throws SQLException;

	public void deleteBook(int bookID) throws SQLException;

	public void modifyBook(Book book) throws SQLException;

	public Set<Book> getBookDetailsByTitle(String title, Set<Book> books,
			Connection connection) throws SQLException;

	public Set<Book> getBookDetailsByAuthor(String author, Set<Book> books,
			Connection connection) throws SQLException;

	public Set<Book> getBookDetailsByPrice(Double price) throws SQLException;

	public Set<Book> getBookDetailsByPublicationName(String publicationName,
			Set<Book> books, Connection connection) throws SQLException;

	public Set<Book> getBookDetailsByAll(String searchString)
			throws SQLException;

	public Book getBookFromCopyCount(int copyID) throws SQLException;

	public Book getBookFromID(int bookID) throws SQLException;

	public boolean checkIfBookExists(int bookID) throws SQLException;

	public ArrayList<Book> getBookDetails(int offset, int noOfRecords)
			throws SQLException;

	public int getNoOfBookRecords() throws SQLException;
}
