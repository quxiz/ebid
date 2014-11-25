<%-- 
    Document   : giveFeedbackView
    Created on : Nov 20, 2014, 6:50:21 PM
    Author     : YumeYami
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
                        <h3 class="panel-title">ให้ Feedback</h3>
                    </div>
                    <div class="panel-body">
                        <c:url var="addAction" value="/giveFeedback/submit" ></c:url>
                        <form:form class="form-horizontal" role="form" action="${addAction}" modelAttribute="feedbackForm" method="POST" name="feedbackForm">
                            <form:hidden path="transactionID" />
                            <div>
                                <c:if test="${isSeller}">
                                    ท่านได้ทำการขายสินค้า
                                    <a href="/viewItem/${item.itemID}">${item.title}</a>
                                </c:if>
                                <c:if test="${isBuyer}">
                                    ท่านได้ทำการซื้อสินค้า
                                    <a href="/viewItem/${item.itemID}">${item.title}</a>
                                </c:if>
                            </div>
                            <br/>
                            <div class="form-group">
                                <c:if test="${isSeller}">
                                    <label for="memberName" class="col-sm-3 control-label">ผู้ซื้อ</label>
                                    <div class="col-sm-9">
                                        <input id="memberName" class="form-control" value="${buyerName}" disabled="true">
                                    </div>    
                                </c:if>
                                <c:if test="${isBuyer}">
                                    <label for="memberName" class="col-sm-3 control-label">ผู้ขาย</label>
                                    <div class="col-sm-9">
                                        <input id="memberName" class="form-control" value="${sellerName}" disabled="true">
                                    </div>     
                                </c:if>
                            </div>
                            <div class="form-group">
                                <label for="rating" class="col-sm-3 control-label">Rating</label>
                                <div class="col-sm-9">
                                    <form:input id="rating" class="rating" data-min="0" data-max="5" data-step="1" path="rating"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="comment" class="col-sm-3 control-label">ความคิดเห็น</label>
                                <div class="col-sm-9">
                                    <form:textarea type="text" class="form-control" id="comment" placeholder="ความคิดเห็น" path="comment"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-9">
                                    <input type="submit" id="submit" class="btn btn-primary" value="ส่งคะแนน">
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