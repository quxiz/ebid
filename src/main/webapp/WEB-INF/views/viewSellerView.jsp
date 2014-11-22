<%-- 
    Document   : viewSellerView
    Created on : Nov 18, 2014, 6:21:08 PM
    Author     : mtmmoei
--%>

<%@page import="com.se.ebid.controller.CountryList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<style type="text/css">
    #carousel{
        max-height: 500px;
        max-width: 1140px
    }
    .carousel-img {
        max-height: 500px;
        margin: 0 auto
    }
    .thumbnail a img {
        max-height: 250px;
    }
    .center {
        float: none;
        margin-left: auto;
        margin-right: auto;
    }
    .centercontents {
        text-align: center !important;
    }
</style>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <div class="container">
            <div class="container">
                <h3>ข้อมูลผู้ขาย</h3>
                <hr>
                <div class = "row">
                    <div class="col-sm-8 col-md-8">	

                        <div class="caption">	
                            <h2><a href="#">${seller.userID}</a></h2>
                            <p>${seller.country}</p>
                            <!--                            <h4>You can find the best clothes at my shop.</h4>-->
                        </div>

                    </div>
                </div>
                <h3>Rating ${sellerRating}/5.0 </h3>
                <hr>
                <div class = "row">
                    <c:forEach items="${sellerFeedback}" var="feedback">
                        <li role="presentation"><a role="menuitem" tabindex="-1">${category.name}</a>
                        </li>
                        <div class="col-sm-12 col-md-12">
                            <div class="thumbnail">
                                <a href="#">${feedback.sellerID}</a><p><span class="glyphicon glyphicon-star" aria-hidden="true"></span><span class="glyphicon glyphicon-star" aria-hidden="true"></span><span class="glyphicon glyphicon-star" aria-hidden="true"></span><span class="glyphicon glyphicon-star-empty" aria-hidden="true"></span><span class="glyphicon glyphicon-star-empty" aria-hidden="true"></span></p>
                                <p>ส่งของตรงเวลาดีครับ</p>
                            </div>
                        </div>
                    </c:forEach>
                    <div class="col-sm-12 col-md-12">
                        <div class="thumbnail">
                            <a href="#">petch</a><p><span class="glyphicon glyphicon-star" aria-hidden="true"></span><span class="glyphicon glyphicon-star" aria-hidden="true"></span><span class="glyphicon glyphicon-star" aria-hidden="true"></span><span class="glyphicon glyphicon-star-empty" aria-hidden="true"></span><span class="glyphicon glyphicon-star-empty" aria-hidden="true"></span></p>
                            <p>ส่งของตรงเวลาดีครับ</p>
                        </div>
                    </div>
                    <br>
                    <div class="col-sm-12 col-md-12">
                        <div class="thumbnail">
                            <a href="#">moei</a><p><span class="glyphicon glyphicon-star" aria-hidden="true"></span><span class="glyphicon glyphicon-star" aria-hidden="true"></span><span class="glyphicon glyphicon-star" aria-hidden="true"></span><span class="glyphicon glyphicon-star" aria-hidden="true"></span><span class="glyphicon glyphicon-star-empty" aria-hidden="true"></span></p>
                            <p>สีเพี้ยนไปจากรูปนิดหน่อย แต่แบบสวยตามรูปเลย</p>
                        </div>
                    </div>
                    <br>
                    <div class="col-sm-12 col-md-12">
                        <div class="thumbnail">
                            <a href="#">kawin</a><p><span class="glyphicon glyphicon-star" aria-hidden="true"></span><span class="glyphicon glyphicon-star" aria-hidden="true"></span><span class="glyphicon glyphicon-star-empty" aria-hidden="true"></span><span class="glyphicon glyphicon-star-empty" aria-hidden="true"></span><span class="glyphicon glyphicon-star-empty" aria-hidden="true"></span></p>
                            <p>ส่งของช้าครับ ของชำรุดหลายจุดด้วยแต่ไม่เห็นบอกในคำอธิบายสินค้า</p>
                        </div>
                    </div>
                </div>
            </div>
            <hr>
            <div class="centercontents">
                <ul class="pagination">
                    <li><a href="#"><span aria-hidden="true">&laquo;</span><span class="sr-only">Previous</span></a></li>
                    <li><a href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                    <li><a href="#"><span aria-hidden="true">&raquo;</span><span class="sr-only">Next</span></a></li>
                </ul> 
            </div>
        </div>
    </tiles:putAttribute>
    <script>

    </script>
</tiles:insertDefinition>
