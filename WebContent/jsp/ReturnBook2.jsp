<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="/LibraryManagementSystem/css/demo.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="/LibraryManagementSystem/javascript/Validation.js"></script>

</head>
<body>
	<center>
		<%@ include file="/jsp/Header.jsp"%>

		<h3 class="borderShadow">
			WELCOME LIBRARIAN
			<c:out value="${name}" />
		</h3>
		<div class="img">
			<br> <a
				href='/LibraryManagementSystem/jsp/Librarian.jsp?emailID=<c:out value="${emailID}"/>'>HOME</a>
			| <a
				href='/LibraryManagementSystem/BookInformation?emailID=<c:out value="${emailID}"/>'>BOOK
				INFORMATION</a> | <a
				href='/LibraryManagementSystem/UserInformation?emailID=<c:out value="${emailID}"/>'>LIBRARIAN
				INFORMATION</a> | <a
				href='/LibraryManagementSystem/jsp/IssueBook.jsp?emailID=<c:out value="${emailID}"/>'>ISSUE</a>
			| RETURN | <a
				href='/LibraryManagementSystem/jsp/AddNewBook.jsp?emailID=<c:out value="${emailID}"/>'>ADD
				BOOK</a> | <a
				href='/LibraryManagementSystem/Pending?emailID=<c:out value="${emailID}"/>'>PENDING</a>
			| <a
				href='/LibraryManagementSystem/CheckBookRequest?emailID=<c:out value="${emailID}"/>'>BOOK
				REQUESTS</a>

		</div>
		<br> <br> <br>

		<form name="goHome"
			action="/LibraryManagementSystem/jsp/Librarian.jsp" method="post">
			<p>
				<font class="success">The book was successfully returned</font>
			</p>
			<input type="submit" class="btn" value="OK">
		</form>

		<div class="footerStyle">Site developed by Xoriant Solutions
			Pvt. Ltd.</div>
	</center>
</body>
</html>