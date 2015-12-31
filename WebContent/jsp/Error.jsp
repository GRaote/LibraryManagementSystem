<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="/LibraryManagementSystem/css/demo.css">
<link rel="stylesheet" type="text/css" href="/css/demo.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Library Management System</title>
<script>
	function goBack() {
		window.history.back()
	}
</script>
</head>
<body>

	<center>
		<%@ include file="/jsp/Header.jsp"%>

		<h3>
			<font class="error">Oops! something went wrong, please try again..</font>
		</h3>

		<button onclick="goBack()">Go Back</button>
		<div class="footerStyle">Site developed by Xoriant Solutions
			Pvt. Ltd.</div>
	</center>

</body>
</html>