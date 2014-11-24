<%-- 
    Document   : answerQuestionView
    Created on : Nov 20, 2014, 6:50:53 PM
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

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="row">
        <div class="container-fluid col-md-6 col-md-offset-3">
            <form>
                <div>
                    <h5>${itemTitle}</h5> <!--getItemID()-->
                </div>
                <hr>
                <div>
                    ${comment.commentDetial}<!--getCommentDetail()-->
                </div>
                <div class="text-right">
                    ${comment.commenterName}<!--getCommenterName()-->
                </div>
                <hr>
                <textarea id="answer" placeholder="คำตอบ" class="col-sm-12 col-md-12" style="resize:vertical;"></textarea><!--คำตอบ-->
                <br>
                <br>
                <br>
                <button type="submit" class="btn btn-primary">
                    ตอบคำถาม
                </button><!--submit()-->
            </form>
        </div>
    </div>

    <div class="modal fade" id="answerModal" aria-hidden="true" style="display: none;">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4>ยืนยันคำตอบ</h4>
                </div>
                <div class="modal-body">
                    นี้คือคำตอบของท่าน?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">ยกเลิก</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal" id="acceptChange">ยืนยัน</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

    <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
  </tiles:putAttribute>
</tiles:insertDefinition>
