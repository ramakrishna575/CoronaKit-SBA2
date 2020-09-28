<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
	<jsp:include page="header.jsp"/>	
	<h4 class="page-header">Products List</h4>
	<c:if test="${deleteconfirmationmsg!=null}">
		<p class="well"> ${deleteconfirmationmsg} </p>
		<br/>
	</c:if>
	 <c:choose>  
        <c:when test="${productlist == null || productlist.isEmpty() }">  
            <p class="well">No Products Available to display</p>  
        </c:when>  
        <c:otherwise>  
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
                        <td>
                        <a class="btn btn-sm btn-danger" href="${pageContext.request.contextPath }/admin/product-delete/${product.id}">DELETE</a>                       
                        </td>
                    </tr>  
                </c:forEach>  
            </table>  
        </c:otherwise>  
    </c:choose>
	
	<jsp:include page="footer.jsp"/>	