<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>我的收藏</title>
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
<div id="main">
        <table>
            <thead>
                <tr>
                    <td>课程名</td>
                    <td>课程简介</td>
                    <td>课程价格</td>
                    <td>操作</td>
                </tr>
            </thead>
            <tbody id="clTby">
                <c:forEach items="${collectionList}" var="var">
                    <tr>
                        <td><a href="/learn/User?method=findCourseByName&name=${var.course.name}">${var.course.name}</a></td>
                        <td>${var.course.introduce}</td>
                        <td>${var.course.price}</td>
                        <td><a href="javascript:;" onclick="delCollections(${var.course.id})">${var.course.id}移除收藏</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
</div>
<script src="../js/jquery-1.12.4.js"></script>
<script>
    var aj = jQuery.noConflict();
    function delCollections(id) {
        aj.ajax({
            url:"/learn/Collection?method=deleteCollection",
            method:"post",
            dataType:"text",
            data:"id="+id,
            success:function (data) {
                if(data=="true"){
                    alert("移除成功")
                }else {
                    alert("-移除失败-")
                }
            },
            error:function () {
                alert("出现错误")
            }
        })
    }
</script>
</body>
</html>
