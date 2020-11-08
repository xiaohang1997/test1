<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加章节</title>
    <style>
        form{
            margin: auto;
        }
    </style>
</head>
<body>
    <%
        String id = (request.getParameter("id"));
    %>
    <form method="post" action="/learn/YunUploadServlet" enctype="multipart/form-data">
        <input type="hidden" name="courseId" id="courseId" value="<%=id%>">
        <p>
            <label>章节名字</label>
            <input type="text" name="name" id="name">
        </p>
        <p>
            <label>视频文件</label>
            <input type="file" name="video" id="video">
        </p>
        <p>
            <label></label>
            <input type="submit" value="添加">
        </p>
    </form>
</body>
</html>
