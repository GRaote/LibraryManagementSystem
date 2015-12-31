package com.xoriant.servlet.form;

import java.sql.Date;
/**
 * Form object for storing user details
 * @author raote_g
 *
 */
public class FormUser {
	private String fullName;
	private String userName;
	private String password;
	private Date dateOfBirth;
	private String emailID;
	private String addressFirstLine;
	private String addressSecondLine;
	private String city;
	private String state;
	private int zipCode;
	private String userType;

	public FormUser() {
		super();
	}

	public FormUser(String fullName, String userName, String password,
			Date dateOfBirth, String emailID, String addressFirstLine,
			String addressSecondLine, String city, String state, int zipCode,
			String userType) {
		super();
		this.fullName = fullName;
		this.userName = userName;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.emailID = emailID;
		this.addressFirstLine = addressFirstLine;
		this.addressSecondLine = addressSecondLine;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.userType = userType;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getAddressFirstLine() {
		return addressFirstLine;
	}

	public void setAddressFirstLine(String addressFirstLine) {
		this.addressFirstLine = addressFirstLine;
	}

	public String getAddressSecondLine() {
		return addressSecondLine;
	}

	public void setAddressSecondLine(String addressSecondLine) {
		this.addressSecondLine = addressSecondLine;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "FormUser [fullName=" + fullName + ", userName=" + userName
				+ ", password=" + password + ", dateOfBirth=" + dateOfBirth
				+ ", emailID=" + emailID + ", addressFirstLine="
				+ addressFirstLine + ", addressSecondLine=" + addressSecondLine
				+ ", city=" + city + ", state=" + state + ", zipCode="
				+ zipCode + ", userType=" + userType + "]";
	}

}
