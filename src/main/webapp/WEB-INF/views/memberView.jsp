<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page session="false" %>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">

<h1>
    Add a Member
</h1>
 
<c:url var="addAction" value="/members/add" ></c:url>
 
<form:form action="${addAction}" commandName="member">
<table>
    <tr>
        <td>
            <form:label path="userID">
                <spring:message text="userID"/>
            </form:label>
        </td>
        <td>
            <form:input path="userID" />
        </td>
    </tr>
    <tr>
        <td>
            <form:label path="password">
                <spring:message text="password"/>
            </form:label>
        </td>
        <td>
            <form:input path="password" />
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit"
                    value="<spring:message text="Add Member"/>" />
        </td>
    </tr>
</table> 
</form:form>
<br>
<h3>Members List</h3>
<c:if test="${!empty listMembers}">
    <table class="tg">
    <tr>
        <th width="80">Member ID</th>
        <th width="120">User ID</th>
        <th width="120">Password</th>
    </tr>
    <c:forEach items="${listMembers}" var="member">
        <tr>
            <td>${member.memberID}</td>
            <td>${member.userID}</td>
            <td>${member.password}</td>
        </tr>
    </c:forEach>
    </table>
</c:if>

    </tiles:putAttribute>
</tiles:insertDefinition>