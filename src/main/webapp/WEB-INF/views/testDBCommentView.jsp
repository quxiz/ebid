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
                <li role="presentation" class="active"><a href="#comment" aria-controls="comment" role="tab" data-toggle="tab">comment</a></li>
            </ul>

            <!-- Tab panes -->
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="comment">
                    <div class="container">
                        <h4>Add a comment</h4>
                        <div>
                            <c:url var="addAction" value="/testDB/comment/saveComment" ></c:url>
                            <form:form action="${addAction}" modelAttribute="comment" method="POST" name="comment">
                                <div class="form-group">
                                    <div><form:hidden placeholder="commentID" path="commentID" /></div>
                                    <div><form:input placeholder="parentID" path="parentID" /></div>
                                    <div><form:input placeholder="itemID" path="itemID" /></div>
                                    <div><form:input placeholder="commenterID" path="commenterID" /></div>
                                    <div><form:input placeholder="commenterName" path="commenterName" /></div>
                                    <div><form:input placeholder="commentDetail" path="commentDetail" /></div>
                                </div>
                                <input type="submit" class="btn btn-default" value="save"/>
                            </form:form>
                        </div>
                        <h4>comments List</h4>
                        <c:if test="${!empty listComments}">
                            <table class="table table-striped">
                                <tr>
                                    <th>commentID</th>
                                    <th>parentID</th>
                                    <th>itemID</th>
                                    <th>commenterID</th>
                                    <th>commenterName</th>
                                    <th>commentDetail</th>
                                    <th>timestamp</th>
                                </tr>
                                <c:forEach items="${listComments}" var="comment">
                                    <tr>
                                        <td>${comment.commentID}</td>
                                        <td>${comment.parentID}</td>
                                        <td>${comment.itemID}</td>
                                        <td>${comment.commenterID}</td>
                                        <td>${comment.commenterName}</td>
                                        <td>${comment.commentDetail}</td>
                                        <td>${comment.timestamp}</td>
                                        <td><a href="${pageContext.request.contextPath}/testDB/comment/findByCommentID/${comment.commentID}">findByCommentID/${comment.commentID}</a></td>
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