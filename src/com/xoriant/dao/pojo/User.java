package com.xoriant.dao.pojo;

import java.sql.Date;
/**
 * for storing a user record consisting the details of the user
 * @author kawal_s
 *
 */
public class User {
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private Date dateOfBirth;
	private String emailID;
	private String userType;
	private String address;
	private int zipCode;
	private Location location;

	public User() {

	}

	public User(String firstName, String lastName, String userName,
			String password, String address, String emailID, int zipCode,
			Date dateOfBirth, String userType, Location location) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.emailID = emailID;
		this.userType = userType;
		this.address = address;
		this.zipCode = zipCode;
		this.location = location;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName
				+ ", userName=" + userName + ", password=" + password
				+ ", dateOfBirth=" + dateOfBirth + ", emailID=" + emailID
				+ ", userType=" + userType + ", address=" + address
				+ ", zipCode=" + zipCode + ", location=" + location + "]";
	}

}
