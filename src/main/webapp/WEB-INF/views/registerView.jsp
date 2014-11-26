<%-- 
    Document   : registerView
    Created on : Nov 18, 2014, 6:20:32 PM
    Author     : mtmmoei
--%>

<%@page import="com.se.ebid.controller.CountryList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">



<style>
    .error {
        color: #ff0000;
    }

    .errorblock {
        color: #000;
        background-color: #ffEEEE;
        border: 3px solid #ff0000;
        padding: 8px;
        margin: 16px;
    }
</style>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">

        <div class="container">
            <div class="col-sm-8" style="float:none; margin-left:auto; margin-right:auto">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">สมัครสมาชิก</h3>
                    </div>
                    <div class="panel-body">
                        <c:url var="addAction" value="/register/submit" ></c:url>
                        <form:form action="${addAction}" class="form-horizontal" role="form" modelAttribute="registrationForm" method="POST" name="registrationForm">
                            <div class="form-group">
                                <label for="inputName" class="col-sm-3 control-label">ชื่อจริง</label>
                                <div class="col-sm-4">
                                    <form:input type="text" class="form-control" id="inputName" placeholder="ชื่อ" path="firstName" required="true"/>
                                    <form:errors path="firstName" cssClass="error" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputSurname" class="col-sm-3 control-label">นามสกุล</label>
                                <div class="col-sm-4">
                                    <form:input type="text" class="form-control" id="inputSurname" placeholder="นามสกุล" path="lastName" required="true"/>
                                    <form:errors path="lastName" cssClass="error" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputAddress" class="col-sm-3 control-label">ที่อยู่</label>
                                <div class="col-sm-8">
                                    <form:textarea class="form-control" id="inputAddress" placeholder="ที่อยู่" path="address"></form:textarea>
                                    <form:errors path="address" cssClass="error" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputCountry" class="col-sm-3 control-label">ประเทศ</label>
                                <div class="col-sm-9">
                                    <div class="dropdown">
                                        <button class="btn btn-default dropdown-toggle" type="button" id="dropdownCountry" data-toggle="dropdown">
                                            - เลือก -&nbsp;&nbsp;<span class="caret"></span>
                                        </button>
                                        <ul id="divNewNotifications" class="dropdown-menu scrollable-menu" role="menu" aria-labelledby="dropdownMenu">

                                            <c:forEach items="${countryList.countries}" var="country">
                                                <li onclick="$('#country-hidden').val('${country}')" role="presentation"><a role="menuitem" tabindex="-1" class="test">${country}</a>
                                                </li>
                                            </c:forEach>
                                            <form:hidden id="country-hidden" path="country" />
                                        </ul>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="inputEmail" class="col-sm-3 control-label">อีเมล</label>
                                <div class="col-sm-4">
                                    <form:input type="email" class="form-control" id="inputEmail" placeholder="อีเมล" path="email" required="true"/>
                                </div>

                            </div>
                            <div class="form-group">
                                <label for="inputTel" class="col-sm-3 control-label">หมายเลขโทรศัพท์</label>
                                <div class="col-sm-4">
                                    <form:input type="text" class="form-control" id="inputTel" placeholder="หมายเลขโทรศัพท์" path="phoneNo" required="true"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputUserID" class="col-sm-3 control-label">ชื่อผู้ใช้</label>
                                <div class="col-sm-4">
                                    <form:input type="text" class="form-control" id="inputUserID" placeholder="ชื่อผู้ใช้" path="userID" required="true"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="inputPassword" class="col-sm-3 control-label">รหัสผ่าน</label>
                                <div class="col-sm-4">
                                    <form:input type="password" class="form-control" id="inputPassword" placeholder="รหัสผ่าน" path="password" required="true"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputPassword2" class="col-sm-3 control-label">ยืนยันรหัสผ่าน</label>
                                <div class="col-sm-4">
                                    <form:input type="password" class="form-control" id="inputPassword2" placeholder="ยืนยันรหัสผ่าน" path="confirmPassword" required="true"/>
                                    <br>
                                </div>
                            </div>

                            <form:errors path="*" cssClass="errorblock" element="div" />

                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-9">
                                    <input type="submit" class="btn btn-primary" value="สมัครสมาชิก"/>
                                </div>
                            </div>
                        </form:form>

                    </div>
                </div>
            </div>
        </div>
        <script>
//            $(function () {
//
//                $(".dropdown-menu li a").click(function () {
//                    var selText = $(this).text();
//                    $(this).parents('.dropdown').find('.dropdown-toggle').html(selText + "&nbsp;&nbsp;" + '<span class="caret"></span>');
//
//  
//                });
//
//            });
        </script>
    </tiles:putAttribute>
</tiles:insertDefinition>
