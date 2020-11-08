var aj = jQuery.noConflict();
//展示课程方向相关的五门课程
function showCourseLimit(id) {
    aj("#a").html("");
    var text="";
    aj.ajax({
        url:"/learn/User?method=findCourseByCourseDirectionId",
        data:"id="+id,
        dataType:"json",
        method:"post",
        scriptCharset:"utf-8",
        success:function (data) {
            for(var i=0;i<data.length;i++){
                text=text+"<a style='display: inline' href=\"/learn/User?method=findCourseByName&name="+data[i].name+"\">"+data[i].name+"</a> ";
            }
            aj("#a").append(text);
        }
    })
}
function showCourse(id){
    aj("#courseList").html("<li class=\"course-nav-item cur-course-nav\"><a href=\"javascript:void(0)\">全部</a></li>");
    var text="";
    if(id==0){
        aj.ajax({
            url: "/learn/User?method=findAllCourse",
            dataType: "json",
            method: "post",
            scriptCharset: "utf-8",
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    text = text + "<li class=\"course-nav-item\">" +
                        "<a style='display: inline' href=\"/learn/User?method=findChapterByCourseId&courseId="+data[i].id+"\">"+
                        data[i].name+"</a></li> "
                }
                aj("#courseList").append(text);
            }
        });
    }else {
        aj.ajax({
            url: "/learn/User?method=findCourseByCourseDirectionId",
            data: "id=" + id,
            dataType: "json",
            method: "post",
            scriptCharset: "utf-8",
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    text = text + "<li class=\"course-nav-item\">" +
                        "<a style='display: inline' href=\"/learn/User?method=findChapterByCourseId&courseId="+data[i].id+"\">"+
                        data[i].name+"</a></li> "
                }
                aj("#courseList").append(text);
            }
        });
    }
}