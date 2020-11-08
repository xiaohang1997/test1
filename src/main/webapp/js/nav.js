$(document).ready(function() {
    $(".order_nav a").on("click",function(event){
        //取消事件的默认行为
        event.preventDefault();
        //显示url
        //alert(this.href);
        $(".main").load(this.href);
    });
});