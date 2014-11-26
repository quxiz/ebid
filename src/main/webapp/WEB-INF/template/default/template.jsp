<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewpoint" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">-->
        <!--        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">-->

<!--        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/datepicker.css">-->
<!--        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-datetimepicker.min.css">-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/star-rating.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery.datetimepicker.css">

        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet">
        <title>${title} - ebid</title>
        <style type="text/css">
            .scrollable-menu {
                height: auto;
                max-height: 200px;
                overflow-x: hidden;
            }
            .navbar-form .input-group-btn,
            .navbar-form .input-group-addon {
                width: auto;
            }
            html,
            body {
                margin:0;
                padding:0;
                height:100%;
            }
            #wrapper {
                min-height:100%;
                position:relative;
            }
            #header {
             
                padding:10px;
            }
            #content {
                padding-bottom:100px; /* Height of the footer element */
            }
            #footer {
            
                width:100%;
                height:100px;
                position:absolute;
                bottom:0;
                left:0;
            }
        </style>
    </head>

    <body>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

        <script src="${pageContext.request.contextPath}/resources/js/moment.js"></script>
<!--        <script src="${pageContext.request.contextPath}/resources/js/bootstrap-datetimepicker.min.js"></script>-->
        <script src="${pageContext.request.contextPath}/resources/js/jquery.countdown.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/star-rating.js"></script>

        <script src="${pageContext.request.contextPath}/resources/js/jquery.datetimepicker.js"></script>


<!--        <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>-->
        <div id="wrapper">
            <div id="header">
                <tiles:insertAttribute name="header" />
            </div>
            <div id="content">
                <tiles:insertAttribute name="body" />
            </div>
            <div id="footer">
                <tiles:insertAttribute name="footer" />
            </div>
        </div>


        <script>
            $(function () {

                $(".dropdown-menu li a").click(function () {
                    var selText = $(this).text();
                    $(this).parents('.dropdown').find('.dropdown-toggle').html(selText + "&nbsp;&nbsp;" + '<span class="caret"></span>');


                });

            });
        </script>
    </body>


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
</footer>
</html>