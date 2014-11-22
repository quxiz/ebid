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

<script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.1.min.js"></script>
        

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
            <div class="col-sm-12" style="float:none; margin-left:auto; margin-right:auto">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">ลงทะเบียนขายสินค้า</h3>
                    </div>
                    <div class="panel-body">
                        <c:url var="addAction" value="/registerItem/sentForm" ></c:url>
                        <form:form action="${addAction}" class="form-horizontal" role="form" modelAttribute="form" method="POST" name="form">
                            <div class="form-group">
                                <label for="input1" class="col-sm-3 control-label" id="test">ชื่อสินค้า</label>
                                <div class="col-sm-6">
                                    <form:input type="text" class="form-control" id="input1" placeholder="ชื่อสินค้า" path="title"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="input2" class="col-sm-3 control-label">สภาพสินค้า</label>
                                <div class="col-sm-6">
                                    <form:input type="text" class="form-control" id="input2" placeholder="สภาพสินค้า" path="condition"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="input3" class="col-sm-3 control-label">จุดบกพร่อง</label>
                                <div class="col-sm-6">
                                    <form:input type="text" class="form-control" id="input3" placeholder="จุดบกพร่อง" path="specific"/>
                                </div>

                            </div>
                            <div class="form-group">
                                <label for="input4" class="col-sm-3 control-label">รายละเอียดสินค้า</label>
                                <div class="col-sm-6">
                                    <form:textarea type="text" class="form-control" id="input4" placeholder="รายละเอียดสินค้า" path="detail"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="category" class="col-sm-3 control-label">ประเภทสินค้า</label>
                                <div class="col-sm-9">
                                    <div class="dropdown">
                                        <button class="btn btn-default dropdown-toggle" type="button" id="dropdownCountry" data-toggle="dropdown">
                                            - เลือก -&nbsp;&nbsp;<span class="caret"></span>
                                        </button>
                                        <ul id="category" class="dropdown-menu scrollable-menu" role="menu" aria-labelledby="dropdownMenu">

                                            <c:forEach items="${categoryList}" var="category">
                                                <li role="presentation"><a role="menuitem" tabindex="-1">${category.name}</a>
                                                </li>
                                            </c:forEach>

                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">

                                <label for="photos" class="col-sm-3 control-label">รูปภาพสินค้า</label>

                                <div class="col-sm-6">
                                    <form:input type="file" id="photos" multiple="multiple" path="photos"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="sellingType" class="col-sm-3 control-label">วิธีการขาย</label>
                                <div class="col-sm-9">
                                    <div class="dropdown">
                                        <button class="btn btn-default dropdown-toggle" type="button" id="input7" data-toggle="dropdown">
                                            - เลือก -&nbsp;&nbsp;<span class="caret"></span>
                                        </button>
                                        <ul id="sellingType" class="dropdown-menu scrollable-menu" role="menu" aria-labelledby="dropdownMenu">

                                            <c:forEach items="${sellingType}" var="selling">
                                                <li role="presentation"><a role="menuitem" tabindex="-1">${selling.name}</a>
                                                </li>
                                            </c:forEach>

                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group" id="auction" hidden="true">
                                <label for="datetimepicker1" class="col-sm-3 control-label">เวลาสิ้นสุดการประมูล</label>

                                <div class="col-sm-3">
                                    <div class='input-group date' id='datetimepicker1'>
                                        <input type="text" class="form-control" id="endTime"/>
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="price" class="col-sm-3 control-label" id="priceLabel">ราคา</label>
                                <div class="input-group col-sm-3">
                                    <form:input type="text" class="form-control" id="price" placeholder="ราคา" path="price"/>
                                    <span class="input-group-addon">บาท</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="quantity" class="col-sm-3 control-label">ปริมาณ</label>
                                <div class="input-group col-sm-3">
                                    <form:input type="text" class="form-control" id="quantity" placeholder="ปริมาณ" path="quantity"/>
                                    <span class="input-group-addon">ชิ้น</span>
                                </div>
                            </div>


                            <div class="form-group">
                                <label for="shippingService" class="col-sm-3 control-label">วิธีการจัดส่ง</label>
                                <div class="col-sm-6">
                                    <form:input type="text" class="form-control" id="shippingService" placeholder="วิธีการจัดส่ง" path="shippingService"/>

                                </div>
                            </div>
                            <div class="form-group">
                                <label for="shippingCost" class="col-sm-3 control-label">ค่าส่ง</label>
                                <div class="input-group col-sm-3">
                                    <form:input type="text" class="form-control" id="shippingCost" placeholder="ค่าส่ง" path="shippingCost"/>
                                    <span class="input-group-addon">บาท</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="packageDetail" class="col-sm-3 control-label">วิธีการจัดส่งหีบห่อ</label>
                                <div class="col-sm-6">
                                    <form:input type="text" class="form-control" id="packageDetail" placeholder="วิธีการจัดส่งหีบห่อ" path="packageDetail"/>

                                </div>
                            </div>
                            <div class="form-group">
                                <label for="returnPolicy" class="col-sm-3 control-label">นโยบายการรับสินค้าคืน</label>
                                <div class="col-sm-6">
                                    <form:input type="text" class="form-control" id="returnPolicy" placeholder="นโยบายการรับสินค้าคืน" path="returnPolicy"/>

                                </div>
                            </div>
                            
                            <br>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-9">
                                    <input type="submit" class="btn btn-primary" value="ประกาศขาย"/>
                                    <a type="button" class="btn btn-default" href="${pageContext.request.contextPath}">ยกเลิก</a>

                                </div>
                                <div class="col-sm-offset-3 col-sm-9">


                                </div>

                            </div>

                        </form:form>

                    </div>
                </div>
            </div>
        </div>


        <script>
            $(function () {

                $("#category li a").click(function () {
                    var selText = $(this).text();
                    $(this).parents('.dropdown').find('.dropdown-toggle').html(selText + "&nbsp;&nbsp;" + '<span class="caret"></span>');

                    //                    var registerAttributes = model.get("RegistrationForm");
                    //                    registerAttributes.country = selText;
                    //                    model.set("RegistrationForm", registerAttributes);
                });
                $("#sellingType li a").click(function () {
                    var selText = $(this).text();
                    $(this).parents('.dropdown').find('.dropdown-toggle').html(selText + "&nbsp;&nbsp;" + '<span class="caret"></span>');

                    //                    var registerAttributes = model.get("RegistrationForm");
                    //                    registerAttributes.country = selText;
                    //                    model.set("RegistrationForm", registerAttributes);
                    if (selText == "ประมูล") {
                        $("#auction").show();
//                        $("#priceLabel").text = "ราคาเริ่มต้น";
                        document.getElementById('priceLabel').innerHTML = 'ราคาเริ่มต้น';
                    } else {
                        $("#auction").hide();
                        document.getElementById('priceLabel').innerHTML = 'ราคา';
                    }
                });
                $('#datetimepicker1').datetimepicker();
//                ($('#datetimepicker1').data("DateTimePicker").getDate()
//              

            });
        </script>
    </tiles:putAttribute>
</tiles:insertDefinition>
