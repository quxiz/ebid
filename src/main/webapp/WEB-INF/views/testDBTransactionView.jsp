<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page session="false" %>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-datetimepicker.min.css">
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap-datetimepicker.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap-datetimepicker.th.js"></script>
        

        <div role="tabpanel">

            <!-- Nav tabs -->
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="#transaction" aria-controls="transaction" role="tab" data-toggle="tab">transaction</a></li>
            </ul>

            <!-- Tab panes -->
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="transaction">
                    <div class="container">
                        <h4>Add a Transaction</h4>
                        <div>
                            <c:url var="addAction" value="/testDB/transaction/saveTransaction" ></c:url>
                            <form:form action="${addAction}" modelAttribute="transaction" method="POST" name="transaction">
                                <div class="form-group">
                                    <div><form:hidden placeholder="transactionID" path="transactionID" /></div>
                                    <div><form:input placeholder="sellerID" path="sellerID" /></div>
                                    <div><form:input placeholder="buyerID" path="buyerID" /></div>
                                    <div><form:input placeholder="itemID" path="itemID" /></div>
                                    <div><form:input placeholder="quantity" path="quantity" /></div>
                                    <div><form:input placeholder="price" path="price" /></div>
                                    <div><form:input placeholder="detail" path="detail" /></div>
                                    <div><form:input placeholder="sellingType" path="sellingType" /></div>
                                    <div><form:input placeholder="shippingService" path="shippingService" /></div>
                                    <div><form:input placeholder="shippingAddress" path="shippingAddress" /></div>
                                    
                                    <div class="form-group">
                                        <label for="dtp_input1" class="col-md-2 control-label">timestamp</label>
                                        <div class="input-group date form_datetime col-md-5" data-date="1979-09-16T05:25:07Z" data-date-format="yyyy-mm-dd hh:ii:ss" data-link-field="dtp_input1">
                                            <input class="form-control" size="16" type="text" value="" readonly>
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                                        </div>
                                        <form:input id="dtp_input1" path="timestamp"/><br/>
                                    </div>
                                    <div><form:input placeholder="completed" path="completed" /></div>
                                </div>
                                <input type="submit" class="btn btn-default" value="save"/>
                            </form:form>
                        </div>
                        <h4>Transactions List</h4>
                        <c:if test="${!empty listTransactions}">
                            <table class="table table-striped">
                                <tr>
                                    <th>transactionID</th>
                                    <th>sellerID</th>
                                    <th>buyerID</th>
                                    <th>itemID</th>
                                    <th>quantity</th>
                                    <th>price</th>
                                    <th>detail</th>
                                    <th>sellingType</th>
                                    <th>shippingService</th>
                                    <th>timestamp</th>
                                    <th>completed</th>
                                </tr>
                                <c:forEach items="${listTransactions}" var="transaction">
                                    <tr>
                                        <td>${transaction.transactionID}</td>
                                        <td>${transaction.sellerID}</td>
                                        <td>${transaction.buyerID}</td>
                                        <td>${transaction.itemID}</td>
                                        <td>${transaction.quantity}</td>
                                        <td>${transaction.price}</td>
                                        <td>${transaction.detail}</td>
                                        <td>${transaction.sellingType}</td>
                                        <td>${transaction.shippingService}</td>
                                        <td>${transaction.shippingAddress}</td>
                                        <td>${transaction.timestamp}</td>
                                        <td>${transaction.completed}</td>
                                        <td><a href="${pageContext.request.contextPath}/testDB/transaction/viewTransaction/${transaction.transactionID}"><i class="glyphicon glyphicon-wrench" aria-hidden="true"></i></a></td>
                                        <td><a href="${pageContext.request.contextPath}/testDB/transaction/findByTransactionID/${transaction.transactionID}">findByTransactionID/${transaction.transactionID}</a></td>
                                        <td><a href="${pageContext.request.contextPath}/testDB/transaction/findBySellerID/${transaction.sellerID}">findBySellerID/${transaction.sellerID}</a></td>
                                        <td><a href="${pageContext.request.contextPath}/testDB/transaction/findByBuyerID/${transaction.buyerID}">findByBuyerID/${transaction.buyerID}</a></td>
                                        <td><a href="${pageContext.request.contextPath}/testDB/transaction/findCompletedByTimestamp">findCompletedByTimestamp in today</a></td>
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
        
        <script type="text/javascript">
//in this line of code, to display the datetimepicker, we used ‘form_datetime’ as an argument to be
//passed in javascript. This is for Date and Time.
            $('.form_datetime').datetimepicker({
                language: 'en',
                weekStart: 1,
                todayBtn: 1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 2,
                forceParse: 0,
                showMeridian: 1
            });
        </script>
    </tiles:putAttribute>
</tiles:insertDefinition>