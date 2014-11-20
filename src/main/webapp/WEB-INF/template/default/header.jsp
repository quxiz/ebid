<%-- 
    Document   : header
    Created on : Nov 17, 2014, 2:23:30 PM
    Author     : mtmmoei
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
            
            
            <form class="navbar-form navbar-nav" role="search" action="${pageContext.request.contextPath}/search" method="POST">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="ค้นหาสินค้า..." name="keyword">
                    <div class="input-group-btn">
                        <!-- Button and dropdown menu -->
                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">ทุกประเภท<span class="caret"></span></button>
                        <ul class="dropdown-menu dropdown-menu-right" role="menu">
                            <li><a href="#">ทุกประเภท</a></li>
                            <li><a href="#">เสื้อผ้า</a></li>
                            <li><a href="#">เพลง</a></li>
                            <li><a href="#">อื่นๆ</a></li>
                        </ul>

                    </div>
                    <button type="submit" class="btn btn-default">ค้นหา</a>
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

