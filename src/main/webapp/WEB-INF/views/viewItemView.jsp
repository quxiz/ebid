<%-- 
    Document   : viewItemView
    Created on : Nov 19, 2014, 9:15:51 PM
    Author     : Kawin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>



<style type="text/css">
    .scrollable-menu {
        height: auto;
        max-height: 200px;
        overflow-x: hidden;
    }


</style>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">

        <div class="container">
            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}">หน้าแรก</a></li>
                <li><a href="${pageContext.request.contextPath}/search?category=All">สินค้า</a></li>
                <li><a href="${pageContext.request.contextPath}/search?category=${item.category}" class="active">${item.category.name}</a></li> <!--ติดไว้ก่อนรอ search-->
            </ol>

            <h4>${item.title}</h4>
            <!--addaction link profile-->
            <a href="${pageContext.request.contextPath}/viewSeller/${item.sellerName}">${item.sellerName}</a> <!--name-->

            <br>
            <br>
            <div class="row">

                <div id="carousel" class="carousel slide container col-sm-7" data-ride="carousel">
                    <!-- Indicators -->
                    <ol class="carousel-indicators">
                        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                    </ol>

                    <!-- Wrapper for slides -->
                    <div class="carousel-inner" role="listbox">

                        <c:if test = "${fn:length(listPhotos)>0}"> 
                            <div class="item active">
                                <img src="${listPhotos[0].photoURL}" alt="..." class="carousel-img">
                                <div class="carousel-caption">
                                </div>
                            </div>
                            <c:if test = "${fn:length(listPhotos)>1}">
                                <c:forEach var="i" begin="1" end="${fn:length(listPhotos)}">
                                    <div class="item">
                                        <img src="${listPhotos[i].photoURL}" alt="..." class="carousel-img">
                                        <div class="carousel-caption">
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:if>

                        </c:if>
                    </div>

                    <!-- Controls -->
                    <a class="left carousel-control" href="#carousel" role="button" data-slide="prev">
                        <span class="glyphicon glyphicon-chevron-left"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="right carousel-control" href="#carousel" role="button" data-slide="next">
                        <span class="glyphicon glyphicon-chevron-right"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>

                <div class="col-sm-5 col">
                    <div class="text-center">
                        <h3>${item.price} บาท</h3>
                    </div>
                    <br>

                    <!--buy form-->
                    <c:if test="${item.sellingType=='BUY'}"> 
                        <c:url var="addAction" value="/viewItem/${item.itemID}/onSubmitBuyForm" ></c:url>
                        <form:form class="form-horizontal" role="form" action="${addAction}" modelAttribute="buyForm" method="POST" name="buyForm">
                            <label for="inputQuantity" class="col-xs-2 control-label">จำนวน</label>
                            <div class="input-group col-xs-4">
                                <form:input type="text" class="form-control" id="inputQuantity" placeholder="จำนวน" value="1" path="quantity"/>
                                <span class="input-group-addon">/${item.quantity}</span>
<!--                                    <label for="inputQuantity" class="col-xs-2 control-label">/${item.quantity}</label>-->
                            </div>

                            <input type="submit" class="btn btn-primary" style="width:160px" value="ซื้อทันที"/>

                        </form:form>
                    </c:if>
                    <!--bid form-->
                    <c:if test="${item.sellingType=='BID'}">

                        <div class="text-center">
                            <div class="countdown">
                                <h4>เวลาที่เหลือ</h4>
                                <h3><span id="clock"></span></h3>
                            </div>
                        </div>
                        <br>


                        <c:url var="addAction" value="/viewItem/${item.itemID}/onSubmitBidForm" ></c:url>
                        <form:form class="form-horizontal" role="form" action="${addAction}" modelAttribute="bidForm" method="POST" name="bidForm">

                            <div class="form-group">
                                <label for="maxBid" class="col-sm-5 control-label">จำนวนเงินสูงสุด</label>
                                <div class="input-group col-sm-4">
                                    <form:input type="text" class="form-control" id="maxBid" path="maxBid" />
                                    <span class="input-group-addon">บาท</span>
                                </div>
                            </div>


                            <div class="form-group">
                                <label for="bidIncrement" class="col-sm-5 control-label">จำนวนเงินเพิ่มแต่ละครั้ง</label>
                                <div class="input-group col-sm-4">
                                    <form:input type="text" class="form-control" id="bidIncrement" path="bidIncrement" />
                                    <span class="input-group-addon">บาท</span>
                                </div>
                            </div>
                            <div class="text-center">
                                <input type="submit" class="btn btn-primary" style="width:160px" value="ประมูล">
                            </div>
                        </form:form>

                    </c:if>

                    <hr>

                    <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                        <div class="panel panel-default">
                            <div class="panel-heading" role="tab" id="headingZero">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapseZero" aria-expanded="true" aria-controls="collapseZero">
                                        คำอธิบายสินค้า
                                    </a>
                                </h4>
                            </div>
                            <div id="collapseZero" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingZero">
                                <div class="panel-body">
                                    ${item.detail}
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading" role="tab" id="headingOne">
                                <h4 class="panel-title">
                                    <a  class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
                                        รายละเอียดสินค้า
                                    </a>
                                </h4>
                            </div>

                            <!-- ยังไม่รุมีไรมั่ง -->
                            <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                                <div class="panel-body" id="specifics-div">

                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading" role="tab" id="headingTwo">
                                <h4 class="panel-title">
                                    <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                        ข้อตกลงและเงื่อนไข
                                    </a>
                                </h4>
                            </div>
                            <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                                <div class="panel-body">
                                    <dl class="dl-horizontal">
                                        <dt>นโยบายการรับสินค้าคืน</dt>
                                        <dd>${item.returnPolicy}</dd>
                                    </dl>
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading" role="tab" id="headingThree">
                                <h4 class="panel-title">
                                    <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                        วิธีการบรรจุหีบห่อและการขนส่ง
                                    </a>
                                </h4>
                            </div>
                            <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                                <div class="panel-body" id="shipping-div">
                                    <dl class="dl-horizontal">
                                        <dt>การบรรจุหีบห่อ</dt>
                                        <dd>${item.packageDetail}</dd>
                                    </dl>
                                    <dl class="dl-horizontal">
                                        <dt> </dt>
                                        <dd> </dd>
                                    </dl>
                                    <dl class="dl-horizontal">
                                        <dt>วิธีการจัดส่ง</dt>
                                        <dd>ค่าส่ง</dd>
                                    </dl>

                                </div>
                            </div>
                        </div>

                    </div>
                </div> <!-- col -->
            </div> <!-- row -->






            <br>
            <br>

            <br>

            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">คำถามเกี่ยวกับสินค้า</h3>
                </div>
                <div class="panel-body">
                    <c:forEach items="${listComments}" var="comment">
                        <c:if test = "${comment.parentID==null}">
                            <h4 class="media-heading">${comment.commenterName}</h4> <!--name-->
                            <p>${comment.commentDetail}</p>
                            <hr>
                            <!-- Nested media object -->
                            <c:forEach items="${listComments}" var="nestedComment">
                                <c:if test ="${nestedComment.parentID == comment.commentID}">
                                    <h4 class="media-heading" style="margin-left:50px">${nestedComment.commenterName}</h4> <!--name-->
                                    <p style="margin-left:50px">${nestedComment.commentDetail}</p>
                                    <hr>
                                </c:if>
                            </c:forEach>
                        </c:if>
                    </c:forEach>

                </div>
                <div class="panel-footer">
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#askQuestionModal">สอบถามข้อมูลสินค้า</button>
                    <div class="modal fade" id="askQuestionModal">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                    <h4 class="modal-title">สอบถามข้อมูลสินค้า</h4>
                                </div>


                                <c:url var="addAction" value="/viewItem/${itemID}/onSubmitQuestionForm" ></c:url>
                                <form:form action="${addAction}" modelAttribute="questionForm" method="POST" name="questionForm">
                                    <div class="modal-body">
                                        <h4>คำถาม</h4>                                   
                                        <form:textarea class="form-control" rows="3" path="question" />
                                    </div>
                                    <div class="modal-footer">
                                        <!--แก้เป็น link-->
                                        <button type="button" class="btn btn-default" data-dismiss="modal">ยกเลิก</button>
                                        <input type="submit" class="btn btn-primary" value="ส่งคำถาม"/>
                                    </div>
                                </form:form>

                            </div><!-- /.modal-content -->
                        </div><!-- /.modal-dialog -->
                    </div><!-- /.modal -->
                </div>
            </div>

        </div>

        <script>
            $(function() {
//ต้องผูกกับ timestamp
                var timestamp = '${item.endTime}';
                var newTimestamp = timestamp.replace("-", "/").replace(".0", "");
                $('#clock').countdown(newTimestamp)
                        .on('update.countdown', function(event) {
                            var format = '%H:%M:%S';
                            if (event.offset.days > 0) {
                                format = '%-d วัน ' + format;
                            }
                            if (event.offset.weeks > 0) {
                                format = '%-w สัปดาห์ ' + format;
                            }
                            $(this).html(event.strftime(format));
                        })
                        .on('finish.countdown', function(event) {
                            $(this).html('หมดเวลาประมูล');
                            $('.countdown').addClass('disabled')
                        });

            });
        </script>

        <script>
            $(document).ready(function() {
                var specificsJson = '${item.specifics}';
                var obj = jQuery.parseJSON(specificsJson);
                $.each(obj, function(key, value) {
                    $("#specifics-div").append('<dl class="dl-horizontal"><dt>' + value.name + '</dt><dd>' + value.value + '</dd></dl>');
                });
                
                var shippingService = '${item.shippingService}';
                var shippingCost = '${item.shippingCost}';
                var delimiter = ' ';
                var shippingServiceArr = shippingService.split(delimiter);
                var shippingCostArr = shippingCost.split(delimiter);
                $.each(shippingServiceArr, function(key, value) {
                    $("#shipping-div").append('<dl class="dl-horizontal"><dt>' + value + '</dt><dd>' + shippingCostArr[key] + '</dd></dl>');
                });
                
            });
        </script>
    </tiles:putAttribute>
</tiles:insertDefinition>
