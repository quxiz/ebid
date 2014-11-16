<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page session="false" %>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">

        <div role="tabpanel">

            <!-- Nav tabs -->
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="#member" aria-controls="member" role="tab" data-toggle="tab">member</a></li>
                <li role="presentation"><a href="#message" aria-controls="message" role="tab" data-toggle="tab">message</a></li>
            </ul>

            <!-- Tab panes -->
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="member">
                    <!-- ================================
                         ===========  member  ===========
                         ================================ -->
                    <div class="container">
                        <h4>Add a Member</h4>
                        <div>
                            <c:url var="addAction" value="/testDB/saveMember" ></c:url>
                            <form:form action="${addAction}" modelAttribute="member" method="POST" name="member">
                                <div class="form-group">
                                    <div><form:hidden placeholder="memberID" path="memberID" /></div>
                                    <div><form:input placeholder="userID" path="userID" /></div>
                                    <div><form:input placeholder="password" path="password" /></div>
                                    <div><form:input placeholder="firstName" path="firstName" /></div>
                                    <div><form:input placeholder="lastName" path="lastName" /></div>
                                    <div><form:input placeholder="address" path="address" /></div>
                                    <div><form:input placeholder="country" path="country" /></div>
                                    <div><form:input placeholder="email" path="email" /></div>
                                    <div><form:input placeholder="phoneNo" path="phoneNo" /></div>
                                    <div><form:input placeholder="paymentAccount" path="paymentAccount" /></div>
                                    <div><form:input placeholder="receivingAccount" path="receivingAccount" /></div>
                                </div>
                                <input type="submit" class="btn btn-default" value="save"/>
                            </form:form>
                        </div>
                        <h4>Members List</h4>
                        <c:if test="${!empty listMembers}">
                            <table class="table table-striped">
                                <tr>
                                    <th>Member ID</th>
                                    <th>User ID</th>
                                    <th>Password</th>
                                    <th>firstName</th>
                                    <th>lastName</th>
                                    <th>address</th>
                                    <th>country</th>
                                    <th>email</th>
                                    <th>phoneNo</th>
                                    <th>activated</th>
                                    <th>timestamp</th>
                                    <th>paymentAccount</th>
                                    <th>receivingAccount</th>
                                    <th>blacklisted</th>
                                    <th>activatedKey</th>
                                    <th>edit</th>
                                </tr>
                                <c:forEach items="${listMembers}" var="member">
                                    <tr>
                                        <td>${member.memberID}</td>
                                        <td>${member.userID}</td>
                                        <td>${member.password}</td>
                                        <td>${member.firstName}</td>
                                        <td>${member.lastName}</td>
                                        <td>${member.address}</td>
                                        <td>${member.country}</td>
                                        <td>${member.email}</td>
                                        <td>${member.phoneNo}</td>
                                        <td>${member.activated}</td>
                                        <td>${member.timestamp}</td>
                                        <td>${member.paymentAccount}</td>
                                        <td>${member.receivingAccount}</td>
                                        <td>${member.blacklisted}</td>
                                        <td>${member.activateKey}</td>
                                        <td><a href="${pageContext.request.contextPath}/testDB/viewMember/${member.memberID}"><i class="glyphicon glyphicon-wrench" aria-hidden="true"></i></a></td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:if>
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane" id="message">
                    ...
</button>
                </div>
            </div>
        </div>



        <script>
            $('#myTab a').click(function(e) {
                e.preventDefault()
                $(this).tab('show')
            })
        </script>
    </tiles:putAttribute>
</tiles:insertDefinition>