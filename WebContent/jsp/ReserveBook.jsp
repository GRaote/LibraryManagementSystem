<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="/LibraryManagementSystem/css/demo.css">
<script type="text/javascript" src="/javascript/demo.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Library Management System</title>
</head>
<body>
	<center>
		<h2 class="borderShadow">
			WELCOME LIBRARIAN
			<c:out value="${formUser.userName}" />
		</h2>
		<div class="img">
			<br> <a
				href="/LibraryManagementSystem/jsp/User.jsp?emailID=<c:out value="${emailID}"/>">HOME|
				<a
				href="/LibraryManagementSystem/UserInformation?emailID=<c:out
					value="${emailID}" />">USER
					INFORMATION</a> | <a id="tab2"
				href="/LibraryManagementSystem/BookDetails?emailID=<c:out value="${emailID}"/>">BOOK
					DETAILS</a> | <a id="tab3"
				href="/LibraryManagementSystem/jsp/Search.jsp?emailID=<c:out value="${emailID}"/>">SEARCH</a>
		</div>
	</center>
	<br>
	<br>
	<div align="center">
		<form
			action='/LibraryManagementSystem/jsp/User.jsp?emailID=<c:out value="${emailID}" />'
			method="post">
			<font class="success">Book reserved succesfully!</font> <br> <br>
			<input type="submit" class="btn" value="OK" name="submit">
		</form>
	</div>
	<div align="center" class="footerStyle">Site developed by Xoriant
		Solutions Pvt. Ltd.</div>
</body>
</html>