<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>交易查询</title>
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
			height: 537px;
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
			background-color: #93999f;
			border-color: #93999f;
			opacity: 1;
			color: white;
			font-size: 15px;
			position: absolute;
			right: 224px;
			top: 700px;

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
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.alipay.config.*"%>
<%@ page import="com.alipay.api.*"%>
<%@ page import="com.alipay.api.request.*"%>
<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page import="java.util.Map" %>
<%
	//获得初始化的AlipayClient
	AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
	
	//设置请求参数
	AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();

	
	//商户订单号，商户网站订单系统中唯一订单号
	String out_trade_no = new String(request.getParameter("WIDTQout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
	//支付宝交易号
	String trade_no = new String(request.getParameter("WIDTQtrade_no").getBytes("ISO-8859-1"),"UTF-8");
	//请二选一设置
	
	alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","+"\"trade_no\":\""+ trade_no +"\"}");

	//请求
	String result = alipayClient.execute(alipayRequest).getBody();
	
	//输出
	//out.println(result);
	String val =(JSON.parseObject(result).getString("alipay_trade_query_response"));
	Map map = (Map) JSON.parse(val);
	//System.out.println(map.get("buyer_user_id"));
	//out.println(map.get("buyer_user_id"));

%>

<body>
<div id="container">

	<div class="top">   <h1 class="sure"> 订单查询</h1></div>
	<div class="main">


		<form action="payindex.jsp" method="post">
			<div class="title-box">

				<p class="inform">订单详细信息</p>
				<a  class=" r" href="#">我有疑问,需要反馈?</a>

			</div>

			<div class="center-box">
				<p>
					<label for="name">用户支付宝账号：</label>
					<input type="text"  style="border:0"  name="name" id="name" value="<%=map.get("buyer_user_id")%>" disabled>

				</p>
				<p>
					<label for="introduce">登录邮箱：</label>
					<input type="text"   style="border:0"  name="introduce" id="introduce" value="<%=map.get("buyer_logon_id")%>" disabled>
				</p>
				<p>
					<label for="price">订单号：</label>
					<input type="text"  style="border:0 "   name="price" id="price" value="<%=map.get("out_trade_no")%>"  disabled>
				</p>
				<p>
					<label for="price11">商户账号：</label>
					<input type="text"  style="border:0 "   name="price11" id="price11" value="<%=map.get("trade_no")%>"  disabled>
				</p>

				<p>
					<label for="tt">金额：</label>
					<input type="text"  style="border:0 "   name="tt" id="tt" value="<%=map.get("total_amount")%>"  disabled>
				</p>


				<p>
					<label for="ttt">下单时间：</label>
					<input type="text"  style="border:0 "   name="ttt" id="ttt" value="<%=map.get("send_pay_date")%>"  disabled>
				</p>
			</div>
			<span class="foot">订单状态： </span>


			<span  class="money"  >  <%=map.get("trade_status")%> </span>





			<p class="submit1">
				<label></label>
				<input type="submit"  style="font-size: larger" value="返回">
			</p>


		</form>

	</div>

</div>











</body>
</html>