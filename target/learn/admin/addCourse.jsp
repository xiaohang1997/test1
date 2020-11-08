<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>添加课程</title>
    <style>
        form{
            margin: auto;
        }
    </style>
</head>
<body>
    <form>
        <p>
            <label>课程名字</label>
            <input type="text" name="name" id="name">
        </p>
        <p>
            <label>课程方向</label>
            <select name="direction">
                <c:forEach var="var" items="${courseAllDirection}">
                    <option value="${var.name}">${var.name}</option>
                </c:forEach>

            </select>
        </p>
        <p>
            <label>课程简介</label>
            <textarea name="introduce" id="introduce"></textarea>
        </p>
        <p>
            <label>课程价格</label>
            <input type="text" name="price" id="price">
        </p>
        <p>
            <label>观看人数</label>
            <input type="text" name="number" id="number">
        </p>
        <p>
            <label>课程难度</label>
            <select name="level">
                <option value="1">简单</option>
                <option value="2">一般</option>
                <option value="3">困难</option>
            </select>
        </p>
        <p>
            <label>课程时长</label>
            <input type="text" name="courseTime" id="courseTime" placeholder="单位：分">
        </p>
        <p>
            <label></label>
            <input type="submit" value="添加">
        </p>
    </form>
    <p id="info"></p>
    <script src="../js/jquery-1.12.4.js"></script>
    <script>
        $(document).ready(function () {
            $(":submit").click(function (event) {
                event.preventDefault();
                $.ajax({
                    url:"/learn/Teacher?method=addCourse",
                    datatype:"text",
                    data:$("form").serialize(),
                    method:"post",
                    success:function (data) {
                        if(data=="true"){
                            $("#info").text("添加成功")
                        }
                    }
                });
            });
        });
    </script>
</body>
</html>
