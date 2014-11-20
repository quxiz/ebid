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
                <li role="presentation" class="active"><a href="#blacklist" aria-controls="blacklist" role="tab" data-toggle="tab">blacklist</a></li>
            </ul>

            <!-- Tab panes -->
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="blacklist">
                    <div class="container">
                        <h4>Add a blacklist</h4>
                        <div>
                            <c:url var="addAction" value="/testDB/blacklist/saveBlacklist" ></c:url>
                            <form:form action="${addAction}" modelAttribute="blacklist" method="POST" name="blacklist">
                                <div class="form-group">
                                    <div><form:hidden placeholder="blacklistID" path="blacklistID" /></div>
                                    <div><form:input placeholder="memberID" path="memberID" /></div>
                                    <div><form:input placeholder="detail" path="detail" /></div>
                                    <div><form:input placeholder="status" path="status" /></div>
                                </div>
                                <input type="submit" class="btn btn-default" value="save"/>
                            </form:form>
                        </div>
                        <h4>blacklists List</h4>
                        <c:if test="${!empty listBlacklists}">
                            <table class="table table-striped">
                                <tr>
                                    <th>blacklistID</th>
                                    <th>memberID</th>
                                    <th>detail</th>
                                    <th>status</th>
                                    <th>timestamp</th>
                                </tr>
                                <c:forEach items="${listBlacklists}" var="blacklist">
                                    <tr>
                                        <td>${blacklist.blacklistID}</td>
                                        <td>${blacklist.memberID}</td>
                                        <td>${blacklist.detail}</td>
                                        <td>${blacklist.status}</td>
                                        <td>${blacklist.timestamp}</td>
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