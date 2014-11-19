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
                <li role="presentation" class="active"><a href="#message" aria-controls="message" role="tab" data-toggle="tab">message</a></li>
            </ul>

            <!-- Tab panes -->
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="message">
                    <div class="container">
                        <h4>Add a Message</h4>
                        <div>
                            <c:url var="addAction" value="/testDB/message/saveMessage" ></c:url>
                            <form:form action="${addAction}" modelAttribute="message" method="POST" name="message">
                                <div class="form-group">
                                    <div><form:hidden placeholder="messageID" path="messageID" /></div>
                                    <div><form:input placeholder="senderID" path="senderID" /></div>
                                    <div><form:input placeholder="receiverID" path="receiverID" /></div>
                                    <div><form:input placeholder="message" path="message" /></div>
                                </div>
                                <input type="submit" class="btn btn-default" value="save"/>
                            </form:form>
                        </div>
                        <h4>Messages List</h4>
                        <c:if test="${!empty listMessages}">
                            <table class="table table-striped">
                                <tr>
                                    <th>Message ID</th>
                                    <th>Sender ID</th>
                                    <th>Receiver ID</th>
                                    <th>message</th>
                                    <th>timestamp</th>
                                    <th>seen</th>
                                </tr>
                                <c:forEach items="${listMessages}" var="message">
                                    <tr>
                                        <td>${message.messageID}</td>
                                        <td>${message.senderID}</td>
                                        <td>${message.receiverID}</td>
                                        <td>${message.message}</td>
                                        <td>${message.timestamp}</td>
                                        <td>${message.seen}</td>
                                        <td><a href="${pageContext.request.contextPath}/testDB/message/viewMessage/${message.messageID}"><i class="glyphicon glyphicon-wrench" aria-hidden="true"></i></a></td>
                                        <td><a href="${pageContext.request.contextPath}/testDB/message/findByReceiverID/${message.receiverID}">findByReceiverID/${message.receiverID}</a></td>
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