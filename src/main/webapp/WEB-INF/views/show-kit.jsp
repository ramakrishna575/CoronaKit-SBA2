<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
	<jsp:include page="header.jsp"/>	
	<h4 class="page-header">Your Corona Kit Details</h4>
	
	<div class="alert alert-info"><b>Product Summary</b>
	</div>
            <table class="table table-striped table-hover">  
                <tr>  
                    <th>Corona Kit ID#</th>  
                    <th>Product ID</th>  
                    <th>Product Name</th>  
                    <th>Quantity</th>  
                    <th>Cost</th>       
                </tr>  
                <c:forEach items="${kitdetails}" var="kitdetail">  
                    <tr>  
                        <td>${kitdetail.coronaKitId }</td>  
                        <td>${kitdetail.productId}</td>  
                        <td>${kitdetail.productFullName}</td>  
                        <td>${kitdetail.quantity }</td>  
                        <td>${kitdetail.amount}</td>   
                    </tr>  
                </c:forEach>  
            </table> 
	
	<jsp:include page="footer.jsp"/>