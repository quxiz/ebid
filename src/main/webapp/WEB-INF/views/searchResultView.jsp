<%-- 
    Document   : searchResultView
    Created on : Nov 17, 2014, 9:35:04 PM
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
        ${keyword}
        ${category}
        <div class="container">
            <h3>ผลการค้นหาสินค้า</h3>
            <hr>

            <div class = "row">
                <div class="col-sm-4 col-md-4">
                    <div class="thumbnail">
                        <a href="ViewItem.html"><img src="img/1.jpg" alt="..."></a>
                    </div>
                </div>

                <div class="col-sm-8 col-md-8">	

                    <div class="caption">	
                        <a href="ViewItem.html"><p>BRANDIT M-65 Classic Herren Jacke schwarz Feldjacke BW Kapuze Fieldjacket M65</p></a>
                        <h3>2038 บาท</h3><h4>ซื้อทันที</h4>

                    </div>

                </div>
            </div>
            <hr>

            <div class = "row">
                <div class="col-sm-4 col-md-4">
                    <div class="thumbnail">
                        <a href="ViewItem.html"><img src="img/2.jpg" alt="..."></a>
                    </div></div>

                <div class="col-sm-8 col-md-8">	

                    <div class="caption">
                        <p>Levis 501 Mens Jeans Stonewashed W30 W32 W34 W36 W38 +</p>
                        <h3>1303 บาท</h3><h4>ซื้อทันที</h4>

                    </div>

                </div>
            </div>
            <hr>

            <div class = "row">
                <div class="col-sm-4 col-md-4">
                    <div class="thumbnail">
                        <a href="ViewItem.html"><img src="img/3.jpg" alt="..."></a>
                    </div>
                </div>
                <div class="col-sm-8 col-md-8">	
                    <div class="caption">
                        <p>Black Rivet Fully Lined Faux-Leather Scuba Jacket w/ Knit Inset</p>
                        <h3>1693 บาท</h3><h4>ซื้อทันที</h4>
                    </div>
                </div>
            </div>
            <hr>
            <div class = "row">
                <div class="col-sm-4 col-md-4">
                    <div class="thumbnail">
                        <a href="ViewItem.html"><img src="img/4.jpg" alt="..."></a>
                    </div>
                </div>
                <div class="col-sm-8 col-md-8">	
                    <div class="caption">
                        <p>FROM OUT OF THE WEST, COUNTRY , CASSETTE TAPE</p>
                        <h3>105 บาท</h3><h4>0 bids</h4>
                    </div>
                </div>
            </div>
            <hr>

            <div class = "row">
                <div class="col-sm-4 col-md-4">
                    <div class="thumbnail">
                        <a href="ViewItem.html"><img src="img/1.jpg" alt="..."></a>
                    </div>
                </div>

                <div class="col-sm-8 col-md-8">	

                    <div class="caption">	
                        <p>BRANDIT M-65 Classic Herren Jacke schwarz Feldjacke BW Kapuze Fieldjacket M65</p>
                        <h3>2038 บาท</h3><h4>ซื้อทันที</h4>

                    </div>

                </div>
            </div>
            <hr>

            <div class = "row">
                <div class="col-sm-4 col-md-4">
                    <div class="thumbnail">
                        <a href="ViewItem.html"><img src="img/2.jpg" alt="..."></a>
                    </div>
                </div>

                <div class="col-sm-8 col-md-8">	

                    <div class="caption">
                        <p>Levis 501 Mens Jeans Stonewashed W30 W32 W34 W36 W38 +</p>
                        <h3>1303 บาท</h3><h4>ซื้อทันที</h4>

                    </div>

                </div>
            </div>
            <hr>

            <div class = "row">
                <div class="col-sm-4 col-md-4">
                    <div class="thumbnail">
                        <a href="ViewItem.html"><img src="img/3.jpg" alt="..."></a>
                    </div>
                </div>

                <div class="col-sm-8 col-md-8">	

                    <div class="caption">
                        <p>Black Rivet Fully Lined Faux-Leather Scuba Jacket w/ Knit Inset</p>
                        <h3>1693 บาท</h3><h4>ซื้อทันที</h4>

                    </div>

                </div>
            </div>
            <hr>

            <div class = "row">
                <div class="col-sm-4 col-md-4">
                    <div class="thumbnail">
                        <a href="ViewItem.html"><img src="img/4.jpg" alt="..."></a>
                    </div>
                </div>

                <div class="col-sm-8 col-md-8">	

                    <div class="caption">
                        <p>FROM OUT OF THE WEST, COUNTRY , CASSETTE TAPE</p>
                        <h3>105 บาท</h3><h4>0 bids</h4>

                    </div>

                </div>
            </div>
            <hr>

            <div class = "row">
                <div class="col-sm-4 col-md-4">
                    <div class="thumbnail">
                        <a href="ViewItem.html"><img src="img/1.jpg" alt="..."></a>
                    </div>
                </div>

                <div class="col-sm-8 col-md-8">	

                    <div class="caption">	
                        <p>BRANDIT M-65 Classic Herren Jacke schwarz Feldjacke BW Kapuze Fieldjacket M65</p>
                        <h3>2038 บาท</h3><h4>ซื้อทันที</h4>

                    </div>

                </div>
            </div>
            <hr>

            <div class = "row">
                <div class="col-sm-4 col-md-4">
                    <div class="thumbnail">
                        <a href="ViewItem.html"><img src="img/2.jpg" alt="..."></a>
                    </div>
                </div>

                <div class="col-sm-8 col-md-8">	

                    <div class="caption">
                        <p>Levis 501 Mens Jeans Stonewashed W30 W32 W34 W36 W38 +</p>
                        <h3>1303 บาท</h3><h4>ซื้อทันที</h4>

                    </div>

                </div>
            </div>
            <hr>

            <div class = "row">
                <div class="col-sm-4 col-md-4">
                    <div class="thumbnail">
                        <a href="ViewItem.html"><img src="img/3.jpg" alt="..."></a>
                    </div>
                </div>

                <div class="col-sm-8 col-md-8">	

                    <div class="caption">
                        <p>Black Rivet Fully Lined Faux-Leather Scuba Jacket w/ Knit Inset</p>
                        <h3>1693 บาท</h3><h4>ซื้อทันที</h4>

                    </div>

                </div>
            </div>
            <hr>

            <div class = "row">
                <div class="col-sm-4 col-md-4">
                    <div class="thumbnail">
                        <a href="ViewItem.html"><img src="img/4.jpg" alt="..."></a>
                    </div>
                </div>

                <div class="col-sm-8 col-md-8">	

                    <div class="caption">
                        <p>FROM OUT OF THE WEST, COUNTRY , CASSETTE TAPE</p>
                        <h3>105 บาท</h3><h4>0 bids</h4>

                    </div>

                </div>
            </div>
            <hr>



        </div>
    </div>
</tiles:putAttribute>
</tiles:insertDefinition>
