<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">

        <!--        <h1>Sign In</h1>
                <form action="${pageContext.request.contextPath}/signIn.do" method="POST">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="userID" name="userID">
                        <input type="password" class="form-control" placeholder="password" name="password">
                    </div>
        <c:if test="${param.error == 'true'}">
            <div>
                userID or password is incorrect.
            </div>
        </c:if>
        <button type="submit" class="btn btn-default">SignIn</button>
    </form>-->
        <div class="container">
            <div class="col-sm-8 col-md-6" style="float:none; margin-left:auto; margin-right:auto">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">เข้าสู่ระบบ</h3>
                    </div>
                    <div class="panel-body">
                        <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/signIn.do" method="POST">
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-3 control-label">ชื่อผู้ใช้</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="inputEmail3" placeholder="ชื่อผู้ใช้" name="userID">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputPassword3" class="col-sm-3 control-label">รหัสผ่าน</label>
                                <div class="col-sm-9">
                                    <input type="password" class="form-control" id="inputPassword3" placeholder="รหัสผ่าน" name="password">
                                    <br>
                                    <a href="${pageContext.request.contextPath}/forgotPassword">ลืมรหัสผ่าน?</a>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-9">
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox"> จำไว้ในระบบ
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-9">
                                    <button type="submit" id="submit" class="btn btn-primary">เข้าสู่ระบบ</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>


        </tiles:putAttribute>
    </tiles:insertDefinition>