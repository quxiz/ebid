<%-- 
    Document   : blacklistMemberView
    Created on : Nov 22, 2014, 7:46:07 PM
    Author     : Kawin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <div class="container">
            <div class="col-sm-8 col-md-6" style="float:none; margin-left:auto; margin-right:auto">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">แก้ไขบัญชีดำ</h3>
                    </div>
                    <div class="panel-body">
                        <c:url var="addAction" value="/blacklist/onSubmit" ></c:url>
                        <form:form class="form-horizontal" role="form" action="${addAction}" modelAttribute="blacklistForm" method="POST" name="blacklistForm">
                            <form:hidden path="userID"/>
                            <div class="form-group">
                                <label for="blacklistStatus" class="col-sm-3 control-label">สถานะ</label>
                                <div class="col-sm-9">
                                    <div class="dropdown">
                                        <button class="btn btn-default dropdown-toggle" type="button" id="input7" data-toggle="dropdown">
                                            - เลือก -&nbsp;&nbsp;<span class="caret"></span>
                                        </button>
                                        <ul id="blacklistType" class="dropdown-menu scrollable-menu" role="menu" aria-labelledby="dropdownMenu">
                                            <li onclick="$('#blacklistStatus').val('NORMAL')" role="presentation">
                                                <a role="menuitem" tabindex="-1">ปกติ</a>
                                            </li>
                                            <li onclick="$('#blacklistStatus').val('BLACKLIST')" role="presentation">
                                                <a role="menuitem" tabindex="-1">ติดบัญชีดำ</a>
                                            </li>
                                            <form:hidden id="blacklistStatus" path="blacklistStatus" />
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="detail" class="col-sm-3 control-label">รายละเอียด</label>
                                <div class="col-sm-9">
                                    <form:textarea type="text" class="form-control" id="detail" placeholder="รายละเอียด" path="detail"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-9">
                                    <input type="submit" id="submit" class="btn btn-primary" value="ตกลง">
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
