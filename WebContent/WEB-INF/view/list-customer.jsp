<!-- Support for jstl tags -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>List Customers</title>
	<link type="text/css" 
			rel = "stylesheet"
			href = "${pageContext.request.contextPath}/resources/css/style.css" />
</head>
<body>
	<div id= "wrapper"> 
		<div id="header">
			<h2>CRM - Customer Relationship Manager </h2>	
		</div>
	</div>
	<div id="container">
		<div id = "content" >
		
			<!--  Create a new Button here -->
			<input type = "button" value = "Add Customer" 
				onClick="window.location.href='showFormForAdd'; return false;"
				class = "add-button"
			/>
		
			<!-- add table here -->
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
			<!-- Loop over and print customers -->
			<c:forEach var="tempCustomer" items="${customers}">
			
				<!-- Construct an update link with customer id  -->
				<c:url var="updateLink" value="/customer/showFormForUpdate">
					<c:param name="customerId" value="${tempCustomer.id}"></c:param>
				</c:url>
				
				<tr>
					<td> ${tempCustomer.firstName } </td> <!-- calls tempCustomer.getFirstName() -->
					<td> ${tempCustomer.lastName } </td> <!-- calls tempCustomer.getLastName() -->
					<td> ${tempCustomer.email } </td> <!-- calls tempCustomer.getEmail() -->
					<td> <a href = "${updateLink}">Update</a> </td>   <!-- this displays the updateLink created above -->
				</tr>
				
			</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>