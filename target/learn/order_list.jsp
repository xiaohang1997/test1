<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html >
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>

        *{
            padding: 0px;
            margin: 0px;
        }
        .order_list{

            border-collapse: collapse;
        }


    </style>
</head>
<body>
<h1>订单列表</h1>

<table class="order_list" border="1px" width="80%">
    <tr class="tab_head">
        <th>订单编号</th>
        <th>课程名</th>
        <th>课程描述</th>
        <th>创建时间</th>
        <th>金额</th>
        <th colspan="2">操作</th>
    </tr>
</table>

<script src="./js/jquery-3.5.1.js"></script>
<script src="./js/tools.js"></script>
<script src="./js/nav.js"></script>
<script src="./js/order.js"></script>
<%  String type=request.getParameter("type") ;  %>


<script language="javascript" type="text/javascript">



    orderList(<%= type%>);
</script>
</body>
</html>