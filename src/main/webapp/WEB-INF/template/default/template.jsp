<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewpoint" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<<<<<<< HEAD
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
        <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">-->
=======

<!--        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/datepicker.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-datetimepicker.min.css">
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet">

<!--        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">-->
<!--        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">-->

>>>>>>> 218f0ba1ee1d387ef06b2aeccf027dfdbfe8712a

        <title>${title}</title>

    </head>
    <tiles:insertAttribute name="header" />
    <body>
        <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<<<<<<< HEAD
        <!--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>-->
=======

        <script src="${pageContext.request.contextPath}/resources/js/moment.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap-datetimepicker.min.js"></script>

<!--        bright-->
<!--        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>-->

>>>>>>> 218f0ba1ee1d387ef06b2aeccf027dfdbfe8712a
        <tiles:insertAttribute name="body" />

        <!--    pleaseLogInModal-->
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
                        <a href="${pageContext.request.contextPath}/signIn" class="btn btn-primary">เข้าสู่ระบบ</a>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div>


    </body>
    <footer>
        <tiles:insertAttribute name="footer" />
    </footer>
</html>