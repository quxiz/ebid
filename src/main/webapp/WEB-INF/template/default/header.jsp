<%-- 
    Document   : header
    Created on : Nov 17, 2014, 2:23:30 PM
    Author     : mtmmoei
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.1.min.js"></script>


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
                <sec:authorize access="isAuthenticated()">
                    <li><a href="${pageContext.request.contextPath}/registerItem">ประกาศขายสินค้า</a></li>
                    </sec:authorize>
                    <sec:authorize access="isAnonymous()">
                    <li data-toggle="modal" data-target="#pleaseLogInModal"><a href="#">ประกาศขายสินค้า</a></li>
                    </sec:authorize>

            </ul>
            <c:url var="addAction" value="/search" ></c:url>
            <form:form action="${addAction}" class="navbar-form navbar-nav" role="search" modelAttribute="searchForm" method="POST" name="searchForm">
                <div class="form-group">
                    <div class="input-group">

                        <form:input class="form-control" placeholder="ค้นหาสินค้า..." path="keyword" />
                        <select class="form-control" name="category">
                            <c:forEach items="${categoryList}" var="category">
                                <option value="${category}">${category.name}</option>
                            </c:forEach>
                        </select>
                        <button type="submit" class="btn btn-default">ค้นหา</a>
                    </div>
                </div>

            </form:form>

            <sec:authorize access="isAnonymous()">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="${pageContext.request.contextPath}/register">สมัครสมาชิก</a></li>
                    <li><a href="${pageContext.request.contextPath}/signIn">เข้าสู่ระบบ</a></li>
                </ul>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><sec:authentication property="principal.username" /><span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="#">กล่องข้อความ</a></li>
                            <li><a href="#">ประวัติการซื้อ</a></li>
                            <li><a href="#">ประวัติการขาย</a></li>
                            <li><a href="#">แก้ไขข้อมูลส่วนตัว</a></li>
                            <li class="divider"></li>
                            <li><a href="${pageContext.request.contextPath}/signOut">ออกจากระบบ</a></li>
                        </ul>
                    </li>
                </ul>		
            </sec:authorize>
        </div>
    </div>

</nav>
<br>
<br>
<br>

<script>
    $(document).on('click', '.category-item', function() {
        alert("OK");
        //var selText = $(this a).text();
        //alert(selText);
        //$("#category-hidden").val(selText);
    });
</script>
