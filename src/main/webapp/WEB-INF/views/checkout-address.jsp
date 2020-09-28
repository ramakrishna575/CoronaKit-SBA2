	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
	<jsp:include page="header.jsp"/>	
	
	<h4 class="page-header">CheckOut Address Page</h4>
		<form:form action="${pageContext.request.contextPath }/user/finalize" method="post">
		<p>Enter Delivery Address including pin code</p> 
		<div class="form-group">  
            <textarea name="Address1" class="md-textarea form-control" rows="3" cols="1"></textarea>                     
        </div>  
        
        <div class="form-group"> 
        <button class="btn pull-right btn-primary">Place Order</button>
        </div>
	</form:form>
	
	<jsp:include page="footer.jsp"/>