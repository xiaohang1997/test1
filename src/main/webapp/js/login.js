var aj = jQuery.noConflict();
function login(){
    aj('#loginTitle').css('color','#337Ab7');
    aj('#loginForm').show();
    aj('#registeTitle').css('color','#000');
    aj('#registeForm').hide();
}
function registe(){
    aj('#loginTitle').css('color','#000');
    aj('#loginForm').hide();
    aj('#registeTitle').css('color','#337Ab7');
    aj('#registeForm').show();
}
function userExit() {
    aj.ajax({
        url:"/learn/User?method=exit",
        dataType:"text",
        method:"post",
        success:function (data) {
            if(data=="true"){
                alert("退出成功")
            }else {
                alert("系统异常")
            }
            window.location.href='/learn/User?method=findCourseDirectionLimit'
        }
    })
}
function checkCode(obj) {
    obj.src="/learn/CheckCodeServlet?"+new Date().getTime();
}
aj("#username").blur(function () {
    if(aj("#username").val()==""){
        aj("#usernameInfo").text("账号不可为空")
        aj("#usernameInfo").css("color","red")
    }else {
        aj("#usernameInfo").text("输入合法")
        aj("#usernameInfo").css("color","green")
    }
})
aj("#password").blur(function () {
    if(aj("#password").val()==""){
        aj("#passwordInfo").text("密码不可为空")
        aj("#passwordInfo").css("color","red")
    }else {
        aj("#passwordInfo").text("输入合法")
        aj("#passwordInfo").css("color","green")
    }
})
aj("#loginBtn").click(function (event) {
    event.preventDefault();
    if(aj("#password").val()!=""&&aj("#username").val()!=""){
        if((document.getElementById("checkbox1").checked)==true){
            aj.ajax({
                url:"/learn/User?method=againLogin",
                dataType:"text",
                data:aj("#loginForm").serialize(),
                method:"post",
                success:function (data) {
                    if(data=="true"){
                        alert("登陆成功")
                    }else if(data=="false"){
                        alert("账号,密码错误或身份不匹配")
                    }else {
                        alert("验证码错误")
                    }
                    window.location.href='/learn/User?method=findCourseDirectionLimit'
                }
            })
        }else {
            aj.ajax({
                url:"/learn/User?method=login",
                dataType:"text",
                data:aj("#loginForm").serialize(),
                method:"post",
                success:function (data) {
                    if(data=="true"){
                        alert("登陆成功")
                    }else if(data=="false"){
                        alert("账号或密码错误")
                    }else {
                        alert("验证码错误")
                    }
                    window.location.href='/learn/User?method=findCourseDirectionLimit'
                }
            })
        }

    }
})
aj("#registeBtn").click(function (event) {
    event.preventDefault();
    if(aj("#rpassword").val()!=""&&aj("#rusername").val()!=""){
        aj.ajax({
            url:"/learn/User?method=register",
            dataType:"text",
            data:aj("#registeForm").serialize(),
            method:"post",
            success:function (data) {
                if(data=="true"){
                    alert("注册成功")
                }else if(data=="false"){
                    alert("注册失败")
                }else {
                    alert("验证码错误")
                }
                window.location.href='/learn/User?method=findCourseDirectionLimit'
            }
        })
    }
})