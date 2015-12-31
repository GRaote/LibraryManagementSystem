package com.xoriant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xoriant.dao.contract.IUserDAO;
import com.xoriant.dao.pojo.Location;
import com.xoriant.dao.pojo.User;
import com.xoriant.dao.supp.ConnectionFactory;

/**
 * 
 * @author kawal_s
 * 
 */
public class UserDAO implements IUserDAO {

	/**
	 * retrieves the location record using the zip code
	 */
	public Location getLocation(int zipCode) throws SQLException {
		Location loc = null;
		Connection connection = ConnectionFactory.getConnection();
		String query = "select * from Location where zipCode=?";
		PreparedStatement pStatement = connection.prepareStatement(query);
		pStatement.setInt(1, zipCode);
		ResultSet resultset = pStatement.executeQuery();
		if (resultset.next()) {
			loc = new Location(resultset.getInt("zipCode"),
					resultset.getString("city"), resultset.getString("state"));
		}
		return loc;

	}

	/**
	 * retrieves the user details from the emailID
	 */
	@Override
	public User getUserDetails(String email) throws SQLException {
		// TODO Auto-generated method stub
		User user = null;
		Connection connection = ConnectionFactory.getConnection();
		String query = "select * from USER where BINARY emailID=?";
		PreparedStatement pStatement = connection.prepareStatement(query);
		pStatement.setString(1, email);
		int zipCode = 0;
		ResultSet resultset = pStatement.executeQuery();
		if (resultset.next()) {
			zipCode = resultset.getInt("zipCode");

			String query2 = "select * from LOCATION WHERE zipCode =?";
			PreparedStatement pStatement2 = connection.prepareStatement(query2);

			pStatement2.setInt(1, zipCode);
			ResultSet resultset2 = pStatement2.executeQuery();
			if (resultset2.next()) {
				user = new User(resultset.getString("firstName"),
						resultset.getString("lastName"),
						resultset.getString("userName"),
						resultset.getString("password"),
						resultset.getString("address"),
						resultset.getString("emailID"),
						resultset.getInt("zipCode"),
						resultset.getDate("dateOfBirth"),
						resultset.getString("userType"), new Location(
								resultset2.getInt("zipCode"),
								resultset2.getString("city"),
								resultset2.getString("state")));

			}
		}
		return user;
	}

	/**
	 * returns the user type of the user
	 */
	@Override
	public String getValidUser(String emailID, String password)
			throws SQLException {

		String usertype = "invalid";
		Connection connection = ConnectionFactory.getConnection();
		String query = "select * from USER where emailID=? and password=PASSWORD(?)";
		PreparedStatement pStatement = connection.prepareStatement(query);
		pStatement.setString(1, emailID);
		pStatement.setString(2, password);
		ResultSet resultset = pStatement.executeQuery();
		if (resultset.next()) {
			usertype = resultset.getString("userType");
		}

		return usertype;
	}

	/**
	 * adds new user
	 */
	@Override
	public void addNewUser(User user) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		String query = "SELECT * FROM LOCATION WHERE zipCode=?";
		PreparedStatement pStatement2 = connection.prepareStatement(query);
		pStatement2.setInt(1, user.getZipCode());
		ResultSet resultSet = pStatement2.executeQuery();

		Location location = user.getLocation();
		if (!resultSet.next()) {
			String query3 = "INSERT INTO LOCATION VALUES(?,?,?)";
			PreparedStatement pStatement3 = connection.prepareStatement(query3);
			pStatement3.setInt(1, location.getZipCode());
			pStatement3.setString(2, location.getCity());
			pStatement3.setString(3, location.getState());
			pStatement3.executeUpdate();
		}

		String query2 = "INSERT INTO USER VALUES(?,?,?,PASSWORD(?),?,?,?,?,?)";

		PreparedStatement pStatement = connection.prepareStatement(query2);
		pStatement.setString(1, user.getFirstName());
		pStatement.setString(2, user.getLastName());
		pStatement.setString(3, user.getUserName());
		pStatement.setString(4, user.getPassword());
		pStatement.setString(5, user.getAddress());
		pStatement.setString(6, user.getEmailID());
		pStatement.setInt(7, user.getZipCode());
		pStatement.setDate(8, user.getDateOfBirth());
		pStatement.setString(9, user.getUserType());
		pStatement.executeUpdate();

	}

	/**
	 * deletes a user record
	 */
	@Override
	public void deleteUser(String emailID) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("DELETE FROM USER WHERE emailID=?");
		preparedStatement.setString(1, emailID);
		preparedStatement.executeUpdate();

	}

	public boolean checkIfUserExists(String emailID) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		String query = "SELECT * FROM USER WHERE emailID=?";
		PreparedStatement pStatement = connection.prepareStatement(query);
		pStatement.setString(1, emailID);
		ResultSet resultSet = pStatement.executeQuery();
		if (resultSet.next()) {
			return true;
		}
		return false;
	}

	/**
	 * modify the user record
	 */
	@Override
	public void modifyUser(User user) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();

		Location location = new Location();
		location = user.getLocation();
		String query2 = "SELECT * FROM LOCATION WHERE zipCode=?";
		PreparedStatement pStatement2 = connection.prepareStatement(query2);
		pStatement2.setInt(1, user.getZipCode());
		ResultSet resultSet = pStatement2.executeQuery();
		if (resultSet.next()) {
		} else {
			String query3 = "INSERT INTO LOCATION VALUES(?,?,?)";
			PreparedStatement pStatement3 = connection.prepareStatement(query3);
			pStatement3.setInt(1, location.getZipCode());
			pStatement3.setString(2, location.getCity());
			pStatement3.setString(3, location.getState());
			pStatement3.executeUpdate();
		}

		String query = "UPDATE USER SET firstName=?, lastName=?, userName=?, password=PASSWORD(?), address=?, emailID=?, zipCode=?, dateOfBirth=?, userType=? where emailID=?";
		PreparedStatement pStatement = connection.prepareStatement(query);

		pStatement.setString(1, user.getFirstName());
		pStatement.setString(2, user.getLastName());
		pStatement.setString(3, user.getUserName());
		pStatement.setString(4, user.getPassword());
		pStatement.setString(5, user.getAddress());
		pStatement.setString(6, user.getEmailID());
		pStatement.setInt(7, user.getZipCode());
		pStatement.setDate(8, user.getDateOfBirth());
		pStatement.setString(9, user.getUserType());
		pStatement.setString(10, user.getEmailID());
		pStatement.executeUpdate();

	}

	public void modifyUserByUser(User user) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		Location location = new Location();
		location = user.getLocation();
		String query2 = "SELECT * FROM LOCATION WHERE zipCode=?";
		PreparedStatement pStatement2 = connection.prepareStatement(query2);
		pStatement2.setInt(1, user.getZipCode());
		ResultSet resultSet = pStatement2.executeQuery();
		if (resultSet.next()) {
		} else {

			String query3 = "INSERT INTO LOCATION VALUES(?,?,?)";
			PreparedStatement pStatement3 = connection.prepareStatement(query3);
			pStatement3.setInt(1, location.getZipCode());
			pStatement3.setString(2, location.getCity());
			pStatement3.setString(3, location.getState());
			pStatement3.executeUpdate();
		}

		String query = "UPDATE USER SET firstName=?, lastName=?, userName=?, password=PASSWORD(?), address=?, zipCode=? where emailID=?";
		PreparedStatement pStatement = connection.prepareStatement(query);

		pStatement.setString(1, user.getFirstName());
		pStatement.setString(2, user.getLastName());
		pStatement.setString(3, user.getUserName());
		pStatement.setString(4, user.getPassword());
		pStatement.setString(5, user.getAddress());
		pStatement.setInt(6, user.getZipCode());
		pStatement.setString(7, user.getEmailID());
		pStatement.executeUpdate();

	}

	@Override
	public List<User> getAllUserList(int offset, int noOfRecords)
			throws SQLException {

		Connection connection = ConnectionFactory.getConnection();
		List<User> users = new ArrayList<>();
		String query = "SELECT * FROM USER limit " + offset + ", "
				+ noOfRecords;
		PreparedStatement pStatement = connection.prepareStatement(query);
		ResultSet resultSet = pStatement.executeQuery();
		while (resultSet.next()) {

			String query2 = "SELECT * FROM LOCATION WHERE zipCode=?";
			PreparedStatement pStatement2 = connection.prepareStatement(query2);

			pStatement2.setInt(1, resultSet.getInt("zipCode"));
			ResultSet resultSet2 = pStatement2.executeQuery();

			if (resultSet2.next()) {
				users.add(new User(resultSet.getString("firstName"), resultSet
						.getString("lastName"),
						resultSet.getString("userName"), resultSet
								.getString("password"), resultSet
								.getString("address"), resultSet
								.getString("emailID"), resultSet
								.getInt("zipCode"), resultSet
								.getDate("dateOfBirth"), resultSet
								.getString("userType"), new Location(resultSet2
								.getInt("zipCode"), resultSet2
								.getString("city"), resultSet2
								.getString("state"))));

			}

		}

		return users;

	}

	@Override
	public int getNoOfRecords() throws SQLException {
		int count = 0;
		Connection connection = ConnectionFactory.getConnection();
		String query = "SELECT * FROM USER";
		PreparedStatement pStatement = connection.prepareStatement(query);
		ResultSet resultSet = pStatement.executeQuery();
		while (resultSet.next()) {
			count++;
		}
		return count;
	}
}
