<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="/LibraryManagementSystem/css/demo.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
			<c:out value="${formUser.userName}" />
		</h3>
		</h1>
		<a
			href='/LibraryManagementSystem/jsp/Librarian.jsp?emailID=<c:out value="${emailID}"/>'>HOME</a>
		| <a
			href='/LibraryManagementSystem/BookInformation?emailID=<c:out value="${emailID}"/>'>BOOK
			INFORMATION</a> |LIBRARIAN
			INFORMATION | <a
			href='/LibraryManagementSystem/jsp/IssueBook.jsp?emailID=<c:out value="${emailID}"/>'>ISSUE</a>
		| <a
			href='/LibraryManagementSystem/jsp/ReturnBook.jsp?emailID=<c:out value="${emailID}"/>'>RETURN</a>
		| <a
			href='/LibraryManagementSystem/jsp/AddNewBook.jsp?emailID=<c:out value="${emailID}"/>'>ADD
			BOOK</a> | <a
			href='/LibraryManagementSystem/Pending?emailID=<c:out value="${emailID}"/>'>PENDING</a>
		| <a
			href='/LibraryManagementSystem/CheckBookRequest?emailID=<c:out value="${emailID}"/>'>BOOK
			REQUESTS</a> <br> <br>
		</div>
		<div align="center">


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
						action="/LibraryManagementSystem/jsp/ModifyLibrarian.jsp?emailID=<c:out value="${emailID}"/>">
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
						<input type="hidden" name="city" id="city"
							value="${formUser.city}" /> <br> <br>
			</div>
		<br> <br> <INPUT type="submit" name="modify" class="btn"
			value="Modify" align="center" />
		</form>

		<div class="footerStyle">Site developed by Xoriant Solutions
			Pvt. Ltd.</div>
	</center>
</body>
</html>