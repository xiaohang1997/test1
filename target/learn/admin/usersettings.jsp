<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <style>
        #main{
            margin: 0 auto;
            background-color: rgba(223, 225, 226, 0.884);
            text-align: center;
            width: 300px;
        
        }
        table{
            margin: 0 auto;
            width: 218px;
            height: 219px;
        }
        p{
            margin: 10px;
        }
    </style>
</head>
<body>
<div id="main">
    <form action="" method="">
        <table>
            <tr>
                <td>
                    <p>个人信息修改</p>
                </td>
            </tr>
            <tr>
                <td>
                    <p>
                        <label for="name">名字：</label>
                        <input type="text" name="name" id="name" value="${user.name}">
                    </p>
                </td>
            </tr>
            <tr>
                <td>
                    <p>
                        <label for="loginName">昵称：</label>
                        <input type="text" name="loginName" id="loginName" value="${user.loginName}">
                    </p>
                </td>
            </tr>
            <tr>
                <td>
                    <p>
                        <label for="loginPwd">密码：</label>
                        <input type="text" name="loginPwd" id="loginPwd" value="${user.loginPwd}">
                    </p>
                </td>
            </tr>
            <tr>
                <td>
                    <p>
                        <label for="email">邮箱：</label>
                        <input type="text" name="email" id="email" value="${user.email}">
                    </p>
                </td>
            </tr>
            <tr>
                <td>
                    <p>
                        <label for="phone">电话：</label>
                        <input type="text" name="phone" id="phone" value="${user.phone}">
                    </p>
                </td>
            </tr>
            <tr>
                <td>
                    <p>
                        <label for="address">地址：</label>
                        <input type="text" name="address" id="address" value="${user.address}">
                    </p>
                </td>
            </tr>
            <tr>
                <td>
                    <p>
                        <label for="school">学校：</label>
                        <input type="text" name="school" id="school" value="${user.school}">
                    </p>
                </td>
            </tr>
            <tr>
                <td>
                    <p>
                        <label for="introduce">个人介绍：</label>
                        <input type="text" name="introduce" id="introduce" value="${user.introduce}">
                    </p>
                </td>
            </tr>
            <tr>
                <td>
                    <p>
                        <label></label>
                        <input type="submit" value="确认提交" id="goto">
                    </p>
                </td>
            </tr>
        </table>
        <input type="hidden" name="lastVideoUrl" value="${user.lastVideoUrl}">
        <input type="hidden" name="identify" value="${user.identify}">
    </form>
</div>
<script src="../js/jquery-1.12.4.js"></script>
<script>
    var aj = jQuery.noConflict();
    aj("#goto").click(function (event) {
        event.preventDefault();
        aj.ajax({
            url:"/learn/User?method=userUpdate",
            type:"post",
            data:aj("form").serialize(),
            dataType:'text',
            success:function (result) {
                if(result=="true"){
                    alert("修改成功")
                }else{
                    alert("修改失败")
                }
                window.location.href="/learn/User?method=findCourseDirectionLimit"
            },
            error:function () {
                alert("出现错误，请稍后再试")
            }
        })
    })
</script>
</body>
</html>