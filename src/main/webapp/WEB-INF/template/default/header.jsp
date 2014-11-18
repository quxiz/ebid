<%-- 
    Document   : header
    Created on : Nov 17, 2014, 2:23:30 PM
    Author     : mtmmoei
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page session="false" %>



<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}">eBid</a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-left">
                <li data-toggle="modal" data-target="#pleaseLogInModal"><a href="#">ประกาศขายสินค้า</a></li>		

            </ul>
            <form class="navbar-form navbar-nav" role="search">
                <div class="input-group">
                    <div>
                        <c:url var="addAction" value="/searchForm/search" ></c:url>
                        <form:form action="${addAction}" modelAttribute="searchForm" method="POST" name="member">
                            <div class="form-group">
                                <div><form:input placeholder="ค้นหาสินค้า..." path="keyword" /></div>
                                <div class="input-group-btn" >
                                    <!-- Button and dropdown menu -->
                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">ทุกประเภท<span class="caret"></span></button>
                                        <ul:input path="category" class="dropdown-menu dropdown-menu-right" role="menu"/>
                                            <li><a href="#">ทุกประเภท</a></li>
                                            <li><a href="#">เสื้อผ้า</a></li>
                                            <li><a href="#">เพลง</a></li>
                                            <li><a href="#">อื่นๆ</a></li>
                                        </ul>

                                </div>

                            </div>
                            <input type="submit" class="btn btn-default" value="ค้นหา"/>
                        </form:form>
                    </div>
                </div>
            </form>

            <ul class="nav navbar-nav navbar-right">
                <li><a href="${pageContext.request.contextPath}/register">สมัครสมาชิก</a></li>
                <li><a href="${pageContext.request.contextPath}/signIn">เข้าสู่ระบบ</a></li>
            </ul>


        </div>
    </div>

</nav>
<br>
<br>
<br>

