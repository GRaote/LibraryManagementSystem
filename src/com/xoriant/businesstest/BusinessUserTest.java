package com.xoriant.businesstest;

import static com.xoriant.business.BusinessUser.businessGetUserDetails;
import static com.xoriant.business.BusinessUser.businessGetUserType;
import static com.xoriant.business.BusinessUser.businessModifyUser;
import static org.junit.Assert.fail;

import java.sql.SQLException;

import org.junit.Test;

import com.xoriant.dao.UserDAO;
import com.xoriant.servlet.form.FormUser;

public class BusinessUserTest {

	private FormUser formUser;

	@Test
	public void testBusinessGetUserType(){
		FormUser formUser=null;
		boolean gotException = false;
		try {
			businessGetUserType(formUser);
		} catch (SQLException | NullPointerException e) {
			System.out.println("Exception due to null value as expected");
			gotException = true;
		}
		if (!gotException) {
			fail("Expected exception but did not occur");
		}
	}

	@Test
	public void testBusinessGetUserDetails(){
		UserDAO userDAO = new UserDAO();
		
		try {
			userDAO.getUserDetails("adarsh.hegde@xoriant.com");
			FormUser formUser=new FormUser(null, null, null, null, "adarsh.hegde@xoriant.com", null, null, null, null, 0, null);
			FormUser formCheck=businessGetUserDetails(formUser);
			if(formCheck.equals(null))
			{
				fail("Cannot get user");
			}
			else
			{
				System.out.println("User details successfully retrieved");
			}
		} catch (SQLException e) {
			fail("Caused an SQL Exception");
		}
	}
	
	@Test
	public void testBusinessModifyUser() throws SQLException{
		formUser = null;
		boolean gotException = false;
		try {
			formUser.setEmailID("adarsh@xoriant.com");
			 businessModifyUser(formUser);
		} catch (NullPointerException e1) {
			System.out.println("Exception due to null value/sql as expected");
			gotException = true;
		}
		if (!gotException) {
			fail("Expected exception but did not occur");
		}

	}
	
	
}
