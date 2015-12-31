package com.xoriant.dao.pojo;
/**
 * For storing the location record 
 * @author hegde_a
 *
 */
public class Location {
	private String state;
	private String city;
	private int zipCode;

	public Location() {

	}

	public Location(int zipCode, String city, String state) {
		super();
		this.state = state;
		this.city = city;
		this.zipCode = zipCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		return "Location [state=" + state + ", city=" + city + ", zipCode="
				+ zipCode + "]";
	}

}
