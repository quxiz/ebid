<%-- 
    Document   : solveComplaintView
    Created on : Nov 20, 2014, 6:53:22 PM
    Author     : YumeYami
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <div class="row">
            <div class="row col-md-8 col-sm-offset-2">
                <table class="table table-bordered table-striped table-hover">
                    <thead>
                        <tr>
                            <th class="col-md-5">รายการข้อร้องเรียน</th>
                            <th class="col-md-1">ผู้แจ้ง</th>
                            <th class="col-md-1">สถานะ</th>
                            <th class="col-md-1"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach begin="1" end="${listSize}" varStatus="loop">
                            <tr>
                                <td>
                                    <text id="title0">${listComplaints[loop.count-1].complaintTitle}</text>
                                    <text id="title0">${listComplaints[loop.count-1].complaintDetail}</text>
                                </td>
                                <td>
                                    <h5 class="media-heading"><a href="#">${listUser[loop.count-1]}</a></h5><!--ชื่อผู้แจ้ง-->
                                </td>
                                <c:if test="${not listComplaints[loop.count-1].solved}">
                                    <td>ยังไม่ได้จัดการ</td>
                                    <td>
                                        <a type="button" id="myButton0" class="btn btn-primary" href="${pageContext.request.contextPath}/solveComplaint/${listComplaints[loop.count-1].complaintID}">ตอบข้อร้องเรียน</a>
                                    </td>
                                </c:if>
                                <c:if test="${listComplaints[loop.count-1].solved}">
                                    <td>จัดการแล้ว</td>
                                    <td>
                                        <button disabled type="button" id="myButton0" class="btn btn-primary" data-toggle="modal" data-target="#answerModal">ตอบข้อร้องเรียน</button>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </tbody><!--สร้าง 1 body ต่อ 1 complaint-->
                </table>
            </div>
        </div>
       

    </tiles:putAttribute>
</tiles:insertDefinition>
