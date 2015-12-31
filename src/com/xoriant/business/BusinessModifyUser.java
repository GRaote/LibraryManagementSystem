package com.xoriant.business;

import java.sql.SQLException;

import com.xoriant.dao.UserDAO;
import com.xoriant.dao.contract.IUserDAO;
import com.xoriant.dao.pojo.Location;
import com.xoriant.dao.pojo.User;
import com.xoriant.servlet.form.FormUser;

/**
 * 
 * @author kawal_s
 * 
 */
public class BusinessModifyUser {
	/**
	 * This allows the admin to modify the user record
	 * 
	 * @param formUser
	 * @throws SQLException
	 */
	public static void businessModifyUserByAdmin(FormUser formUser)
			throws SQLException {
		String str = formUser.getFullName();
		String[] name = str.split(" ");

		IUserDAO use = new UserDAO();

		String fulladdress = formUser.getAddressFirstLine() + "|"
				+ formUser.getAddressSecondLine();
		Location loc = new Location(formUser.getZipCode(), formUser.getCity(),
				formUser.getCity());
		// TODO Auto-generated method stub
		User user = new User(name[0], name[1], formUser.getUserName(),
				formUser.getPassword(), fulladdress, formUser.getEmailID(),
				formUser.getZipCode(), formUser.getDateOfBirth(),
				formUser.getUserType(), loc);
		use.modifyUser(user);
	}
}
