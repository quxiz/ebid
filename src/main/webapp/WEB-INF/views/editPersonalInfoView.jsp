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
                          
                            <form:form class="form-horizontal" role="form" modelAttribute="personalInfoForm" method="POST" name="personalInfoForm" onsubmit="return confirmSubmit(this, '${pageContext.request.contextPath}/editPersonalInfo/onSubmitPersonalInfo')">
                                <div class="form-group">
                                    <label for="inputName" class="col-sm-3 control-label">ชื่อจริง</label>
                                    <div class="col-sm-4">
                                        <form:input type="text" class="form-control" id="inputName" placeholder="ชื่อ" path="firstName" />
                                        <form:errors path="firstName" cssClass="error" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputSurname" class="col-sm-3 control-label">นามสกุล</label>
                                    <div class="col-sm-4">
                                        <form:input type="text" class="form-control" id="inputSurname" placeholder="นามสกุล" path="lastName"/>
                                        <form:errors path="lastName" cssClass="error" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputAddress" class="col-sm-3 control-label">ที่อยู่</label>
                                    <div class="col-sm-8">
                                        <form:textarea class="form-control" id="inputAddress" placeholder="ที่อยู่" path="address"></form:textarea>
                                        <form:errors path="address" cssClass="error" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputCountry" class="col-sm-3 control-label">ประเทศ</label>
                                    <div class="col-sm-9">
                                        <div class="dropdown">
                                            <button class="btn btn-default dropdown-toggle" type="button" id="dropdownCountry" data-toggle="dropdown">
                                                ${member.country}&nbsp;&nbsp;<span class="caret"></span>
                                            </button>
                                            <ul id="divNewNotifications" class="dropdown-menu scrollable-menu" role="menu" aria-labelledby="dropdownMenu">

                                                <c:forEach items="${countryList.countries}" var="country">
                                                    <li onclick="$('#country-hidden').val('${country}')" role="presentation"><a role="menuitem" tabindex="-1" class="test">${country}</a>
                                                    </li>
                                                </c:forEach>
                                                <form:hidden id="country-hidden" path="country" />
                                            </ul>
                                        </div>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <label for="inputTel" class="col-sm-3 control-label">หมายเลขโทรศัพท์</label>
                                    <div class="col-sm-4">
                                        <form:input type="text" class="form-control" id="inputTel" placeholder="หมายเลขโทรศัพท์" path="phoneNo"/>
                                    </div>
                                </div>


                                <form:errors path="*" cssClass="errorblock" element="div" />

                                <div class="form-group">
                                    <div class="col-sm-offset-3 col-sm-9">
                                        <button type="submit" class="btn btn-primary">บันทึก</button>
                                    </div>
                                </div>
                            </form:form>

                        </div>
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane" id="password">
                    <div class="panel panel-default">
                        <br>
                        <br>
                        <br>
                        <div class="panel-body">

                            <form:form class="form-horizontal" role="form" id="editPasswordForm" modelAttribute="editPasswordForm" method="POST" name="editPasswordForm" onsubmit="return confirmSubmit(this, '${pageContext.request.contextPath}/editPersonalInfo/onSubmitEditPassword')">
                                <div class="form-group">
                                    <label for="inputNewPassword" class="col-sm-3 control-label">รหัสผ่านใหม่</label>
                                    <div class="col-sm-4">
                                        <form:input type="password" class="form-control" id="inputNewPassword" placeholder="รหัสผ่านใหม่" path="newPassword"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputNewPassword2" class="col-sm-3 control-label">ยืนยันรหัสผ่านใหม่</label>
                                    <div class="col-sm-4">
                                        <form:input type="password" class="form-control" id="inputNewPassword2" placeholder="ยืนยันรหัสผ่านใหม่" path="confirmNewPassword"/>
                                        <br>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputOldPassword" class="col-sm-3 control-label">รหัสผ่านเก่า</label>
                                    <div class="col-sm-4">
                                        <form:input type="password" class="form-control" id="inputOldPassword" placeholder="รหัสผ่านเก่า" path="oldPassword"/><!--check with getPassword()-->
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-3 col-sm-9">
                                        <input type="submit" class="btn btn-primary" value="บันทึก"/>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane" id="payment">
                    <div class="panel panel-default">
                        <br>
                        <br>
                        <br>
                        <div class="panel-body">
                            <form:form class="form-horizontal" role="form" id="PaymentInfoForm" modelAttribute="paymentInfoForm" method="POST" name="paymentInfoForm" onsubmit="return confirmSubmit(this, '${pageContext.request.contextPath}/editPersonalInfo/editPaymentInfo/onSubmit')">
                                <div class="form-group">
                                    <label for="inputPassword" class="col-sm-3 control-label">บัญชีชำระเงิน</label>
                                    <div class="col-sm-4">
                                        <form:input type="email" class="form-control" id="newPayment" placeholder="อีเมล" path="payPalAccount"/><!--getPaypalAccount()-->
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-3 col-sm-9">
                                        <input type="submit" class="btn btn-primary" value="บันทึก">
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane" id="recieve">
                    <div class="panel panel-default">
                        <br>
                        <br>
                        <br>
                        <div class="panel-body">
                            <form:form class="form-horizontal" role="form" id="RecievingInfoForm" modelAttribute="receivingInfoForm" method="POST" name="receivingInfoForm" onsubmit="return confirmSubmit(this, '${pageContext.request.contextPath}/editPersonalInfo/editReceivingInfo/onSubmit')">
                                <div class="form-group">
                                    <label for="inputPassword" class="col-sm-3 control-label">บัญชีรับเงิน</label>
                                    <div class="col-sm-4">
                                        <form:input type="email" class="form-control" id="newPayment" placeholder="อีเมล" path="payPalAccount"/><!--getPaypalAccount()-->
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-3 col-sm-9">
                                        <input type="submit" class="btn btn-primary" value="บันทึก">
                                    </div>
                                </div>
                            </form:form>
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
                        <input type="submit" class="btn btn-primary" data-dismiss="modal" id="acceptChange" value="ยืนยัน"/>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->

        <script>
            function confirmSubmit(submitForm, submitUrl) { // <--- changed here
                if (confirm("คุณต้องการแก้ไขข้อมูล?")) {
                    submitForm.action = submitUrl;          // <--- changed here
                    return true;                      // <--- changed here
                }
                return false;                         // <--- changed here
            }
        </script>
    </tiles:putAttribute>
</tiles:insertDefinition>


