<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>"Welcome "${login}""</title>
</head>
<body>
<h1>Welcome "${login}"</h1>
<form action="customer">
	
	<a href ="Amountdeposited.jsp" ><h1>deposit money</h1></a>
	<a href="SearchAnumber.jsp"><h1>Transfer money from one account to another</h1></a>
	<a href="withdraw.jsp"><h1>withdraw money</h1></a>
	<a href="display.jsp"><h1>Balance Inquiry</h1></a>
	<a href ="customer"><h1>print mini-statement</h1></a>
	<a href="password.jsp"><h1>change password</h1></a>
	</form>

</body>
</html>