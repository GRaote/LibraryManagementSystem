package com.xoriant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.xoriant.dao.contract.IBookDetailsDAO;
import com.xoriant.dao.pojo.Book;
import com.xoriant.dao.supp.ConnectionFactory;

/**
 * 
 * @author hegde_a
 * 
 */
public class BookDetailsDAO implements IBookDetailsDAO {
	/**
	 * retrieves the book by title
	 */
	public Set<Book> getBookDetailsByTitle(String title, Set<Book> books,
			Connection connection) throws SQLException {
		String query = "SELECT * from BOOK_DETAILS WHERE title like ?";
		PreparedStatement pStatement = connection.prepareStatement(query);
		if (!title.equals(null))
			pStatement.setString(1, "%" + title + "%");

		ResultSet resultSet = pStatement.executeQuery();
		while (resultSet.next()) {
			books.add(new Book(resultSet.getInt(1), resultSet.getString(2),
					resultSet.getString(3), resultSet.getDouble(4), resultSet
							.getString(5), resultSet.getInt(6)));
		}
		return books;
	}

	/**
	 * retrieves the book searched by the author name
	 */
	public Set<Book> getBookDetailsByAuthor(String author, Set<Book> books,
			Connection connection) throws SQLException {
		String query = "SELECT * from BOOK_DETAILS WHERE  author like ? ";
		PreparedStatement pStatement = connection.prepareStatement(query);

		if (!author.equals(null))
			pStatement.setString(1, "%" + author + "%");

		ResultSet resultSet = pStatement.executeQuery();
		while (resultSet.next()) {
			books.add(new Book(resultSet.getInt(1), resultSet.getString(2),
					resultSet.getString(3), resultSet.getDouble(4), resultSet
							.getString(5), resultSet.getInt(6)));
		}
		return books;
	}

	/**
	 * retrieves the book searched by price
	 */
	public Set<Book> getBookDetailsByPrice(Double price) throws SQLException {
		Set<Book> books = new HashSet<>();
		Connection connection = ConnectionFactory.getConnection();
		String query = "SELECT * from BOOK_DETAILS WHERE price>=? ";
		PreparedStatement pStatement = connection.prepareStatement(query);
		pStatement.setDouble(1, price);

		ResultSet resultSet = pStatement.executeQuery();
		while (resultSet.next()) {
			books.add(new Book(resultSet.getInt(1), resultSet.getString(2),
					resultSet.getString(3), resultSet.getDouble(4), resultSet
							.getString(5), resultSet.getInt(6)));
		}
		return books;
	}

	/**
	 * retrieves the book searched by the publication name
	 */
	public Set<Book> getBookDetailsByPublicationName(String allStringSearch,
			Set<Book> books, Connection connection) throws SQLException {
		String query = "SELECT * from BOOK_DETAILS WHERE  publicationName like ?";
		PreparedStatement pStatement = connection.prepareStatement(query);

		if (!allStringSearch.equals(null))
			pStatement.setString(1, "%" + allStringSearch + "%");

		ResultSet resultSet = pStatement.executeQuery();
		while (resultSet.next()) {

			books.add(new Book(resultSet.getInt(1), resultSet.getString(2),
					resultSet.getString(3), resultSet.getDouble(4), resultSet
							.getString(5), resultSet.getInt(6)));
		}
		return books;
	}

	public Set<Book> getBookDetailsByAll(String searchString)
			throws SQLException {
		Set<Book> books = new HashSet<>();
		Connection connection = ConnectionFactory.getConnection();
		getBookDetailsByPublicationName(searchString, books, connection);
		getBookDetailsByAuthor(searchString, books, connection);
		getBookDetailsByTitle(searchString, books, connection);
		return books;
	}

	/**
	 * adds a book into the database
	 */
	@Override
	public void addBook(Book book) throws SQLException {
		int bookID = 0;
		int copyID = 0;
		Connection connection = ConnectionFactory.getConnection();
		String query2 = "SELECT MAX(bookID) FROM BOOK_DETAILS";
		PreparedStatement pStatement2 = connection.prepareStatement(query2);
		ResultSet resultSet = pStatement2.executeQuery();
		if (resultSet.next()) {
			bookID = resultSet.getInt(1);
		}
		bookID++;
		String query = "INSERT INTO BOOK_DETAILS(bookID,title,author,price,publicationName,bookCount) VALUES(?,?,?,?,?,?)";
		PreparedStatement pStatement = connection.prepareStatement(query);
		pStatement.setInt(1, bookID);
		pStatement.setString(2, book.getTitle());
		pStatement.setString(3, book.getAuthor());
		pStatement.setDouble(4, book.getPrice());
		pStatement.setString(5, book.getPublicationName());
		pStatement.setInt(6, book.getBookCount());
		pStatement.executeUpdate();
		String query5 = "SELECT MAX(copyID) FROM BOOK_STATUS";
		PreparedStatement pStatement5 = connection.prepareStatement(query5);
		ResultSet resultSet5 = pStatement5.executeQuery();
		if (resultSet5.next()) {
			copyID = resultSet5.getInt(1);
		}
		copyID++;
		int bookCount = book.getBookCount();
		for (int i = 0; i < bookCount; i++) {
			String query3 = "INSERT INTO COPY_TABLE(copyID,bookID) VALUES(?,?)";
			PreparedStatement pStatement3 = connection.prepareStatement(query3);
			pStatement3.setInt(1, copyID);
			pStatement3.setInt(2, bookID);
			pStatement3.executeUpdate();

			String query4 = "INSERT INTO BOOK_STATUS(bookStatus,emailID,copyID) VALUES(?,?,?)";
			PreparedStatement pStatement4 = connection.prepareStatement(query4);
			pStatement4.setString(1, "AVAILABLE");
			pStatement4.setString(2, "tehreem.ansari@xoriant.com");
			pStatement4.setInt(3, copyID);
			pStatement4.executeUpdate();
			copyID++;
		}

	}

	/**
	 * deletes a book from the datbase
	 */
	@Override
	public void deleteBook(int bookID) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		String query = "DELETE FROM BOOK_DETAILS WHERE bookID=?";
		PreparedStatement pStatement = connection.prepareStatement(query);
		pStatement.setInt(1, bookID);
		pStatement.executeUpdate();
	}

	/**
	 * modifies the book record in the database
	 */
	@Override
	public void modifyBook(Book book) throws SQLException {
		int copyCount = 0;
		Connection connection = ConnectionFactory.getConnection();
		String query2 = "SELECT bookCount from BOOK_DETAILS where bookID=?";
		PreparedStatement preparedStatement = connection
				.prepareStatement(query2);
		preparedStatement.setInt(1, book.getBookID());
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			copyCount = resultSet.getInt(1);
		}
		if (copyCount <= book.getBookCount()) {
			String query = "UPDATE BOOK_DETAILS SET title=? , author=? , price=? , publicationName=? , bookCount=? WHERE bookID=?";
			PreparedStatement pStatement = connection.prepareStatement(query);

			pStatement.setString(1, book.getTitle());
			pStatement.setString(2, book.getAuthor());
			pStatement.setDouble(3, book.getPrice());
			pStatement.setString(4, book.getPublicationName());
			pStatement.setInt(5, book.getBookCount());
			pStatement.setInt(6, book.getBookID());
			pStatement.executeUpdate();
			int lastCopyID = 0;

			String query5 = "SELECT MAX(copyID) FROM BOOK_STATUS";
			PreparedStatement pStatement5 = connection.prepareStatement(query5);
			ResultSet resultSet5 = pStatement5.executeQuery();
			if (resultSet5.next()) {
				lastCopyID = resultSet5.getInt(1);
			}
			lastCopyID++;

			int addedCopy = book.getBookCount() - copyCount;
			for (int i = 0; i < addedCopy; i++) {
				String query3 = "INSERT INTO COPY_TABLE(copyID,bookID) VALUES(?,?)";
				PreparedStatement pStatement3 = connection
						.prepareStatement(query3);
				pStatement3.setInt(1, lastCopyID);
				pStatement3.setInt(2, book.getBookID());
				pStatement3.executeUpdate();

				String query4 = "INSERT INTO BOOK_STATUS(bookStatus,emailID,copyID) VALUES(?,?,?)";
				PreparedStatement pStatement4 = connection
						.prepareStatement(query4);
				pStatement4.setString(1, "AVAILABLE");
				pStatement4.setString(2, "tehreem.ansari@xoriant.com");
				pStatement4.setInt(3, lastCopyID);
				pStatement4.executeUpdate();
				lastCopyID++;
			}
		}
	}

	/**
	 * retrieves the book from the copy count
	 * 
	 * @param copyID
	 * @return
	 * @throws SQLException
	 */
	public Book getBookFromCopyCount(int copyID) throws SQLException {
		Book book = null;
		Connection connection = ConnectionFactory.getConnection();
		String query = "select * from book_details where bookID in (select bookID from copy_table where copyID=?)";
		PreparedStatement pStatement = connection.prepareStatement(query);
		pStatement.setInt(1, copyID);
		ResultSet resultSet = pStatement.executeQuery();
		if (resultSet.next()) {
			book = new Book(resultSet.getInt(1), resultSet.getString(2),
					resultSet.getString(3), resultSet.getDouble(4),
					resultSet.getString(5), resultSet.getInt(6));
		}
		return book;
	}

	public boolean checkIfBookExists(int bookID) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		String query = "select * from book_details where bookID=?";
		PreparedStatement pStatement = connection.prepareStatement(query);
		pStatement.setInt(1, bookID);
		ResultSet resultSet = pStatement.executeQuery();
		if (resultSet.next()) {
			return true;
		}
		return false;
	}

	/**
	 * retrieves the book record from the book ID
	 * 
	 * @param bookID
	 * @return
	 * @throws SQLException
	 */
	public Book getBookFromID(int bookID) throws SQLException {
		Book book = null;
		Connection connection = ConnectionFactory.getConnection();
		String query = "select * from book_details where bookID=?";
		PreparedStatement pStatement = connection.prepareStatement(query);
		pStatement.setInt(1, bookID);
		ResultSet resultSet = pStatement.executeQuery();
		if (resultSet.next()) {
			book = new Book(resultSet.getInt(1), resultSet.getString(2),
					resultSet.getString(3), resultSet.getDouble(4),
					resultSet.getString(5), resultSet.getInt(6));
		}
		return book;
	}

	@Override
	public ArrayList<Book> getBookDetails(int offset, int noOfRecords)
			throws SQLException {
		ArrayList<Book> bookList = new ArrayList<>();
		Connection connection = ConnectionFactory.getConnection();
		String query = "SELECT * from BOOK_DETAILS limit " + offset + ", "
				+ noOfRecords;
		PreparedStatement pStatement = connection.prepareStatement(query);
		ResultSet resultSet = pStatement.executeQuery();
		while (resultSet.next()) {
			bookList.add(new Book(resultSet.getInt(1), resultSet.getString(2),
					resultSet.getString(3), resultSet.getDouble(4), resultSet
							.getString(5), resultSet.getInt(6)));
		}
		return bookList;
	}

	@Override
	public int getNoOfBookRecords() throws SQLException {
		int count = 0;
		Connection connection = ConnectionFactory.getConnection();
		String query = "SELECT * from BOOK_DETAILS";
		PreparedStatement pStatement = connection.prepareStatement(query);
		ResultSet resultSet = pStatement.executeQuery();
		while (resultSet.next()) {
			count++;
		}
		return count;
	}
}
