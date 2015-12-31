package com.xoriant.business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import com.xoriant.dao.BookRequestStatusDAO;
import com.xoriant.dao.contract.IBookRequestStatusDAO;
import com.xoriant.dao.pojo.BookRequestStatus;
import com.xoriant.servlet.form.FormBookRequestStatus;

/**
 * 
 * @author hegde_a
 * 
 */
public class BusinessReserve {
	/**
	 * This gives a list of all the books being requested by users
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<FormBookRequestStatus> businessGetAllRequestedBooks()
			throws SQLException {
		IBookRequestStatusDAO bookRequestStatusDAO = new BookRequestStatusDAO();
		ArrayList<BookRequestStatus> bookListDetails = bookRequestStatusDAO
				.getAllRequestedBooks();

		ArrayList<FormBookRequestStatus> formBookRequestStatusList = new ArrayList<>();

		Iterator<BookRequestStatus> itr = bookListDetails.iterator();

		while (itr.hasNext()) {
			BookRequestStatus bookRequestStatusTemp = itr.next();
			formBookRequestStatusList.add(new FormBookRequestStatus(
					bookRequestStatusTemp.getEmailID(), bookRequestStatusTemp
							.getCopyID(), bookRequestStatusTemp
							.getRequestStatus()));
		}
		return formBookRequestStatusList;
	}

	/**
	 * This is used to change the status of the request
	 * 
	 * @param formBookRequestStatus
	 * @throws SQLException
	 */
	public static void businessCancelBookRequest(
			FormBookRequestStatus formBookRequestStatus) throws SQLException {
		IBookRequestStatusDAO bookRequestStatusDAO = new BookRequestStatusDAO();
		BookRequestStatus bookRequestStatus = new BookRequestStatus(
				formBookRequestStatus.getEmailID(),
				formBookRequestStatus.getCopyID(),
				formBookRequestStatus.getBookRequestStatus());
		bookRequestStatusDAO.cancelBookRequest(bookRequestStatus.getCopyID(),
				bookRequestStatus.getEmailID());

	}
}
