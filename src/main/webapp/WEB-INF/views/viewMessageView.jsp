<%-- 
    Document   : viewMessageView
    Created on : Nov 20, 2014, 6:38:24 PM
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
        <div class="container">
            <div class="col-md-8" style="float:none; margin-left:auto; margin-right:auto">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">กล่องข้อความ</h3>
                    </div>
                    <div class="panel-body">
                <table class="table table-bordered table-striped table-hover">
                    <thead>
                        <tr>
                            <th class="col-md-6">ข้อความ</th>
                            <th class="col-md-2">ผู้ส่ง</th>
                            <th class="col-md-2">เวลาที่ได้รับ</th> <!--timestamp to string-->
                        </tr>
                    </thead>
                    
                    <c:forEach begin = "1" end = "${listSize}" varStatus = "loop">
                    <tbody>
                        <tr>
                            <td data-toggle="modal" data-id="1" data-target="#viewModal${listMessages[loop.count-1].messageID}">
                                <text id="title0">
                                ${listMessages[loop.count-1].message}<!--getMessageID()-->
                                </text>
                            </td>
                            <td>
                                <h5>${listMessages[loop.count-1].senderName}</h5><!--getSenderName()-->
                            </td>
                            <td>
                                <h5>${listMessages[loop.count-1].timestamp}</h5><!--timeStamp.toString-->
                            </td>
                        </tr>
                    </tbody><!--สร้าง 1 body ต่อ 1 message-->
                    </c:forEach>
                </table>
                        
                    </div>
                </div>
            </div>
        </div>
        <c:forEach begin="1" end="${listSize}" varStatus = "loop">
        <div class="modal fade" id="viewModal${listMessages[loop.count-1].messageID}" aria-hidden="true" style="display: none;">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title">ข้อความ</h4>
                    </div>
                    <div class="modal-body">
                        <p>${listMessages[loop.count-1].message}</p>
                        <br>
                        <br>
                        <div class="text-right">
                            <text type="text">
                            ${listMessages[loop.count-1].senderName}<!--getSenderName()-->
                            </text>
                        </div>
                        <br>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">ปิด</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->
</c:forEach>
        
    </tiles:putAttribute>
</tiles:insertDefinition>
