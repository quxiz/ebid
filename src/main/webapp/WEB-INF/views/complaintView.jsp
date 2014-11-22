<%-- 
    Document   : complaintView
    Created on : Nov 22, 2014, 12:33:02 PM
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
                        <h3 class="panel-title">ร้องเรียน</h3>
                    </div>
                    <div class="panel-body">
                        <c:url var="addAction" value="/complaint" ></c:url>
                        <form:form class="form-horizontal" role="form" action="${addAction}" modelAttribute="complaintForm" method="POST" name="complaintForm">
                            <div class="form-group">
                                <label for="title" class="col-sm-3 control-label">หัวข้อ</label>
                                <div class="col-sm-9">
                                    <form:input type="text" class="form-control" id="title" placeholder="หัวข้อ" path="title"/>
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
