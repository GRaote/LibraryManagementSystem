package com.xoriant.dao.contract;

import java.sql.SQLException;
import java.util.List;

import com.xoriant.dao.pojo.Location;
import com.xoriant.dao.pojo.User;

public interface IUserDAO {

	public User getUserDetails(String emailID) throws SQLException;

	public String getValidUser(String emailID, String password)
			throws SQLException;

	public void addNewUser(User user) throws SQLException;

	public void deleteUser(String emailID) throws SQLException;

	public void modifyUser(User user) throws SQLException;

	public Location getLocation(int zipCode) throws SQLException;

	public void modifyUserByUser(User user) throws SQLException;

	public boolean checkIfUserExists(String emailID) throws SQLException;

	public List<User> getAllUserList(int offset, int noOfRecords)
			throws SQLException;

	public int getNoOfRecords() throws SQLException;
}
