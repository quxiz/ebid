<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">



        <h1>Sign In</h1>
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
        </form>



    </tiles:putAttribute>
</tiles:insertDefinition>