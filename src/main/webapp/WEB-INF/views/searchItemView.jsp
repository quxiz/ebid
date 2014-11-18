<%-- 
    Document   : searchItemView
    Created on : Nov 17, 2014, 5:35:41 PM
    Author     : Kawin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/indexResponsive.css">
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        
        <form action="${pageContext.request.contextPath}/meoi" method="POST">
            
        </form>
        
    </tiles:putAttribute>
</tiles:insertDefinition>