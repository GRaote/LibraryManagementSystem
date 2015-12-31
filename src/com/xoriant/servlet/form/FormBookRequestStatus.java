package com.xoriant.servlet.form;
/**
 * 
 * @author kawal_s
 *
 */
public class FormBookRequestStatus {
	private String emailID;
	private int copyID;
	private String requestStatus;

	public FormBookRequestStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FormBookRequestStatus(String emailID, int copyID,
			String requestStatus) {
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

	public String getBookRequestStatus() {
		return requestStatus;
	}

	public void setBookRequestStatus(String bookRequestStatus) {
		this.requestStatus = bookRequestStatus;
	}

	@Override
	public String toString() {
		return "FormBookRequestStatus [emailID=" + emailID + ", copyID="
				+ copyID + ", bookRequestStatus=" + requestStatus + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((requestStatus == null) ? 0 : requestStatus.hashCode());
		result = prime * result + copyID;
		result = prime * result + ((emailID == null) ? 0 : emailID.hashCode());
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
		FormBookRequestStatus other = (FormBookRequestStatus) obj;
		if (requestStatus == null) {
			if (other.requestStatus != null)
				return false;
		} else if (!requestStatus.equals(other.requestStatus))
			return false;
		if (copyID != other.copyID)
			return false;
		if (emailID == null) {
			if (other.emailID != null)
				return false;
		} else if (!emailID.equals(other.emailID))
			return false;
		return true;
	}

}
