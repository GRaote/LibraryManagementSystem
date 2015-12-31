<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="/LibraryManagementSystem/css/demo.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

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
			Welcome LIBRARIAN
			<c:out value="${name}" />
		</h3>
		<a
			href='/LibraryManagementSystem/jsp/Librarian.jsp?emailID=<c:out value="${emailID}"/>'>HOME</a>
		| BOOK INFORMATION | <a
			href='/LibraryManagementSystem/UserInformation?emailID=<c:out value="${emailID}"/>'>LIBRARIAN
			INFORMATION</a> | <a
			href='/LibraryManagementSystem/jsp/IssueBook.jsp?emailID=<c:out value="${emailID}"/>'>ISSUE</a>
		| <a
			href='/LibraryManagementSystem/jsp/ReturnBook.jsp?emailID=<c:out value="${emailID}"/>'>RETURN</a>
		| <a
			href='/LibraryManagementSystem/jsp/AddNewBook.jsp?emailID=<c:out value="${emailID}"/>'>ADD
			BOOK</a> | <a
			href='/LibraryManagementSystem/Pending?emailID=<c:out value="${emailID}"/>'>PENDING</a>
		| <a
			href='/LibraryManagementSystem/CheckBookRequest?emailID=<c:out value="${emailID}"/>'>BOOK
			REQUESTS</a> <br> <br> <br> <br>



		<div id="container" name="table">
			<div id="first-row" name="tr">
				<div id="col">Title</div>
				<div id="col">Author</div>
				<div id="col">Price</div>
				<div id="col">Publication Name</div>
				<div id="col">Count</div>
				<div id="col">Action</div>
			</div>

			<c:forEach items="${formBook}" var="book">
				<div id="row" name="tr">


					<div id="col">
						<c:out value="${book.title}" />
					</div>
					<div id="col">
						<c:out value="${book.author}" />
					</div>
					<div id="col">
						<c:out value="${book.price}" />
					</div>
					<div id="col">
						<c:out value="${book.publicationName}" />
					</div>
					<div id="col">
						<c:out value="${book.bookCount}" />
					</div>
					<div id="col">
						<form action="/LibraryManagementSystem/UpdateBook" method="post">
							<input type="hidden" name="bookID" value="${book.bookID}" /> <input
								class="btn" type="submit" name="action" value="DELETE">
							<input class="btn" type="submit" name="action" value="UPDATE">
						</form>
					</div>

				</div>

			</c:forEach>
		</div>


		<table border="1">
			<tr>
				<c:forEach begin="1" end="${noOfPages}" var="i">
					<c:choose>
						<c:when test="${currentPage eq i}">
							<td>${i}</td>

						</c:when>
						<c:otherwise>
							<td><a
								href="/LibraryManagementSystem/BookInformation?page=${i}">${i}</a></td>

						</c:otherwise>
					</c:choose>
				</c:forEach>
			</tr>
		</table>

		<br>
		<c:if test="${not empty op}">
			<h5>
				<font class="success">The book record has been successfully <c:out
						value="${op}" />
				</font>
			</h5>
		</c:if>
		<div class="footerStyle">Site developed by Xoriant Solutions
			Pvt. Ltd.</div>
	</center>

</body>
</html>