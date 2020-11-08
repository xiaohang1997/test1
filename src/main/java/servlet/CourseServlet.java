package servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import model.Course;
import service.CourseService;
import service.impl.CourseServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/course.do")
public class CourseServlet extends BaseServlet {
    public void addCourseCount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain;charset=UTF-8");
        CourseService courseService = new CourseServiceImpl();
        PrintWriter writer = resp.getWriter();
        //String是课程名Integer是观看次数
        Map<String,Integer> map= new HashMap<>();
        String name = req.getParameter("name");
        Course course = courseService.findByType("name", name).get(0);
        Integer number = course.getNumber();
        course.setNumber(number+1);
        boolean flag = courseService.update(course);
        if(flag){
            HttpSession session = req.getSession();
            Course newCourse = courseService.findByType("name", name).get(0);
            session.setAttribute(name,newCourse.getNumber());
        }
        writer.write("true");
    }
    public void showNumber(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        CourseService courseService = new CourseServiceImpl();
        List<Course> courses = courseService.findHighCourse();
        JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(courses));
        writer.write(jsonArray.toString());
    }
}
