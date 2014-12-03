<%-- 
    Document   : footer
    Created on : Nov 17, 2014, 2:21:02 PM
    Author     : mtmmoei
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<hr>
<ul class="list-inline text-center">
    
    <sec:authorize access="isAuthenticated()">
        <li><a href="${pageContext.request.contextPath}/complaint">ร้องเรียน</a></li>
    </sec:authorize>
    <sec:authorize access="isAnonymous()">
        <li data-toggle="modal" data-target="#pleaseLogInModal"><a href="#">ร้องเรียน</a></li>
    </sec:authorize>

</ul>
<div class="text-center">Copyright © 2014 eBid Inc. All Rights Reserved.</div>

