<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>   
	<jsp:include page="header.jsp"/>	
		<h4 class="page-header">Admin Dashboard</h4>	    	
 <core:if test="${msg != null }">  
 	<div class="container-fluid">			
		<div class="alert alert-success"> 
        <Strong>${msg }</Strong> 
        </div>        
     </div>   	
        <br/> 
 </core:if> 
 	<jsp:include page="footer.jsp"/>