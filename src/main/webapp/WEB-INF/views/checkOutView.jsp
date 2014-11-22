<%-- 
    Document   : checkOutView
    Created on : Nov 20, 2014, 6:51:37 PM
    Author     : YumeYami
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <div class="container">
            <div class="col-sm-10" style="float:none; margin-left:auto; margin-right:auto">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">ชำระค่าสินค้า</h3>
                    </div>
                    <div class="panel-body">
                        <c:url var="addAction" value="/checkOut/submit" ></c:url>
                        <form:form class="form-horizontal" role="form" action="${addAction}" modelAttribute="transactionForm" method="POST" name="transactionForm">
                            <div class="form-group">
                                <label for="inputAddress" class="col-sm-3 control-label">ที่อยู่จัดส่ง</label>
                                <div class="col-sm-6">
                                    <form:textarea type="text" class="form-control" id="inputAddress" placeholder="ที่อยู่" path="address"/>
                                </div>

                            </div>

                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-9">
                                    <input class="btn btn-primary" id="confirmAddress" style="width: 100px;" value="ยืนยันที่อยู่"/>
                                </div>
                            </div>
                            <br>


                            <div class="form-group shipping" hidden="true">
                                <label for="sendingMethod" class="col-sm-3 control-label">วิธีการจัดส่ง</label>
                                <div class="col-sm-6">
                                    <form:input type="text" class="form-control" id="sendingMethod" value="" path=""/>
                                </div>

                            </div>
                            <div class="form-group shipping" hidden="true">
                                <div class="col-sm-offset-3 col-sm-9">
                                    <input class="btn btn-primary" id="next" style="width: 100px;" value="ต่อไป"/>
                                </div>
                            </div>

                            <br>
                            <br>
                            <div class="row" id="transaction" hidden="true">
                                <div class="col-sm-8 col-sm-offset-2">
                                    <table class="table table-bordered table-striped table-hover">
                                        <thead>
                                            <tr>
                                                <th class="col-sm-5">รายการ</th>
                                                <th class="col-sm-1">จำนวน</th>
                                                <th class="col-sm-3">จำนวนเงินที่ต้องชำระ</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>${item.title}</td>
                                                <td>${transaction.quantity}</td>
                                                <td>${item.price}</td>
                                            </tr>
                                        </tbody>
                                        <tbody>
                                            <tr>
                                                <td colspan="2">ค่าส่งสินค้า</td>
                                                <td>${transaction.shippingCost}</td>
                                            </tr>
                                        </tbody>
                                        <tfoot>
                                            <tr>
                                                <td colspan="2">รวม</td>
                                                <td>${transaction.price}</td>
                                            </tr>
                                        </tfoot>
                                    </table>
                                    <dl class="dl-horizontal">
                                        <dt>ที่อยู่</dt>
                                        <dd>${transaction.shippingAddress}</dd>
                                    </dl>
                                    <dl class="dl-horizontal">
                                        <dt>วิธีการจัดส่ง</dt>
                                        <dd>${transaction.shippingService}</dd>
                                    </dl>
                                    <div class="col-md-8 col-sm-offset-2 text-center">
                                        <input type="submit" class=" btn btn-primary" value="ชำระเงิน"/>
                                        <a class="btn btn-default" href="#">ยกเลิก</a>
                                    </div>
                                </div>




                            </div>

                        </form:form>



                    </div>


                </div>


            </div>



        </div>
        <script>

            $("#confirmAddress").click(function () {
                $(".shipping").show();
                document.getElementById('inputAddress').disabled = true;
                document.getElementById('confirmAddress').disabled = true;
            });
            $("#next").click(function () {
                $("#transaction").show();
                document.getElementById('next').disabled = true;
                document.getElementById('sendingMethod').disabled = true;
            });
        </script>
    </tiles:putAttribute>
</tiles:insertDefinition>