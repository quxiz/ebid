<%-- 
    Document   : solveComplaintIDView
    Created on : Nov 27, 2014, 2:44:36 AM
    Author     : mtmmoei
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
                        <h3 class="panel-title">ตอบข้อร้องเรียน</h3>
                    </div>
                    <div class="panel-body">
                        <c:url var="addAction" value="/solveComplaint/submit" ></c:url>
                        <form:form class="form-horizontal" role="form" action="${addAction}" modelAttribute="solveComplaintForm" method="POST" name="solveComplaintForm">
                            <form:hidden path="complaintID" />
                            <form:hidden path="solverID" />
                            <div class="form-group">
                                <label for="comment" class="col-sm-3 control-label">คำตอบ</label>
                                <div class="col-sm-9">
                                    <form:textarea type="text" class="form-control" id="comment" placeholder="คำตอบ" path="detail"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-9">
                                    <input type="submit" id="submit" class="btn btn-primary" value="ส่งคำตอบ">
                                </div>
                            </div>
                        </form:form>

                    </div>

                </div>


            </div>


        </div>



    </div>
</tiles:putAttribute>
</tiles:insertDefinition>