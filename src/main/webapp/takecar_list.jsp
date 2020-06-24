<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/4/25
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<html>
<head>
    <title>约车管理</title>
</head>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="css/font-awesome.min.css"/>
<script src="js/jquery-3.4.1.min.js" type="text/javascript" charset="utf-8"></script>
<script src="js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
<body>


<table style="width: 100% " class="table border-bottom">
    <tr><th colspan="10"><i class="fa fa-bars mr-2" ></i>约车列表</th></tr>
    <tr><td colspan="10">

        <form class="form-inline my-2 my-lg-0" method="post" action="takecar?dos=selectby">
            <button class="btn btn-outline-primary my-2 my-sm-0 mr-3" type="button" data-toggle="modal" data-target="#exampleModalScrollable"><i class="fa fa-plus-square-o mr-2" ></i>添加约车信息</button>
            搜索：
            <input class="form-control mr-sm-2" type="text" name="lpnumber" placeholder="请输入车牌号" aria-label="Search" size="28px">
            <button class="btn btn-outline-primary my-2 my-sm-0" type="submit">搜索</button>
        </form>
    </td></tr>
    <tr class="text-center">
        <th>序号</th>
        <th>学员姓名</th>
        <th>学员编号</th>
        <th>车牌号</th>
        <th>培训类型</th>
        <th>开始时间</th>
        <th>结束时间</th>
        <th>约车时间</th>
        <th>操作</th>
    </tr>
    <c:if test="${empty flag}">
        <c:if test="${empty takecarlist}">
            <c:redirect url="/takecar?dos=select"></c:redirect>
        </c:if>
    </c:if>
    <c:if test="${flag}"><tr><td colspan="10" class="text-center">无数据</td></tr></c:if>
    <c:if test="${!empty takecarlist}">
        <c:forEach items="${takecarlist}" var="c" varStatus="status">
            <tr class="text-center">
                <td>${status.count}</td>
                <td>${c.name}</td>
                <td>${c.num}</td>
                <td>${c.lpnumber}</td>
                <td>${c.type}</td>
                <td>${c.starttime}</td>
                <td>${c.endtime}</td>
                <td>${c.time}</td>
                <td>

                    <div class="btn-group btn-group-toggle" data-toggle="buttons">
                        <button class="btn btn-outline-primary my-2 my-sm-0 " type="submit" data-toggle="modal" data-target="#exampleModal" data-whatever="${c}"><i class="fa fa-pencil-square-o mr-2"></i>修改</button>
                        <a href="takecar?dos=delete&id=${c.id}" class="btn btn-outline-danger my-sm-0 my-3" type="submit"><i class="fa fa-trash-o mr-2" ></i>删除</a>
                    </div>
                </td></tr>


        </c:forEach>
    </c:if>

    <tr>
        <td colspan="3" class="text-center">
            <c:if test="${empty selectby}">
            <c:if test="${pum>0}">
                <a href="takecar?dos=select&pum=${pum-1}" class="btn btn-info active" role="button" aria-pressed="true">上一页</a>
            </c:if>
            </c:if>
            <c:if test="${selectby}">
                <c:if test="${pum>0}">
                    <a href="takecar?dos=selectby&pum=${pum-1}&lpnumber=${lpnumber}" class="btn btn-info active" role="button" aria-pressed="true">上一页</a>
                </c:if>
            </c:if>
        </td>
        <td colspan="4"  class="text-center"><c:if test="${empty flag}"><p class="mt-2">【${pum+1}/${sum}】</p></c:if></td>
        <td colspan="3" class="text-center">
            <c:if test="${empty selectby}">
            <c:if test="${(pum+1)<sum}"><a href="takecar?dos=select&pum=${pum+1}" class="btn btn-info  active" role="button" aria-pressed="true">下一页</a></c:if>
            </c:if>
            <c:if test="${selectby}">
                <c:if test="${(pum+1)<sum}"><a href="takecar?dos=selectby&pum=${pum+1}&lpnumber=${lpnumber}" class="btn btn-info  active" role="button" aria-pressed="true">下一页</a></c:if>
            </c:if>
        </td></tr>
</table>


<div class="modal fade" id="exampleModalScrollable" tabindex="-1" role="dialog" aria-labelledby="exampleModalScrollableTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-scrollable" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalScrollableTitle">添加约车信息</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">
                <form method="post" action="takecar?dos=insert">

                    <div class="form-group mt-3">
                        <select name="stuid" required>
                            <option value="">-学员姓名-</option>
                            <c:forEach items="${studentslist}" var="c">
                            <option value="${c.id}">${c.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group mt-3">
                        <select name="carid" required>
                            <option value="">-车牌号-</option>
                            <c:forEach items="${carlist}" var="c">
                                <option value="${c.id}">${c.lpnumber}</option>
                            </c:forEach>
                        </select>
                    </div>


                    <div class="form-group mt-3">
                        <select name="type" required>
                            <option value="" >-培训类型-</option>
                            <option value="全天">全天</option>
                            <option value="周末">周末</option>
                        </select>
                    </div>

                    <div class="form-group mt-3">
                        <select name="starttime" required>
                            <option value="" >-开始时间-</option>
                            <option value="2020-03-04">2020-03-04</option>
                            <option value="2020-03-14">2020-03-14</option>
                            <option value="2020-03-24">2020-03-24</option>
                            <option value="2020-04-04">2020-04-04</option>
                        </select>
                    </div>

                    <div class="form-group mt-3">
                        <select name="endtime" required>
                            <option value="">-结束时间-</option>
                            <option value="2020-06-04">2020-06-04</option>
                            <option value="2020-06-14">2020-06-14</option>
                            <option value="2020-06-24">2020-06-24</option>
                            <option value="2020-07-04">2020-07-04</option>
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
                <h5 class="modal-title" id="exampleModalLabel">修改约车信息</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>


            <div class="modal-body">
                <form method="post" action="takecar?dos=update">
                    <div class="form-group mt-3">
                        序号： <input type="text" name="id" class="form-control" required placeholder="id" readonly>
                    </div>
                    <div class="form-group mt-3" required>
                        <input type="hidden" name="stuid"/>
                        <input type="text" readonly name="stuname" class="form-control">
                    </div>

                    <div class="form-group mt-3">
                        <select name="carid" required>
                            <option value="">-车牌号-</option>
                            <c:forEach items="${carlist}" var="c">
                                <option value="${c.id}">${c.lpnumber}</option>
                            </c:forEach>
                        </select>
                    </div>


                    <div class="form-group mt-3">
                        <select name="type" required>
                            <option value="">-培训类型-</option>
                            <option value="全天">全天</option>
                            <option value="周末">周末</option>
                        </select>
                    </div>

                    <div class="form-group mt-3">
                        <select name="starttime" required>
                            <option value="">-开始时间-</option>
                            <option value="2020-03-04">2020-03-04</option>
                            <option value="2020-03-14">2020-03-14</option>
                            <option value="2020-03-24">2020-03-24</option>
                            <option value="2020-04-04">2020-04-04</option>
                        </select>
                    </div>

                    <div class="form-group mt-3">
                        <select name="endtime" required>
                            <option value="">-结束时间-</option>
                            <option value="2020-06-04">2020-06-04</option>
                            <option value="2020-06-14">2020-06-14</option>
                            <option value="2020-06-24">2020-06-24</option>
                            <option value="2020-07-04">2020-07-04</option>
                        </select>
                    </div>

                    <div class="form-group mt-3">
                        约车时间： <input type="text" name="time" class="form-control" required readonly>
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

        modal.find('[name=stuid]').val(c[2])
        modal.find('[name=stuname]').val(c[1])
        modal.find('[name=carid]').val(c[3])
        modal.find('[name=type]').val(c[6])
        modal.find('[name=starttime]').val(c[7])
        modal.find('[name=endtime]').val(c[8])
        modal.find('[name=time]').val(c[9])
        modal.find('[name=carid]').innerText=c[5]
    })
</script>

</body>
</html>
