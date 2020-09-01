<!-- this line is used to use functionality like for each loop -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
 <!-- this line is used to use functionality like form  -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 
<!DOCTYPE HTML>
<HTML>
<head>
<TITLE>
LIST CUSTOMERS
 
</TITLE>
<link type="text/css"
      rel = "stylesheet"
      href= "${pageContext.request.contextPath}/resources/css/style.css"/>
</head>
<body>
<div id ="wrapper">
      <div id ="header">
           <h2>CRM - Customer Relationship Management </h2>
      </div>

</div>
<div id= "container">
<div id ="content">
<!-- add html table -->
<input type="button" value="add Customer" onClick="window.location.href='showFormForAdd';return false;" class="add-customer-style.css"/>
<!-- add a search box -->
<form:form action= "search" method="GET">
Search Customer :<input type="text" name="theSearchName">
<input type="submit" value="Search" class="add-button">
</form:form>


<table>
<tr>
<th>FirstName</th>
<th>LastName</th>
<th>email</th>
<th>Action</th>
</tr>
<!-- loop over and print all customers -->
<c:forEach var="tempCustomer" items="${customers}">
<c:url var="updateLink" value="/customers/showFormForUpdate">
<c:param name="customerId" value="${tempCustomer.id}"/>
</c:url>

<c:url var="deleteLink" value="/customers/delete">
<c:param name="customerId" value="${tempCustomer.id}" ></c:param>
</c:url>
<tr>

   <td>${tempCustomer.firstName} </td>
   <td>${tempCustomer.lastName} </td>
   <td>${tempCustomer.email} </td>
   <td><a href="${updateLink}">Update</a>
       |
       <a href="${deleteLink}" onclick="if(!(confirm('Are you sure you want to delete the customer?')))return false">Delete</a>
   </td>
</tr>
</c:forEach>

</table>


</div> 
</div>

</body>
</HTML> 