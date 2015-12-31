<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
<title></title>
<link rel="stylesheet"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.6/themes/base/jquery-ui.css"
	type="text/css" media="all" />
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"
	type="text/javascript"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.6/jquery-ui.min.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(function() {

		$(".button").button();
		$('#txtDate').datepicker({
			dateFormat : "dd/mm/yy",
			yearRange : "-120:+00",
			changeMonth : true,
			changeYear : true,
			maxDate : new Date
		});
		$("#button").button();
		$("#selectmenu1").selectmenu();
		$("#selectmenu2").selectmenu();
	});
</script>
</head>
<body>
	<form name="form1" method="post"
		action="jquery-datepicker-disable-future-dates.aspx" id="form1">
		<div>
			<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE"
				value="/wEPDwUJMTI2NTkwNzg4ZGRCovYA6eSctnwwP0aKrRc7ty/4e56uq4+lT4Py9Ew3qw==" />
		</div>

		<div>

			<input type="hidden" name="__EVENTVALIDATION" id="__EVENTVALIDATION"
				value="/wEdAAL8YxE8RMTg4ayad72eVprhQmTnPkxgOAq63YiVY5iwpv8JnXvq0JqUsVQjwkWagnZZMw1ULrl0b/IVkZA50RBR" />
		</div>
		<input name="txtDate" required="required" placeholder="12/12/2000"
			type="text" id="txtDate" />
	</form>
</body>
</html>
