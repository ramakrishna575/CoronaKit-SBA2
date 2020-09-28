<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
	<jsp:include page="header.jsp"/>	
	
	<h4 class="page-header">Products List</h4>
	<c:choose>  
        <c:when test="${productlist == null || productlist.isEmpty() }">  
            <p>No Products Available to display</p>  
        </c:when>  
        <c:otherwise>  
        	<p>Add Products from below listed table & click on 'Show Added Products in kit' button</p>  
			<br/> 
			<strong>Click on 'Add Product to cart' button again to increase the Quantity.</strong>
			<br/> 
			<br/>
            <table class="table table-striped table-hover">  
                <tr>  
                    <th>Product id#</th>  
                    <th>Product Name</th>  
                    <th>Product Cost</th>  
                    <th>Product Description</th>   
                    
                    <th>Actions</th>                    
                </tr>  
                <c:forEach items="${productlist}" var="product">  
                    <tr>  
                        <td>${product.id }</td>  
                        <td>${product.productName }</td>  
                        <td>${product.cost }</td>  
                        <td>${product.productDescription}</td>                       
                       
                      <td> <a class="btn btn-sm btn-primary" href="${pageContext.request.contextPath }/user/add-to-cart/${product.id}">Add Product to cart</a> </td>
                    </tr>  
                </c:forEach>  
            </table>  
        </c:otherwise>  
    </c:choose> 
    
   <nav>   
   		<br/>       
        <a class="btn btn-sm btn-primary" href="${pageContext.request.contextPath }/user/show-cart">Show Added Products in kit</a>               
    </nav>
	
	
	
	<jsp:include page="footer.jsp"/>	  