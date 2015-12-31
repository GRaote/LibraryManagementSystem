package com.xoriant.servlet.form;

import java.sql.Date;
/**
 * Object for storing pending requests 
 * @author raote_g
 *
 */
public class FormPendingRequest {
	private String emailID;
	private int bookID;
	private String title;
	private Date dueDate;

	public FormPendingRequest() {
		super();
	}

	public FormPendingRequest(String emailID, int bookID, String title,
			Date dueDate) {
		super();
		this.emailID = emailID;
		this.bookID = bookID;
		this.title = title;
		this.dueDate = dueDate;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
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

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	@Override
	public String toString() {
		return "FormPendingRequest [emailID=" + emailID + ", bookID=" + bookID
				+ ", title=" + title + ", dueDate=" + dueDate + "]";
	}

}
