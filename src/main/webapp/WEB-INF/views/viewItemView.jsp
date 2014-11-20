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
                <li><a href="${pageContext.request.contextPath}">ebid</a></li>
                <li class="active">สินค้า</li>
                <li class="active">เสื้อผ้า</li> <!--ติดไว้ก่อนรอ search-->
            </ol>

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
                                <img src="img/${listPhotos[0].photoURL}" alt="..." class="carousel-img">
                                <div class="carousel-caption">
                                </div>
                            </div>
                            <c:if test = "${fn:length(listPhotos)>1}">
                                <c:forEach var="i" begin="1" end="${fn:length(listPhotos)}">
                                    <div class="item">
                                        <img src="img/${listPhotos[i].photoURL}" alt="..." class="carousel-img">
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


                    <!--buy form-->
                    <c:if test="${item.sellingType==BUY}"> 
                        <c:url var="addAction" value="/viewItem/onSubmitBuyForm" ></c:url>
                        <form:form action="${addAction}" modelAttribute="buyform" method="POST" name="buyform">
                            <form class="form-horizontal" role="form">
                                <div class="form-group">
                                    <label for="inputQuantity" class="col-xs-2 control-label">จำนวน</label>
                                    <div class="col-xs-4">
                                        <form:input type="text" class="form-control" id="inputQuantity" placeholder="จำนวน" value="1" path="quantity"/>
                                    </div>
                                    <label for="inputQuantity" class="col-xs-2 control-label">/${item.quantity}</label>
                                </div>
                            </form>
                            <input type="submit" class="btn btn-default" value="ซื้นทันที"/>
                        </form:form>
                    </c:if>
                    <!--bid form-->
                    <c:if test="${item.sellingType==BID}">


                        <c:url var="addAction" value="/viewItem/onSubmitBidForm" ></c:url>
                        <form:form action="${addAction}" modelAttribute="bidform" method="POST" name="bidform">

                        </form:form>

                    </c:if>





                    <hr>

                    <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                        <div class="panel panel-default">
                            <div class="panel-heading" role="tab" id="headingOne">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                        รายละเอียดสินค้า
                                    </a>
                                </h4>
                            </div>

                            <!-- ยังไม่รุมีไรมั่ง -->
                            <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                                <div class="panel-body">
                                    <dl class="dl-horizontal">
                                        <dt>ยี่ห้อ</dt>
                                        <dd>BRANDIT</dd>
                                    </dl>
                                    <dl class="dl-horizontal">
                                        <dt>คุณสมบัติ</dt>
                                        <dd>โพลีเอสเตอร์</dd>
                                    </dl>
                                    <dl class="dl-horizontal">
                                        <dt>ขนาด</dt>
                                        <dd>M</dd>
                                    </dl>
                                    <dl class="dl-horizontal">
                                        <dt>สภาพสินค้า</dt>
                                        <dd>ใหม่</dd>
                                    </dl>
                                    <dl class="dl-horizontal">
                                        <dt>จุดบกพร่อง</dt>
                                        <dd>ไม่มี</dd>
                                    </dl>


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
                                        <dt>รูปแบบการชำระเงิน</dt>
                                        <dd>${item.paymentMethod}</dd>
                                    </dl>
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
                                <div class="panel-body">
                                    <dl class="dl-horizontal">
                                        <dl class="dl-horizontal">
                                            <dt>การบรรจุหีบห่อ</dt>
                                            <dd>${item.packageDetail}</dd>
                                        </dl>
                                        <dt>วิธีการจัดส่ง</dt>
                                        <dd>${item.shippingService}</dd>
                                    </dl>
                                    <dl class="dl-horizontal">
                                        <dt>ค่าส่ง</dt>
                                        <dd>${item.shippingCost} บาท</dd>
                                    </dl>

                                </div>
                            </div>
                        </div>

                    </div>
                </div> <!-- col -->
            </div> <!-- row -->


            <h4>${item.title}</h4>
            
            <!--addaction link profile-->
            <a href="SellerProfile.html">${item.sellerID}</a> <!--name-->

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
                            <h5 class="media-heading">${comment.commenterID}</h5> <!--name-->
                            <p>${comment.commentDetail}</p>
                            <!-- Nested media object -->
                            <c:forEach items="${listComments}" var="nestedComment">
                                <c:if test ="${nestedComment.parentID == comment.commentID}">
                                    <h5 class="media-heading" style="margin-left:50px">${nestedComment.commenterID}</h5> <!--name-->
                                    <p style="margin-left:50px">${nestedComment.commentDetail}</p>
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


                                <c:url var="addAction" value="/viewItem/onSubmitQuestionForm" ></c:url>
                                <form:form action="${addAction}" modelAttribute="qform" method="POST" name="qform">
                                    <div class="modal-body">
                                        <h4>คำถาม</h4>                                   
                                        <form:textarea class="form-control" rows="3" path="question" />
                                    </div>
                                    <div class="modal-footer">
                                        <!--แก้เป็น link-->
                                        <button type="button" class="btn btn-default" data-dismiss="modal">ยกเลิก</button>
                                        <input type="submit" class="btn btn-default" value="ส่งคำถาม"/>
                                    </div>
                                </form:form>

                            </div><!-- /.modal-content -->
                        </div><!-- /.modal-dialog -->
                    </div><!-- /.modal -->
                </div>
            </div>

        </div>
        <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>

    </tiles:putAttribute>
</tiles:insertDefinition>
