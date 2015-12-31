<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="/LibraryManagementSystem/css/demo.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<center>
		<%@ include file="/jsp/Header.jsp"%>

		<h3 class="borderShadow">
			WELCOME LIBRARIAN
			<c:out value="${name}" />
		</h3>
		<div class="img">
			<br>
	<a href='/LibraryManagementSystem/jsp/Librarian.jsp?emailID=<c:out value="${emailID}"/>'>HOME</a> |
		
				 <a href='/LibraryManagementSystem/BookInformation?emailID=<c:out value="${emailID}"/>'>BOOK INFORMATION</a> |
			 
			  <a href='/LibraryManagementSystem/UserInformation?emailID=<c:out value="${emailID}"/>'>LIBRARIAN INFORMATION</a> |
			  
			   <a href='/LibraryManagementSystem/jsp/IssueBook.jsp?emailID=<c:out value="${emailID}"/>'>ISSUE</a> |
			   
			    <a href='/LibraryManagementSystem/jsp/ReturnBook.jsp?emailID=<c:out value="${emailID}"/>'>RETURN</a> | 
			    
			     <a href='/LibraryManagementSystem/jsp/AddNewBook.jsp?emailID=<c:out value="${emailID}"/>'>ADD BOOK</a>  |
			    
			     <a href='/LibraryManagementSystem/Pending?emailID=<c:out value="${emailID}"/>'>PENDING</a> |
			     
			<a href='/LibraryManagementSystem/CheckBookRequest?emailID=<c:out value="${emailID}"/>'>BOOK REQUESTS</a>
		</div>

		<c:choose>
			<c:when test="${empty formBook}">
				<font color="red"></font>
				<p>The book was not reserved</p>
				</font>
			</c:when>
			<c:otherwise>

				<div align="center">
					<p>The following book is issued</p>
					<table border="1" width="250" height="100">
						<tr>
							<td>Title :</td>
							<td><c:out value="${formBook.title}" /></td>
						</tr>
						<tr>
							<td>Author :</td>
							<td><c:out value="${formBook.author}" /></td>
						</tr>
						<tr>
							<td>Price :</td>
							<td><c:out value="${formBook.price}" /></td>
						</tr>
						<tr>
							<td>Publication Name :</td>
							<td><c:out value="${formBook.publicationName}" /></td>
						</tr>
					</table>

				</div>
			</c:otherwise>
		</c:choose>

		<p>
			Issue Date:
			<c:out value="${formBookStatus.issuedOn}" />
			&nbsp;&nbsp; Due Date:
			<c:out value="${formBookStatus.dueDate}" />
		</p>
		<br> <br>
		<form action="/LibraryManagementSystem/jsp/Librarian.jsp"
			method="post">
			<input type="submit" class="btn" value="OK">
		</form>
		</div>
		<div class="footerStyle">Site developed by Xoriant Solutions
			Pvt. Ltd.</div>
	</center>
</body>
</html>