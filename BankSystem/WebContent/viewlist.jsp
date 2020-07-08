<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>"Details of Customer"</title>
<style type="text/css">


body{
background-color: white;

}
table,th,td{
border :ipx dotted whilte;
border-collapse: collapse;
padding:20px;
font-size: 20px;
}
th{
background:purple;
padding:30px;
text-transform: uppercase;

}


</style>
</head>
<h1> "details of Customers"</h1>
<body>

			
		<table border ="5">
		
				<tr>
							<td>Customer name</td>
							<td>Account number</td>
							<td>Account balance</td>
							<
				</tr>
				<c:forEach var="l" items="${list}">
				<tr>
							<td>"${l.name}"</td>
							<td>"${l.number}"</td>
							<td>"${l.balance}"</td>
				</tr>	
				</c:forEach>
		
		</table>
					
				

</body>
</html>