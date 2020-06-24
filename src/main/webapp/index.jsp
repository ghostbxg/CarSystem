<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/4/23
  Time: 21:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<html>
<head>
    <title>驾校信息管理平台</title>
</head>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="css/font-awesome.min.css"/>
<script src="js/jquery-3.4.1.min.js" type="text/javascript" charset="utf-8"></script>
<script src="js/bootstrap.js" type="text/javascript" charset="utf-8"></script>


<body>
<c:if test="${empty coach}">
    <c:redirect url="/logn.html"></c:redirect>

</c:if>
<nav class="navbar navbar-expand-lg  navbar-dark bg-dark">
    <a class="navbar-brand" href="#">驾校信息管理平台</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="btn btn-danger" href="exit.jsp" role="button"><i class="fa fa-power-off" ></i>退出登录</a>
            </li>

        </ul>
        <form class="form-inline my-2 my-lg-0">
            <span class="mr-3 text-secondary">欢迎您：${coach.name}！</span>
        </form>
    </div>
</nav>
<div class="row" style="height: 100%">
    <div class="col-2">
        <nav class="navbar border bg-dark text-light">
            <div class="my-1"><i class="fa fa-bars mr-2" ></i>菜单</div>
        </nav>
        <nav class="navbar border">
            <a class="nav-link dropdown-toggle text-dark" href="#" role="button" data-toggle="collapse" data-target="#navbarToggleExternalContent">
                <i class="fa fa-user" ></i> <strong>人员信息管理</strong>
            </a>
        </nav>
        <div class="pos-f-t">
            <div class="collapse" id="navbarToggleExternalContent">
                <div class="p-3 border">
                    <a class="text-dark ml-3" href="students_list.jsp" target="iframe1">
                        <i class="fa fa-caret-right " ></i> 学员信息管理
                    </a>
                </div>
                <div class="p-3 border">
                    <a class="text-dark ml-3" href="coach_list.jsp" target="iframe1">
                        <i class="fa fa-caret-right " ></i>  教练信息管理
                    </a>
                </div>

            </div>

        </div>

        <nav class="navbar border">
            <a class="nav-link dropdown-toggle text-dark" href="#"  role="button" data-toggle="collapse" data-target="#navbarToggleExternalContent1">
                <i class="fa fa-edit" ></i>   <strong>车辆信息管理</strong>
            </a>
        </nav>

        <div class="pos-f-t">
            <div class="collapse" id="navbarToggleExternalContent1">
                <div class="p-3 border">
                    <a class="text-dark ml-3" href="car_list.jsp" target="iframe1">
                        <i class="fa fa-caret-right " ></i>  车辆信息管理
                    </a>
                </div>
                <div class="p-3 border">
                    <a class="text-dark ml-3" href="takecar_list.jsp" target="iframe1">
                        <i class="fa fa-caret-right " ></i> 约车管理
                    </a>
                </div>

            </div>

        </div>

        <nav class="navbar border">
            <a class="nav-link dropdown-toggle text-dark" href="#"  role="button" data-toggle="collapse" data-target="#navbarToggleExternalContent2">
                <i class="fa fa-edit" ></i>  <strong>  费用管理</strong>
            </a>
        </nav>

        <div class="pos-f-t">
            <div class="collapse" id="navbarToggleExternalContent2">
                <div class="p-3 border">
                    <a class="text-dark ml-3" href="pay_list.jsp" target="iframe1">
                        <i class="fa fa-caret-right " ></i>   缴费管理
                    </a>
                </div>

            </div>

        </div>

        <nav class="navbar border">
            <a class="nav-link dropdown-toggle text-dark" href="#"  role="button" data-toggle="collapse" data-target="#navbarToggleExternalContent3">
                <i class="fa fa-edit" ></i>  <strong>考试管理</strong>
            </a>
        </nav>

        <div class="pos-f-t">
            <div class="collapse" id="navbarToggleExternalContent3">
                <div class="p-3 border">
                    <a class="text-dark ml-3" href="exam_list.jsp" target="iframe1">
                        <i class="fa fa-caret-right " ></i>  考试管理
                    </a>
                </div>

            </div>

        </div>

    </div>
    <div class="col text-center">
        <iframe src="students_list.jsp" width="100%" frameborder="0" height="100%" scrolling="auto" name="iframe1"></iframe>
    </div>
</div>
</body>
</html>
