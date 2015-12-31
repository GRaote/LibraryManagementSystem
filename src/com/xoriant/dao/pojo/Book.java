package com.xoriant.dao.pojo;
/**
 * For creating the book object to store a book record
 * @author raote_g
 *
 */
public class Book {

	private int bookID;
	private String title;
	private String author;
	private double price;
	private String publicationName;
	private int bookCount;

	public Book() {
		super();
	}

	public Book(int bookID, String title, String author, double price,
			String publicationName, int bookCount) {
		super();
		this.bookID = bookID;
		this.title = title;
		this.author = author;
		this.price = price;
		this.publicationName = publicationName;
		this.bookCount = bookCount;
	}

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPublicationName() {
		return publicationName;
	}

	public void setPublicationName(String publicationName) {
		this.publicationName = publicationName;
	}

	public int getBookCount() {
		return bookCount;
	}

	public void setBookCount(int bookCount) {
		this.bookCount = bookCount;
	}

	@Override
	public String toString() {
		return "Book [bookID=" + bookID + ", title=" + title + ", author="
				+ author + ", price=" + price + ", publicationName="
				+ publicationName + ", bookCount=" + bookCount + "]";
	}

}
