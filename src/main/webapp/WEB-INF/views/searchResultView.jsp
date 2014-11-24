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
        max-width: 250px
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
            <h3>ผลการค้นหาคำว่า ${keyword} จาก ${category}</h3>
            <hr>
            <c:forEach items="${listItems}" var = "item" varStatus="status">
                <div class = "row">
                    <div class="col-sm-4 col-md-4">
                        <div class="thumbnail">
                            <a href="viewItem/${item.itemID}"><img src="${listPhotos[status.index].photoURL}" alt="..."></a>
                        </div>
                    </div>
                    <div class="col-sm-8 col-md-8">	
                        <div class="caption">	
                            <a href="viewItem/${item.itemID}"><p>${item.title}</p></a>
                            <h3>${item.price} บาท</h3><h4>${item.sellingType}</h4>
                        </div>
                    </div>
                </div>
                <hr>
            </c:forEach>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
