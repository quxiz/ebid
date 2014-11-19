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
                <li role="presentation" class="active"><a href="#item" aria-controls="item" role="tab" data-toggle="tab">item</a></li>
            </ul>

            <!-- Tab panes -->
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="item">
                    <div class="container">
                        <h4>Add a Item</h4>
                        <div>
                            <c:url var="addAction" value="/testDB/item/saveItem" ></c:url>
                            <form:form action="${addAction}" modelAttribute="item" method="POST" name="item">
                                <div class="form-group">
                                    <div><form:hidden placeholder="itemID" path="itemID" /></div>
                                    <div><form:input placeholder="sellerID" path="sellerID" /></div>
                                    <div><form:input placeholder="title" path="title" /></div>
                                    <div><form:input placeholder="condition" path="condition" /></div>
                                    <div><form:input placeholder="specific" path="specific" /></div>
                                    <div><form:input placeholder="detail" path="detail" /></div>
                                    <div><form:input placeholder="category" path="category" /></div>
                                    <div><form:input placeholder="sellingType" path="sellingType" /></div>
                                    <div><form:input placeholder="price" path="price" /></div>
                                    <div><form:input placeholder="quantity" path="quantity" /></div>
                                    <div><form:input placeholder="paymentMethod" path="paymentMethod" /></div>
                                    <div><form:input placeholder="shippingService" path="shippingService" /></div>
                                    <div><form:input placeholder="shippingCost" path="shippingCost" /></div>
                                    <div><form:input placeholder="packageDetail" path="packageDetail" /></div>
                                    <div><form:input placeholder="returnPolicy" path="returnPolicy" /></div>
                                    <div><form:input placeholder="delivery" path="delivery" /></div>
                                </div>
                                <input type="submit" class="btn btn-default" value="save"/>
                            </form:form>
                        </div>
                        <h4>Items List</h4>
                        <c:if test="${!empty listItems}">
                            <table class="table table-striped">
                                <tr>
                                    <th>Item ID</th>
                                    <th>SellerID</th>
                                    <th>title</th>
                                    <th>condition</th>
                                    <th>specific</th>
                                    <th>detail</th>
                                    <th>category</th>
                                    <th>sellingType</th>
                                    <th>price</th>
                                    <th>quantity</th>
                                    <th>startTime</th>
                                    <th>endTime</th>
                                    <th>paymentMethod</th>
                                    <th>shippingService</th>
                                    <th>shippingCost</th>
                                    <th>packageDetail</th>
                                    <th>returnPolicy</th>
                                    <th>timestamp</th>
                                    <th>delivery</th>
                                </tr>
                                <c:forEach items="${listItems}" var="item">
                                    <tr>
                                        <td>${item.itemID}</td>
                                        <td>${item.sellingID}</td>
                                        <td>${item.title}</td>
                                        <td>${item.condition}</td>
                                        <td>${item.specific}</td>
                                        <td>${item.detail}</td>
                                        <td>${item.category}</td>
                                        <td>${item.sellingType}</td>
                                        <td>${item.price}</td>
                                        <td>${item.quantity}</td>
                                        <td>${item.startTime}</td>
                                        <td>${item.endTime}</td>
                                        <td>${item.paymentMethod}</td>
                                        <td>${item.shippingService}</td>
                                        <td>${item.shippingCost}</td>
                                        <td>${item.packageDetail}</td>
                                        <td>${item.returnPolicy}</td>
                                        <td>${item.timestamp}</td>
                                        <td>${item.delivery}</td>
                                        <td><a href="${pageContext.request.contextPath}/testDB/item/findByItemID/${item.itemID}">findByItemID/${item.itemID}</a></td>
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