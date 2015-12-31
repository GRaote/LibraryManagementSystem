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
			<c:out value="${name}" />
		</h3>
		<div class="img">
		
			HOME |
			
			 <a href='/LibraryManagementSystem/BookInformation?emailID=<c:out value="${emailID}"/>'>BOOK INFORMATION</a> |
			 
			  <a href='/LibraryManagementSystem/UserInformation?emailID=<c:out value="${emailID}"/>'>LIBRARIAN INFORMATION</a> |
			  
			   <a href='/LibraryManagementSystem/jsp/IssueBook.jsp?emailID=<c:out value="${emailID}"/>'>ISSUE</a> |
			   
			    <a href='/LibraryManagementSystem/jsp/ReturnBook.jsp?emailID=<c:out value="${emailID}"/>'>RETURN</a> | 
			    
			     <a href='/LibraryManagementSystem/jsp/AddNewBook.jsp?emailID=<c:out value="${emailID}"/>'>ADD BOOK</a>  |
			    
			     <a href='/LibraryManagementSystem/Pending?emailID=<c:out value="${emailID}"/>'>PENDING</a> |
			     
			<a href='/LibraryManagementSystem/CheckBookRequest?emailID=<c:out value="${emailID}"/>'>BOOK REQUESTS</a>
			
			<br> <br>
			
			<div class="img">
				<img src="/LibraryManagementSystem/images/issue.jpg" width="980"
					height="228">

			</div>
		</div>
		<div align="center">
			<div class="footerStyle">Site developed by Xoriant Solutions
				Pvt. Ltd.</div>
		</div>
	</center>
	</font>
</body>
</html>