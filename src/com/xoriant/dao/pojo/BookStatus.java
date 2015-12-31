package com.xoriant.dao.pojo;

import java.sql.Date;
/**
 * for storing the book status record
 * @author raote_g
 *
 */
public class BookStatus {
	private String emailID;
	private int copyID;
	private Date issuedOn;
	private Date dueDate;
	private String bookStatus;

	public BookStatus() {
		super();
	}

	public BookStatus(String bookStatus, String emailID, Date issuedOn,
			Date dueDate, int copyID) {
		super();
		this.bookStatus = bookStatus;
		this.emailID = emailID;
		this.issuedOn = issuedOn;
		this.dueDate = dueDate;
		this.copyID = copyID;

	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public int getCopyID() {
		return copyID;
	}

	public void setCopyID(int copyID) {
		this.copyID = copyID;
	}

	public Date getIssuedOn() {
		return issuedOn;
	}

	public void setIssuedOn(Date issuedOn) {
		this.issuedOn = issuedOn;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getBookStatus() {
		return bookStatus;
	}

	public void setBookStatus(String bookStatus) {
		this.bookStatus = bookStatus;
	}

	@Override
	public String toString() {
		return "BookStatus [emailID=" + emailID + ", copyID=" + copyID
				+ ", issuedOn=" + issuedOn + ", dueDate=" + dueDate
				+ ", bookStatus=" + bookStatus + "]";
	}

}
