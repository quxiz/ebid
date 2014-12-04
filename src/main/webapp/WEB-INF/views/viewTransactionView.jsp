<%-- 
    Document   : viewTransactionView
    Created on : Nov 18, 2014, 6:21:40 PM
    Author     : mtmmoei
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <div class="container">
          
                    <div class="col-sm-12">
                        <ul id="myTab" class="nav nav-tabs" role="tablist">
                            <li role="presentation" class="active"><a href="#buying" aria-controls="buying" role="tab" data-toggle="tab">ข้อมูลการซื้อ</a></li>
                            <li role="presentation"><a href="#selling" aria-controls="selling" role="tab" data-toggle="tab">ข้อมูลการขาย</a></li>
                        </ul>
                    </div>
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane active in" id="buying">
                            <div class="panel panel-default">
                         

                                <table class="table table-bordered table-striped table-hover">
                                    <thead>
                                        <tr>
                                            <th class="col-md-1">วันที่</th>
                                            <th class="col-md-3">สินค้า</th>
                                            <th class="col-md-1">จำนวน</th>
                                            <th class="col-md-1">ราคา</th>
                                            <th class="col-md-1">การซื้อ</th>
                                            <th class="col-md-3">ที่อยู่จัดส่ง</th>
                                            <th class="col-md-1">การจัดส่ง</th>
                                            
                                            <th class="col-md-1">ผู้ขาย</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${listBuyTransactions}" var="transaction" varStatus="status">

                                            <tr>
                                                <td>
                                                    ${transaction.timestamp}
                                                </td>
                                                <td>

                                                    ${transaction.title} <!--getItemID()-->
                                                </td>
                                                <td>
                                                    ${transaction.quantity}<!--getQuatity()-->
                                                </td>
                                                <td>
                                                    ${transaction.price}<!--getPrice()-->
                                                </td>
                                                <td>
                                                    ${transaction.sellingType}<!--getSellingType()-->
                                                </td>
                                                <td>
                                                    ${transaction.shippingAddress}<!--getShippingService()-->
                                                </td>
                                                <td>
                                                    ${transaction.shippingService}<!--getShippingService()-->
                                                </td>
                                                
                                                <td>
                                                    <a href="${pageContext.request.contextPath}/viewSeller/${transaction.sellerName}">${transaction.sellerName}</a><!--ชื่อผู้แจ้ง getSellerID()-->
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody><!--สร้าง 1 body ต่อ 1 transaction-->
                                </table>
                            </div>
                          
                        </div>
                        <div role="tabpanel" class="tab-pane" id="selling">
                            <div class="panel panel-default">
                       
                                <table class="table table-bordered table-striped table-hover">
                                    <thead>
                                        <tr>
                                            <th class="col-md-1">วันที่</th>
                                            <th class="col-md-3">สินค้า</th>
                                            <th class="col-md-1">จำนวน</th>
                                            <th class="col-md-1">ราคา</th>
                                            <th class="col-md-1">การขาย</th>
                                            <th class="col-md-3">ที่อยู่จัดส่ง</th>
                                            <th class="col-md-1">การจัดส่ง</th>
                                            
                                            <th class="col-md-1">ผู้ซื้อ</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${listSellTransactions}" var="transaction" varStatus="status">
                                            <tr>

                                                <td>
                                                    ${transaction.timestamp}
                                                </td>
                                                <td>
                                                    ${transaction.title}<!--getItemID()-->
                                                </td>
                                                <td>
                                                    ${transaction.quantity}<!--getQuatity()-->
                                                </td>
                                                <td>
                                                    ${transaction.price}<!--getPrice()-->
                                                </td>
                                                <td>
                                                    ${transaction.sellingType}<!--getSellingType()-->
                                                </td>
                                                <td>
                                                    ${transaction.shippingAddress}<!--getShippingService()-->
                                                </td>
                                                <td>
                                                    ${transaction.shippingService}<!--getShippingService()-->
                                                </td>
                                                
                                                <td>
                                                    <a href="${pageContext.request.contextPath}/viewSeller/${transaction.buyerName}">${transaction.buyerName}</a><!--ชื่อผู้แจ้ง getBuyerID()-->
                                                </td>

                                            </tr>
                                        </c:forEach>
                                    </tbody><!--สร้าง 1 body ต่อ 1 transaction-->
                                </table>
                                <!--                                <div class="centercontents text-center">
                                                                    <ul class="pagination">
                                                                        <li><a href="#"><span aria-hidden="true">&laquo;</span><span class="sr-only">Previous</span></a></li>
                                                                        <li><a href="#">1</a></li>
                                                                        <li><a href="#">2</a></li>
                                                                        <li><a href="#">3</a></li>
                                                                        <li><a href="#">4</a></li>
                                                                        <li><a href="#">5</a></li>
                                                                        <li><a href="#"><span aria-hidden="true">&raquo;</span><span class="sr-only">Next</span></a></li>
                                                                    </ul>
                                                                </div>-->

                            </div>
                           
                        </div>

                    </div>
               
        </div>
        <div class="modal fade" id="answerModal" aria-hidden="true" style="display: none;">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4>ยืนยันการแก้ไข</h4>
                    </div>
                    <div class="modal-body">
                        คุณต้องการแก้ไขข้อมูล?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">ยกเลิก</button>
                        <button type="button" class="btn btn-primary" data-dismiss="modal" id="acceptChange">ยืนยัน</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->

       
    </tiles:putAttribute>
</tiles:insertDefinition>
