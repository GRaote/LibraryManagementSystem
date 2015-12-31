package com.xoriant.business;

import java.sql.SQLException;
import java.util.StringTokenizer;

import com.xoriant.dao.UserDAO;
import com.xoriant.dao.contract.IUserDAO;
import com.xoriant.dao.pojo.Location;
import com.xoriant.dao.pojo.User;
import com.xoriant.servlet.form.FormUser;

/**
 * 
 * @author raote_g
 * 
 */
public class BusinessModifyUserByAdmin {
	/**
	 * This retrieves the user details for modification by admnin
	 * 
	 * @param formUser2
	 * @return
	 * @throws SQLException
	 */
	public static FormUser businessGetUserDetails(FormUser formUser2)
			throws SQLException {
		IUserDAO userDAO = new UserDAO();
		User user = new User();
		user = userDAO.getUserDetails(formUser2.getEmailID());
		FormUser formUser = new FormUser();
		StringTokenizer st = new StringTokenizer(user.getAddress(), "|");
		String[] address = new String[2];
		int i = 0;
		while (st.hasMoreTokens()) {
			address[i] = st.nextToken();
			i++;
		}
		formUser.setAddressFirstLine(address[0]);

		formUser.setAddressSecondLine(address[1]);
		formUser.setZipCode(user.getZipCode());
		formUser.setUserName(user.getUserName());
		formUser.setDateOfBirth(user.getDateOfBirth());
		formUser.setEmailID(user.getEmailID());
		formUser.setUserType(user.getUserType());
		formUser.setPassword(user.getPassword());
		formUser.setFullName(user.getFirstName() + " " + user.getLastName());

		Location loc = new Location();

		loc = user.getLocation();

		formUser.setZipCode(loc.getZipCode());
		formUser.setCity(loc.getCity());
		formUser.setState(loc.getState());
		System.out.println("Abc" + formUser);
		return formUser;
	}

}
