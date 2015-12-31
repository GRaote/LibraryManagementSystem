<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="/LibraryManagementSystem/javascript/Validation.js"></script>
<script src="/LibraryManagementSystem/javascript/city_state.js"></script>

<link rel="stylesheet" type="text/css"
	href="/LibraryManagementSystem/css/demo.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Library Management System</title>
</head>
<body>
	<c:if
		test="${(empty sessionScope.name) || (sessionScope.userType !='ADMIN')}">
		<c:redirect url="HomeTestLogin.jsp">
		</c:redirect>
	</c:if>
	<center>
		<div class="headerStyle">
			<%@ include file="/jsp/Header.jsp"%>
			<h3 class="borderShadow">
				Welcome Admin
				<c:out value="${name}" />
			</h3>
		</div>
		<div class="content">
			<nav> <a
				href='/LibraryManagementSystem/jsp/AdminPortal.jsp?emailID=<c:out value="${emailID}"/>'>HOME</a>|

			<a
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


			<jsp:useBean id="userBean" class="com.xoriant.servlet.form.FormUser"
				scope="request">

				<jsp:setProperty property="*" name="userBean" />
			</jsp:useBean>

			<br>
			<form id="currentForm"
				action="/LibraryManagementSystem/Modify?emailID=<c:out value="${emailID}"/>"
				method="post">

				<table cellpadding="3">
					<tr>
						<td width="150px"><strong>Full Name</strong></td>
						<td><input type="text" id="newsID" name="fullName"
							value="<jsp:getProperty name="userBean" property="fullName" />"
							required="required" placeholder="Eg:Sam Joe"
							aria-describedby="name-format" required aria-required=”true”
							pattern="[A-Za-z]+\s[A-Za-z]+"
							title="Enter String Eg:Firstname(space)Lastname[NO SPACES ALLOWED ELSEWHERE]" /></td>
					</tr>

					<tr>
						<td><strong>User Name</strong></td>
						<td><input type="text" id="newsTitle" name="userName"
							value="<jsp:getProperty name="userBean" property="userName"/>"
							required="required" placeholder="Eg:Joe" required
							aria-required=”true” pattern="[A-Za-z]+"
							title="Please Enter String[NO SPACES ALLOWED]"
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
						<td><input id="sample" type="text" name="emailID"
							value="<jsp:getProperty name="userBean" property="emailID"/>"
							READONLY placeholder="Eg:sam@xxx.com" required="required"
							onblur="validateEmail()"></td>
					</tr>

					<tr>
						<td><strong>Address</strong></td>
						<td><input name="addressFirstLine" type="text"
							required="required" placeholder="Eg:106,Winchester Hiranandani"
							value="<jsp:getProperty name="userBean" property="addressFirstLine"/>"
							pattern="(\s)*([a-zA-Z0-9\/,.\-\(\)\s])*[a-zA-Z]([a-zA-Z0-9\/,.\-\(\)\s])*"
							onKeyDown="limitText(this.form.addressFirstLine,this.form.countdown,50);"
							onKeyUp="limitText(this.form.addressFirstLine,this.form.countdown,50);"
							maxlength="50" title="Enter valid address Eg:106,Winchester.."><br>
							<font size="1">(Maximum characters: 50)<br> You have
								<input readonly type="text" name="countdown" size="3" value="50">
								characters left. </td>
					</tr>
					<tr>
						<td><strong></strong></td>
						<td><input type="text" name="addressSecondLine"
							value="<jsp:getProperty name="userBean" property="addressSecondLine"/>"
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

				 <br> <INPUT type="submit" class="btn"
					onclick="return validateEmail()" name="modify" value="Modify" />
				&nbsp<input type="button" class="btn" onclick="resetFunction()"
					value="Default">
			</form>
		</div>
		<br>
		<br> Site developed by Xoriant Solutions Pvt. Ltd.

	</center>
</body>
</html>