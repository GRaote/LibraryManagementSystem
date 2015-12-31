<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="/LibraryManagementSystem/css/demo.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Portal</title>

</head>
<body>
	<c:if
		test="${(empty sessionScope.name) || (sessionScope.userType !='ADMIN')}">
		<c:redirect url="HomeTestLogin.jsp">
		</c:redirect>
	</c:if>
	<center>
		<%@ include file="/jsp/Header.jsp"%>

		<h3 class="borderShadow">
			Welcome Admin
			<c:out value="${sessionScope.name}" />
		</h3>
		<nav> HOME | <a
			href='/LibraryManagementSystem/AdminHome?emailID=<c:out value="${emailID}"/>'>USER
			INFORMATION</a> | <a
			href='/LibraryManagementSystem/UserInformation?emailID=<c:out value="${emailID}"/>'>ADMIN
			INFORMATION</a> | <a
			href='/LibraryManagementSystem/jsp/AddUser.jsp?emailID=<c:out value="${emailID}"/>'>ADD
			USER</a> | <a
			href='/LibraryManagementSystem/jsp/AdminUserInfo.jsp?emailID=<c:out value="${emailID}"/>'>MODIFY
			USER</a> | <a
			href='/LibraryManagementSystem/jsp/AdminDeleteUser.jsp?emailID=<c:out value="${emailID}"/>'>DELETE
			USER</a> </nav>
		<br>
		<c:if test="${not empty temp}">
			<p class="success">
				<c:out value="${temp}" />
			</p>
		</c:if>
		<br>

		<div class="img">
			<img src="/LibraryManagementSystem/images/admin.jpg" width="980"
				height="228">

		</div>
		<div class="footerStyle">Site developed by Xoriant Solutions
			Pvt. Ltd.</div>
	</center>
	</font>
</body>
</html>