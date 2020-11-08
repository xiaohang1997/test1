<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>已添加课程</title>
    <style>
        a{
            text-decoration-line: none;
        }
        a:link,a:visited,a:hover{
            color: rgb(121, 192, 224);
        }
        table{
            margin: auto;
            border: 1px rgb(121, 192, 224) solid;
            text-align: center;
        }
        td{
            margin: 2px;
            padding: 2px;
            border: 1px rgb(121, 192, 224) solid;
            color: rgb(121, 192, 224);
            text-align: center;
        }
    </style>
</head>
<body>
    <a href="/learn/User?method=findCourseDirectionLimit">首页</a>
    <table>
        <thead>
            <tr>
                <td>课程方向</td>
                <td>课程名字</td>
                <td>课程介绍</td>
                <td>课程价格</td>
                <td>观看人数</td>
                <td>课程难度</td>
                <td>课程时长</td>
                <td>上传时间</td>
                <td colspan="3">操作</td>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${teacherCourse}" var="var">
                <tr>
                    <td>${var.courseDirection.name}</td>
                    <td>${var.name}</td>
                    <td>${var.introduce}</td>
                    <td>${var.price}</td>
                    <td>${var.number}</td>
                    <td>${var.level}</td>
                    <td>${var.courseTime}</td>
                    <td>${var.createTime}</td>
                    <td>
                        <a href="./addChapter.jsp?id=${var.id}">添加章节</a>
                    </td>
                    <td>
                        <a href="javascript:void(0)" onclick="change(this,${var.id})" class="clt">收藏</a>
                    </td>
                    <td>
                        <a href="/learn/Teacher?method=deleteCourse&id=${var.id}">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
        <tr>
            <td colspan="11">
                <a href="./addCourse.jsp">新增课程</a>
            </td>
        </tr>
    </table>
    <p id="info"></p>
    <script src="../js/jquery-1.12.4.js"></script>
    <script>
        var aj = jQuery.noConflict()
        function change(obj,id) {
            if(aj(obj).text()=="收藏"){
                aj.ajax({
                    url: "/learn/Collection?method=addCollection",
                    dataType: "text",
                    data:"id="+id,
                    method: "get",
                    success: function (data) {
                        if (data == "true") {
                            aj("#info").text("收藏成功")
                        }else {
                            aj("#info").text("收藏失败")
                        }
                    }
                });
            }else {
                aj.ajax({
                    url: "/learn/Collection?method=deleteCollection",
                    dataType: "text",
                    data:"id="+id,
                    method: "get",
                    success: function (data) {
                        if (data == "true") {
                            aj("#info").text("取消收藏成功")
                        }else {
                            aj("#info").text("取消收藏失败")
                        }
                    }
                });
            }
            if(aj(obj).text()=="收藏"){
                aj(obj).text("取消收藏")
            }else {
                aj(obj).text("收藏")
            }
        }
    </script>
</body>
</html>
