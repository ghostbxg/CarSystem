<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/4/23
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<html>
<head>
    <title>教练信息管理界面</title>
</head>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="css/font-awesome.min.css"/>
<script src="js/jquery-3.4.1.min.js" type="text/javascript" charset="utf-8"></script>
<script src="js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
<body>


<table style="width: 100% " class="table border-bottom">
    <tr><th colspan="10"><i class="fa fa-bars mr-2" ></i>教练信息列表</th></tr>
    <tr><td colspan="10">
            <button class="btn btn-outline-primary my-2 my-sm-0 mr-3" type="submit" data-toggle="modal" data-target="#exampleModalScrollable"><i class="fa fa-plus-square-o mr-2" ></i>添加教练</button>
</td></tr>
    <tr class="text-center">
        <th>序号</th>
        <th>教练名称</th>
        <th>登入密码</th>
        <th>教练编号</th>
        <th>教练证件号</th>
        <th>联系电话</th>
        <th>工资</th>
        <th>驾照类型</th>
        <th>入职时间</th>
    </tr>
    <c:if test="${empty flag}">
    <c:if test="${empty coachlist}">
        <c:redirect url="/coach?dos=select"></c:redirect>
    </c:if>
    </c:if>
    <c:if test="${flag}"><tr><td colspan="10" class="text-center">无数据</td></tr></c:if>
    <c:if test="${!empty coachlist}">
        <c:forEach items="${coachlist}" var="c" varStatus="status">
    <tr class="text-center">
        <td>${status.count}</td>
        <td>${c.name}</td>
        <td>${c.password}</td>
        <td>${c.num}</td>
        <td>${c.idnumber}</td>
        <td>${c.tel}</td>
        <td>${c.salary}</td>
        <td>${c.type}</td>
        <td>${c.time}</td>
        <td>

            <div class="btn-group btn-group-toggle" data-toggle="buttons">
                <button class="btn btn-outline-primary my-2 my-sm-0 " type="submit" data-toggle="modal" data-target="#exampleModal" data-whatever="${c}"><i class="fa fa-pencil-square-o mr-2"></i>修改</button>
                <a href="students?dos=delete&id=${c.id}" class="btn btn-outline-danger my-sm-0 my-3" type="submit"><i class="fa fa-trash-o mr-2" ></i>删除</a>
            </div>
        </td></tr>


        </c:forEach>
    </c:if>

    <tr>
        <td colspan="3" class="text-center">
            <c:if test="${pum>0}">
            <a href="coach?dos=select&pum=${pum-1}" class="btn btn-info active" role="button" aria-pressed="true">上一页</a>
            </c:if>
        </td>
        <td colspan="4"  class="text-center"><c:if test="${empty flag}"><p class="mt-2">【${pum+1}/${sum}】</p></c:if></td>
        <td colspan="3" class="text-center"><c:if test="${(pum+1)<sum}"><a href="coach?dos=select&pum=${pum+1}" class="btn btn-info  active" role="button" aria-pressed="true">下一页</a></c:if></td></tr>
</table>


<div class="modal fade" id="exampleModalScrollable" tabindex="-1" role="dialog" aria-labelledby="exampleModalScrollableTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-scrollable" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalScrollableTitle">添加教练</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">
                <form method="post" action="coach?dos=insert">
                    <div class="form-group mt-3">
                        <input type="text" name="name" class="form-control" required placeholder="姓名">
                    </div>

                    <div class="form-group mt-3">
                        <input type="password" name="password" class="form-control" required placeholder="密码">
                    </div>

                    <div class="form-group mt-3">
                        <input type="text" name="number" class="form-control" required placeholder="编号">
                    </div>

                    <div class="form-group mt-3">
                        <input type="text" name="idnumber" class="form-control" required placeholder="证件号">
                    </div>

                    <div class="form-group mt-3">
                        <input type="text" name="tel" class="form-control" required placeholder="联系电话">
                    </div>

                    <div class="form-group mt-3">
                        <input type="text" name="salary" class="form-control" required placeholder="工资">
                    </div>

                    <div class="form-group mt-3">
                        <select name="type" required>
                            <option value="">-驾照类型-</option>
                            <option value="C1">C1</option>
                            <option value="A1">A1</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">确认</button>
            </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>

            </div>

        </div>
    </div>
</div>


<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-scrollable" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">修改信息</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>


            <div class="modal-body">
                <form method="post" action="coach?dos=update">
                    <div class="form-group mt-3">
                       序号： <input type="text" name="id" class="form-control" required placeholder="id" readonly>
                    </div>
                    <div class="form-group mt-3">
                       姓名： <input type="text" name="name" class="form-control" required placeholder="姓名">
                    </div>

                    <div class="form-group mt-3">
                       密码： <input type="text" name="password" class="form-control" required placeholder="密码">
                    </div>

                    <div class="form-group mt-3">
                       编号： <input type="text" name="number" class="form-control" required placeholder="编号">
                    </div>

                    <div class="form-group mt-3">
                       证件号： <input type="text" name="idnumber" class="form-control" required placeholder="证件号">
                    </div>

                    <div class="form-group mt-3">
                       联系电话： <input type="text" name="tel" class="form-control" required placeholder="联系电话">
                    </div>

                    <div class="form-group mt-3">
                        工资：<input type="text" name="salary" class="form-control" required placeholder="工资">
                    </div>

                    <div class="form-group mt-3">
                        <select name="type" required>
                            <option value="">-申请驾照类型-</option>
                            <option value="C1">C1</option>
                            <option value="A1">A1</option>
                        </select>
                    </div>
                    <div class="form-group mt-3">
                       就职时间： <input type="text" name="time" class="form-control" required readonly>
                    </div>
                    <button type="submit" class="btn btn-primary">确认</button>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>

            </div>

        </div>
    </div>
</div>
<script>
    $('#exampleModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget)
        var recipient =button.data('whatever').split("=")
        recipient=recipient.toString().replace(/'/g,"").replace("}","").split(",");
        var c=new Array();
        var j=0;
        for(var i=0;i<recipient.length;i++){
            if(i!=0&&i%2!=0){
                c[j++]=recipient[i];
            }

        }
        var modal = $(this)
        modal.find('[name=id]').val(c[0])
        modal.find('[name=name]').val(c[1])
        modal.find('[name=password]').val(c[2])
        modal.find('[name=number]').val(c[3])
        modal.find('[name=idnumber]').val(c[4])
        modal.find('[name=tel]').val(c[5])
        modal.find('[name=salary]').val(c[6])
        modal.find('[name=type]').val(c[7])
        modal.find('[name=time]').val(c[8])
    })
</script>
</body>
</html>
