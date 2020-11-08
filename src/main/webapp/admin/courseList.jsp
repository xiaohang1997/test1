<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>课程</title>

    <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="../css/reset.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="../js/jquery-1.12.4.js"></script>
    <script type="text/javascript" src="../js/bootstrap.min.js"></script>
</head>
<body>
<!-- 头部-start -->
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
            <a href="#" class="header-nav-item"  style="margin-left:0px;font-size:14px;" id="userdetail">我的信息</a>
        </nav>
    </div>
</div>
<!-- 头部-end -->

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



<div class="f-main clearfix">
    <!-- 一级分类-start -->
    <div class="course-nav-row clearfix">
        <span class="hd">方向：</span>
        <ul class="course-nav">
            <li class="course-nav-item cur-course-nav">
                <a href="javascript:void(0)" onclick="showCourse(0)">全部</a>
            </li>
            <c:forEach items="${courseAllDirection}" var="var">
                <li class="course-nav-item">
                    <a href="javascript:void(0)" onclick="showCourse(${var.id})">${var.name}</a>
                </li>
            </c:forEach>
        </ul>
    </div>
    <!-- 一级分类-end -->

    <!-- 二级分类-start -->
    <div class="course-nav-row clearfix">
        <span class="hd">分类：</span>
        <ul class="course-nav" id="courseList">
            <li class="course-nav-item cur-course-nav">
                <a href="javascript:void(0)">全部</a>
            </li>
            <c:forEach items="${allCourse}" var="var">
                <li class="course-nav-item">
                    <a href="/learn/User?method=findChapterByCourseId&courseId=${var.id}">${var.name}</a>
                </li>
            </c:forEach>
        </ul>
    </div>
    <!-- 二级分类-end -->

    <!-- 课程章节列表-start -->
    <div class="types-block clearfix" style="padding:0px;">
        <div class="types-content clearfix" style="margin-bottom: 20px;">
            <c:choose>
                <c:when test="${empty allChapter}">
                    <c:forEach items="${chapter}" var="var">
                        <a href="/learn/User?method=findCourseByName&name=${var.course.name}">
                            <div class="course-card-container">
                                <div class="course-card-top green-bg">
                                    <span>课程名:${var.course.name}</span>
                                </div>

                                <div class="course-card-content">
                                    <p title="${var.course.introduce}">课程介绍：${var.course.introduce}</p>
                                    <h3 class="course-card-name">章节名：${var.name}</h3>
                                    <div class="course-card-bottom">
                                        <div class="course-card-info">${var.course.level}<span>·</span>${var.course.number}人在学</div>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${allChapter}" var="var">
                        <a href="/learn/User?method=findCourseByName&name=${var.course.name}">
                            <div class="course-card-container">
                                <div class="course-card-top green-bg">
                                    <span>课程名:${var.course.name}</span>
                                </div>

                                <div class="course-card-content">
                                    <p title="${var.course.introduce}">课程介绍：${var.course.introduce}</p>
                                    <h3 class="course-card-name">章节名：${var.name}</h3>
                                    <div class="course-card-bottom">
                                        <div class="course-card-info">${var.course.level}<span>·</span>${var.course.number}人在学</div>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </c:forEach>
                </c:otherwise>
            </c:choose>

        </div>

    </div>
    <!-- 课程列表-end -->
</div>

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

<script charset="utf-8" src="../js/login.js"></script><!--登录注册-->
<script charset="utf-8" src="../js/headTailAndCategory.js"></script><!--头像及课程分类-->
<script charset="utf-8" src="../js/show.js"></script><!--课程显示-->
<script type="text/javascript">
    var aj = jQuery.noConflict();
    //登录注册

    //头像及课程分类展示

</script>
</body>

</html>

