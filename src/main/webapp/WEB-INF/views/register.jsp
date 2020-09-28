<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>

	<jsp:include page="header.jsp"/>	
	
	<h4 class="page-header">New User Registration</h4>
	<hr/>
	
	<spring:form action="${pageContext.request.contextPath}/register-save" method="POST" modelAttribute="user">
		<spring:label path="username">Enter user name</spring:label>
		<spring:input path="username"/>
		<br/>
		<spring:label path="password">Enter password</spring:label>
		<spring:password  path="password"/>
		<br/>
		<spring:label path="email">Enter email</spring:label>
		<spring:input path="email"/>
		<br/>
		<spring:label path="contact">Enter Contact number</spring:label>
		<spring:input path="contact"/>
		<br/>
		<spring:label path="role">Select Role</spring:label>
		<ul>
			<li> <spring:checkbox path="role" value="ADMIN" label="ADMIN"/></li>
			<li> <spring:checkbox path="role" value="USER" label="USER"/></li>
		</ul>
		<br/>
		<br/>
		<input type="submit" value="Register">

	</spring:form>
	
	<jsp:include page="footer.jsp"/>	