var aj = jQuery.noConflict();
aj(function(){
    aj("#userdetail").popover({
        trigger:'manual',
        placement : 'bottom',
        html: 'true',
        content : '<div style="width:300px;height:300px;">' +
            '<a href="./usersettings.jsp">信息修改</a>' +
            '<a href="../orderIndex.jsp">订单中心</a>' +
            '<a href="/learn/Collection?method=findCollectionByUserId">我的收藏</a>' +
            '<a href="/learn/User?method=checkUserIdentify">课程管理</a>' +
            '</div>',
        animation: false
    }).on("mouseenter", function () {
        var _this = this;
        aj(this).popover("show");
    }).on("mouseleave", function () {
        var _this = this;
        setTimeout(function () {
            if (!aj(".popover:hover").length) {
                aj(_this).popover("hide")
            }
        }, 1000);
    });
    //课程分类展示
    aj(".category").popover({
        trigger:'manual',
        placement : 'right',
        html: 'true',
        content : '',
        animation: false
    }).on("mouseenter", function () {
        var cid = aj(this).attr('c-id');
        aj('#' + cid).show();
        aj('#' + cid).hover(function(){
            aj('#' + cid).show();
        },function(){
            aj('#' + cid).hide();
        });
    }).on("mouseleave", function () {
        var cid = aj(this).attr('c-id');
        aj('#' + cid).hide();
    });
});