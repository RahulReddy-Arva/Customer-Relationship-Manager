<!-- Support for jstl tags -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List Customers</title>
</head>
<body>
	<div id= "wrapper"> 
		<div id="header">
			<h2>CRM - Customer Relationship Manager </h2>	
		</div>
	</div>
	<div id="container">
		<div id = "content" >
			<!-- add table here -->
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
				</tr>
			<!-- Loop over and print customers -->
			<c:forEach var="tempCustomer" items="${customers}">
				<tr>
					<td> ${tempCustomer.firstName } </td> <!-- calls tempCustomer.getFirstName() -->
					<td> ${tempCustomer.lastName } </td> <!-- calls tempCustomer.getLastName() -->
					<td> ${tempCustomer.email } </td> <!-- calls tempCustomer.getEmail() -->
				</tr>
			</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>