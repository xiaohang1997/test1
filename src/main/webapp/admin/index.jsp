<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>

    <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="../css/reset.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="../js/jquery-1.12.4.js"></script>
    <script type="text/javascript" src="../js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../js/echarts.min.js"></script><!--图表插件-->
</head>
<body>
<div class="f-header">
    <div class="f-header-box clearfix">
        <a href=".."  class="logo" title="网校在线学习平台"></a>
        <nav class="header-nav">
            <a href="./index.jsp" class="header-nav-item">首 页</a>
            <a href="/learn/User?method=findChapter" class="header-nav-item">课 程</a>
        </nav>

        <nav class="header-nav" style="float:right">
            <c:choose>
                <c:when test="${empty user}">
                    <a href="#myModal" class="header-nav-item"  data-toggle="modal" onclick="login();"  style="margin-right:0px;font-size:14px;">登录/注册</a>
                </c:when>
                <c:otherwise>
                    <a href="#myModal" class="header-nav-item"  data-toggle="modal" onclick="userExit();"  style="margin-right:0px;font-size:14px;">欢迎${user.name},退出</a>
                </c:otherwise>
            </c:choose>
            <a href="#" data-toggle="popover"  class="header-nav-item"  style="margin-left:0px;font-size:14px;" id="userdetail">我的信息</a>
        </nav>
    </div>
</div>

<!-- 登录注册-start -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"  style="position:fixed; top:30%;">
    <div class="modal-dialog" role="document">
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true" style="font-size:18px;">×</span>
                </button>
                <h4 class="modal-title"  id="loginTitle"  style="float: left; color: #337Ab7;cursor: pointer; " onclick="login();">登 录</h4>
                <h4 class="modal-title"  id="registeTitle"  style="float: left;margin-left: 20px;cursor: pointer;" onclick="registe();">注 册</h4>
                <div class="clearfix"></div>
            </div>

            <div class="modal-body">
                <form id="loginForm" class="form-horizontal" style="padding: 0px 20px;">
                    <div class="form-group">
                        <input type="email" class="form-control" name="username" id="username"  placeholder="用户名">
                    </div>
                    <div class="form-group help">
                        <input type="password" class="form-control" name="password" id="password"  placeholder="密　码">
                    </div>
                    <div class="form-group help">
                        <input type="text" class="form-control" name="checkC" id="checkC"  placeholder="验证码">
                        <img id="checkCodeImg" src="/learn/CheckCodeServlet" onclick="checkCode(this)"/>
                    </div>
                    <div class="form-group help">
                        <input type="radio" name="identify" value="0" checked>用户
                        <input type="radio" name="identify" value="1">讲师
                    </div>
                    <div class="form-group">
                        <label>
                            <input type="checkbox" value="None" id="checkbox1" name="check">
                            <span class="text" style="color: #787D82;font-size: 14px;">下次自动登录</span>
                        </label>
                    </div>

                    <a href="javascript:void(0)">
                        <div class="header-login-btn" id="loginBtn">登 录</div>
                    </a>
                </form>

                <form id="registeForm" class="form-horizontal" style="padding: 0px 20px;display: none;">
                    <div class="form-group">
                        <input type="email" class="form-control" name="rusername" id="rusername"  placeholder="用户名">
                    </div>
                    <div class="form-group help">
                        <input type="password" class="form-control" name="rpassword" id="rpassword"  placeholder="密　码">
                    </div>
                    <div class="form-group help">
                        <input type="text" class="form-control" name="rcheckC" id="rcheckC"  placeholder="验证码">
                        <img id="rcheckCodeImg" src="/learn/CheckCodeServlet" onclick="checkCode(this)"/>
                    </div>
                    <div class="form-group help">
                        <input type="radio" name="ridentify" checked value="0">用户
                        <input type="radio" name="ridentify" value="1">讲师
                    </div>
                    <a href="javascript:void(0)">
                        <div class="header-login-btn" id="registeBtn">注 册</div>
                    </a>
                </form>

            </div>

        </div>
    </div>
</div>
<!-- 登录注册-end -->

<div class="f-main">
    <!-- 轮播 分类-start -->
    <div class="clearfix">
        <div class=main-category>
            <div class="main-bg">
                <div class="main-bg-item " style="background-image: url('../images/c1.jpg'); ">
                    <div class="main-title-1"></div>
                </div>

                <div class="main-bg-item " style="background-image: url('../images/c2.jpg'); ">
                    <div class="main-title-2"></div>
                </div>

                <div class="main-bg-item " style="background-image: url('../images/c3.jpg'); ">
                    <div class="main-title-3"></div>
                </div>
            </div>

            <div class="f-nav-box">
                <div class="bg-nav">
                    <a class="cur"></a> <a></a> <a></a>
                </div>
            </div>

            <div class="main-category-menu">
                <c:forEach items="${courseDirection}" var="var">
                    <div class="category" c-id="a">
                        <div class="group"><a href="javascript:void(0)" onmouseover="showCourseLimit(${var.id})" onclick="show(${var.id})">${var.name}</a></div>
                    </div>
                </c:forEach>
            </div>

            <div class="main-category-submenu-content"  id="a" >

            </div>
        </div>
    </div>
    <!-- 轮播 分类-end -->

    <!-- 实战推荐-start -->
    <div class="types-block clearfix">
        <h3 class="types-title">实战推荐</h3>
        <div class="types-content">
            <c:forEach items="${courseByPrice}" var="var">
                <a href="/learn/User?method=findCourseByName&name=${var.name}">
                    <div class="course-card-container">
                        <div class="course-card-top green-bg">
                            <span>${var.courseDirection.name}</span>
                        </div>

                        <div class="course-card-content">
                            <h3 class="course-card-name">${var.name}</h3>
                            <p title="${var.introduce}">${var.introduce}</p>
                            <div class="course-card-bottom">
                                <div class="course-card-info">${var.level}<span>·</span>${var.number}人在学</div>
                                <div class="course-card-price">￥${var.price}元</div>
                            </div>
                        </div>
                    </div>
                </a>
            </c:forEach>
        </div>
    </div>
    <!-- 实战推荐-end -->

    <!-- 免费好课-start -->
    <div class="types-block clearfix">
        <h3 class="types-title">免费好课</h3>
        <div class="types-content">
            <c:forEach items="${courseByFreePrice}" var="var">
                <a href="/learn/User?method=findCourseByName&name=${var.name}">
                    <div class="course-card-container">
                        <div class="course-card-top green-bg">
                            <span>${var.courseDirection.name}</span>
                        </div>

                        <div class="course-card-content">
                            <h3 class="course-card-name">${var.name}</h3>
                            <p title="${var.introduce}">${var.introduce}</p>
                            <div class="course-card-bottom">
                                <div class="course-card-info">${var.level}<span>·</span>${var.number}人在学</div>
                            </div>
                        </div>
                    </div>
                </a>
            </c:forEach>
        </div>
    </div>
    <!-- 免费好课-end -->

    <!-- java课程-start -->
    <div class="types-block clearfix" id="EChartsMain" style="width: 800px;height: 400px">

    </div>
    <!-- java课程-end -->

    <!--名校讲师-start -->
    <div class="types-block clearfix">
        <h3 class="types-title">名校讲师</h3>
        <c:forEach items="${teacher}" var="var">
            <a href="/learn/User?method=findUserBy&id=${var.id}">
                <div class="lecturer-card-container">
                    <div class="lecturer-item">
                        <img class="lecturer-uimg" src="../images/header.jpg">  <!--头像url-->
                        <span class="lecturer-name">${var.name}</span>
                        <span class="lecturer-title">${var.school}</span>
                        <span class="lecturer-p" >${var.introduce}</span>
                    </div>
                </div>
            </a>
        </c:forEach>
    </div>
    <!--名校讲师-end -->
</div>


<!-- 页脚-start -->
<div class="f-footer">
    <div class="f-footer-box clearfix">
        <div class="footer-link">
            <a href="javascript:void(0);"  target="_blank" title="企业合作">企业合作</a>
            <a href="javascript:void(0);" target="_blank" title="联系我们">联系我们</a>
            <a href="javascript:void(0);" target="_blank" title="常见问题">常见问题</a>
            <a href="javascript:void(0);" target="_blank" title="意见反馈">意见反馈</a>
            <a href="javascript:void(0);" target="_blank" title="友情链接">友情链接</a>
        </div>
        <div class="footer-copyright">
            <span> 三组 </span>
        </div>
    </div>
</div>
<!-- 页脚-end-->

</body>
<script charset="utf-8" src="../js/show.js"></script><!--展示相关课程-->
<script charset="utf-8" src="../js/login.js"></script><!--登录注册-->
<script charset="utf-8" src="../js/headTailAndCategory.js"></script><!--头像及课程分类-->
<script>
    var aj = jQuery.noConflict();
    //展示相关方向的课程

    //登录注册

    //头像及课程分类展示

    //轮播
    aj(function(){
        var index = 0;
        var timer = 4000;
        aj('.bg-nav a').click(function(){
            index = aj('.bg-nav a').index(aj(this));
            rollBg(index);
        });
        aj('.index-roll-item').click(function(){
            index = aj('.index-roll-item').index(aj(this));
            rollBg(index);
        });
        var rollBg = function(i){
            aj('.main-bg-item').fadeOut(1000);
            aj(aj('.main-bg-item')[i]).fadeIn(1000);
            aj('.bg-nav a').removeClass('cur');
            aj(aj('.bg-nav a')[i]).addClass('cur');
            aj('.index-roll-item').removeClass('cur');
            aj(aj('.index-roll-item')[i]).addClass('cur');
        }
        setInterval(function(){
            index += 1;
            index = index%3;
            rollBg(index);
        }, timer);

    });
    aj.ajax({
        url: '/learn/course.do?method=showNumber',
        type: 'post',
        dataType: 'json',
        success: function (result) {
            //初始化Echarts对象
            var myChart = echarts.init(document.getElementById("EChartsMain"));
            //指定图表的配置项和数据
            var option = {
                //设置图表的标题
                title:{
                    text:"观看次数最多的五门课"
                },
                //图例
                legend:{
                    data:["观看人数"]
                },
                //x轴数据
                xAxis:{
                    data:[result[0].name,result[1].name,result[2].name,result[3].name,result[4].name,result[5].name]
                },
                //y轴数据
                yAxis:{},
                //根据相应系列进行配置
                series:[
                    {
                        name:'次数',
                        type:'bar',
                        data:[result[0].number,result[1].number,result[2].number,result[3].number,result[4].number,result[5].number]
                    }
                ]
            }
            //渲染图表
            myChart.setOption(option);
        },
        error: function () {
            alert('系统异常');
        }
    })
</script>
</html>
