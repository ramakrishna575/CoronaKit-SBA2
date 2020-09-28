<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Corona Kit Portal</title>
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
		integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
		crossorigin="anonymous">
	
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
</head>
<body>
	<div class="page-header text-center">
	  	<h1>Corona Kit Portal</h1>
	</div>
<nav class="navbar navbar-expand-md bg-dark navbar-dark">
  <!-- Brand -->

  <!-- Navbar links -->
  <div  id="collapsibleNavbar">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath }/home">Home</a>
      </li>
      
      <c:choose>
      	<c:when test="${unm!=null && role=='ROLE_ADMIN' }">
	      <li class="nav-item">
	        <a class="nav-link" href="${pageContext.request.contextPath }/admin/product-entry">Add Product</a>
	      </li>	
	       <li class="nav-item">
	        <a class="nav-link" href="${pageContext.request.contextPath }/admin/product-list">Show Product(s)</a>
	      </li>	
	      <li class="nav-item">
	      	  <a class="nav-link" href="${pageContext.request.contextPath }/logout">Sign Out</a>
	      </li>
      	</c:when>
      	<c:when test="${unm!=null && role=='ROLE_USER' }">
	      	<li class="nav-item">
		       <a class="nav-link" href="${pageContext.request.contextPath }/user/show-list">Create Kit</a>
		    </li>
	      	<li class="nav-item">
		       <a class="nav-link" href="${pageContext.request.contextPath }/user/show-kit">Show Kit</a>
		    </li>
		    <li class="nav-item">
	      	  <a class="nav-link" href="${pageContext.request.contextPath }/logout">Sign Out</a>
	      	</li>
      	</c:when>
      	<c:otherwise>
      		<li class="nav-item">
	      	  <a class="nav-link" href="${pageContext.request.contextPath }/custom-login">Sign In</a>
	      	</li>
	      	<li class="nav-item">
	      	  <a class="nav-link" href="${pageContext.request.contextPath }/register">Sign Up</a>
	      	</li>
      	</c:otherwise>
      </c:choose>
    </ul>
  </div>
</nav>