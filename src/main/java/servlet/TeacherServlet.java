package servlet;

import model.*;
import service.ChapterService;
import service.CourseDirectionService;
import service.CourseService;
import service.VideoService;
import service.impl.ChapterServiceImpl;
import service.impl.CourseDirectionServiceImpl;
import service.impl.CourseServiceImpl;
import service.impl.VideoServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@WebServlet("/Teacher")
public class TeacherServlet extends BaseServlet{
    private ChapterService chapterService = new ChapterServiceImpl();
    private CourseService courseService = new CourseServiceImpl();
    private CourseDirectionService courseDirectionService = new CourseDirectionServiceImpl();
    private VideoService videoService = new VideoServiceImpl();
    public void addCourse(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain;charset=UTF-8");
        String direction = request.getParameter("direction");
        CourseDirection courseDirection = new CourseDirection();
        courseDirection.setName(direction);
        courseDirection.setId(1);
        String name = request.getParameter("name");
        String introduce = request.getParameter("introduce");
        int price = Integer.valueOf(request.getParameter("price"));
        int number = Integer.valueOf(request.getParameter("number"));
        int level = Integer.valueOf(request.getParameter("level"));
        int courseTime = Integer.valueOf(request.getParameter("courseTime"));
        //Date courseTime = new SimpleDateFormat("yyyy-MM-dd").parse(cTime);
        User user = (User) request.getSession().getAttribute("user");

        Course course = new Course();
        course.setName(name);
        course.setUser(user);
        course.setCourseDirection(courseDirection);
        course.setIntroduce(introduce);
        course.setPrice(price);
        course.setNumber(number);
        course.setLevel(level);
        course.setCourseTime(courseTime);
        course.setCreateTime(new Date());

        if(courseService.save(course)){
            response.getWriter().write("true");
        }else {
            response.getWriter().write("false");
        }

    }
    public void deleteCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        User user = (User) request.getSession().getAttribute("user");
        Course course = new Course();
        Chapter chapter = new Chapter();
        Video video = new Video();
        int id = Integer.valueOf(request.getParameter("id"));
        List<Chapter> chapterList = chapterService.findByType("courseId",id);
        if(chapterList.size()!=0) {
            for (Chapter cli : chapterList) {
                List<Video> videoList = videoService.findByType("chapterId", cli.getId());
                if (videoList.size() != 0) {
                    for (Video vli : videoList) {
                        videoService.delete(vli);
                    }
                }
                chapterService.delete(cli);
            }

        }
        courseService.delete(courseService.findById(id));
        response.sendRedirect("admin/teacherCourse.jsp");
    }
    public void selectCourse(HttpServletRequest request, HttpServletResponse response) throws IOException{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        User user = (User) request.getSession().getAttribute("user");
        List<Course> list = courseService.findByType("userId",user.getId());
        for(Course li:list){
            li.setCourseDirection(courseDirectionService.findById(li.getCourseDirection().getId()));
        }
        session.setAttribute("teacherCourse",list);
        //JSONArray jsonarray= JSONArray.parseArray(JSON.toJSONString(list));
        //response.getWriter().write(jsonarray.toString());
        response.sendRedirect("admin/teacherCourse.jsp");
    }
}
