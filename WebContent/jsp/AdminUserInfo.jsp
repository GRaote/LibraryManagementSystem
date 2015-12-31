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
<script src="/LibraryManagementSystem/javascript/Validation.js"></script>
<script src="/LibraryManagementSystem/javascript/city_state.js"></script>
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

		<a
			href='/LibraryManagementSystem/AdminHome?emailID=<c:out value="${emailID}"/>'>USER
			INFORMATION</a> | <a
			href='/LibraryManagementSystem/UserInformation?emailID=<c:out value="${emailID}"/>'>ADMIN
			INFORMATION</a> | <a
			href='/LibraryManagementSystem/jsp/AddUser.jsp?emailID=<c:out value="${emailID}"/>'>ADD
			USER</a> | MODIFY USER | <a
			href='/LibraryManagementSystem/jsp/AdminDeleteUser.jsp?emailID=<c:out value="${emailID}"/>'>DELETE
			USER</a> </nav>

		<!-- 	-------------------------------------------------------------------------		------- -->
		<form action="/LibraryManagementSystem/AdminGetUserInfo" method="post"
			name="search">
			<br>
			<tr>
				<td><strong>E-MAIL</strong></td>
				<td><input type="email" id="emailID" name="emailID"
					required="required" placeholder="sam@xxx.com" /></td>


			</tr>
			<br> <br> <INPUT type="submit" class="btn" name="get info"
				value="Search" />

		</form>

		<c:if test="${not empty temp}">
			<p class="error">
				<c:out value="${temp}" />
			</p>
		</c:if>


		<!-- 	-------------------------------------------- -->
		<c:if test="${not empty temp1}">
			<p class="error">
				<c:out value="${temp1}" />
			</p>
		</c:if>

		<form action="/LibraryManagementSystem/ModifyAdmin" method="post"
			align:"center"  name="adduser" id="currentForm">

			<table cellpadding="3">
				<tr>
					<td width="150px"><strong>Full Name</strong></td>
					<td><input type="text" id="newsID" name="fullName"
						value="${formUser.fullName}" required="required"
						placeholder="Eg:Sam Joe" aria-describedby="name-format" required
						aria-required=”true” pattern="[A-Za-z]+\s[A-Za-z]+"
						title="Enter String Eg:Firstname(space)Lastname[NO SPACES ALLOWED ELSEWHERE]" /></td>
				</tr>

				<tr>
					<td><strong>User Name</strong></td>
					<td><input type="text" id="newsTitle" name="userName"
						value="${formUser.userName}" required="required"
						placeholder="Eg:Joe" required aria-required=”true”
						pattern="[A-Za-z]+" title="Please Enter String[NO SPACES ALLOWED]"
						onblur="nospaces(this)" /></td>
				</tr>

				<tr>
					<td><strong>Password</strong></td>
					<td><input type="text" id="newsDesc" name="password"
						required="required" placeholder="*******" required="required"
						aria-required=”true” onblur="nospaces(this)"
						pattern="^.*(?=.{6,})([a-zA-Z0-9]*)([!@#$%^&+=*])([a-zA-Z0-9]*)([!@#$%^&+=*])([a-zA-Z0-9]*).*$"
						title="Two Special Characters , Minimum length is 6 characters"
						onblur="nospaces(this)" /></td>
				</tr>
				<tr>
					<td><strong>E-Mail</strong></td>
					<td><input type="text" id="sample" name="emailID" READONLY
						value="${formUser.emailID}" placeholder="Eg:sam@xxx.com"
						required="required" onblur="validateEmail()"></td>
				</tr>
				<tr>
					<td><strong>Date Of Birth</strong></td>
					<td><%@ include file="/jsp/Date.jsp"%>
				</tr>
				<tr>
					<td><strong>User Type:</strong></td>
					<td><select name="userType" required id=ddlView>
							<option value="">Select</option>
							<option value="LIBRARIAN">LIBRARIAN</option>
							<option value="USER">USER</option>
							<option value="ADMIN">ADMIN</option>
					</select></td>
				<tr>
				<tr>
					<td><strong>Address</strong></td>
					<td><input name="addressFirstLine" type="text"
						value="${formUser.addressFirstLine}" required="required"
						placeholder="Eg:106,Winchester Hiranandani"
						pattern="(\s)*([a-zA-Z0-9\/,.\-\(\)\s])*[a-zA-Z]([a-zA-Z0-9\/,.\-\(\)\s])*"
						onKeyDown="limitText(this.form.addressFirstLine,this.form.countdown,50);"
						onKeyUp="limitText(this.form.addressFirstLine,this.form.countdown,50);"
						maxlength="50" title="Enter valid address Eg:106,Winchester.."><br>
						<font size="1">(Maximum characters: 50)<br> You have <input
							readonly type="text" name="countdown" size="3" value="50">
							characters left. </td>
				</tr>
				<tr>
					<td><strong></strong></td>
					<td><input type="text" name="addressSecondLine"
						value="${formUser.addressSecondLine}"
						title="Enter valid address Eg:Powai west" required="required"
						pattern="(\s)*([a-zA-Z0-9\/,.\-\(\)\s])*[a-zA-Z]([a-zA-Z0-9\/,.\-\(\)\s])*"
						placeholder="Eg:Powai west" /></td>
				</tr>

				<tr>
					<td><strong>State</strong></td>

					<td><select onchange="setState(this,city,zip)" size="1"
						name="region" id="ddlstate" required>

							<option value="">SELECT State</option>

							<script type="text/javascript">
								setRegions(this);
							</script>
					</select></td>
				</tr>
				<tr>
					<td><strong>City</strong></td>
					<td><select name="city" size="1" disabled="disabled"
						onchange="zipCode(this,zip)" id="ddlcity" required>
							<option value="0" selected="selected">SELECT city</option>
					</select></td>
				</tr>
				<tr>
					<td><strong>Zip Code</strong></td>
					<td><select name="zip" size="1" disabled="disabled"
						onchange="print_city_state(city,this)" required>
							<option value="0" selected="selected">SELECT State</option>
					</select></td>
				</tr>
			</table>
			<input type="submit" class="btn" name="submit" value="Modify" /> <input
				type="button" class="btn" onclick="resetFunction()" value="Reset">
		</form>
		<br>
		<br> Site developed by Xoriant Solutions Pvt. Ltd.

	</center>
</body>
</html>