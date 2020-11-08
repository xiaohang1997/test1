<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>登录</title>
    <link href="../res/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="../js/jquery-1.12.4.js"></script>
    <script type="text/javascript" src="../js/bootstrap.min.js"></script>
</head>
<body>
    <form>
        <p>
            <label>账号</label>
            <input type="text" name="loginName" id="loginName" placeholder="请输入账号">
            <span id="loginNameInfo"></span>
        </p>
        <p>
            <label>密码</label>
            <input type="password" name="loginPwd" id="loginPwd" placeholder="请输入密码">
            <span id="loginPwdInfo"></span>
        </p>
        <p>
            <label>验证码</label>
            <input type="text" id="inputPassword3" name="checkC" placeholder="请输入验证码">
            <img id="checkCodeImg" src="/learn/CheckCodeServlet" onclick="checkCode(this)"/>
        </p>
        <p>
            <label></label>
            <input type="submit" value="登录">
        </p>
    </form>
    <script src="js/jquery-1.12.4.js"></script>
    <script>
        function checkCode(obj) {
            obj.src="CheckCodeServlet?"+new Date().getTime();
        }
        $("#loginName").blur(function () {
            if($("#loginName").val()==""){
                $("#loginNameInfo").text("账号不可为空")
                $("#loginNameInfo").css("color","red")
            }else {
                $("#loginNameInfo").text("输入合法")
                $("#loginNameInfo").css("color","green")
            }
        })
        $("#loginPwd").blur(function () {
            if($("#loginPwd").val()==""){
                $("#loginPwdInfo").text("密码不可为空")
                $("#loginPwdInfo").css("color","red")
            }else {
                $("#loginPwdInfo").text("输入合法")
                $("#loginPwdInfo").css("color","green")
            }
        })
        $(":submit").click(function (event) {
            event.preventDefault();
            if($("#loginPwd").val()!=""&&$("#loginName").val()!=""){
                $.ajax({
                    url:"/learn/User?method=login",
                    dataType:"text",
                    data:$("form").serialize(),
                    method:"post",
                    success:function (data) {
                        if(data=="true"){
                            alert("登陆成功")
                            window.location.href='Teacher?method=selectCourse'
                        }else if(data=="false"){
                            alert("账号或密码错误")
                        }else {
                            alert("验证码错误")
                        }
                    }
                })
            }
        })
    </script>
</body>
</html>
