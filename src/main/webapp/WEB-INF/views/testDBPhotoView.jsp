<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                <li role="presentation" class="active"><a href="#photo" aria-controls="photo" role="tab" data-toggle="tab">photo</a></li>
            </ul>

            <!-- Tab panes -->
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="photo">
                    <div class="container">
                        <h4>Add a Photo</h4>
                        <div>
                            <c:url var="addAction" value="/testDB/photo/savePhoto" ></c:url>
                            <form:form action="${addAction}" modelAttribute="uploadedFile" method="POST" enctype="multipart/form-data">
                                <div class="form-group">
                                    <input type="file" name="file" />
                                </div>
                                <input type="submit" class="btn btn-default" value="save"/>
                            </form:form>
                        </div>
                        <h4>Photos List</h4>
                        <c:if test="${!empty listPhotos}">
                            <table class="table table-striped">
                                <tr>
                                    <th>Photo ID</th>
                                    <th>ItemID</th>
                                    <th>photoURL</th>
                                </tr>
                                <c:forEach items="${listPhotos}" var="photo">
                                    <tr>
                                        <td>${photo.photoID}</td>
                                        <td>${photo.itemID}</td>
                                        <td>${photo.photoURL}</td>
                                        <td><img src="${pageContext.request.contextPath}${photo.photoURL}"></td>
                                        <td><a href="${pageContext.request.contextPath}/testDB/photo/findByItemID/${photo.itemID}">findByItemID/${photo.itemID}</a></td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:if>
                    </div>
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