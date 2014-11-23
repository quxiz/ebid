<%-- 
    Document   : forgotPasswordView
    Created on : Nov 18, 2014, 6:20:18 PM
    Author     : mtmmoei
--%>

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
                        <h3 class="panel-title">ลืมรหัสผ่าน</h3>
                    </div>
                    <div class="panel-body">
                        <c:url var="addAction" value="/register/submit" ></c:url>
                            <p>กรุณากรอกอีเมลที่ใช้ในการลงทะเบียนเพื่อรอรับลิงค์สำหรับเปลี่ยนรหัสผ่าน</p>
                            <br>
                        <form:form class="form-horizontal" role="form" action="${addAction}" modelAttribute="forgotPasswordForm" method="POST" name="forgotPasswordForm">
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-3 control-label">อีเมล</label>
                                <div class="col-sm-9">
                                    <form:input type="email" class="form-control" id="inputEmail3" placeholder="อีเมล" path="email"/>
                                </div>
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