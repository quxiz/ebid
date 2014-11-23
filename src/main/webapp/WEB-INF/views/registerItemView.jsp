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
                                <label for="input4" class="col-sm-3 control-label">รายละเอียดสินค้า</label>
                                <div class="col-sm-6">
                                    <form:textarea type="text" class="form-control" id="input4" placeholder="รายละเอียดสินค้า" path="detail"/>
                                </div>
                            </div>
                            <div id="specifics-form">
                                <div class="form-group">
                                    <label for="condition" class="col-sm-3 control-label">สภาพสินค้า</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" name="condition" placeholder="สภาพสินค้า"/>
                                    </div>

                                </div>
                                <div class="form-group">
                                    <label for="something" class="col-sm-3 control-label">จุดบกพร่อง</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" name="something" placeholder="จุดบกพร่อง"/>
                                    </div>

                                </div>
                            </div>

                            <form:hidden id="specifics-json" placeholder="itemID" path="specifics" value=""/>



                            <div class="form-group">
                                <label for="category" class="col-sm-3 control-label">ประเภทสินค้า</label>
                                <div class="col-sm-9">
                                    <div class="dropdown">
                                        <button class="btn btn-default dropdown-toggle" type="button" id="dropdownCountry" data-toggle="dropdown">
                                            - เลือก -&nbsp;&nbsp;<span class="caret"></span>
                                        </button>
                                        <ul id="category" class="dropdown-menu scrollable-menu" role="menu" aria-labelledby="dropdownMenu">

                                            <c:forEach items="${categoryList}" var="category" begin="1">
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
                                        <form:input type="text" class="form-control" id="endTime" path="endTime"/>
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
                                <label for="quantity" class="col-sm-3 control-label">จำนวน</label>
                                <div class="input-group col-sm-3">
                                    <form:input type="text" class="form-control" id="quantity" placeholder="ปริมาณ" path="quantity"/>
                                    <span class="input-group-addon">ชิ้น</span>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label">วิธีการจัดส่ง</label>
                                <div class="col-sm-1">
                                    <div class="checkbox" >
                                        <label>
                                            <input id="shippingService1" type="checkbox" value="" onclick="validate()">
                                            ด่วน
                                        </label>
                                    </div>

                                </div>
                                <div class="col-sm-8">
                                    <label for="shippingServicePrice1" class="col-sm-1 control-label">ราคา</label>
                                    <div class="input-group col-sm-3">
                                        <input type="text" class="form-control" id="shippingServicePrice1" onkeyup="savePrice()" disabled="true" value="0"/>
                                        <span class="input-group-addon">บาท</span>
                                    </div>
                                </div>
                                <div class="col-sm-1 col-sm-offset-3">
                                    <div class="checkbox" >
                                        <label>
                                            <input id="shippingService2" type="checkbox" value="" onclick="validate()">
                                            มาตรฐาน
                                        </label>
                                    </div>

                                </div>
                                <div class="col-sm-8">
                                    <label for="shippingServicePrice2" class="col-sm-1 control-label">ราคา</label>
                                    <div class="input-group col-sm-3">
                                        <input type="text" class="form-control" id="shippingServicePrice2" disabled="true" onkeyup="savePrice()" value="0"/>
                                        <span class ="input-group-addon">บาท</span>
                                    </div>
                                </div>
                                <div class="col-sm-1 col-sm-offset-3">
                                    <div class="checkbox" >
                                        <label>
                                            <input id="shippingService3" type="checkbox" value="" onclick="validate()">
                                            ประหยัด
                                        </label>
                                    </div>

                                </div>
                                <div class="col-sm-8">
                                    <label for="shippingServicePrice3" class="col-sm-1 control-label">ราคา</label>
                                    <div class="input-group col-sm-3">
                                        <input type="text" class="form-control" id="shippingServicePrice3" disabled="true" onkeyup="savePrice()" value="0"/>
                                        <span class ="input-group-addon">บาท</span>
                                    </div>
                                </div>
                            </div>
                            <form:hidden id="shippingService" path="shippingService"/>
                            <form:hidden id="shippingCost" path="shippingCost"/>

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
//                $('#endTimeInput').keyup(function () {
//                    try {
//                    var dateFormat = new simpleDateFormat();
//                            dateFormat.applyPattern('dd/MM/yyyy h:mm a');
//                            Date parsedDate = dateFormat.parse($('#endTimeInput').text());
//                            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
//                            $('#endTime').val(timestamp);
//                } catch (e) {//this generic but you csan control another types of exception
//                }
//            });

            });</script>
        <script>
            function validate() {
                var shippingService = "";
                if (document.getElementById('shippingService1').checked) {
                    document.getElementById('shippingServicePrice1').disabled = false;
                    shippingService += "ด่วน ";
                } else if (!document.getElementById('shippingService1').checked) {
                    document.getElementById('shippingServicePrice1').disabled = true;
                    $("#shippingServicePrice1").val("0");
               
                }
                ;
                if (document.getElementById('shippingService2').checked) {
                    document.getElementById('shippingServicePrice2').disabled = false;
                    shippingService += "มาตรฐาน "
                } else if (!document.getElementById('shippingService2').checked) {
                    document.getElementById('shippingServicePrice2').disabled = true;
                    $("#shippingServicePrice2").val("0");
                
                }
                ;
                if (document.getElementById('shippingService3').checked) {
                    document.getElementById('shippingServicePrice3').disabled = false;
                    shippingService += "ประหยัด"
                } else if (!document.getElementById('shippingService3').checked) {
                    document.getElementById('shippingServicePrice3').disabled = true;
                    $("#shippingServicePrice3").val("0");
            
                }
                ;
                $("#shippingService").val(shippingService);
                savePrice();
            }

            function savePrice() {
                var shippingCost = "";
                if (document.getElementById('shippingService1').checked) {

                    shippingCost += $("#shippingServicePrice1").val() + " ";

                }
                ;
                if (document.getElementById('shippingService2').checked) {

                    shippingCost += $("#shippingServicePrice2").val() + " ";

                }
                ;
                if (document.getElementById('shippingService3').checked) {

                    shippingCost += $("#shippingServicePrice3").val().toString();
                }
                ;
                $("#shippingCost").val(shippingCost);
            }
        </script>
        <script>
            $("#specifics-form").keyup(function () {
                $("#specifics-json").val(JSON.stringify($("#specifics-form :input").serializeArray()));
            });
            $("#specifics-add").click(function () {
                var specificName = $("#specifics-name").val();
                $("#specifics-name").val("");
                $("#specifics-form").append('<div class="specifics-' + specificName + '">' + specificName + ' : <input name="' + specificName + '" value=""><span id="specifics-remove" class="glyphicon glyphicon-remove" aria-hidden="true"></span></div>');
            });
            $(document).on("click", "#specifics-remove", function () {
                $(this).parent().remove();
            });</script>

        <script>
            $(document).ready(function () {
                if ($("#specifics-json").val()) {
                    $("#specifics-form").empty();
                    var obj = jQuery.parseJSON($("#specifics-json").val());
                    $.each(obj, function (key, value) {
                        $("#specifics-form").append('<div class="specifics-' + value.name + '">' + value.name + ' : <input name="' + value.name + '" value="' + value.value + '"><span id="specifics-remove" class="glyphicon glyphicon-remove" aria-hidden="true"></span></div>');
                    });
                }
            }
            );
        </script>
    </tiles:putAttribute>
</tiles:insertDefinition>
