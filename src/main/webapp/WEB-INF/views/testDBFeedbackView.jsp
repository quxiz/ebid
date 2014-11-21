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
                <li role="presentation" class="active"><a href="#feedback" aria-controls="feedback" role="tab" data-toggle="tab">feedback</a></li>
            </ul>

            <!-- Tab panes -->
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="feedback">
                    <div class="container">
                        <h4>Add a Feedback</h4>
                        <div>
                            <c:url var="addAction" value="/testDB/feedback/saveFeedback" ></c:url>
                            <form:form action="${addAction}" modelAttribute="feedback" method="POST" name="feedback">
                                <div class="form-group">
                                    <div><form:input placeholder="transactionID" path="transactionID" /></div>
                                    <div><form:input placeholder="sellerID" path="sellerID" /></div>
                                    <div><form:input placeholder="buyerID" path="buyerID" /></div>
                                    <div><form:input placeholder="itemID" path="itemID" /></div>
                                    <div><form:input placeholder="sellerComment" path="sellerComment" /></div>
                                    <div><form:input placeholder="buyerComment" path="buyerComment" /></div>
                                    <div><form:input placeholder="sellerRating" path="sellerRating" /></div>
                                    <div><form:input placeholder="buyerRating" path="buyerRating" /></div>
                                </div>
                                <input type="submit" class="btn btn-default" value="save"/>
                            </form:form>
                        </div>
                        <h4>Feedbacks List</h4>
                        <c:if test="${!empty listFeedbacks}">
                            <table class="table table-striped">
                                <tr>
                                    <th>transactionID</th>
                                    <th>sellerID</th>
                                    <th>buyerID</th>
                                    <th>itemID</th>
                                    <th>sellerComment</th>
                                    <th>buyerComment</th>
                                    <th>sellerRating</th>
                                    <th>buyerRating</th>
                                </tr>
                                <c:forEach items="${listFeedbacks}" var="feedback">
                                    <tr>
                                        <td>${feedback.transactionID}</td>
                                        <td>${feedback.sellerID}</td>
                                        <td>${feedback.buyerID}</td>
                                        <td>${feedback.itemID}</td>
                                        <td>${feedback.sellerComment}</td>
                                        <td>${feedback.buyerComment}</td>
                                        <td>${feedback.sellerRating}</td>
                                        <td>${feedback.buyerRating}</td>
                                        <td><a href="${pageContext.request.contextPath}/testDB/feedback/viewFeedback/${feedback.transactionID}"><i class="glyphicon glyphicon-wrench" aria-hidden="true"></i></a></td>
                                        <td><a href="${pageContext.request.contextPath}/testDB/feedback/findByTransactionID/${feedback.transactionID}">findByTransactionID/${feedback.transactionID}</a></td>
                                        <td><a href="${pageContext.request.contextPath}/testDB/feedback/findBySellerID/${feedback.sellerID}">findBySellerID/${feedback.sellerID}</a></td>
                                        <td><a href="${pageContext.request.contextPath}/testDB/feedback/findByBuyerID/${feedback.buyerID}">findByBuyerID/${feedback.buyerID}</a></td>
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