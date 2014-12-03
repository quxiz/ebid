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

        <div class="row" style="padding: 5%">
            <div class="col-sm-6 col-md-3">
                <div class="thumbnail">
                    <a href="${pageContext.request.contextPath}/viewMessage" class="thumbnail">
                        <img src="${pageContext.request.contextPath}/resources/img/inbox.png">
                    </a>
                    <div class="caption">
                        <h3 style="text-align: center"><a href="${pageContext.request.contextPath}/viewMessage">กล่องข้อความ</a></h3>
                    </div>
                </div>
            </div>
            <div class="col-sm-6 col-md-3">
                <div class="thumbnail">
                    <a href="${pageContext.request.contextPath}/blacklist" class="thumbnail">
                        <img src="${pageContext.request.contextPath}/resources/img/blacklist.png">
                    </a>
                    <div class="caption">
                        <h3 style="text-align: center"><a href="${pageContext.request.contextPath}/blacklist">แก้ไขบัญชีดำ</a></h3>
                    </div>
                </div>
            </div>
            <div class="col-sm-6 col-md-3">
                <div class="thumbnail">
                    <a href="${pageContext.request.contextPath}/solveComplaint" class="thumbnail">
                        <img src="${pageContext.request.contextPath}/resources/img/complaint.jpg">
                    </a>
                    <div class="caption">
                        <h3 style="text-align: center"><a href="${pageContext.request.contextPath}/solveComplaint">ตอบข้อร้องเรียน</a></h3>
                    </div>
                </div>
            </div>
            <div class="col-sm-6 col-md-3">
                <div class="thumbnail">
                    <a href="${pageContext.request.contextPath}/report" class="thumbnail">
                        <img src="${pageContext.request.contextPath}/resources/img/report.png">
                    </a>
                    <div class="caption">
                        <h3 style="text-align: center"><a href="${pageContext.request.contextPath}/report">ออกรายงาน</a></h3>
                    </div>
                </div>
            </div>
        </div>

    </tiles:putAttribute>
</tiles:insertDefinition>