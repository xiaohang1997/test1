package servlet;


import model.Collection;
import model.Course;
import model.User;
import service.CollectionService;
import service.CourseService;
import service.impl.CollectionServiceImpl;
import service.impl.CourseServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/Collection")
public class CollectionServlet extends BaseServlet {
    private CollectionService collectionService = new CollectionServiceImpl();
    private CourseService courseService = new CourseServiceImpl();
    public void findCollectionByUserId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Collection collection = new Collection();
        User user = (User) session.getAttribute("user");
        if(user==null){
            response.getWriter().write("alert('请登录')");
            response.sendRedirect("User?method=findCourseDirectionLimit");
        }else {
            List<Collection> collectionList = collectionService.findByType("userId",user.getId());
            if(collectionList.size()!=0){
                for(Collection li:collectionList){
                    li.setCourse(courseService.findById(li.getCourse().getId()));
                }
                session.setAttribute("collectionList",collectionList);
                response.sendRedirect("admin/collectionList.jsp");
            }else {
                response.getWriter().write("<script>alert('还没有收藏')</script>");
                response.getWriter().write("<a href='User?method=findCourseDirectionLimit'>点击返回首页</a>");
            }
        }
    }
    public void addCollection(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain;charset=UTF-8");
        Collection collection = new Collection();
        HttpSession session = request.getSession();
        User user = (User) request.getSession().getAttribute("user");
        collection.setCourse(courseService.findById(Integer.valueOf(request.getParameter("id"))));
        collection.setUser(user);
        if(collectionService.save(collection)){
            response.getWriter().write("true");
        }else {
            response.getWriter().write("false");
        }

    }
    public void deleteCollection(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain;charset=UTF-8");
        Collection collection = new Collection();
        HttpSession session = request.getSession();
        User user = (User) request.getSession().getAttribute("user");
        Course course = courseService.findById(Integer.valueOf(request.getParameter("id")));
        collection.setCourse(course);
        List<Collection> list = collectionService.findByType("courseId",course.getId());
        int i = list.size();
        for(Collection li : list){
            if(li.getUser().getId()==user.getId()) {
                i++;
                collectionService.delete(li);
            }
        }
        if(i==list.size()) {
            response.getWriter().write("false");
        }else {
            response.getWriter().write("true");
        }
    }
}
