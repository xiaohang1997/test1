<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>视频</title>

    <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="../css/reset.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="../js/jquery-1.12.4.js"></script>
    <script type="text/javascript" src="../js/bootstrap.min.js"></script>
    <style>
        #pageChange{
            display: inline-block;
            border: 1px solid blue;
            background-color: #5bc0de;
            margin: 2px;
            height: 20px;
            line-height: 20px;
        }
        #pageNumberChange{
            text-align: center;
            display: inline-block;
            border: 1px solid blue;
            background-color: #5bc0de;
            margin: 2px;
            width: 30px;
            height: 20px;
            line-height: 20px;
        }
    </style>
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

<div class="f-main clearfix">
    <div class="main-course-left">
        <div class="course-info">
            <div class="course-title" style="font-size: 24px;">${chapterById.name}</div>

            <div class="course-video">
                <video src="http://qgxypoyoj.hn-bkt.clouddn.com/${chapterById.video.url}" width="100%" height="100%" controls preload></video>
            </div>

            <div class="course-menu">
                <a  href="javascript:void(0)"><span class="menu-item  cur">评论</span></a>
            </div>
        </div>

        <!-- 评论-start -->
        <div>
            <c:forEach items="${page.list}" var="content">
                <div class="comment clearfix">
                    <div class="comment-header"><img class="lecturer-uimg" src="../images/header.jpg"></div>
                    <div class="comment-main">
                        <div class="user-name">${content.user.name}</div>
                        <div class="comment-content">${content.content}</div>
                        <div class="comment-footer">
                            <span>${content.createTime} </span>
                            <a href="">评论章节：${chapterById.name}</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <!--分页-->
        <div>
            <ul>
                <li id="pageChange"><a href="/learn/content.do?method=contentList&pNumber=${(page.nowPageNumber-1)<1?page.nowPageNumber:(page.nowPageNumber-1)}&&id=${chapterById.id}">上一页</a></li>
                <c:forEach items="${page.pageNumber}" var="var">
                    <li id="pageNumberChange">
                        <a href="/learn/content.do?method=contentList&pNumber=${var}&id=${chapterById.id}">${var}</a>
                    </li>
                </c:forEach>
                <li id="pageChange"><a href="/learn/content.do?method=contentList&pNumber=${(page.nowPageNumber+1)>page.pages?page.nowPageNumber:(page.nowPageNumber+1)}&id=${chapterById.id}">下一页</a></li>
                <li id="pageChange"><a href="/learn/content.do?method=contentList&pNumber=${page.pages}&id=${chapterById.id}">尾页</a></li>
            </ul>
        </div>

        <!-- 发布评论-start -->
        <div style="margin-top: 20px;">
            <div>
                <span id="commentTitle">发布评论：</span>
                <span id="commentTip" style="margin-left: 10px;color:#777;">长度小于200</span>
            </div>
            <form id="commentForm" action="${s.base}/courseComment/doComment.html" method="post"  style="margin: 5px 0px;">
                <input type="hidden" id="commentType" name="type" value="0"/>
                <input type="hidden" name="sectionId" value="${(courseSection.id)}"/>
                <input type="hidden" name="courseId" value="${(courseSection.courseId)}"/>

                <textarea id="content" name="content" rows="" cols="100"></textarea>
                <div>
                    <input id="indeityCode" name="indeityCode"  type="text" placeholder="请输入验证码">
                    <img id="indeityImg" src="/learn/CheckCodeServlet" onclick="checkCode(this)" style="width:80px;height:26px;margin-left:10px;margin-top:-3px;"/>
                </div>
            </form>
            <input type="button" value="发布" class="btn" onclick="doComment(${chapterById.video.id});">
        </div>

        <!-- 评论-end -->
    </div>

    <!-- 章节-start -->
    <div class="main-course-right"  >
        <h4 class="mt-50">本课程所有章节</h4>
        <div class="video-course-fix-parent">
            <div class="video-course-fix">
                <div class="chapter" style="padding: 0px ;border: none;">
                    <a href="javascript:void(0);" class="js-open">
                        <h3>
                            <strong>${chapterById.course.name}</strong>
                            <span class="drop-down">▼</span>
                        </h3>
                    </a>
                    <c:forEach items="${chapterByCourseId}" var="var">
                        <ul class="chapter-sub" style="padding-left:10px;">
                            <a href="/learn/content.do?method=contentList&id=${var.id}" >
                                <li class="ellipsis video-li"><i class="icon-video">▶</i> ${var.name}</li>
                            </a>
                        </ul>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
    <!-- 章节-end -->
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
            <span>©&nbsp;2017&nbsp; 备案 </span>
        </div>
    </div>
</div>
<!-- 页脚-end-->
<script src="../js/login.js"></script>
<script src="../js/headTailAndCategory.js"></script>
<script type="text/javascript">
    var aj = jQuery.noConflict();
    function checkCode(obj) {
        obj.src="/learn/CheckCodeServlet?"+new Date().getTime();
    }
    aj(function(){
        aj('.chapter li').hover(function(){
            aj(this).find('.icon-video').css('color','#0089D2');
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
<script type="text/javascript">
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
        aj(".btn").click(function (event) {
            event.preventDefault();
        })
        function doComment(id){
            aj.ajax({
                url:"/learn/content.do?method=addContent",
                type:'post',
                data:{
                    'content':aj("#content").val(),
                    'videoId':id,
                    'checkC':aj("#indeityCode").val(),
                },
                dataType:'text',
                success:function (result) {
                    if(result=="true"){
                        aj("#content").val("")
                        aj("#indeityCode").val("")
                        alert("添加成功")
                    }else {
                        alert("-添加成功-")
                    }
                },
                error:function () {
                    alert("系统异常")
                }
            })
        }
</script>
</body>
</html>
