<%-- 
    Document   : buyItemView
    Created on : Nov 20, 2014, 10:24:29 AM
    Author     : Kawin
--%>

<%-- 
    Document   : viewItemView
    Created on : Nov 19, 2014, 9:15:51 PM
    Author     : Kawin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>



<style type="text/css">
    .scrollable-menu {
        height: auto;
        max-height: 200px;
        overflow-x: hidden;
    }

    .thumbnail a img {
        height:250px;
        /*        max-height: 250px;*/
        max-width: 250px
    }

</style>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <div class="container">
            <div class="col-sm-8" style="float:none; margin-left:auto; margin-right:auto">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">ยืนยันการซื้อสินค้า</h3>
                    </div>
                    <div class="panel-body">
                       
                        <dl class="dl-horizontal">
                            <dt>ชื่อสินค้า</dt>
                            <dd>${item.detail}</dd>
                        </dl>
                        <dl class="dl-horizontal">
                            <dt>ประเภทสินค้า</dt>
                            <dd>${item.category.name}</dd>
                        </dl>


                        <dl class="dl-horizontal">
                            <dt>จำนวนชิ้นที่ซื้อ</dt>
                            <dd>${invoice.quantity}</dd>
                        </dl>
                        <dl class="dl-horizontal">
                            <dt>ราคาต่อชิ้น</dt>
                            <dd>${item.price}</dd>
                        </dl>  
                        <dl class="dl-horizontal">
                            <dt>รวมเป็นเงิน</dt>
                            <dd>${invoice.total} บาท (ยังไม่รวมค่าจัดส่ง)</dd>
                        </dl>
                        <br>
                        <div class = "form-group text-center">

                            <c:url var="addAction" value="/buyItem/${item.itemID}/confirmBuy" ></c:url>
                            <form:form action="${addAction}" modelAttribute="buyForm" method="POST" name="buyForm">
                                <form:hidden path="quantity" />
                                <input type="submit" class="btn btn-primary" value="ยืนยันการสั่งซื้อ"/>
                                <a type="button" class="btn btn-default" href="${pageContext.request.contextPath}/viewItem/${item.itemID}">ยกเลิก</a>
                            </form:form>


                        </div>
                    </div>


                </div>


            </div>



        </div>

        <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>

    </tiles:putAttribute>
</tiles:insertDefinition>

