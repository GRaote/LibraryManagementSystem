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
			<c:out value="${formUser.userName}" />
		</h3>
		<a
			href="/LibraryManagementSystem/jsp/User.jsp?emailID=<c:out value="${emailID}"/>">HOME|
			<a class="atag" id="tab1">USER INFORMATION</a> | <a id="tab2"
			href="/LibraryManagementSystem/BookDetails?emailID=<c:out value="${emailID}"/>">BOOK
				DETAILS</a> | <a id="tab3"
			href="/LibraryManagementSystem/jsp/Search.jsp?emailID=<c:out value="${emailID}"/>">SEARCH</a>
			<br> <br> <br>

			<div id="container-user" name="table" align="center">
						<div id="row" name="tr">
							<div id="col">
								<b>Name:</b>
							</div>
							<div id="col">
								<c:out value="${formUser.fullName }" />
							</div>
						</div>
						<div id="row" name="tr">
							<div id="col">
								<b>Address:</b>
							</div>
							<div id="col">
								<c:out value="${formUser.addressFirstLine }" />
								<c:out value="${formUser.addressSecondLine }" />
								,
								<c:out value="${formUser.zipCode }" />
								,
								<c:out value="${formUser.city }" />
								,
								<c:out value="${formUser.state }" />
							</div>
						</div>

						<div id="row" name="tr">
							<div id="col">
								<b> Date Of Birth:</b>
							</div>
							<div id="col">
								<c:out value="${formUser.dateOfBirth }" />
							</div>
						</div>
					</div>
			
			<form method="post"
				action="/LibraryManagementSystem/jsp/ModifyUser.jsp?emailID=<c:out value="${emailID}"/>">
				<input type="hidden" name="fullName" id="fullName"
					value="${formUser.fullName}" /> <input type="hidden"
					name="userName" id="userName" value="${formUser.userName}" /> <input
					type="hidden" name="password" id="password" /> <input
					type="hidden" name="emailID" id="emailID"
					value="${formUser.emailID}" /> <input type="hidden"
					name="addressFirstLine" id="addressFirstLine"
					value="${formUser.addressFirstLine}" /> <input type="hidden"
					name="addressSecondLine" id="addressSecondLine"
					value="${formUser.addressSecondLine}" /> <input type="hidden"
					name="zipCode" id="zipCode" value="${formUser.zipCode}" /> <input
					type="hidden" name="state" id="state" value="${formUser.state}" />
				<input type="hidden" name="city" id="city" value="${formUser.city}" />
				<br> <br> <INPUT type="submit" class="btn" name="modify"
					value="Modify" />
			</form>
			<div class="footerStyle">Site developed by Xoriant Solutions
				Pvt. Ltd.</div>
	</center>
	<br>
</body>
</html>