<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="/LibraryManagementSystem/css/demo.css">
<link rel="stylesheet" type="text/css" href="/css/demo.css">
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
		<a
			href="/LibraryManagementSystem/jsp/User.jsp?emailID=<c:out value="${emailID}"/>">HOME|
			<a
			href="/LibraryManagementSystem/UserInformation?emailID=<c:out value="${emailID}" />">USER
				INFORMATION</a> | <a id="tab2">BOOK DETAILS</a> | <a id="tab3"
			href="/LibraryManagementSystem/jsp/Search.jsp?emailID=<c:out value="${emailID}"/>">SEARCH</a>

			<br> <br> <c:if test="${not empty formBookWithStatus}">
				<b>ISSUED BOOKS:</b>
				<br>
				<br>
				<table>
					<c:forEach var="ob" varStatus="status"
						items="${formBookWithStatus}">

						<tr>
							<td><b>Title: &nbsp;</b></td>
							<td><c:out value="${ob.title }" /></td>
						</tr>
						<tr>
							<td><b>Author: &nbsp;</b></td>
							<td><c:out value="${ob.author }" /></td>
						</tr>
						<tr>
							<td><b>Price: &nbsp;</b></td>
							<td><c:out value="${ob.price }" /></td>
						</tr>
						<tr>
							<td><b>Publication: &nbsp;</b></td>
							<td><c:out value="${ob.publicationName }" /></td>
						</tr>
						<tr>
							<td><b>Issued On: &nbsp; </b></td>
							<td><c:out value="${ob.issuedOn }" /></td>
						</tr>
						<tr>
							<td><b>Due Date: &nbsp; </b></td>
							<td><c:out value="${ob.dueDate }" /></td>
						</tr>
						<tr>
							<td></td>
						</tr>
						<tr>
							<td></td>
						</tr>
					</c:forEach>
				</table>
			</c:if> <c:if test="${empty formBookWithStatus}">
				<font></font>
			</c:if> <c:if test="${not empty requestedBooks}">
				<br>
				<b>BOOKS RESERVED:</b>
				<br>
				<br>
				<table>
					<c:forEach var="ob" varStatus="status" items="${requestedBooks}">

						<tr>
							<td><b>Title: &nbsp;</b></td>
							<td><c:out value="${ob.title }" /></td>
						</tr>
						<tr>
							<td><b>Author: &nbsp;</b></td>
							<td><c:out value="${ob.author }" /></td>
						</tr>
						<tr>
							<td><b>Price: &nbsp;</b></td>
							<td><c:out value="${ob.price }" /></td>
						</tr>
						<tr>
							<td><b>Publication: &nbsp;</b></td>
							<td><c:out value="${ob.publicationName }" /></td>
						</tr>
						<tr>
							<td><b>Status:</b></td>
							<td>Request/Renewal not approved yet</td>
						</tr>
						<tr>
							<td></td>
						</tr>
						<tr>
							<td></td>
						</tr>
					</c:forEach>
				</table>
			</c:if> <c:if test="${empty requestedBooks}">
				<font></font>
			</c:if>
	</center>
	<center>
		<div class="footerStyle">Site developed by Xoriant Solutions
			Pvt. Ltd.</div>
	</center>

</body>
</html>