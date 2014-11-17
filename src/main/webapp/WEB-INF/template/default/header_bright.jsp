<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/home">ebid</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/testDB">testDB</a></li>
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
</nav>
