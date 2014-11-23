<%-- 
    Document   : editPersonalInfoView
    Created on : Nov 23, 2014, 6:27:22 PM
    Author     : mtmmoei
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page session="false" %>


<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <div class="container">
            <div class="col-sm-12">
                <ul id="myTab" class="nav nav-tabs" role="tablist">
                    <li role="presentation" class="active"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">แก้ไขข้อมูลทั่วไป</a></li>
                    <li role="presentation"><a href="#password" aria-controls="password" role="tab" data-toggle="tab">แก้ไขรหัสผ่าน</a></li>
                    <li role="presentation"><a href="#payment" aria-controls="payment" role="tab" data-toggle="tab">แก้ไขข้อมูลการชำระเงิน</a></li>
                    <li role="presentation"><a href="#recieve" aria-controls="recieve" role="tab" data-toggle="tab">แก้ไขข้อมูลบัญชีรับเงิน</a></li>
                </ul>
            </div>
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active in" id="profile">
                    <div class="panel panel-default">
                        <br>
                        <br>
                        <br>
                        <div class="panel-body">
                            <form class="form-horizontal" role="form" id="personalInfoForm">
                                <div class="form-group">
                                    <label for="inputName" class="col-sm-3 control-label">ชื่อจริง</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control" id="inputName" placeholder="ชื่อ" value="วงศกร"> <!--getFirstName()-->
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputSurname" class="col-sm-3 control-label">นามสกุล</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control" id="inputSurname" placeholder="นามสกุล" value="โยธินวัฒน์"> <!--getLastName()-->
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputAddress" class="col-sm-3 control-label">ที่อยู่</label>
                                    <div class="col-sm-8">
                                        <textarea class="form-control" id="inputAddress" placeholder="ที่อยู่"></textarea> <!--getAddress()-->
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputCountry" class="col-sm-3 control-label">ประเทศ</label>
                                    <div class="col-sm-9">
                                        <div class="dropdown">
                                            <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown">
                                                - เลือก -
                                                <span class="caret"></span>
                                            </button>
                                            <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                                                <li role="presentation"><a role="menuitem" tabindex="-1" href="#">America</a></li>
                                                <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Japan</a></li>
                                                <li role="presentation"><a role="menuitem" tabindex="-1" href="#">South Korea</a></li>
                                                <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Thailand</a></li>
                                            </ul>
                                        </div>
                                    </div><!--getCountry()-->
                                </div>

                                <div class="form-group">
                                    <label for="inputEmail" class="col-sm-3 control-label">อีเมล</label>
                                    <div class="col-sm-4">
                                        <input type="email" class="form-control" id="inputEmail" placeholder="อีเมล" value="wongsakorn1"><!--getEmail()-->
                                    </div>
                                    <div class="input-group col-sm-4">
                                        <div class="input-group-addon">@</div>
                                        <input class="form-control" type="email" placeholder="อีเมล" value="hotmail.com"><!--getEmail()-->
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputTel" class="col-sm-3 control-label">หมายเลขโทรศัพท์</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control" id="inputTel" placeholder="หมายเลขโทรศัพท์" value="081-888-8888"><!--getPhoneNo()-->
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-3 col-sm-9">
                                        <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#answerModal">บันทึก</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane" id="password">
                    <div class="panel panel-default">
                        <br>
                        <br>
                        <br>
                        <div class="panel-body">
                            <form class="form-horizontal" role="form" id="editPasswordForm">
                                <div class="form-group">
                                    <label for="inputNewPassword" class="col-sm-3 control-label">รหัสผ่านใหม่</label>
                                    <div class="col-sm-4">
                                        <input type="password" class="form-control" id="inputNewPassword" placeholder="รหัสผ่านใหม่">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputNewPassword2" class="col-sm-3 control-label">ยืนยันรหัสผ่านใหม่</label>
                                    <div class="col-sm-4">
                                        <input type="password" class="form-control" id="inputNewPassword2" placeholder="ยืนยันรหัสผ่านใหม่">
                                        <br>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputOldPassword" class="col-sm-3 control-label">รหัสผ่านเก่า</label>
                                    <div class="col-sm-4">
                                        <input type="password" class="form-control" id="inputOldPassword" placeholder="รหัสผ่านเก่า"><!--check with getPassword()-->
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-3 col-sm-9">
                                        <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#answerModal">บันทึก</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane" id="payment">
                    <div class="panel panel-default">
                        <br>
                        <br>
                        <br>
                        <div class="panel-body">
                            <form class="form-horizontal" role="form" id="PaymentInfoForm">
                                <div class="form-group">
                                    <label for="inputPassword" class="col-sm-3 control-label">บัญชีชำระเงิน</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control" id="newPayment" placeholder="บัญชี" value=""><!--getPaypalAccount()-->
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-3 col-sm-9">
                                        <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#answerModal">บันทึก</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane" id="recieve">
                    <div class="panel panel-default">
                        <br>
                        <br>
                        <br>
                        <div class="panel-body">
                            <form class="form-horizontal" role="form" id="RecievingInfoForm">
                                <div class="form-group">
                                    <label for="inputPassword" class="col-sm-3 control-label">บัญชีรับเงิน</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control" id="newPayment" placeholder="บัญชี" value=""><!--getPaypalAccount()-->
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-3 col-sm-9">
                                        <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#answerModal">บันทึก</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="answerModal" aria-hidden="true" style="display: none;">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4>ยืนยันการแก้ไข</h4>
                    </div>
                    <div class="modal-body">
                        คุณต้องการแก้ไขข้อมูล?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">ยกเลิก</button>
                        <button type="button" class="btn btn-primary" data-dismiss="modal" id="acceptChange">ยืนยัน</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->

    </tiles:putAttribute>
</tiles:insertDefinition>


