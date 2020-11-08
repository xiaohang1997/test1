<%--
  Created by IntelliJ IDEA.
  User: Illya
  Date: 2020/9/22
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
</head>
<body>
    <form>
        <p>
            <label>账号</label>
            <input type="text" name="loginName" id="loginName">
            <span id="loginNameInfo"></span>
        </p>
        <p>
            <label>密码</label>
            <input type="password" name="loginPwd" id="loginPwd">
            <span id="loginPwdInfo"></span>
        </p>
        <p>
            <label>验证码</label>
            <input type="text" id="inputPassword3" name="checkC" placeholder="请输入验证码">
            <img id="checkCodeImg" src="CheckCodeServlet" onclick="checkCode(this)"/>
        </p>
        <p>
            <label></label>
            <input type="submit" value="注册">
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
            if($("#loginPwd").val()!=""&&$("#loginName").val()!="") {
                $.ajax({
                    url: "/learn/User?method=register",
                    dataType: "text",
                    data: $("form").serialize(),
                    method: "post",
                    success: function (data) {
                        if (data == "true") {
                            alert("注册成功")
                        } else {
                            alert("注册失败")
                        }
                    }
                })
            }
        })
    </script>
</body>
</html>
