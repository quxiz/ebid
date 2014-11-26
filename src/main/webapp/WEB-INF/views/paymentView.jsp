<%-- 
    Document   : payment
    Created on : Nov 22, 2014, 2:55:11 PM
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
                <h3>Paypal (dummy)</h3>
                <hr>               
                <div>
                <a href ="${pageContext.request.contextPath}/checkOut/checkoutTransaction/${transactionID}" class="btn btn-primary">ตกลง</a>
                <a href ="${pageContext.request.contextPath}/checkOut/error" class="btn btn-primary" >มีเงินในบัญชีไม่พอ</a>
                <a href ="${pageContext.request.contextPath}/checkOut/${transactionID}" class="btn btn-primary">ยกเลิก</a>
                </div>                       
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>

