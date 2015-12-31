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

			<a id="tab2" />HOME| <a
				href="/LibraryManagementSystem/UserInformation?emailID=<c:out
					value="${emailID}" />">USER
				INFORMATION </a> | <a id="tab2"
				href="/LibraryManagementSystem/BookDetails?emailID=<c:out value="${emailID}"/>">BOOK
				DETAILS</a> |<a id="tab3"
				href="/LibraryManagementSystem/jsp/Search.jsp?emailID=<c:out value="${emailID}"/>">SEARCH</a>
			<br><div class="img">
				<img src="/LibraryManagementSystem/images/user.jpg" width="980"
					height="320">

			</div>
			<div class="footerStyle">Site developed by Xoriant Solutions Pvt.
		Ltd.</div>
		</center>
	</font>
</body>
</html>