<%-- 
    Document   : showView
    Created on : Nov 23, 2014, 2:44:51 PM
    Author     : Kawin
--%>

<%@page import="com.se.ebid.controller.CountryList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<style type="text/css">
    #carousel{
        max-height: 500px;
        max-width: 1140px
    }
    .carousel-img {
        max-height: 500px;
        margin: 0 auto
    }
    .thumbnail a img {
        max-height: 250px;
    }
    .center {
        float: none;
        margin-left: auto;
        margin-right: auto;
    }
    .centercontents {
        text-align: center !important;
    }
</style>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <div class="container">
            <c:if test ="${isSuccess}">
                <h3>สำเร็จ!</h3>
            </c:if>
            <c:if test ="${!isSuccess}">
                <h3>ไม่สำเร็จ!</h3>
            </c:if>
                <hr>               
                <div>
<<<<<<< HEAD
                <h4>${text}</h4>
                <c:if test ="${link != null}"><a href ="${pageContext.request.contextPath}/${link}" type = "button" class="btn btn-primary">btnText</a></c:if>
=======
                ${text}
                
                </div>
                <br>
                <br>
>>>>>>> 6f7d1d9d9c7dd6662ae6740312b96bb6750fad43
                <a href ="${pageContext.request.contextPath}" type = "button" class="btn btn-primary">กลับหน้าหลัก</a>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>


