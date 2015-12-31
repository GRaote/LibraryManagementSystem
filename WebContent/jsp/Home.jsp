<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="/LibraryManagementSystem/css/demo.css">
<link rel="stylesheet" type="text/css" href="/css/demo.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Library Management System</title>

</head>
<body>

	<div class="top">
		<%@ include file="/jsp/HeaderHome.jsp"%>
	</div>
	<div class="mid">
		<div class="img">
			<img src="/LibraryManagementSystem/images/Home.jpg" width="980"
				height="158">
		</div>
		<c:if test="${not empty temp}">
			<p class="error">
				<c:out value="${temp}" />
			</p>
		</c:if>
		<center>
			<form name="login" action="/LibraryManagementSystem/UserType"
				method="post">
				<table class="tableStyle">
					<tr>
						<td><h3>Email ID:</h3></td>
						<td><h3>
								<input type="email" required="required" name="emailID"
									placeholder="Email Id">
							</h3></td>
					</tr>

					<tr>
						<td><h3>Password:</h3></td>
						<td><h3>
								<input type="password" name="password" placeholder="******"
									required aria-required=”true”>
							</h3></td>
					</tr>
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
	</div>
	<div class="footerStyle">Site developed by Xoriant Solutions Pvt
		Ltd</div>

</body>
</html>