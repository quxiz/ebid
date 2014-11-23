<%-- 
    Document   : viewMessageView
    Created on : Nov 20, 2014, 6:38:24 PM
    Author     : YumeYami
--%>
<%@page import="com.se.ebid.controller.CountryList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <div class="container">
            <div class="col-md-8" style="float:none; margin-left:auto; margin-right:auto">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">กล่องข้อความ</h3>
                    </div>
                    <div class="panel-body">
                <table class="table table-bordered table-striped table-hover">
                    <thead>
                        <tr>
                            <th class="col-md-6">ข้อความ</th>
                            <th class="col-md-2">ผู้ส่ง</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td data-toggle="modal" data-id="1" data-target="#viewModal">
                                <text id="title0">
                                ไม่ได้รับสินค้า<!--getMessageID()-->
                                </text>
                            </td>
                            <td>
                                <a href="#">KJ</a></h5><!--getSenderName()-->
                            </td>
                        </tr>
                    </tbody><!--สร้าง 1 body ต่อ 1 message-->
                </table>
                        <div class="centercontents text-center">
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
                </div>
            </div>
        </div>

        <div class="modal fade" id="viewModal" aria-hidden="true" style="display: none;">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title">ข้อความ</h4>
                    </div>
                    <div class="modal-body">
                        <a type="text" href="#">
                            ไม่ได้รับสินค้า BRANDIT M-65 Classic Herren Jacke schwarz Feldjacke BW Kapuze Fieldjacket M65 ของ SoulOfNature ครับ
                            <!--getMessage() /จะเป็นลิงค์ไปยังหน้าที่เกี่ยวข้อง-->
                        </a>
                        <br>
                        <br>
                        <div class="text-right">
                            <text type="text">
                            <a href="#">KJ</a><!--getSenderName()-->
                            </text>
                        </div>
                        <br>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">ปิด</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->

        
    </tiles:putAttribute>
</tiles:insertDefinition>
