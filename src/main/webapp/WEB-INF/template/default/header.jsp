<%-- 
    Document   : header
    Created on : Nov 17, 2014, 2:23:30 PM
    Author     : mtmmoei
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
				<div class="modal fade" id="pleaseLogInModal">
					<div class="modal-dialog modal-sm">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
								<h4 class="modal-title"></h4>
							</div>
							<div class="modal-body">
								<p>กรุณาเข้าสู่ระบบ</p>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">ยกเลิก</button>
								<a href="LogIn.html" class="btn btn-primary">เข้าสู่ระบบ</a>
							</div>
						</div><!-- /.modal-content -->
					</div><!-- /.modal-dialog -->
				</div><!-- /.modal -->
			</ul>
			<form class="navbar-form navbar-nav" role="search">
				<div class="input-group">
					<input type="text" class="form-control" placeholder="ค้นหาสินค้า...">
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
				</div>
				<button type="submit" class="btn btn-default">ค้นหา</button>
			</form>
	
			<ul class="nav navbar-nav navbar-right">
				<li><a href="${pageContext.request.contextPath}/Register.html">สมัครสมาชิก</a></li>
				<li><a href="${pageContext.request.contextPath}/signIn.do">เข้าสู่ระบบ</a></li>
			</ul>

		</div>
	</div>
</nav>
        <br>
	<br>
	<br>
</html>
