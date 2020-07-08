<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="bank">
	"${login}"
	<a href ="CreateCustomer.jsp" ><h1>Creating a Customer account </h1></a>
	<a href="SearchCustomer.jsp"><h1>Search CustomerdetailsThrough Account number</h1></a>
	<a href="ModifyCustomer.jsp"><h1>Modify Customerdetails Through Account number</h1></a>
	<a href="balanceInquiry.jsp"><h1>Balance Inquiry</h1></a>
	<a href ="CloseCustomerAccount.jsp"><h1>Close Customer Account</h1></a>
	<a href="bank"><h1>Display all customerdetails</h1></a>
	</form>
</body>
</html>