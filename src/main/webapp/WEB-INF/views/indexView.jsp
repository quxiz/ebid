<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/indexResponsive.css">

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        
        ${message}
        <p>Hello! This is the default welcome page for a Spring Web MVC project.</p>
        <p><i>To display a different welcome page for this project, modify</i>
            <tt>index.jsp</tt> <i>, or create your own welcome page then change
                the redirection in</i> <tt>redirect.jsp</tt> <i>to point to the new
                welcome page and also update the welcome-file setting in</i>
            <tt>web.xml</tt>.</p>
        
        <img src="${pageContext.request.contextPath}/resources/img/ebay1.png">
        <img src="${pageContext.request.contextPath}/resources/img/ebay2.gif">
    
    </tiles:putAttribute>
</tiles:insertDefinition>