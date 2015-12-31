<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js">
	
</script>
<script type="text/javascript">
	function showData(value) {
		$.ajax({
			url : "/LibraryManagementSystem/Test?name=" + value,
			type : "POST",
			async : false,
			success : function(data) {
				$('#name').val(data);
				
			}
		});
	}
</script>
</head>
<body>
	<form name="employee">
		<input type="text" name="name" id="name"
			onkeyup="showData(this.value);" list="languages"><br>

		<datalist id="languages" name="languages">
		<option id="name2"></option>
		<option></option>
		</datalist>
<p><c:out value="${buffer}" />
		</table>
</body>
</html>