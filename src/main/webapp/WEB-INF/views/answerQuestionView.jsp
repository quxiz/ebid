<%-- 
    Document   : answerQuestionView
    Created on : Nov 20, 2014, 6:50:53 PM
    Author     : YumeYami
--%>

<%@page import="com.se.ebid.controller.CountryList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <!DOCTYPE html>
        <div class="container">
            <div class="col-sm-8 col-md-6" style="float:none; margin-left:auto; margin-right:auto">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">ตอบคำถาม</h3>
                    </div>
                    <div class="panel-body">
                        <form:form modelAttribute="answerForm" method="POST" name="answerForm" onsubmit="return confirmSubmit(this, '${pageContext.request.contextPath}/answerQuestion/onSubmit')">
                            <div class="form-group">
                                <label for="title" class="col-sm-3 control-label">ชื่อสินค้า</label>
                                <div class="col-sm-9">
                                    <div id="title">${itemTitle}</div>
                                </div>
                            </div>
                            <label for="detail" class="col-sm-3 control-label">รายละเอียด</label>
                            <div class="col-sm-9">
                                ${comment.commentDetail}
                            </div>
                            <div class="text-right">
                                โดย ${comment.commenterName}<!--getCommenterName()-->
                            </div>
                            <hr>
                            <div class="col-sm-9"> 
                                <form:textarea id="answer" placeholder="คำตอบ" class="form-control" style="resize:vertical;" path ="answer"/><!--คำตอบ-->
                            </div>
                            <form:hidden path="itemID"/>
                            <form:hidden path="parentID"/>
                            <form:hidden path="askerID"/>
                            <br>
                            <br>
                            <br>
                            <input type="submit" class="btn btn-primary" value="ตอบคำถาม">

                            <!--submit()-->
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
        <script>
            function confirmSubmit(submitForm, submitUrl) { // <--- changed here
                if (confirm("นี่คือคำตอบของท่าน?")) {
                    submitForm.action = submitUrl;          // <--- changed here
                    return true;                      // <--- changed here
                }
                return false;                         // <--- changed here
            }
        </script>
        <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
    </tiles:putAttribute>
</tiles:insertDefinition>
