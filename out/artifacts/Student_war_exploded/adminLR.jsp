<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>学生宿舍管理系统</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/dashboard.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/bootstrap-table/1.12.1/bootstrap-table.min.js"></script>
    <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>

    <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="http://apps.bdimg.com/libs/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap-table/1.12.1/bootstrap-table.min.js"></script>

    <script language="JavaScript" src="js/showLR.js"></script>
    <script language="JavaScript">
        $(function () {
            //1.初始化Table
            var oTable = new TableInit();
            oTable.Init();
        });
    </script>

</head>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <span class="navbar-brand">学生宿舍管理系统</span>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <span class="navbar-right navbar-brand">${user.uname}${user.utype==2?"(宿管)":"(学生)"}</span>
        </div>
    </div>
</nav>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar hidden-xs">
            <ul class="nav nav-sidebar">
                <li><a href="adminRoom.jsp">宿舍信息 </a></li>
                <li><a href="adminExp.jsp">快件信息</a></li>
                <li><a href="adminSer.jsp">维修信息</a></li>
                <li class="active"><a href="adminLR.jsp">学生离校与返校</a></li>
                <li><a href="adminRec.jsp">晚归记录</a></li>
            </ul>
        </div>
        <div class="visible-xs">
            <a href="adminRoom.jsp">宿舍信息</a></li>
            <a href="adminExp.jsp">快件信息</a></li>
            <a href="adminSer.jsp">维修信息</a></li>
            <a href="adminLR.jsp">学生离校与返校</a></li>
            <a href="adminRec.jsp">晚归记录</a></li>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h2 class="sub-header">学生离校与返校</h2>
            <div class="table-responsive">
                <table class="table table-striped" id="Table">
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>

