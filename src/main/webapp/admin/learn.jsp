<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学习课程</title>

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
    <!-- 基本信息 -->
    <div class="main-course-left">
        <div class="course-info">
            <div class="course-title">${courseByName.name}</div>

            <div class="course-meta">
                <c:choose>
                    <c:when test="${empty user.lastVideoUrl}">
                        <a href="/learn/content.do?method=contentList&id=${sessionScope.chapterByCourseId[0].id}" class="learn-btn" >开始学习</a>
                    </c:when>
                    <c:otherwise>
                        <a href="video.jsp?url=${user.lastVideoUrl}" class="learn-btn" >继续学习</a>
                        <div class="static-item"  >
                            <div class="meta">上次学到</div>
                            <div class="meta-value">${user.lastVideoUrl}</div>
                        </div>
                    </c:otherwise>
                </c:choose>
                <div class="static-item"  >
                    <div class="meta">学习次数</div>
                    <div class="meta-value">${courseByName.number}</div>
                </div>
                <div class="static-item">
                    <div class="meta">难度级别</div>
                    <div class="meta-value">${courseByName.level}</div>
                </div>
                <div class="static-item" style="border:none;">
                    <div class="meta">课程时长</div>
                    <div class="meta-value">${courseByName.courseTime}</div>
                </div>
                <a onclick="changeCollect(this,${courseByName.id})" href="javascript:void(0)" class="following" style="float: right;margin-top:5px;" title="收藏">
                </a>
            </div>

            <div class="course-brief">
                ${courseByName.introduce}
            </div>

            <div class="course-menu">
                <span class="menu-item cur">章节</span>
            </div>
        </div>
        <!-- 基本信息-end -->

        <!-- 课程章节 - start -->
        <div>
            <c:forEach items="${chapterByCourseId}" var="var">
                <div class="chapter">
                    <a href="javascript:void(0);" class="js-open">
                        <h3>
                            <strong><div class="icon-chapter">=</div> ${var.name}</strong>
                            <span class="drop-down">▼</span>
                        </h3>
                    </a>
                    <ul class="chapter-sub">
                        <a id="learnVideo" href="javascript:;" onclick="saveLastUrl(${var.video.id},${var.id})">
                            <li class="chapter-sub-li">
                                <i class="icon-video">▶</i> 学习视频：${var.video.id}
                            </li>
                        </a>
                    </ul>
                </div>
            </c:forEach>

        </div>
        <!-- 课程章节 - end -->
    </div>

    <!-- 教师信息&推荐课程 - start -->
    <div class="main-course-right"  >

        <div class="lecturer-item" style="width: 100%;">
            <img class="lecturer-uimg" src="../images/header.jpg">
            <span class="lecturer-name">${courseByName.user.name}</span>
            <span class="lecturer-title">${courseByName.user.school}</span>
            <span class="lecturer-p" >${courseByName.user.introduce}</span>
        </div>

        <h4 class="mt-50">该老师其他课程</h4>
        <c:forEach items="${courseByTeacher}" var="var">
            <a href="/learn/User?method=findCourseByName&name=${var.name}" class="mb-5"><li class="ellipsis oc-color-hover">${var.name}</li></a>
        </c:forEach>
    </div>
    <!-- 教师信息&推荐课程 - end -->

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
<script charset="utf-8" src="../js/login.js"></script><!--登录注册-->
<script charset="utf-8" src="../js/headTailAndCategory.js"></script><!--头像及课程分类-->
<script charset="utf-8" src="../js/change.js"></script><!--收藏-->
<script type="text/javascript">
    var aj = jQuery.noConflict();
    //保存观看视频记录
    aj("#learnVideo").click(function (event) {
        alert("1")
        event.preventDefault();
    })
    function saveLastUrl(videoId,id){
        aj.ajax({
            url:"/learn/User?method=saveLastUrl",
            data:"videoId="+videoId,
            dataType:"text",
            method:"post",
            success:function (data) {
                window.location.href="/learn/content.do?method=contentList&id="+id
            },
            error:function () {
                alert("error!")
            }
        })
    }

    aj(function(){
        aj('.chapter li').hover(function(){
            aj(this).find('.icon-video').css('color','#FFF');
        },function(){
            aj(this).find('.icon-video').css('color','#777');
        });

        aj('.js-open').click(function(){
            var display = aj(this).parent().find('ul').css('display');
            if(display == 'none'){
                aj(this).parent().find('ul').css('display','block');
                aj(this).find('.drop-down').html('▼');
            }else{
                aj(this).parent().find('ul').css('display','none');
                aj(this).find('.drop-down').html('▲');
            }
        });
    });
</script>

</body>

</html>

