<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" type="text/css"
	href="/LibraryManagementSystem/css/demo.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Library Management System</title>
</head>
<body>

	<div class="top">
		<h1 class="borderShadow">Library Management System</h1>
	</div>

	<div class="mid">
		<br>
		<c:if test="${(empty sessionScope.name)}">
			<h4 class="error">Session Expired , please log in again</h4>
		</c:if>

		<br>
		<center>
			<form name="login" action="/LibraryManagementSystem/UserType"
				method="post">
				<table class="tableStyle">
					<tr>
						<td><h3>Email ID:</h3></td>
						<td><h3>
								<input type="text" required="required" name="emailID"
									placeholder="Email Id">
							</h3></td>
					</tr>

					<tr>
						<td><h3>Password:</h3></td>
						<td><h3>
								<input type="password" required="required" name="password"
									placeholder="******">
							</h3></td>
					</tr>
					<tr>
					<tr>
						<td><h3>User Type:</h3></td>
						<td><h3>
								<select name="userType">
									<option>ADMIN</option>
									<option>LIBRARIAN</option>
									<option>USER</option>
								</select>
							</h3></td>
					<tr>
				</table>
				<br> <input type="submit" class="btn" name="login"
					value="LOGIN">
			</form>
		</center>
		<br>
	</div>
	<div class="footerStyle">Site developed by Xoriant Solutions Pvt
		Ltd.</div>

</body>
</html>