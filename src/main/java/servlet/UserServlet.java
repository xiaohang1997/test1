package servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import model.*;
import service.*;
import service.impl.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/User")
public class UserServlet extends BaseServlet{
    private ChapterService chapterService = new ChapterServiceImpl();
    private UserService userService = new UserServiceImpl();
    private OrderService orderService = new OrderServiceImpl();
    private VideoService videoService = new VideoServiceImpl();
    private CourseService courseService = new CourseServiceImpl();
    private CourseDirectionService courseDirectionService = new CourseDirectionServiceImpl();
    //查询所有课程方向
    public void findAllCourseDirection(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        List<CourseDirection> list = courseDirectionService.findAll();
        List<Course> courseList = courseService.findAll();
        session.setAttribute("courseAllDirection",list);
        session.setAttribute("allCourse",courseList);
        //response.sendRedirect("admin/courseList.jsp");
        response.sendRedirect("admin/courseList.jsp");
    }
    //查询所有课程
    public void findAllCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        HttpSession session = request.getSession();
        List<Course> list = courseService.findAll();
        JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(list));
        response.getWriter().write(jsonArray.toString());
    }
    //查询指定数目的课程方向
    public void findCourseDirectionLimit(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        List<CourseDirection> list = courseDirectionService.findCourseDirectionLimit(5);
        session.setAttribute("courseDirection",list);
        findCourseByPriceLimit(request, response); //查询价格最高的五门课，实战推荐处使用
        findCourseByFreePriceLimit(request, response); //查询免费的五门课，免费好课处使用
        findTeacherLimit(request, response); //查询五位老师，名校讲师处使用
        response.sendRedirect("admin/index.jsp");
    }
    public void findCourseById(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        int id = Integer.valueOf(request.getParameter("id"));
        Course course = courseService.findById(id);
        course.setUser(userService.findById(course.getUser().getId()));
        session.setAttribute("courseById",course);
    }
    public void findCourseByName(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String name = request.getParameter("name");
        Course course = courseService.findByType("name",name).get(0);
        if(course.getPrice()==0) {
            course.setUser(userService.findById(course.getUser().getId()));
            course.setCourseDirection(courseDirectionService.findById(course.getCourseDirection().getId()));

            List<Chapter> list = chapterService.findByType("courseId", course.getId());
            for (Chapter li : list) {
                li.setCourse(course);
                List<Video> videoList = videoService.findByType("chapterId", li.getId());
                if (videoList.size() != 0) {
                    li.setVideo(videoList.get(0));
                }
            }

            List<Course> courseList = courseService.findByType("userId", course.getUser().getId());
            for (Course li : courseList) {
                CourseDirection courseDirection = courseDirectionService.findById(li.getCourseDirection().getId());
                li.setCourseDirection(courseDirection);
            }
            session.setAttribute("courseByTeacher", courseList);
            session.setAttribute("courseByName", course);
            session.setAttribute("chapterByCourseId", list);
            response.sendRedirect("admin/learn.jsp");
        }else {
            List<Order> orderList=orderService.findByType("courseId", course.getId());
            if(orderList.size()!=0&&orderList.get(0).getStatus()==1) {
                course.setUser(userService.findById(course.getUser().getId()));
                course.setCourseDirection(courseDirectionService.findById(course.getCourseDirection().getId()));

                List<Chapter> list = chapterService.findByType("courseId", course.getId());
                for (Chapter li : list) {
                    li.setCourse(course);
                    List<Video> videoList = videoService.findByType("chapterId", li.getId());
                    if (videoList.size() != 0) {
                        li.setVideo(videoList.get(0));
                    }
                }

                List<Course> courseList = courseService.findByType("userId", course.getUser().getId());
                for (Course li : courseList) {
                    CourseDirection courseDirection = courseDirectionService.findById(li.getCourseDirection().getId());
                    li.setCourseDirection(courseDirection);
                }
                session.setAttribute("courseByTeacher", courseList);
                session.setAttribute("courseByName", course);
                session.setAttribute("chapterByCourseId", list);
                response.sendRedirect("admin/learn.jsp");
            }else if(orderList.size()!=0){
                response.sendRedirect("orderIndex.jsp");
            }else {
                response.sendRedirect("order.do?op=findCourse&id=" + course.getId());
            }
        }
    }
    //查询指定数目的付费课程，价格由高到低
    public void findCourseByPriceLimit(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String name = request.getParameter("name");
        List<Course> list = courseService.findByPriceLimit(5);
        for(Course li:list){
            li.setCourseDirection(courseDirectionService.findById(li.getCourseDirection().getId()));
            li.setUser(userService.findById(li.getUser().getId()));
        }
        session.setAttribute("courseByPrice",list);
        //JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(list));
        //response.getWriter().write(jsonArray.toString());
    }
    //查询指定数目的免费课程
    public void findCourseByFreePriceLimit(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String name = request.getParameter("name");
        String str = request.getParameter("limit");
        int limit = 5;
        if(str!=null){
            limit=Integer.valueOf(str);
        }
        List<Course> list = courseService.findByFreePriceLimit(limit);
        for(Course li:list){
            li.setCourseDirection(courseDirectionService.findById(li.getCourseDirection().getId()));
            li.setUser(userService.findById(li.getUser().getId()));
        }
        session.setAttribute("courseByFreePrice",list);
    }
    public void findCourseByCourseDirectionId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        HttpSession session = request.getSession();
        int id = Integer.valueOf(request.getParameter("id"));
        List<Course> list = courseService.findByType("courseDirectionId",id);
        for(Course li:list){
            li.setUser(userService.findById(li.getUser().getId()));
        }
        session.setAttribute("courseByCourseDirectionId",list);

        //System.out.println(((List<Course>)session.getAttribute("courseByCourseDirectionId")).get(0).getName());
        JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(list));
        response.getWriter().write(jsonArray.toString());
    }
    public void findChapter(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String courseName = request.getParameter("courseName");
        List<Chapter> list = chapterService.findAll();
        for(Chapter li:list){
            li.setCourse(courseService.findById(li.getCourse().getId()));
        }
        session.setAttribute("allChapter",list);
        response.sendRedirect("User?method=findAllCourseDirection");
    }
//    public void findChapterById(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        HttpSession session = request.getSession();
//        int chapterId = Integer.valueOf(request.getParameter("chapterId"));
//        Chapter chapter = chapterService.findById(chapterId);
//        chapter.setCourse(courseService.findById(chapter.getCourse().getId()));
//
//        session.setAttribute("ChapterById",chapter);
//        response.sendRedirect("admin/video.jsp");
//    }
    public void findChapterByCourseId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        int courseId = Integer.valueOf(request.getParameter("courseId"));
        List<Chapter> list = chapterService.findByType("courseId",courseId);
        for(Chapter li:list){
            li.setCourse(courseService.findById(courseId));
        }
        session.setAttribute("chapter",list);
        session.setAttribute("allChapter",null);
        response.sendRedirect("User?method=findAllCourseDirection");
    }
    public void findTeacherLimit(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        List<User> list = userService.findTeacherLimit(5);
        session.setAttribute("teacher",list);
    }
    public boolean login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String loginName = request.getParameter("username");
        String loginPwd = request.getParameter("password");
        String checkC = request.getParameter("checkC");  //接收验证码
        int identify = Integer.valueOf(request.getParameter("identify")); //接收身份
        if(session.getAttribute("checkCode").toString().equalsIgnoreCase(checkC)) {  //判断验证码是否正确
            List<User> list = userService.findByType("loginName", loginName);
            if(list!=null){
                User user = list.get(0);
                if (user.getLoginPwd() != null) {
                    if (user.getLoginPwd().equals(loginPwd)&&user.getIdentify()==identify) {
                        session.setAttribute("user",user);
                        out.write("true");
                        return true;
                    }else {
                        out.write("false");
                    }
                } else {
                    out.write("false");
                }
            }else {
                out.write("false");
            }
        }else{
            out.write("checkFalse");
        }
        return false;
    }
    public void againLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        HttpSession session = request.getSession();
        String loginName = request.getParameter("username");
        String loginPwd = request.getParameter("password");
        int identify = Integer.valueOf(request.getParameter("identify"));
        User user = new User();
        user.setLoginName(loginName);
        user.setLoginPwd(loginPwd);
        user.setIdentify(identify);
        if (userService.login(user)) {
            session.setAttribute("user",user);
            Cookie cookie = new Cookie(loginName, loginName + "==" + loginPwd+"=="+identify);
            cookie.setMaxAge(24 * 60 * 60 * 7);
            response.addCookie(cookie);
            writer.write("true");
        } else {
            writer.write("false");
        }
    }
    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String loginName = request.getParameter("rusername");
        String loginPwd = request.getParameter("rpassword");
        String checkC = request.getParameter("rcheckC");  //接收验证码
        int identify = Integer.valueOf(request.getParameter("ridentify")); //接收身份
        if(session.getAttribute("checkCode").toString().equalsIgnoreCase(checkC)) {  //判断验证码是否正确
            User user = new User();
            user.setLoginName(loginName);
            user.setLoginPwd(loginPwd);
            user.setIdentify(identify);
            if (userService.save(user)) {
                out.write("true");
            } else {
                out.write("false");
            }
        }else{
            out.write("checkFalse");
        }
    }
    public void userUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain;charset=UTF-8");
        HttpSession session = req.getSession();
        PrintWriter writer = resp.getWriter();
        User user = (User) session.getAttribute("user");
        String name = req.getParameter("name");
        String loginName = req.getParameter("loginName");
        String loginPwd = req.getParameter("loginPwd");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        String school = req.getParameter("school");
        String introduce = req.getParameter("introduce");
        String lastVideoUrl = req.getParameter("lastVideoUrl");
        int identify = Integer.valueOf(req.getParameter("identify"));
        user.setName(name);
        user.setLoginName(loginName);
        user.setLoginPwd(loginPwd);
        user.setEmail(email);
        user.setPhone(phone);
        user.setAddress(address);
        user.setSchool(school);
        user.setIntroduce(introduce);
        user.setIdentify(identify);
        user.setLastVideoUrl(lastVideoUrl);
        boolean flag = userService.update(user);
        if(flag){
            writer.write("true");
        }else{
            writer.write("false");
        }
    }
    public void findUserBy(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain;charset=UTF-8");
        HttpSession session = request.getSession();
        int id = Integer.valueOf(request.getParameter("id"));
        User user = userService.findById(id);
        session.setAttribute("userById",user);
        response.sendRedirect("admin/userInfo.jsp");
    }
    public void exit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Cookie[] cookies = request.getCookies();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        for(Cookie cookie:cookies){
            String value = cookie.getValue();
            String[] login = value.split("==");
            if(login.length==3){
                String loginName = login[0];
                if(user.getLoginName().equals(loginName)){
                    cookie = new Cookie(loginName,"");
                    cookie.setMaxAge(0); //设置立即删除
                    response.addCookie(cookie);
                }
            }
        }
        session.removeAttribute("user");
        out.write("true");
        //request.getRequestDispatcher("login.jsp").forward(request,response);
    }
    public void saveLastUrl(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain;charset=UTF-8");
        int videoId = Integer.valueOf(request.getParameter("videoId"));
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        user.setLastVideoUrl(videoService.findById(videoId).getUrl());
        if (userService.update(user)){
            session.setAttribute("user",user);
        }
        response.getWriter().write("true");
    }
    public void checkUserIdentify(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user!=null) {
            if (user.getIdentify() == 1) {
                response.sendRedirect("/learn/Teacher?method=selectCourse");
            } else {
                response.getWriter().write("<script>alert('你不是讲师，不能进行课程管理')</script>");
            }
        }else {
            //response.getWriter().write("alert('请登录')");
            response.sendRedirect("User?method=findCourseDirectionLimit");
        }
    }
}
