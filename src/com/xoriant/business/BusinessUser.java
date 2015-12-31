package com.xoriant.business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import com.xoriant.dao.UserDAO;
import com.xoriant.dao.contract.IUserDAO;
import com.xoriant.dao.pojo.Location;
import com.xoriant.dao.pojo.User;
import com.xoriant.servlet.form.FormBookStatus;
import com.xoriant.servlet.form.FormUser;

/**
 * 
 * @author raote_g
 * 
 */
public class BusinessUser {

	/**
	 * this returns the type of user Admin/Librarian/User
	 * 
	 * @param formUser
	 * @return
	 * @throws SQLException
	 */
	public static String businessGetUserType(FormUser formUser)
			throws SQLException {

		IUserDAO user = new UserDAO();
		String userType = user.getValidUser(formUser.getEmailID(),
				formUser.getPassword());
		return userType;

	}

	/**
	 * this retrieves the user record from the emailID
	 * 
	 * @param formUser
	 * @return
	 */
	public static FormUser businessGetUserDetails(FormUser formUser)
			throws NullPointerException {
		IUserDAO userDAO = new UserDAO();
		User user = new User();
		try {
			user = userDAO.getUserDetails(formUser.getEmailID());
			String[] address = new String[2];
			address[0] = "";
			address[1] = "";
			int i = 0;
			StringTokenizer st = new StringTokenizer(user.getAddress(), "|");
			while (st.hasMoreTokens()) {
				address[i] = st.nextToken();
				i++;
			}
			formUser.setAddressFirstLine(address[0]);

			if (!address[1].equals(null)) {
				formUser.setAddressSecondLine(address[1]);
			}

			formUser.setZipCode(user.getZipCode());
			formUser.setUserName(user.getUserName());
			formUser.setDateOfBirth(user.getDateOfBirth());
			formUser.setUserType(user.getUserType());
			formUser.setFullName(user.getFirstName() + " " + user.getLastName());
			formUser.setPassword(user.getPassword());
			Location loc = new Location();
			loc.setZipCode(user.getZipCode());
			loc = userDAO.getLocation(loc.getZipCode());

			formUser.setCity(loc.getCity());
			formUser.setState(loc.getState());

			return formUser;
		} catch (SQLException e) {
			return null;
		}
	}

	/**
	 * this modifies the user record in the database
	 * 
	 * @param formUser
	 * @throws SQLException
	 */
	public static void businessModifyUser(FormUser formUser)
			throws SQLException {
		String str = formUser.getFullName();
		String[] name = str.split(" ");

		IUserDAO userDAO = new UserDAO();
		Location loc = new Location(formUser.getZipCode(), formUser.getCity(),
				formUser.getState());
		User user = new User();
		user.setFirstName(name[0]);
		if (!name[1].equals(null)) {
			user.setLastName(name[1]);
		}
		user.setAddress(formUser.getAddressFirstLine() + "|"
				+ formUser.getAddressSecondLine());
		user.setUserName(formUser.getUserName());
		user.setPassword(formUser.getPassword());
		user.setZipCode(formUser.getZipCode());
		user.setDateOfBirth(formUser.getDateOfBirth());
		user.setEmailID(formUser.getEmailID());
		user.setLocation(loc);
		userDAO.modifyUserByUser(user);

	}

	/**
	 * this retrieves the user details from a book status record containing the
	 * user's emailID
	 * 
	 * @param formBookStatus
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<FormUser> businessGetUserDetails(
			ArrayList<FormBookStatus> formBookStatus) throws SQLException {
		Iterator<FormBookStatus> itr = formBookStatus.iterator();
		ArrayList<FormUser> arrayList = new ArrayList<>();
		while (itr.hasNext()) {
			FormBookStatus formBookStatus2 = itr.next();
			IUserDAO userDAO = new UserDAO();
			User user = userDAO.getUserDetails(formBookStatus2.getEmailID());
			arrayList.add(new FormUser("", "", "", null, user.getEmailID(), "",
					"", "", "", 0, "USER"));

		}
		return arrayList;
	}

	public static boolean businessCheckIfUserExists(FormUser formUser)
			throws SQLException {
		IUserDAO user = new UserDAO();
		if (user.checkIfUserExists(formUser.getEmailID())) {
			return true;
		}
		return false;
	}

	/**
	 * method returns a list of all users to be viewed by the admin
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<FormUser> businessGetAllUsers(int page, int noOfRecords)
			throws SQLException {
		IUserDAO userDAO = new UserDAO();
		List<User> users = userDAO.getAllUserList(page, noOfRecords);
		List<FormUser> formUsers = new ArrayList<>();
		Iterator<User> itr = users.iterator();
		while (itr.hasNext()) {
			User user = itr.next();
			String fullName = user.getFirstName() + " " + user.getLastName();

			String[] address = new String[2];
			address[0] = "";
			address[1] = "";
			int i = 0;
			StringTokenizer st = new StringTokenizer(user.getAddress(), "|");
			while (st.hasMoreTokens()) {
				address[i] = st.nextToken();
				i++;
			}
			formUsers.add(new FormUser(fullName, null, null, user
					.getDateOfBirth(), user.getEmailID(), address[0],
					address[1], user.getLocation().getCity(), user
							.getLocation().getState(), 0, user.getUserType()));

		}

		return formUsers;
	}
}
