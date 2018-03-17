<!-- Support for jstl tags -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri = "http://www.springframework.org/tags/form" %>

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
			Add new Customer: 
			<input type = "button" value = "Add Customer" 
				onClick="window.location.href='showFormForAdd'; return false;"
				class = "add-button"
			/>
			
			<!-- Create the search bar here -->
			
			<form:form action="searchCustomer" method="POST">
				Search Customer : 
				<input type="text" name = "theSearchName" />
				<input type = "Submit" value="Search" class="add-button" />
			</form:form>
			
			
		
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
				
				<!-- Construct an delete link with customer id  -->
				<c:url var="deleteLink" value="/customer/delete">
					<c:param name="customerId" value="${tempCustomer.id}"></c:param>
				</c:url>
				
				<tr>
					<td> ${tempCustomer.firstName } </td> <!-- calls tempCustomer.getFirstName() -->
					<td> ${tempCustomer.lastName } </td> <!-- calls tempCustomer.getLastName() -->
					<td> ${tempCustomer.email } </td> <!-- calls tempCustomer.getEmail() -->
					<td> 
						<a href = "${updateLink}">Update</a> <!-- this displays the updateLink created above -->
						| 
						<a href="${deleteLink}"
								onClick = "if (!(confirm('Are you sure you want to delete this customer? '))) return false ">Delete</a>  <!-- this displays the deleteLink created above -->
					</td>   
				</tr>
				
			</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>