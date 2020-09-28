<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
	<jsp:include page="header.jsp"/>	
	<h4 class="page-header">Corona Kit Details</h4>
	
	<c:choose>  
        <c:when test="${cartaddedproduct == null || cartaddedproduct.isEmpty() }">  
            <p>No Products has been added to Cart</p>  
        </c:when>  
        <c:otherwise>  
        <p>Corona Kit details are as below, Check out to enter Address </p> 
        <br/>
            <table class="table table-striped table-hover"> 
                <tr>  
                    <th>Product id#</th>  
                    <th>Product Name</th>  
                    <th>Product Cost</th>  
                    <th>Product Description</th>         
                    <th>Product Quantity</th>     
                    <th>Action</th>                             
                </tr>  
                <c:forEach items="${cartaddedproduct}" var="product">  
                    <tr>  
                        <td>${product.id }</td>  
                        <td>${product.productName }</td>  
                        <td>${product.cost }</td>  
                        <td>${product.productDescription}</td>               
                        <td>${Qtymap.get(product.id)}</td>    
                        <td> <a class="btn btn-sm btn-danger" href="${pageContext.request.contextPath }/user/delete/${product.id}">Delete Product</a> </td> 
                    </tr>  
                </c:forEach>  
            </table>  
            
            <nav>
		   		<br/>          
		        <a class="btn btn-sm btn-primary"href="${pageContext.request.contextPath }/user/checkout">Checkout</a>               
   			 </nav>   
        </c:otherwise>  
    </c:choose> 
	
	<jsp:include page="footer.jsp"/>