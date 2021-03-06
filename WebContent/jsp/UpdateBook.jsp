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
<script src="/LibraryManagementSystem/javascript/Validation.js"></script>

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
			| <a
				href='/LibraryManagementSystem/CheckBookRequest?emailID=<c:out value="${emailID}"/>'>BOOK
				REQUESTS</a>
		</div>
		<br>
		<table border="1" bordercolor="brown">
			<tr>
				<td>
					<div align="center">
						<form name="modify1" id="modify1"
							action="/LibraryManagementSystem/UpdateBook" method="post">
							<table>
								<tr>

									<td><input type="hidden" name="bookID"
										value="${formBook.bookID}" pattern="^[1-9]\d*$" /></td>
								</tr>
								<tr>
									<td>Title:</td>
									<td><input type="text" name="title" required="required"
										pattern="^[a-zA-Z.\s]+$" placeholder="Eg. Harry Potter"
										title="Please Enter A String" value="${formBook.title}" /></td>
								</tr>
								<tr>
									<td>Author:</td>
									<td><input type="text" name="author" required="required"
										pattern="^[a-zA-Z.\s]+$" value="${formBook.author}"
										title="Please Enter A String" placeholder="Eg. J.K.Rowling" /></td>
								</tr>
								<tr>
									<td>Price:</td>
									<td><input type="text" name="price" required="required"
										value="${formBook.price}" placeholder="Eg. 500"
										onblur="nospaces(this)" title="Enter Numerical Value"
										pattern="[0-9.]+" /></td>
								</tr>
								<tr>
									<td>Publication Name:</td>
									<td><input type="text" name="publicationName"
										pattern="^[a-zA-Z.\s]+$" placeholder="Eg. Wiley Publication"
										title="Please Enter A String" required="required"
										value="${formBook.publicationName}" /></td>
								</tr>
								<tr>
									<td>Count:</td>
									<td><input type="number" name="bookCount" min="1" max="50"
										required="required" placeholder="1" pattern="[0-9]+"
										onblur="nospaces(this)" value="${formBook.bookCount}"></td>
								</tr>
							</table>
							<input type="hidden" value="${formBook.bookCount}"
								name="copyCount"> <br> <input type="submit"
								class="btn" name="action" value="MODIFY"
								onclick="return validateModify()">



						</form>
					</div>
				</td>

			</tr>
		</table>
		<c:if test="${not empty op}">
			<h5>
				<font class="success">The book record has been successfully <c:out
						value="${op}" />
				</font>
			</h5>
		</c:if>
		<c:if test="${not empty temp}">
			<p class="error">
				<c:out value="${temp}" />
			</p>
		</c:if>
		<div class="footerStyle">Site developed by Xoriant Solutions
			Pvt. Ltd.</div>
	</center>
</body>
</html>