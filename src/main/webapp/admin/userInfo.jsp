<%--
  Created by IntelliJ IDEA.
  User: Illya
  Date: 2020/9/27
  Time: 8:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>信息查看</title>
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
    <table>
        <tr>
            <td>姓名</td>
            <td>${userById.name}</td>
        </tr>
        <tr>
            <td>简介</td>
            <td>${userById.introduce}</td>
        </tr>
        <tr>
            <td>学校</td>
            <td>${userById.school}</td>
        </tr>
        <tr>
            <td>邮箱</td>
            <td>${userById.email}</td>
        </tr>
        <tr>
            <td>电话</td>
            <td>${userById.phone}</td>
        </tr>
    </table>
</body>
</html>
