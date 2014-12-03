<%-- 
    Document   : showReportView
    Created on : Nov 26, 2014, 9:33:53 PM
    Author     : mtmmoei
--%>
<%@page import="com.se.ebid.controller.CountryList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style>
    .dl-horizontal:before, .dl-horizontal:after {
        content: "";
        display: table;
    }
    .dl-horizontal:after {
        clear: both;
    }
    .dl-horizontal:before, .dl-horizontal:after {
        content: "";
        display: table;
    }
    .dl-horizontal dt {
        clear: left;
        float: left;
        width: 60%;
    }
</style>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <div class="container">
            <div class="col-sm-8" style="float:none; margin-left:auto; margin-right:auto">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">รายงานสินค้า${report.reportType.name} ประจำเดือน ${reportMonth} ปี ${reportYear}</h3>
                    </div>
                    <div class="panel-body">
                        <br>
                        
                        <div>
                            <div class="col-sm-6">
                                <strong>จำนวนเงินค่าสินค้าทั้งหมดที่ผู้ซื้อชำระ</strong>
                            </div>
                            <div><fmt:formatNumber value="${report.buyingAmount}" type="number" groupingUsed="true" maxFractionDigits="3" /> บาท</div>
                        </div>
                        <br>
                        <div>
                            <div class="col-sm-6">
                                <strong>รายรับที่ได้จากค่าบริการ</strong>
                            </div>
                            <div><fmt:formatNumber value="${report.chargeAmount}" type="number" groupingUsed="true" maxFractionDigits="3" /> บาท</div>
                        </div>
                        <br>
                        <div>
                            <div class="col-sm-6">
                                <strong>ค่าสินค้าที่จ่ายให้ผู้ขายหลังหักค่าบริการ</strong>
                            </div>
                            <div><fmt:formatNumber value="${report.sellingAmount}" type="number" groupingUsed="true" maxFractionDigits="3" /> บาท</div>
                        </div>
                        <br>
                        <div>
                            <div class="col-sm-6">
                                <strong>จำนวนการซื้อขายสินค้า</strong>
                            </div>
                            <div>${report.transactionAmount} ชิ้น</div>
                        </div>

                    </div>
                </div>
            </div>
        </div>

    </tiles:putAttribute>
</tiles:insertDefinition>