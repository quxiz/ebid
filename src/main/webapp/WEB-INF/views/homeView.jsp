<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<style type="text/css">

    .carousel-img {
        max-height: 500px;
        margin: 0 auto
    }
    /*    .carousel-inner{
            width:100%;
            max-height: 500px !important;
            
        }*/
    .thumbnail a img {
        height:250px;
        /*        max-height: 250px;*/
        max-width: 250px
    }
    /* Smaller than standard 960 (devices and browsers) */
    @media only screen and (max-width: 959px) {
        #carousel{
            height: 500px;
            max-width: 1140px
        }
    }

    /* Tablet Portrait size to standard 960 (devices and browsers) */
    @media only screen and (min-width: 768px) and (max-width: 959px) {

    }

    /* All Mobile Sizes (devices and browser) */
    @media only screen and (max-width: 767px) {

    }

    /* Mobile Landscape Size to Tablet Portrait (devices and browsers) */
    @media only screen and (min-width: 480px) and (max-width: 767px) {}

    /* Mobile Portrait Size to Mobile Landscape Size (devices and browsers) */
    @media only screen and (max-width: 479px) {
        #carousel{
            height: 250px;
            max-width: 1140px
        }
    }
</style>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">


        <!--carousel-->

        <div id="carousel" class="carousel slide container" data-ride="carousel">
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                <li data-target="#carousel-example-generic" data-slide-to="3"></li>
            </ol>

            <!-- Wrapper for slides -->
            <div class="carousel-inner" role="listbox">

                <div class="item active">                       
                    <a href="${pageContext.request.contextPath}/viewItem/${listRecentItems[0].itemID}"><img src="${listPhotos[0].photoURL}" alt="" class="carousel-img">
                        <!--                                    <div class="carousel-caption">
                                                                <h4>${listRecentItems[0].title}</h4>
                                                            </div>-->
                    </a>
                </div>    


                <c:if test = "${maxIndex > 0}">
                    <c:forEach begin="1" end="${maxIndex}" varStatus="loop">                       

                        <div class="item">                       
                            <a href="${pageContext.request.contextPath}/viewItem/${listRecentItems[loop.count].itemID}"><img src="${listPhotos[loop.count].photoURL}" alt="" class="carousel-img">
                                <!--                                    <div class="carousel-caption">
                                                                        <h4>${listRecentItems[loop.count].title}</h4>
                                                                    </div>-->
                            </a>
                        </div>   
                    </c:forEach>
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

        <br>
        <!--container-->
        <div class="container">
            <h3>สินค้าล่าสุด</h3>
            <hr>
            <div class="row">
                <c:if test = "${maxIndex >= 0}">
                    <c:forEach begin="0" end="${maxIndex}" varStatus="loop">
                        <div class="col-sm-2 col-md-3">
                            <div class="thumbnail">
                                <a href="${pageContext.request.contextPath}/viewItem/${listRecentItems[loop.count-1].itemID}"><img src="${listPhotos[loop.count-1].photoURL}" alt="..."></a>
                                <div class="caption">
                                    <h3>${listRecentItems[loop.count-1].title}</h3>
                                    <c:choose>
                                        <c:when test = "${listRecentItems[loop.count-1].sellingType=='BUY'}">
                                            <h4>${listRecentItems[loop.count-1].price} บาท</h4><h4><span class="label label-success">ซื้อทันที</span></h4>
                                        </c:when>


                                        <c:when test="${currentDate > listRecentItems[loop.count-1].endTime}"> 
                                            <h4>หมดเวลาประมูล</h4>
                                            <h4><span class="label label-warning">ประมูล</span></h4>
                                        </c:when>


                                        <c:otherwise>
                                            <h4>ราคาปัจจุบัน ${listRecentItems[loop.count-1].price} บาท</h4><h4><span class="label label-warning">ประมูล</span></h4>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>