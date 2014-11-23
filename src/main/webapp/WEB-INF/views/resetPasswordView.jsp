<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <div class="container">
            <div class="col-sm-8 col-md-6" style="float:none; margin-left:auto; margin-right:auto">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">ตั้งรหัสผ่านใหม่</h3>
                    </div>
                    <div class="panel-body">
                        <c:url var="addAction" value="/resetPassword/onSubmit" ></c:url>
                            <p>กรุณากรอกรหัสผ่านใหม่</p>
                            <br>
                        <form:form class="form-horizontal" role="form" action="${addAction}" modelAttribute="resetPasswordForm" method="POST" name="resetPasswordForm">
                            <div class="form-group">
                                <label for="input1" class="col-sm-3 control-label">รหัสผ่านใหม่</label>
                                <div class="col-sm-9">
                                    <form:input type="text" class="form-control" id="input1" placeholder="รหัสผ่านใหม่" path="newPassword"/>
                                </div>
                                <label for="input2" class="col-sm-3 control-label">ยืนยันรหัสผ่านใหม่</label>
                                <div class="col-sm-9">
                                    <form:input type="text" class="form-control" id="input2" placeholder="ยืนยันรหัสผ่านใหม่" path="confirmNewPassword"/>
                                </div>
                                <form:hidden path="email" />
                                <form:hidden path="secret" />
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-9">
                                    <input type="submit" id="submit" class="btn btn-primary" value="ตกลง">
                                </div>
                            </div>
                        </form:form>


                    </div>


                </div>


            </div>



        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>