<%-- 
    Document   : blacklistView
    Created on : Nov 20, 2014, 6:49:55 PM
    Author     : YumeYami
--%>

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
                        <h3 class="panel-title">แก้ไขบัญชีดำ</h3>
                    </div>
                    <div class="panel-body">
                        <c:url var="addAction" value="/blacklist/selectMember" ></c:url>
                        <form:form action="${addAction}" class="form-horizontal" role="form" modelAttribute="blacklistForm" method="POST" name="blacklistForm">
                            <div class="form-group">
                                <label for="userId" class="col-sm-3 control-label">ชื่อผู้ใช้</label>
                                <div class="col-sm-4">
                                    <form:input type="text" class="form-control" id="userID" placeholder="ชื่อผู้ใช้" path="userID"/>
                                </div>
                            </div>
                                
                            <form:errors path="*" cssClass="errorblock" element="div" />

                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-9">
                                    <input type="submit" class="btn btn-primary" value="แก้ไขบัญชีดำ"/>
                                </div>
                            </div>
                        </form:form>

                    </div>
                </div>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
