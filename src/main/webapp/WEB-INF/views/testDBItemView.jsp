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
                                    <div><form:input placeholder="detail" path="detail" /></div>
                                    <div><form:input placeholder="category" path="category" /></div>
                                    <div><form:input placeholder="sellingType" path="sellingType" /></div>
                                    <div><form:input placeholder="price" path="price" /></div>
                                    <div><form:input placeholder="quantity" path="quantity" /></div>
                                    <div><form:input placeholder="shippingService" path="shippingService" /></div>
                                    <div><form:input placeholder="shippingCost" path="shippingCost" /></div>
                                    <div><form:input placeholder="packageDetail" path="packageDetail" /></div>
                                    <div><form:input placeholder="returnPolicy" path="returnPolicy" /></div>

                                    <div class="form-group">
                                        <label for="dtp_input1" class="col-md-2 control-label">Date And Time</label>
                                        <div class="input-group date form_datetime col-md-5" data-date="1979-09-16T05:25:07Z" data-date-format="yyyy-mm-dd hh:ii:ss" data-link-field="dtp_input1">
                                            <input class="form-control" size="16" type="text" value="" readonly>
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                                        </div>
                                        <form:hidden id="dtp_input1" path="endTime"/><br/>
                                    </div>
                                    <div>
                                        Specifics
                                        <div id="specifics-form">
                                            <div class="specifics-condition">condition : <input name="condition" value=""><span id="specifics-remove" class="glyphicon glyphicon-remove" aria-hidden="true"></span></div>
                                            <div class="specifics-something">something : <input name="something" value=""><span id="specifics-remove" class="glyphicon glyphicon-remove" aria-hidden="true"></span></div>

                                        </div>
                                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
                                            Add new specific
                                        </button>
                                        <!-- Modal -->
                                        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                                        <h4 class="modal-title" id="myModalLabel">Add new specific</h4>
                                                    </div>
                                                    <div class="modal-body">
                                                        Type your new specific name : <input id="specifics-name" value="" placeholder="Specific name">
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                        <button id="specifics-add" type="button" class="btn btn-primary" data-dismiss="modal">Add</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <form:input id="specifics-json" path="specifics" />
                                    </div>
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
                                    <th>specifics</th>
                                    <th>detail</th>
                                    <th>category</th>
                                    <th>sellingType</th>
                                    <th>price</th>
                                    <th>quantity</th>
                                    <th>startTime</th>
                                    <th>endTime</th>
                                    <th>shippingService</th>
                                    <th>shippingCost</th>
                                    <th>packageDetail</th>
                                    <th>returnPolicy</th>
                                    <th>timestamp</th>

                                </tr>
                                <c:forEach items="${listItems}" var="item">
                                    <tr>
                                        <td>${item.itemID}</td>
                                        <td>${item.sellerID}</td>
                                        <td>${item.title}</td>
                                        <td>${item.specifics}</td>
                                        <td>${item.detail}</td>
                                        <td>${item.category}</td>
                                        <td>${item.sellingType}</td>
                                        <td>${item.price}</td>
                                        <td>${item.quantity}</td>
                                        <td>${item.startTime}</td>
                                        <td>${item.endTime}</td>
                                        <td>${item.shippingService}</td>
                                        <td>${item.shippingCost}</td>
                                        <td>${item.packageDetail}</td>
                                        <td>${item.returnPolicy}</td>
                                        <td>${item.timestamp}</td>

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

        <script>
            $("#specifics-form").keyup(function() {
                $("#specifics-json").val(JSON.stringify($("#specifics-form :input").serializeArray()));
            });

            $("#specifics-add").click(function() {
                var specificName = $("#specifics-name").val();
                $("#specifics-name").val("");
                $("#specifics-form").append('<div class="specifics-' + specificName + '">' + specificName + ' : <input name="' + specificName + '" value=""><span id="specifics-remove" class="glyphicon glyphicon-remove" aria-hidden="true"></span></div>');
            });

            $(document).on("click", "#specifics-remove", function() {
                $(this).parent().remove();
            });

        </script>

        <script>
            $(document).ready(function() {
                if ($("#specifics-json").val()) {
                    $("#specifics-form").empty();
                    var obj = jQuery.parseJSON($("#specifics-json").val());
                    $.each(obj, function(key, value) {
                        $("#specifics-form").append('<div class="specifics-' + value.name + '">' + value.name + ' : <input name="' + value.name + '" value="' + value.value + '"><span id="specifics-remove" class="glyphicon glyphicon-remove" aria-hidden="true"></span></div>');
                    });
                }
            });
        </script>
    </tiles:putAttribute>
</tiles:insertDefinition>