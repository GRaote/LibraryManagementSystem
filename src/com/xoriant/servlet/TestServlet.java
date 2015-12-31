package com.xoriant.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xoriant.dao.supp.ConnectionFactory;

/**
 * Servlet implementation class TestServlet
 */
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doCommon(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doCommon(request, response);
	}

	protected void doCommon(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		// List<String> searchResult = new ArrayList<String>();
		String buffer = "";
		try {
			Connection connection = ConnectionFactory.getConnection();
			System.out.println("Hello");
			String query = "SELECT * from BOOK_DETAILS WHERE title like ?";
			PreparedStatement pStatement = connection.prepareStatement(query);
			pStatement.setString(1, "%" + name + "%");
			ResultSet rs = pStatement.executeQuery();

			while (rs.next()) {
				buffer = buffer + " " + rs.getString("title");
			}

			request.setAttribute("buffer", buffer);
			System.out.println(buffer);
			response.getWriter().println(buffer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
