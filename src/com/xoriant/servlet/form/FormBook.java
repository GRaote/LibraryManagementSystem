package com.xoriant.servlet.form;

/**
 * Form object for book
 * 
 * @author hegde_a
 * 
 */
public class FormBook {
	private int bookID;
	private String title;
	private String author;
	private double price;
	private String publicationName;
	private int bookCount;

	public FormBook() {
		super();
	}

	public FormBook(int bookID, String title, String author, double price,
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
		return "FormBook [bookID=" + bookID + ", title=" + title + ", author="
				+ author + ", price=" + price + ", publicationName="
				+ publicationName + ", bookCount=" + bookCount + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + bookCount;
		result = prime * result + bookID;
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((publicationName == null) ? 0 : publicationName.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FormBook other = (FormBook) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (bookCount != other.bookCount)
			return false;
		if (bookID != other.bookID)
			return false;
		if (Double.doubleToLongBits(price) != Double
				.doubleToLongBits(other.price))
			return false;
		if (publicationName == null) {
			if (other.publicationName != null)
				return false;
		} else if (!publicationName.equals(other.publicationName))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}
