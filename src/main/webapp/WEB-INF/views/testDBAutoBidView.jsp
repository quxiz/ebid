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
                <li role="presentation" class="active"><a href="#autoBid" aria-controls="autoBid" role="tab" data-toggle="tab">autoBid</a></li>
            </ul>

            <!-- Tab panes -->
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="autoBid">
                    <div class="container">
                        <h4>Add a AutoBid</h4>
                        <div>
                            <c:url var="addAction" value="/testDB/autoBid/saveAutoBid" ></c:url>
                            <form:form action="${addAction}" modelAttribute="autoBid" method="POST" name="autoBid">
                                <div class="form-group">
                                    <div><form:input placeholder="itemID" path="itemID" /></div>
                                    <div><form:input placeholder="bidderID" path="bidderID" /></div>
                                    <div><form:input placeholder="maxBid" path="maxBid" /></div>
                                    <div><form:input placeholder="bidIncrement" path="bidIncrement" /></div>
                                    <div><form:input placeholder="timestamp" path="timestamp" /></div>
                                </div>
                                <input type="submit" class="btn btn-default" value="save"/>
                            </form:form>
                        </div>
                        <h4>AutoBids List</h4>
                        <c:if test="${!empty listAutoBids}">
                            <table class="table table-striped">
                                <tr>
                                    <th>itemID</th>
                                    <th>bidderID</th>
                                    <th>maxBid</th>
                                    <th>bidIncrement</th>
                                    <th>timestamp</th>
                                </tr>
                                <c:forEach items="${listAutoBids}" var="autoBid">
                                    <tr>
                                        <td>${autoBid.itemID}</td>
                                        <td>${autoBid.bidderID}</td>
                                        <td>${autoBid.maxBid}</td>
                                        <td>${autoBid.bidIncrement}</td>
                                        <td>${autoBid.timestamp}</td>
                                        <td><a href="${pageContext.request.contextPath}/testDB/autoBid/viewAutoBid/${autoBid.itemID}"><i class="glyphicon glyphicon-wrench" aria-hidden="true"></i></a></td>
                                        <td><a href="${pageContext.request.contextPath}/testDB/autoBid/findByItemID/${autoBid.itemID}">findByItemID/${autoBid.itemID}</a></td>
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