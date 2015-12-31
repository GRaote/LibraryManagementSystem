<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="/LibraryManagementSystem/javascript/Validation.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="/LibraryManagementSystem/css/demo.css">
<title>Insert title here</title>
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
			<c:out value="${name}" />
		</h3>

			<nav>	
	 <a href='/LibraryManagementSystem/jsp/AdminPortal.jsp?emailID=<c:out value="${emailID}"/>'>HOME</a>|
		 
		<a href='/LibraryManagementSystem/AdminHome?emailID=<c:out value="${emailID}"/>'>USER INFORMATION</a> |
		
<a href='/LibraryManagementSystem/UserInformation?emailID=<c:out value="${emailID}"/>'>ADMIN INFORMATION</a> | 
			
    <a href='/LibraryManagementSystem/jsp/AddUser.jsp?emailID=<c:out value="${emailID}"/>'>ADD USER</a> |
			
  <a href='/LibraryManagementSystem/jsp/AdminUserInfo.jsp?emailID=<c:out value="${emailID}"/>'>MODIFY USER</a> | 
			
	DELETE USER 
				</nav>

		<br> <br>
		<c:if test="${not empty admin}">
			<p class="error">
				<c:out value="${admin}" />
			</p>
		</c:if>
		<c:if test="${not empty temp}">
			<p class="success">
				<c:out value="${temp}" />
			</p>
		</c:if>
	</center>
	<div align="center">

		<form action="/LibraryManagementSystem/AdminDelete" method="post"
			name="DeleteUser">


			<br> <strong>E-MAIL</strong> <input type="email" id="emailID"
				name="emailID" placeholder="sam@xxx.com" required="required" /> <br>
			<br> <INPUT type="submit" name="get info" value="Delete"
				class="btn" onclick="return validateEmail()" /><br>


		</form>
	</div>
	<center>
		<div class="footerStyle">Site developed by Xoriant Solutions
			Pvt. Ltd.</div>
	</center>
</body>
</html>