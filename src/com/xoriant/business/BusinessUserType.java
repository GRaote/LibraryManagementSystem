package com.xoriant.business;

import java.sql.SQLException;

import com.xoriant.dao.UserDAO;
import com.xoriant.dao.contract.IUserDAO;
import com.xoriant.servlet.form.FormUser;

/**
 * 
 * @author hegde_a
 * 
 */
public class BusinessUserType {
	/**
	 * this gives the usertype from the user's emailID and password
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

}
