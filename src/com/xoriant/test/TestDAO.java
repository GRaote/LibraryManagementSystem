package com.xoriant.test;

import java.sql.SQLException;

import com.xoriant.business.BusinessUser;
import com.xoriant.dao.BookIssueDAO;
import com.xoriant.dao.BookRequestStatusDAO;
import com.xoriant.dao.UserDAO;
import com.xoriant.servlet.form.FormUser;

public class TestDAO {
	public static void main(String[] args) throws SQLException {

		BookIssueDAO a = new BookIssueDAO();
		BookRequestStatusDAO b = new BookRequestStatusDAO();
		/*
		 * System.out.println(a.checkReserveBook1(4,
		 * "gaurang.raote@xoriant.com"));
		 */
		// System.out.println(b.ifIssuedOrRequested("gaurang.raote@xoriant.com",
		// 5));
		// System.out.println(a.getBookID(3));
		/*
		 * System.out.println("Before");
		 * System.out.println("---------------------");
		 * System.out.println("Gaurang can reserve?" + a.checkReserveBook1(1,
		 * "gaurang.raote@xoriant.com"));
		 * System.out.println("Gaurang can renew?" + a.checkRenew1(1,
		 * "gaurang.raote@xoriant.com"));
		 * System.out.println("===================================");
		 * System.out.println("kawal can reserve?" + a.checkReserveBook1(1,
		 * "kavalpreet.singh@xoriant.com"));
		 * System.out.println("kawal can renew?" + a.checkRenew1(1,
		 * "kavalpreet.singh@xoriant.com"));
		 */
		// a.reserveBook1(4, "gaurang.raote@xoriant.com");
		// a.reserveBook1(1, "gaurang.raote@xoriant.com");
		// a.issueBook1(5, "gaurang.raote@xoriant.com");
		// a.renewBook(5, "gaurang.raote@xoriant.com");
		// a.returnBook(6, "gaurang.raote@xoriant.com");

		// a.reserveBook1(3, "kavalpreet.singh@xoriant.com");
		// a.issueBook1(3, "kavalpreet.singh@xoriant.com");
		// a.renewBook(1, "kavalpreet.singh@xoriant.com");
		// a.returnBook(1, "kavalpreet.singh@xoriant.com");
		/*
		 * System.out.println("===================================");
		 * System.out.println("After");
		 * System.out.println("---------------------");
		 * System.out.println("Gaurang can reserve?" + a.checkReserveBook1(1,
		 * "gaurang.raote@xoriant.com"));
		 * System.out.println("Gaurang can renew?" + a.checkRenew1(1,
		 * "gaurang.raote@xoriant.com"));
		 * System.out.println("===================================");
		 * System.out.println("kawal can reserve?" + a.checkReserveBook1(1,
		 * "kavalpreet.singh@xoriant.com"));
		 * System.out.println("kawal can renew?" + a.checkRenew1(1,
		 * "kavalpreet.singh@xoriant.com"));
		 * System.out.println("adarsh can reserve?" + a.checkReserveBook1(1,
		 * "adarsh.hegde@xoriant.com")); System.out.println("adarsh can renew?"
		 * + a.checkRenew1(1, "adarsh.hegde@xoriant.com"));
		 */
		// ArrayList<Book> arrayList = a
		// .getAllRequestedBooks("gaurang.raote@xoriant.com");
		// Iterator<Book> itr = arrayList.iterator();
		// while (itr.hasNext()) {
		// System.out.println("ITr" + itr.next());
		// }

		/*
		 * ArrayList<BookRequestStatus> arrayList1 = b.getAllRequestedBooks();
		 * Iterator<BookRequestStatus> itr1 = arrayList1.iterator(); while
		 * (itr1.hasNext()) { System.out.println("ITr" + itr1.next()); }
		 */
		/*
		 * ArrayList<Book> arrayList2 = a
		 * .getIssuedBooks("gaurang.raote@xoriant.com"); Iterator<Book> itr2 =
		 * arrayList2.iterator(); while (itr2.hasNext()) {
		 * System.out.println("ITr" + itr2.next()); }
		 */

		// b.cancelBookRequest(6, "gaurang.raote@xoriant.com");
		/*
		 * FormUser formUser = new FormUser();
		 * formUser.setEmailID("gaurang.raote@xoriant.com"); int count = 0;
		 * ArrayList<FormBookStatus> formBookStatus =
		 * businessGetDatesList(formUser); Iterator<FormBookStatus> itr =
		 * formBookStatus.iterator(); while (itr.hasNext()) { itr.next();
		 * count++; } System.out.println(count);
		 */

		UserDAO user = new UserDAO();
		System.out.println(user.getNoOfRecords());
		/*
		 * FormUser f = new FormUser();
		 * f.setEmailID("gaurang.ghraote@xoriant.com");
		 * System.out.println(BusinessUser.businessCheckIfUserExists(f));
		 * System.out.println(user.checkIfUserExists("gaurang.raote@xorim"));
		 */

	}
}
