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
				<a href="${pageContext.request.contextPath}" class="btn btn-default">ค้นหา</a>
			</form>
	
			<ul class="nav navbar-nav navbar-right">
				<li><a href="${pageContext.request.contextPath}/signInView">สมัครสมาชิก</a></li>
				<li><a href="${pageContext.request.contextPath}/signIn.do">เข้าสู่ระบบ</a></li>
			</ul>

<<<<<<< HEAD
<<<<<<< HEAD
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/testDB">testDB</a></li>
                <li><a href="${pageContext.request.contextPath}/testQuartz">testQuartz</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#">Action</a></li>
                        <li><a href="#">Another action</a></li>
                        <li><a href="#">Something else here</a></li>
                        <li class="divider"></li>
                        <li><a href="#">Separated link</a></li>
                        <li class="divider"></li>
                        <li><a href="#">One more separated link</a></li>
                    </ul>
                </li>
            </ul>
            <form class="navbar-form navbar-left" role="search">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </form>
            <sec:authorize access="isAnonymous()">
                <form class="navbar-form navbar-right" action="${pageContext.request.contextPath}/signIn.do" method="post">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="userID" name="userID">
                        <input type="password" class="form-control" placeholder="password" name="password">
                    </div>
                    <button type="submit" class="btn btn-default">SignIn</button>
                </form>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="${pageContext.request.contextPath}/signOut" >SignOut</a>
                    </li>
                </ul>
            </sec:authorize>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
=======
		</div>
	</div>
>>>>>>> f6cb47348836971c1b67cb55132c7566b4113c6d
=======
		</div>
	</div>
>>>>>>> f6cb47348836971c1b67cb55132c7566b4113c6d
</nav>
        <br>
	<br>
	<br>

