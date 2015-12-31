
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
	<c:if
		test="${(empty sessionScope.name) || (sessionScope.userType !='USER')}">
		<c:redirect url="HomeTestLogin.jsp">
		</c:redirect>
	</c:if>
	<center>
		<%@ include file="/jsp/Header.jsp"%>
		<h3 class="borderShadow">
			Welcome User
			<c:out value="${name}" />

		</h3>
		<a class="atag" id="tab1"
			href="/LibraryManagementSystem/UserInformation?emailID=<c:out value="${emailID}" />">USER
			INFORMATION</a> | <a
			href="/LibraryManagementSystem/BookDetails?emailID=<c:out value="${emailID}" />">BOOK
			DETAILS</a> | <a id="tab3">SEARCH</a><br> <br>
		<table border="1">
			<tr>
				<th>TITLE</th>
				<th>AUTHOR</th>
				<th>PRICE</th>
				<th>PUBLICATION NAME</th>
				<th>ACTION</th>
			</tr>
			<c:if test="${not empty renewList}">
				<c:forEach var="ob" varStatus="status" items="${renewList}">
					<tr>
						<form
							action='/LibraryManagementSystem/ReserveBook?emailID=<c:out value="${emailID}" />'
							method="post" name="book">
							<input type="hidden" name="bookID" value="${ob.bookID }" READONLY />
							<input type="hidden" name="getAction" value="renew" READONLY />
							<td><c:out value="${ob.title}" /></td>
							<td><c:out value="${ob.author}" /></td>
							<td><c:out value="${ob.price}" /></td>
							<td><c:out value="${ob.publicationName}" /></td>
							<td><input type="submit" name="renew" value="Renew" /></td>
						</form>
					</tr>
				</c:forEach>
				<br>
			</c:if>

			<c:if test="${not empty reserveList}">
				<c:forEach var="ob" varStatus="status" items="${reserveList}">
					<tr>
						<form
							action='/LibraryManagementSystem/ReserveBook?emailID=<c:out value="${emailID}" />'
							method="post" name="book">
							<tr>
								<input type="hidden" name="bookID" value="${ob.bookID }"
									READONLY />
								<input type="hidden" name="getAction" value="reserve" READONLY />
								<td><c:out value="${ob.title}" /></td>
								<td><c:out value="${ob.author}" /></td>
								<td><c:out value="${ob.price}" /></td>
								<td><c:out value="${ob.publicationName}" /></td>
								<td><input type="submit" name="reserve" value="Reserve" /></td>
						</form>
					</tr>
				</c:forEach>
				<br>
			</c:if>

			<c:if test="${not empty noActionList}">

				<c:forEach var="ob" varStatus="status" items="${noActionList}">
					<tr>
						<td><c:out value="${ob.title}" /></td>
						<td><c:out value="${ob.author}" /></td>
						<td><c:out value="${ob.price}" /></td>
						<td><c:out value="${ob.publicationName}" /></td>
						<td></td>
					</tr>



				</c:forEach>
				<br>
			</c:if>
		</table>

		<br>
		<c:if test="${empty formBooks}">
			<font class="error">Sorry! The book is not available in the
				library</font>
		</c:if>

		<br> <br>
		<form
			action="/LibraryManagementSystem/jsp/Search.jsp?emailID=<c:out value="${emailID}"/>"
			method="post" name="search">
			<INPUT type="submit" name="submit" value="Search Again" />
		</form>
		<div class="footerStyle">Site developed by Xoriant Solutions
			Pvt. Ltd.</div>
	</center>
</body>
</html>