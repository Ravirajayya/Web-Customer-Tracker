<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.luv2code.springdemo.util.SortUtils" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix= "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>

<head>
<title>List customers</title>

<!-- Reference our style sheet --> 
	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css" />
 
</head>

<body>

	<div id="wrappper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>
	
	<div id="Container">
		<div id="content">
		
			<!-- put new button Add customer -->
			<input type="button" value = "Add Customer"
			       onClick="window.location.href='showFormForAdd'; return false;"
			       class="add-button"/>

			<!--  add a search box -->
            <form:form action="search" method="GET">
                Search customer: <input type="text" name="theSearchName" />
                <input type="submit" value="Search" class="add-button" />
            </form:form>
            
            <!-- Construct a sort link for first name -->
            <c:url var="sortLinkFirstName" value="/customer/list">
            	<c:param name="sort" value="<%= Integer.toString(SortUtils.FIRST_NAME) %>"/>
            </c:url>
            
            <!-- Construct a sort link for last name -->
            <c:url var="sortLinkLastName" value="/customer/list">
            	<c:param name="sort" value="<%= Integer.toString(SortUtils.LAST_NAME) %>"/>
            </c:url>
            
            <!-- Construct a sort link for email -->
            <c:url var="sortLinkEmail" value="/customer/list">
            	<c:param name="sort" value="<%= Integer.toString(SortUtils.EMAIL) %>"/>
            </c:url>
            
			<!--  add html table here -->
			<table>
				<tr>
					<th><a href="${sortLinkFirstName}">First Name</a></th>
					<th><a href="${sortLinkLastName}">Last Name</a></th>
					<th><a href="${sortLinkEmail}">Email Id</a></th>
					<th>Action</th>
					<th>Delete</th>
				</tr>
				
				<!-- Loop over an print our customer -->
				<c:forEach var="tempCustomer" items="${customer}">
				
					<!--  Construct an "update" link with customer id -->
					<c:url var="updatelink" value="/customer/showFromForupdate">
						<c:param name="customerId" value="${tempCustomer.id}"/>
					</c:url>
					
					<!--  Construct an "delete" link with customer id -->
					<c:url var="deletelink" value="/customer/delete">
						<c:param name="customerId" value="${tempCustomer.id}"/>
					</c:url>
					
					<tr>
						<td>${tempCustomer.firstName}</td>
						<td>${tempCustomer.lastName}</td>
						<td>${tempCustomer.email}</td>
						
						<td>
							<!-- Display the update link -->
							<a href="${updatelink}">Update</a>
						</td>
						
						<td>
							<!-- Display the delete link -->
							<a href="${deletelink}" 
							   onclick="if(!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>

</html>