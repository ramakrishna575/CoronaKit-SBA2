<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="spring-form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>    
	<jsp:include page="header.jsp"/>

	 <core:if test="${param.error != null}">   		
		<div class="alert alert-warning"> 
        	<i>Invalid Credentials!!!</i>
        </div>        
	 </core:if> 
	 <core:if test="${param.logout != null}">		
		<div class="alert alert-info"> 
        	<i>You have been logged out successfully!!!</i>
        </div>         
	</core:if>

	<spring-form:form action="${pageContext.request.contextPath}/validate" method="POST">
		<br/>
			<label>Enter user name</label>
				<input type="text" name="username" />
		<br/>
		<br/>
			<label>Enter password</label>
				<input type="password" name="password" />
		<br/>
		<br/>
			<input type="submit" value="Login" />
	</spring-form:form>

<jsp:include page="footer.jsp"/>	  