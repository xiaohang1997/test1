<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>首页</title>
</head>
<body>
    <c:choose>
        <c:when test="${empty user}">
            <h2>请<a href="../login.jsp">登录</a></h2>
        </c:when>
        <c:otherwise>
            <h2>欢迎${user.name},<a href="/learn/User?method=exit">退出</a></h2>
        </c:otherwise>
    </c:choose>
    <table>
        <thead>
            <tr>
                <c:forEach items="${courseDirection}" var="var">
                    <td>
                            <a href="javascript:void(0)" onclick="show(${var.id})">${var.name}</a>
                    </td>
                </c:forEach>
            </tr>
        </thead>
        <tbody></tbody>
    </table>
    <script src="../js/jquery-1.12.4.js"></script>
    <script>
        var aj = jQuery.noConflict();
        function show(id) {
            var text1="<tr><td>课程名</td><td>讲师</td><td>简介</td><td>价格</td>" +
                "<td>观看人数</td><td>课程难度</td><td>课程时长</td><td>创建时间</td></tr>";
            aj("tbody").html(text1);
            aj.ajax({
                url:"/learn/User?method=findCourseByCourseDirectionId",
                data:"id="+id,
                dataType:"json",
                method:"post",
                scriptCharset:"utf-8",
                success:function (data) {
                        for(var i=0;i<data.length;i++){
                            var text2="<tr><td>"+data[i].name+"</td><td>"+data[i].user.name+"</td><td>"+data[i].introduce+"</td><td>"
                                +data[i].price+"</td><td>"+data[i].number+"</td><td>"+data[i].level+"</td><td>"+data[i].courseTime+
                                "</td><td>"+data[i].createTime+"</td></tr>"
                            aj("tbody").append(text2);
                        }
                    }
            })
        }
    </script>
</body>
</html>
