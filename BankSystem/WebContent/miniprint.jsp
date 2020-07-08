<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

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
<body>
		
			<h1 >"Mini-Statement"</h1>
		<table border ="5">
		
				<tr>
							
							<td>Date with time</td>
							<td>operation</td>
							<td>Amount</td>
				</tr>
				<c:forEach var="l" items="${list}">
				<tr>
						
							<td>"${l.date}"</td>
							<td>"${l.s}"</td>
							<td>"${l.amount}"</td>
				</tr>	
				</c:forEach>
		
		</table>
					
				
		
</body>
</html>