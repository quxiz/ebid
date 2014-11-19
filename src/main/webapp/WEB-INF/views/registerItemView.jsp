<%-- 
    Document   : registerItem
    Created on : Nov 19, 2014, 1:01:35 PM
    Author     : Kawin
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page session="false" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>



<style type="text/css">
    .scrollable-menu {
        height: auto;
        max-height: 200px;
        overflow-x: hidden;
    }

    .centercontents {
    text-align: center !important;
}
    
</style>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">

        <div class="container">
            <div class="col-sm-8" style="float:none; margin-left:auto; margin-right:auto">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">ลงทะเบียนขายสินค้า</h3>
                    </div>


                    <div class="panel-body">

                           
                        <c:url var="addAction" value="/registerItem/sentForm" ></c:url>
                            <form:form action="${addAction}" modelAttribute="form" method="POST" name="form">
                                <div class="form-group centercontents">
                                    <div><form:input placeholder="Name" path="title" /></div>
                                    <div><form:input placeholder="condition" path="condition" /></div>
                                    <div><form:input placeholder="specific" path="specific"/></div>
                                    <div><form:input placeholder="detail" path="detail" /></div>
                                    <div><form:input placeholder="category" path="category" /></div>
                                    <div><form:input placeholder="price" path="price" /></div>
                                    <div><form:input placeholder="quantity" path="quantity" /></div>
                                    <div><form:input placeholder="shippingCost" path="shippingCost" /></div>
                                </div>
                                <input type="submit" class="btn btn-default centercontents" value="ประกาศขาย"/>
                            </form:form>
                            
                            
                            
                    </div>
                </div>
            </div>
        </div>
     

    </tiles:putAttribute>
</tiles:insertDefinition>
