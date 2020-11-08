<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html >
<head>
    <meta  charset="UTF-8">
    <title>Title</title>
<!--    <link rel="stylesheet" href="./css/order.css">-->
    <style>
        *{
            padding: 0px;
            margin: 0px;
        }
        #container{
            width: 1152px;
            margin: 36px auto;


            font: 14px/2em "PingFang SC","微软雅黑","Microsoft YaHei",Helvetica,"Helvetica Neue",Tahoma,Arial,sans-serif;
        }
        body{

            color: #5e5e5e;
            font: 14px/2em "PingFang SC","微软雅黑","Microsoft YaHei",Helvetica,"Helvetica Neue",Tahoma,Arial,sans-serif;
        }
        .top{
            width:100%;
            height: 100px;

        }
        .left{
            margin-right: 32px;
            width: 128px;
            box-sizing: border-box;
            float: left;
        }
        div{
            display: block;
        }

        .main{



        }
        ul li{
            list-style: none;
        }
        li ul li{
            text-indent: 15px;
        }
        a{

            background-color: transparent;
            text-decoration: none;
            color: black;
            margin: 15px;
        }
       a:hover{

           background: #4d555d;
           border-radius: 20px;
           color: white;

       }

        .slider{

            padding-bottom: 16px;
            font-size: 14px;
            color: #4d555d;
            line-height: 32px;
            border-bottom: 1px solid #d9dde1;
        }
        .nav-left{

            width: 100%;
            list-style: none;
        }
        .nav-left li{

            margin-top: 16px;
            width: 100%;
            height: 32px;
            line-height: 32px;
            box-sizing: border-box;
            cursor: pointer;
            font-size: 14px;
            color: #4d555d;
        }
        .nav-left span{

            font-size: 16px;
            line-height: 32px;
            font-family: 'imv2' !important;
            float: right;

            font-style: normal;
            font-weight: normal;
            font-variant: normal;
            text-transform: none;
            line-height: 1;
            letter-spacing: 0;
            -webkit-font-feature-settings: "liga";
            -moz-font-feature-settings: "liga=1";
            -moz-font-feature-settings: "liga";
            -ms-font-feature-settings: "liga" 1;
            font-feature-settings: "liga";
            -webkit-font-variant-ligatures: discretionary-ligatures;
            font-variant-ligatures: discretionary-ligatures;
            -webkit-font-smoothing: antialiased;
            -moz-osx-font-smoothing: grayscale;
        }
        h1{

            font-weight: 400;
            font: 14px/2em "PingFang SC","微软雅黑","Microsoft YaHei",Helvetica,"Helvetica Neue",Tahoma,Arial,sans-serif;
        }
    .all{

        margin-right: 24px;
        float: left;
        font-size: 16px;
        color: #07111b;
        line-height: 32px;
        font-weight: 700;
        position: absolute;
        left: 343px;
        top: 35px;
    }
        .order_nav{

            list-style-type: none;
            left: 500px;
            list-style-type: none;
            position: absolute;
            top: 36px;
        }
        .order_nav a{
            margin-left: 15px;
            margin-right: 69px;
            line-height: 32px;
            text-align: center;
            font-size: 15px;
        }


    </style>
</head>
<body>
 <div id="container">
   <div class="top">
       <h1 class="slider">订单中心</h1>
      <h2 class="all">我的订单</h2>

           <li class="order_nav">


                 <a  id="mo" href="order_list.jsp?type=0" >全部</a>
                  <a class="order_nav-1" href="order_list.jsp?type=1">未支付</a>
                   <a class="order_nav-2" href="order_list.jsp?type=2">已完成</a>
                   <a class="order_nav-3" href="order_list.jsp?type=3">已失效</a>
                   <a class="order_nav-4" href="order_list.jsp?type=4">订单回收站</a>



           </li>



   </div>
   <div class="left">
      <ul class="nav-left">
          <li><p>我的订单></p> <span></span></li>
          <li> <p>我的余额></p><span></span></li>
          <li><p>课程资讯></p><span></span></li>
          <li><p>消费记录></p><span></span></li>
      </ul>
       
   </div>

   <div class="main">
     <span id="kong" ></span>

   </div>
     <a href='User?method=findCourseDirectionLimit'>点击返回首页</a>
 </div>




 <script src="./js/jquery-3.5.1.js"> </script>
 <script src="./js/tools.js"></script>
 <script src="./js/nav.js"></script>
 <script src="./js/order.js"></script>



 <script language="javascript" type="text/javascript">




     $(document).ready(function(){

         $("#mo").focus();

        $("#mo").click()


     });
 </script>



</body>
</html>