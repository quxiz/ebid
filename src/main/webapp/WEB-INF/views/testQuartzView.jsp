<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
 
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
 
 
 
        <a class="btn btn-default" href="${pageContext.request.contextPath}/testQuartz/add">add</a>
 
 
 
    </tiles:putAttribute>
</tiles:insertDefinition>