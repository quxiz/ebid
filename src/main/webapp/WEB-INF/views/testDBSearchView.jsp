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
        <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.1.min.js"></script>    

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
                            <c:url var="addAction" value="/testDB/item/search/submit" ></c:url>
                            <form:form action="${addAction}" class="navbar-form navbar-nav" role="search" modelAttribute="searchForm" method="POST" name="searchForm">
                                <div class="input-group">


                                    <form:input class="form-control" placeholder="ค้นหาสินค้า..." path="keyword" />


                                    <div class="input-group-btn dropdown">

                                        <!-- Button and dropdown menu -->
                                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">ทุกประเภท<span class="caret"></span></button>
                                        <ul class="dropdown-menu scrollable-menu" role="menu" id="categoryHeader">
                                            <c:forEach items="${categoryList}" var="category">
                                                <li onclick="$('#category-hidden2').val('${category}')" role="presentation" class="category-item"><a role="menuitem" tabindex="-1">${category.name}</a>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                        <form:hidden id="category-hidden2" path="category" />
                                    </div>
                                    <button type="submit" class="btn btn-default">ค้นหา</button>
                                </div>

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