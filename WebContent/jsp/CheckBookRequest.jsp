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
				BOOK</a> | <a
				href='/LibraryManagementSystem/Pending?emailID=<c:out value="${emailID}"/>'>PENDING</a>
			| BOOK REQUESTS
		</div>
		<br>
		<c:if test="${not empty requestedList}">

			<c:forEach var="ob" varStatus="status" items="${requestedList}">

				<div id="container-book" name="table">
					<table>
						<tr>
							<td><b>EmailID:</b></td>
							<td><c:out value="${ob.emailID }" /></td>
						</tr>
						<tr>
							<td><b>CopyID:</b></td>
							<td><c:out value="${ob.copyID }" /></td>
						</tr>
						<tr>
							<td><b>Request Status:</b>
							<td><c:choose>
									<c:when test="${ob.bookRequestStatus eq 'NEW'}">
									Reserved Book
								</c:when>
									<c:otherwise>Renewed Book</c:otherwise>
								</c:choose></td>

						</tr>
						<tr>
						</tr>

					</table>
					<form name="cancel" action="/LibraryManagementSystem/CancelRequest"
						method="post">

						<input type="hidden" name="emailID" id="emailID"
							value="${ob.emailID}" /> <input type="hidden" name="copyID"
							id="copyID" value="${ob.copyID}" /> <input type="hidden"
							name="bookRequestStatus" id="bookRequestStatus"
							value="<c:out value="${ob.bookRequestStatus}"/>" /> <input
							type="submit" name="cancel" value="Cancel Request">
					</form>
				</div>
				<br>
			</c:forEach>

		</c:if>
		<br> <br>
		<c:if test="${empty requestedList}">
			<font>No Requests made.</font>
		</c:if>
		<br> <br>
		<div class="footerStyle">Site developed by Xoriant Solutions
			Pvt. Ltd.</div>
	</center>
</body>
</html>