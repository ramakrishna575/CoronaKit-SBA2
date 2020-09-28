<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

	<jsp:include page="header.jsp"/>	

 	<h4 class="page-header">Add New Product</h4>
	<section class="container-fluid p-6">
		<div class="col-sm-6">
		<form:form action="${pageContext.request.contextPath }/admin/product-save" method="post" modelAttribute="product">      
			<div class="form-group"> 
	            <form:label path="productName">Product Name</form:label>  
	            <form:input type="text" path="productName" class="form-control"/>  
	            <form:errors path="productName" class="alert alert-warning"/>
	        </div>       
	        <div class="form-group">
	            <form:label path="cost">Product Cost</form:label>  
	            <form:input type="number" path="cost" class="form-control" />  
	            <form:errors path="cost" class="alert alert-warning"/>
	        </div>         
	        <div class="form-group">  
	            <form:label path="productDescription">Product Description</form:label>  
	            <form:input type="text" path="productDescription" class="form-control"/>  
	            <form:errors path="productDescription" class="alert alert-warning"/>
	        </div>    
   			<div class="form-group">                          
       			<button class="btn pull-right btn-primary">SAVE</button>
  			</div>                   
   		</form:form> 
   		</div> 
	</section>

	<jsp:include page="footer.jsp"/>	    	
