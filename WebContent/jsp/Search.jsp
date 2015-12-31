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
		<nav> <a
			href='/LibraryManagementSystem/jsp/User.jsp?emailID=<c:out value="${emailID}"/>'>HOME|
			<a
			href='/LibraryManagementSystem/UserInformation?emailID=<c:out value="${emailID}" />'>USER
				INFORMATION</a> | <a
			href='/LibraryManagementSystem/BookDetails?emailID=<c:out value="${emailID}"/>'>BOOK
				DETAILS</a> | <a>SEARCH</a></nav>
		<br>
		<c:if test="${not empty temp}">
			<p class="error">
				<c:out value="${temp}" />
			</p>
		</c:if>
		<br>
		<form
			action="/LibraryManagementSystem/SearchByAll?emailID=<c:out value="${emailID}"/>"
			method="post" name="search">
			<fieldset>
				<br> <br> <input type="text" name="search"></input> <br>
				<br> <input type="submit" id="search" value="Search"
					name="search" />
			</fieldset>

		</form>
		<div class="footerStyle">Site developed by Xoriant Solutions
			Pvt. Ltd.</div>
	</center>
</body>
</html>