package com.xoriant.dao.pojo;
/**
 * for mapping the bookID with the copyID
 * @author hegde_a
 *
 */
public class Copy {
	private int bookID;
	private int copyID;

	public Copy() {

	}

	public Copy(int bookID, int copyID) {
		super();
		this.bookID = bookID;
		this.copyID = copyID;
	}

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public int getCopyID() {
		return copyID;
	}

	public void setCopyID(int copyID) {
		this.copyID = copyID;
	}

	@Override
	public String toString() {
		return "Copy [bookID=" + bookID + ", copyID=" + copyID + "]";
	}

		

}
