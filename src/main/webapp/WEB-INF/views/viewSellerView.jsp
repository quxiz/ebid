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
                            <h2>${seller.userID}</h2>
                            <p>${seller.country}</p>
                            <!--                            <h4>You can find the best clothes at my shop.</h4>-->
                        </div>

                    </div>
                </div>
                <h3>Rating ${sellerRating}/5.0 </h3>
                <hr>
                <div class = "row">
                    <c:forEach items="${sellerFeedback}" var="feedback">
                        <div class="col-sm-12 col-md-12">
                            <div class="thumbnail">
                                <h4>${feedback.buyerName}</h4>
                                <c:if test ="${feedback.buyerRating > 0}">
                                    <c:forEach begin = "1" end = "${feedback.buyerRating}" >
                                        <span class="glyphicon glyphicon-star" aria-hidden="true"></span>
                                    </c:forEach>
                                </c:if>
                                <c:if test ="${feedback.buyerRating < 5}">
                                    <c:forEach begin = "1" end = "${5-feedback.buyerRating}" >
                                        <span class="glyphicon glyphicon-star-empty" aria-hidden="true"></span>
                                    </c:forEach>
                                </c:if>
                                <p>${feedback.buyerComment}</p>
                            </div>
                        </div>
                        <hr>
                    </c:forEach>                                     
                </div>
            </div>
            <hr>

        </div>
    </tiles:putAttribute>
    <script>

    </script>
</tiles:insertDefinition>
