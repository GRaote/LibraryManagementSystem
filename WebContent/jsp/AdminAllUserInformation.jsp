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
			<c:out value="${name}" />
		</h3>

		<nav> <a
			href='/LibraryManagementSystem/jsp/AdminPortal.jsp?emailID=<c:out value="${emailID}"/>'>HOME</a>|

		USER INFORMATION | <a
			href='/LibraryManagementSystem/UserInformation?emailID=<c:out value="${emailID}"/>'>ADMIN
			INFORMATION</a> | <a
			href='/LibraryManagementSystem/jsp/AddUser.jsp?emailID=<c:out value="${emailID}"/>'>ADD
			USER</a> | <a
			href='/LibraryManagementSystem/jsp/AdminUserInfo.jsp?emailID=<c:out value="${emailID}"/>'>MODIFY
			USER</a> | <a
			href='/LibraryManagementSystem/jsp/AdminDeleteUser.jsp?emailID=<c:out value="${emailID}"/>'>DELETE
			USER</a> </nav>


		<br>
		<div id="container" name="table">
			<div id="first-row" name="tr">
				<div id="col">Email</div>
				<div id="col">Name</div>
				<div id="col">DOB</div>
				<div id="col">Location</div>
			</div>

			<c:forEach items="${formUsers}" var="user">
				<div id="row" name="tr">
					<div id="col">
						<c:out value="${user.emailID}" />
					</div>
					<div id="col">
						<c:out value="${user.fullName}" />
					</div>
					<div id="col">
						<c:out value="${user.dateOfBirth}" />
					</div>
					<div id="col">
						<c:out value="${user.city}" />
						,
						<c:out value="${user.state}" />
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
							<td><a href="/LibraryManagementSystem/AdminHome?page=${i}">${i}</a></td>

						</c:otherwise>
					</c:choose>
				</c:forEach>
			</tr>
		</table>





		<br>
		<div class="footerStyle">Site developed by Xoriant Solutions
			Pvt. Ltd.</div>
	</center>

</body>
</html>