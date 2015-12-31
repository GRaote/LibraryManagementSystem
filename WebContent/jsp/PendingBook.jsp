<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="/LibraryManagementSystem/css/demo.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:if
		test="${(empty sessionScope.name) || (sessionScope.userType !='LIBRARIAN')}">
		<c:redirect url="HomeTestLogin.jsp">
		</c:redirect>
	</c:if>
	<center>
		<%@ include file="/jsp/Header.jsp"%>

		<h3 class="borderShadow">
			WELCOME LIBRARIAN
			<c:out value="${name}" />
		</h3>
		<div class="img">

			<a
				href='/LibraryManagementSystem/jsp/Librarian.jsp?emailID=<c:out value="${emailID}"/>'>HOME</a>
			| <a
				href='/LibraryManagementSystem/BookInformation?emailID=<c:out value="${emailID}"/>'>BOOK
				INFORMATION</a> | <a
				href='/LibraryManagementSystem/UserInformation?emailID=<c:out value="${emailID}"/>'>LIBRARIAN
				INFORMATION</a> | <a
				href='/LibraryManagementSystem/jsp/IssueBook.jsp?emailID=<c:out value="${emailID}"/>'>ISSUE</a>
			| <a
				href='/LibraryManagementSystem/jsp/ReturnBook.jsp?emailID=<c:out value="${emailID}"/>'>RETURN</a>
			| <a
				href='/LibraryManagementSystem/jsp/AddNewBook.jsp?emailID=<c:out value="${emailID}"/>'>ADD
				BOOK</a> | PENDING | <a
				href='/LibraryManagementSystem/CheckBookRequest?emailID=<c:out value="${emailID}"/>'>BOOK
				REQUESTS</a> <br> <br>
		</div>
		<c:if test="${not empty pendingList}">
			<table border="1">
				<tr>
					<th>User</th>
					<th>Book ID</th>
					<th>Title</th>
					<th>Due Date</th>
					<th>Email</th>
				</tr>

				<c:forEach items="${pendingList}" var="pendingList">
					<tr>
						<form
							action='/LibraryManagementSystem/SendMail?emailID=<c:out value="${pendingList.emailID}"/>'
							method="post">
							<td><c:out value="${pendingList.emailID}" /></td>
							<td><c:out value="${pendingList.bookID}" /></td>
							<td><c:out value="${pendingList.title}" /></td>
							<td><c:out value="${pendingList.dueDate}" /></td> <input
								type="hidden" name="emailID" value="${pendingList.emailID}">
							<input type="hidden" name="title" value="${pendingList.title}">
							<td><input type="submit" name="submit" value="Email"></td>
						</form>
					</tr>
				</c:forEach>

			</table>
		</c:if>
		<br>
		<br>
		<c:if test="${empty pendingList}">
			No books have crossed their respective due date
		</c:if>
		<br> <br>
		<div class="footerStyle">Site developed by Xoriant Solutions
			Pvt. Ltd.</div>
	</center>
</body>
</html>