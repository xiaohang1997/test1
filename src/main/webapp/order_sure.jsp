<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html >
<head>
    <meta charset="UTF-8">
    <title>确认订单</title>
    <style>
        *{
            padding: 0px;
            margin: 0px;
        }
        #container{
            width:1500px;
            height:  800px;
            margin: 0px auto;
            background: #fff;
            color: #5e5e5e;
            font: 14px/2em "PingFang SC","微软雅黑","Microsoft YaHei",Helvetica,"Helvetica Neue",Tahoma,Arial,sans-serif;

        }
        div {
            display: block;
        }
        body {
            background: #fff;
            color: #5e5e5e;
            font: 14px/2em "PingFang SC", "微软雅黑", "Microsoft YaHei", Helvetica, "Helvetica Neue", Tahoma, Arial, sans-serif
        }
        .top{
            width:100%;
            height: 160px;

            background-image: url("./images/cart-header-bg.jpg");
        }

        .main{
            width: 1152px;
            padding: 0 36px 32px;
            height: 480px;
            background-color: #fff;
            margin-top: -40px;
            margin-left: auto;
            margin-right: auto;
            box-shadow: 0 8px 16px 0 rgba(7,17,27,.1);
            border-radius: 8px;
            box-sizing: border-box;

        }
        .sure{


            font-size: 32px;
            line-height: 115px;
            margin-right: 25px;
            color: #07111b;
            font-weight: 200;
            left: 210px;
            position: absolute;
        }
        .inform{

            margin-left: 12px;
            color: #07111b;
            font-size: 16px;
            line-height: 16px;
        }
        .title-box{

            padding-top: 36px;
            padding-bottom: 24px;
        }
        .title-box a {
            color: #93999f;
            font-size: 12px;
            line-height: 16px;
        }
        a {
            background-color: transparent;
        }
        .r{
            top: 155px;
            text-decoration: none;
            position: absolute;
            right: 227px;
        }
        .center-box  p{

            padding: 24px;
            box-sizing: border-box;
            background: #f3f5f7;
            margin-bottom: 1px;
            outline: none;




        }
        .foot{


            margin-left: 12px;
            color: #07111b;
            font-size: 16px;
            line-height: 130px;
        }
        .submit1 input{
            border-style: solid;
            border-radius: 12px;
            border-width: 8px;
            cursor: pointer;
            -weibkit-transition: all .3s;
            -moz-transition: all .3s;
            transition: all .3s;
            /* color: #fff; */
            background-color: #f20d0d;
            border-color: #f20d0d;
            opacity: 1;
            color: white;
            font-size: 15px;
            position: absolute;
            right: 224px;

        }
        .money{

            color: #f01414;
            font-size: 24px;
        }
        .method{
            position: absolute;
            top: 450px;
            right: 200px;

        }
        .method li{

            float: left;
            padding: 0 12px;
            height: 26px;
            line-height: 26px;
            border: 1px solid #f01414;
            border-radius: 18px;
            font-size: 12px;
            color: #f01414;
            margin: 27px 20px 27px 0;
            list-style-type: none;



        }
    </style>
</head>
<body>
    <div id="container">

    <div class="top">   <h1 class="sure"> 确认订单</h1></div>
    <div class="main">


        <form action="" method="">
            <div class="title-box">

                <p class="inform">商品信息</p>
                <a  class=" r" href="#">我有疑问,需要反馈?</a>

            </div>

            <div class="center-box">
            <p>
                <label for="name">课程名：</label>
                <input type="text"  style="border:0"  name="name" id="name" value="${course.name}" disabled>

            </p>
            <p>
                <label for="introduce">课程描述：</label>
                <input type="text"   style="border:0"  name="introduce" id="introduce" value="${course.introduce}" disabled>
            </p>
            <p>
                <label for="price">课程金额：</label>
                <input type="text"  style="border:0 "   name="price" id="price" value="${course.price}"  disabled>
            </p>
            </div>
            <span class="foot">应付总金额： </span>


            <span  class="money"  >  <em>￥</em> ${course.price} <em>.00</em></span>

                <ul class=" method">
                    <li>支持花呗</li>
                    <li>可开发票</li>
                    <li>不可退款</li>
                </ul>



            <p class="submit1">
                <label></label>
                <input type="submit"  style="font-size: larger" value="提交订单">
            </p>


        </form>

    </div>

    </div>

    <script src="./js/jquery-3.5.1.js"></script>
    <script src="./js/tools.js"></script>
    <script src="./js/nav.js"></script>
    <script src="./js/order.js"></script>



</body>
</html>