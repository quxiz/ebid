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
            <div class="col-sm-10" style="float:none; margin-left:auto; margin-right:auto">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">รายงาน</h3>
                    </div>

                    <div class="panel-body">

                        <form class="form-horizontal" role="form">
                            <label for="inputReport" class="col-sm-2 control-label">ประเภทรายงาน</label>
                            <div class="col-sm-1">
                                <div class="dropdown">
                                    <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown">
                                        - เลือก -
                                        <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">สินค้าประมูล</a></li>
                                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">สินค้าขายโดยตรง</a></li>
                                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">สินค้าโดยรวม</a></li>
                                    </ul>
                                </div>
                            </div>
                            <label for="inputMonth" class="col-sm-2 control-label">เดือน</label>
                            <div class="col-sm-3">
                                <div class="dropdown">
                                    <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown">
                                        - เลือก -
                                        <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu2">
                                        <li role="presentation"><a role="menuitem" tabindex="-1">1/2557</a></li>
                                        <li role="presentation"><a role="menuitem" tabindex="-1">2/2557</a></li>
                                        <li role="presentation"><a role="menuitem" tabindex="-1">3/2557</a></li>
                                        <li role="presentation"><a role="menuitem" tabindex="-1">4/2557</a></li>
                                        <li role="presentation"><a role="menuitem" tabindex="-1">5/2557</a></li>
                                        <li role="presentation"><a role="menuitem" tabindex="-1">6/2557</a></li>
                                        <li role="presentation"><a role="menuitem" tabindex="-1">7/2557</a></li>
                                        <li role="presentation"><a role="menuitem" tabindex="-1">8/2557</a></li>
                                        <li role="presentation"><a role="menuitem" tabindex="-1">9/2557</a></li>
                                        <li role="presentation"><a role="menuitem" tabindex="-1">10/2557</a></li>
                                        <li role="presentation"><a role="menuitem" tabindex="-1">11/2557</a></li>
                                        <li role="presentation"><a role="menuitem" tabindex="-1">12/2557</a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="col-sm-2 col-md-2">
                                <a class="btn btn-primary" href="#">
                                    ออกรายงาน
                                </a>
                            </div>
                        </form>
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
