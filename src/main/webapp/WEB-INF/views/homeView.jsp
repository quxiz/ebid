<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
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
            </ol>

            <!-- Wrapper for slides -->
            <div class="carousel-inner" role="listbox">
                <c:if test = "${maxIndex} > 0">
                    <c:forEach begin="0" end="${maxIndex}" varStatus="loop">
                        Index: ${loop.index}<br/>

                        <div class="item active">
                            <a href="${pageContext.request.contextPath}/viewItem/${recentItem.itemID}"><img src="${listPhotos[loop.indexi].photoURL}" alt="..." class="carousel-img">
                                <div class="carousel-caption">
                                    <h4>${listRecentItems[loop.index].title}</h4>
                                </div>
                            </a>
                        </div>         

                    </c:forEach>
                </c:if>

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
                    <c:if test = "${maxIndex} > 0">
                        <c:forEach begin="0" end="${maxIndex}" varStatus="loop">
                            <div class="col-sm-4 col-md-3">
                                <div class="thumbnail">
                                    <a href="${pageContext.request.contextPath}/viewItem/${listRecentItems[loop.index].itemID}"><img src="${listPhotos[loop.index].photoURL}" alt="..."></a>
                                    <div class="caption">
                                        <c:if test = "${listRecentItems[loop.index].sellingType=='BUY'}">
                                            <h3>${listRecentItems[loop.index].price} บาท</h3><h4>ซื้อทันที</h4>
                                        </c:if>
                                        <c:if test = "${listRecentItems[loop.index].sellingType=='BID'}">
                                            <h3>ราคาปัจุบัน ${listRecentItems[loop.index].price} บาท</h3><h4>ประมูล</h4>
                                        </c:if>
                                        <p>listRecentItems[loop.index].title</p>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
            </div>



        </tiles:putAttribute>
    </tiles:insertDefinition>