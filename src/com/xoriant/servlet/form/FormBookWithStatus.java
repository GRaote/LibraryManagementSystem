package com.xoriant.servlet.form;

import java.sql.Date;
/**
 * Object for storing book details with dates 
 * @author raote_g
 *
 */
public class FormBookWithStatus {
	private String title;
	private String author;
	private double price;
	private String publicationName;
	private Date issuedOn;
	private Date dueDate;
	public FormBookWithStatus() {
		super();
	}
	public FormBookWithStatus(String title, String author, double price,
			String publicationName, Date issuedOn, Date dueDate) {
		super();
		this.title = title;
		this.author = author;
		this.price = price;
		this.publicationName = publicationName;
		this.issuedOn = issuedOn;
		this.dueDate = dueDate;
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
	@Override
	public String toString() {
		return "FormBookWithStatus [title=" + title + ", author=" + author
				+ ", price=" + price + ", publicationName=" + publicationName
				+ ", issuedOn=" + issuedOn + ", dueDate=" + dueDate + "]";
	}



}
