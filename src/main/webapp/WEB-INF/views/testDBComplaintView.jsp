<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page session="false" %>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">

        <div role="tabpanel">

            <!-- Nav tabs -->
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="#complaint" aria-controls="complaint" role="tab" data-toggle="tab">complaint</a></li>
            </ul>

            <!-- Tab panes -->
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="complaint">
                    <div class="container">
                        <h4>Add a complaint</h4>
                        <div>
                            <c:url var="addAction" value="/testDB/complaint/saveComplaint" ></c:url>
                            <form:form action="${addAction}" modelAttribute="complaint" method="POST" name="complaint">
                                <div class="form-group">
                                    <div><form:hidden placeholder="complaintID" path="complaintID" /></div>
                                    <div><form:input placeholder="complainterID" path="complainterID" /></div>
                                    <div><form:input placeholder="complaintTitle" path="complaintTitle" /></div>
                                    <div><form:input placeholder="complaintDetail" path="complaintDetail" /></div>
                                    <div><form:input placeholder="solverID" path="solverID" /></div>
                                    <div><form:input placeholder="solveDetail" path="solveDetail" /></div>
                                </div>
                                <input type="submit" class="btn btn-default" value="save"/>
                            </form:form>
                        </div>
                        <h4>complaints List</h4>
                        <c:if test="${!empty listComplaints}">
                            <table class="table table-striped">
                                <tr>
                                    <th>complaintID</th>
                                    <th>complainterID</th>
                                    <th>complaintTitle</th>
                                    <th>complaintDetail</th>
                                    <th>complaintTimestamp</th>
                                    <th>solverID</th>
                                    <th>solveDetail</th>
                                    <th>solveTimestamp</th>
                                </tr>
                                <c:forEach items="${listComplaints}" var="complaint">
                                    <tr>
                                        <td>${complaint.complaintID}</td>
                                        <td>${complaint.complainterID}</td>
                                        <td>${complaint.complaintTitle}</td>
                                        <td>${complaint.complaintDetail}</td>
                                        <td>${complaint.complaintTimestamp}</td>
                                        <td>${complaint.solverID}</td>
                                        <td>${complaint.solveDetail}</td>
                                        <td>${complaint.solveTimestamp}</td>
                                        <td><a href="${pageContext.request.contextPath}/testDB/complaint/findByComplaintID/${complaint.complaintID}">findByComplaintID/${complaint.complaintID}</a></td>
                                        <td><a href="${pageContext.request.contextPath}/testDB/complaint/getComplaint">getComplaint</a></td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>



        <script>
            $('#myTab a').click(function(e) {
                e.preventDefault()
                $(this).tab('show')
            })
        </script>
    </tiles:putAttribute>
</tiles:insertDefinition>