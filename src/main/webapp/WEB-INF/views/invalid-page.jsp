<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  
pageEncoding="ISO-8859-1"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>  

	<jsp:include page="header.jsp"/>
     <div class="alert alert-danger"> 
    <h3><strong>Error:</strong> ${errMsg }</h3>  
    </div>
    <p>  
        Sorry for the inconvenience!  
        Contact Technology team if problem persists!  
    </p>  
 <jsp:include page="footer.jsp"/>