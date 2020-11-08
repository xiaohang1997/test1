var aj = jQuery.noConflict()
function changeCollect(obj,courseId) {
    if(aj(obj).attr("class")=="following"){
        aj.ajax({
            url: "/learn/Collection?method=addCollection",
            dataType: "text",
            data:"id="+courseId,
            method: "post",
            success: function (data) {
                if (data == "true") {
                    aj(obj).attr("class","followed")
                }else {
                    alert("收藏失败")
                }
            }
        });
    }else {
        aj.ajax({
            url: "/learn/Collection?method=deleteCollection",
            dataType: "text",
            data:"id="+courseId,
            method: "post",
            success: function (data) {
                if (data == "true") {
                    aj(obj).attr("class","following")
                }else {
                    alert("取消收藏失败")
                }
            }
        });
    }
}