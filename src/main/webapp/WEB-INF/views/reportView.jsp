<%-- 
    Document   : reportView
    Created on : Nov 20, 2014, 6:46:28 PM
    Author     : YumeYami
--%>
<%@page import="com.se.ebid.controller.CountryList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">



<style>
    .error {
        color: #ff0000;
    }

    .errorblock {
        color: #000;
        background-color: #ffEEEE;
        border: 3px solid #ff0000;
        padding: 8px;
        margin: 16px;
    }
</style>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">

        <div class="container">
            <div class="col-sm-6" style="float:none; margin-left:auto; margin-right:auto">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">รายงาน</h3>
                    </div>

                    <div class="panel-body">
                        <c:url var="addAction" value="/report/printReport" ></c:url>
                        <form:form action="${addAction}" class="form-horizontal" role="form" modelAttribute="reportForm" method="POST" name="reportForm">
                            <div class="form-group">
                                <label for="inputReport" class="col-sm-5 control-label">ประเภทรายงาน</label>
                                <div class="col-sm-1">
                                    <div class="dropdown">
                                        <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown">
                                            - เลือก -
                                            <span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                                            <li onclick="$('#reportType').val('BID')" role="presentation"><a role="menuitem" tabindex="-1">สินค้าประมูล</a></li>
                                            <li onclick="$('#reportType').val('BUY')" role="presentation"><a role="menuitem" tabindex="-1">สินค้าซื้อโดยตรง</a></li>
                                            <li onclick="$('#reportType').val('ALL')" role="presentation"><a role="menuitem" tabindex="-1">สินค้าโดยรวม</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <form:hidden id="reportType" path="reportType"/>
                            <div class="form-group">
                                <label for="inputMonth" class="col-sm-5 control-label">เดือน</label>
                                <div class="col-sm-3">
                                    <div class="dropdown">
                                        <button class="btn btn-default dropdown-toggle" type="button" id="inputMonth" data-toggle="dropdown">
                                            - เลือก -
                                            <span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu2">

                                            <li onclick="$('#month').val('1')" role="presentation"><a role="menuitem" tabindex="-1">1</a></li>
                                            <li onclick="$('#month').val('2')" role="presentation"><a role="menuitem" tabindex="-1">2</a></li>
                                            <li onclick="$('#month').val('3')" role="presentation"><a role="menuitem" tabindex="-1">3</a></li>
                                            <li onclick="$('#month').val('4')" role="presentation"><a role="menuitem" tabindex="-1">4</a></li>
                                            <li onclick="$('#month').val('5')" role="presentation"><a role="menuitem" tabindex="-1">5</a></li>
                                            <li onclick="$('#month').val('6')" role="presentation"><a role="menuitem" tabindex="-1">6</a></li>
                                            <li onclick="$('#month').val('7')" role="presentation"><a role="menuitem" tabindex="-1">7</a></li>
                                            <li onclick="$('#month').val('8')" role="presentation"><a role="menuitem" tabindex="-1">8</a></li>
                                            <li onclick="$('#month').val('9')" role="presentation"><a role="menuitem" tabindex="-1">9</a></li>
                                            <li onclick="$('#month').val('10')" role="presentation"><a role="menuitem" tabindex="-1">10</a></li>
                                            <li onclick="$('#month').val('11')" role="presentation"><a role="menuitem" tabindex="-1">11</a></li>
                                            <li onclick="$('#month').val('12')" role="presentation"><a role="menuitem" tabindex="-1">12</a></li>

                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <form:hidden id="month" path="month"/>
                            <div class="form-group">
                                <label for="inputYear" class="col-sm-5 control-label">ปี</label>
                                <div class="col-sm-3">
                                    <div class="dropdown">
                                        <button class="btn btn-default dropdown-toggle" type="button" id="inputYear" data-toggle="dropdown">
                                            - เลือก -
                                            <span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu2">

                                            <li onclick="$('#year').val('2557')" role="presentation"><a role="menuitem" tabindex="-1">2557</a></li>

                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <form:hidden id="year" path="year"/>
                            <br>
                            <div class="col-sm-offset-5">
                                <input class="btn btn-primary" type="submit" value="ออกรายงาน">

                            </div>

                        </form:form>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="confirmModal" aria-hidden="true" style="display: none;">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4>รายงานสินค้าประมูลประจำเดือนตุลาคม ปี 2557</h4><!--getUserID()-->
                    </div>
                    <div class="modal-body">
                        --รายงาน--
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal" data-target="#confirmModal">ปิด</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->
        <script>
//            $(function () {
//
//                $(".dropdown-menu li a").click(function () {
//                    var selText = $(this).text();
//                    $(this).parents('.dropdown').find('.dropdown-toggle').html(selText + "&nbsp;&nbsp;" + '<span class="caret"></span>');
//
//  
//                });
//
//            });
        </script>
    </tiles:putAttribute>
</tiles:insertDefinition>
