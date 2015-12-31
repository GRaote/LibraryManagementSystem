package com.xoriant.dao.pojo;

/**
 * For storing the book request status with the corresponding emailID and copyID
 * 
 * @author raote_g
 * 
 */
public class BookRequestStatus {
	private String emailID;
	private int copyID;
	private String requestStatus;

	public BookRequestStatus() {
		super();
	}

	public BookRequestStatus(String emailID, int copyID, String requestStatus) {
		super();
		this.emailID = emailID;
		this.copyID = copyID;
		this.requestStatus = requestStatus;
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

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	@Override
	public String toString() {
		return "BookRequestStatus [emailID=" + emailID + ", copyID=" + copyID
				+ ", requestStatus=" + requestStatus + "]";
	}

}
