/*
* 订单列表
* */
function orderList(type){


    if(type==0){
        $.ajax({
            "url":"order.do",
            "type":"GET",
            "data":{"op":"list"},
            "dataType":"json",
            "success":function(result){

                //清空表格数据
                $("tr").not(".tab_head").remove();
                //alert(result);
                $.each(result,function(index,data){
                    $(".order_list").append(initRow(data));
                });
            }
        });



    }else{

        $.ajax({
            "url":"order.do",
            "type":"GET",
            "data":{"op":"findByType","type":type},
            "dataType":"json",
            "success":function(result){
                //清空表格数据
                $("tr").not(".tab_head").remove();
                //alert(result);
                $.each(result,function(index,data){
                    $(".order_list").append(initRow(data));
                });
            }
        });


    }
//插入数据
    function initRow(order){

        var date1=new Date(order.date);

        var year = date1.getFullYear();
        var month = date1.getMonth()+1;
        var date= date1.getDate();
        var hour = date1.getHours();
        var minutes = date1.getMinutes();
        var second = date1.getSeconds();
        var time =year+"年"+month+"月"+date+"日"+hour+"时"+minutes +"分"+second+"秒" ;

        var status=order.status;
        if(status=="0"){

            status="立即支付"

            return $("<tr><td>"
                +order.orderNum+"</td><td>"+order.course.name+"</td><td>"
                +order.course.introduce+"</td><td>"
                +time+"</td><td>"+order.course.price+"</td><td ><a  style='color: red' href='javascript:fastadd(\""+order.id+"\");'>"+status+"</a><a  style='color: darkolivegreen' href='javascript: reCover(\""+order.id+"\");'>取消订单</a></td></tr>");
        }else if(status=="1"){

             status="已完成"
            return $("<tr><td>"
                +order.orderNum+"</td><td>"+order.course.name+"</td><td>"
                +order.course.introduce+"</td><td>"
                +time+"</td><td>"+order.course.price+"</td><td ><span style='color:deepskyblue'>"+status+"</span><a  style='color: crimson' href='javascript: del(\""+order.id+"\");'>删除订单</a></td></tr>");
        }else if(status=="2"){
            status="已关闭"
            return $("<tr><td>"
                +order.orderNum+"</td><td>"+order.course.name+"</td><td>"
                +order.course.introduce+"</td><td>"
                +time+"</td><td>"+order.course.price+"</td><td ><a style='color: #4d555d' href='#'>"+status+"</a><a style='color: crimson' href='javascript: del(\""+order.id+"\");'>删除订单</a></td></tr>");

        }else if(status=="3"){

            status="还原"
                return $(
                    "<tr><td>"
                    +order.orderNum+"</td><td>"+order.course.name+"</td><td>"
                    +order.course.introduce+"</td><td>"
                    +time+"</td><td>"+order.course.price+"</td><td ><a style='color: chartreuse' href='javascript:reCover(\""+order.id+"\");'>"+status+"</a><a style='color: black' href='javascript:dell(\""+order.id+"\");'>永久删除订单</a></td></tr>");

        }




    }
}

/**
 * 还原订单 取消订单
 */
function reCover(Id){
    //alert(loginId);

    if(confirm("是否要更改订单状态？")){
        $.ajax({

            "url":"order.do",
            "type":"POST",
            "data":{"op":"update","orderId":Id,"status":2},
            "dataType":"text",
            "success":function(result){
                if(result=='true'){
                    //删除成功，刷新页面
                    // userList();
                    window.location.reload();
                }else{
                    alert("还原失败！");


                }
            }
        });
    }
}

/**
 * 立即支付
 * @param Id
 */
function fastadd(Id){
    //alert(loginId);

    if(confirm("是否要立即支付此订单")){
        $.ajax({
            "url":"order.do?op=fastAdd",
            "type":"POST",
            "data":{op:"fastAdd","orderId":Id},
            "dataType":"text",
            "success":function(result){
                if(result=='true'){

                    // userList();
                    window.location.href="payindex.jsp";
                }else{
                    alert("支付失败！");
                }
            }
        });
    }
}

/**
 * 删除订单 加入回收站
 */
function del(Id){
    //alert(loginId);
    if(confirm("是否要删除？")){
        $.ajax({
            "url":"order.do",
            "type":"POST",
            "data":{"op":"update","orderId":Id,"status":3},
            "dataType":"text",
            "success":function(result){
                if(result=='true'){
                    //删除成功，刷新页面
                    // userList();
                    window.location.reload();
                }else{
                    alert("删除失败！");
                }
            }
        });
    }
}

/**
 *
 * 永久删除  从回收站删除
 */
function dell(Id){
    //alert(loginId);
    if(confirm("是否要永久删除？")){
        $.ajax({
            "url":"order.do",
            "type":"POST",
            "data":{"op":"del","orderId":Id},
            "dataType":"text",
            "success":function(result){
                if(result=='true'){
                    //删除成功，刷新页面
                    // userList();
                    window.location.reload();
                }else{
                    alert("删除失败！");
                }
            }
        });
    }
}

    $(":submit").on("click",function (event) {

        event.preventDefault();
        //alert("添加订单。。。")
        $.ajax({
            "url":"order.do?op=add",
            "type":"post",

            "data":
                { "form":JSON.stringify(serializeObject($("form")))
                     },
            "dataType":"text",
            "success":function(result){
                //alert(JSON.stringify(serializeObject($("form"))))I;
                if(result=='true'){
                    alert("添加订单成功");
                    //跳转到付款页面（支付宝测试页面）
                    // location.reload();
                    window.location.href="payindex.jsp";
                }
            }
        });
    });
