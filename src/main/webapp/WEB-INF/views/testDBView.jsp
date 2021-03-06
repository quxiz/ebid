<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page session="false" %>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <div><a href="${pageContext.request.contextPath}/testDB/member">${pageContext.request.contextPath}/testDB/member</a></div>
        <div><a href="${pageContext.request.contextPath}/testDB/message">${pageContext.request.contextPath}/testDB/message</a></div>
        <div><a href="${pageContext.request.contextPath}/testDB/item">${pageContext.request.contextPath}/testDB/item</a></div>
        <div><a href="${pageContext.request.contextPath}/testDB/photo">${pageContext.request.contextPath}/testDB/photo</a></div>
        <div><a href="${pageContext.request.contextPath}/testDB/complaint">${pageContext.request.contextPath}/testDB/complaint</a></div>
        <div><a href="${pageContext.request.contextPath}/testDB/blacklist">${pageContext.request.contextPath}/testDB/blacklist</a></div>
        <div><a href="${pageContext.request.contextPath}/testDB/comment">${pageContext.request.contextPath}/testDB/comment</a></div>
        <div><a href="${pageContext.request.contextPath}/testDB/feedback">${pageContext.request.contextPath}/testDB/feedback</a></div>
        <div><a href="${pageContext.request.contextPath}/testDB/transaction">${pageContext.request.contextPath}/testDB/transaction</a></div>
        <div><a href="${pageContext.request.contextPath}/testDB/autoBid">${pageContext.request.contextPath}/testDB/autoBid</a></div>
        
    </tiles:putAttribute>
</tiles:insertDefinition>