package servlet;

import model.*;
import service.*;
import service.impl.*;
import util.Page;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
@WebServlet("/content.do")
public class ContentServlet extends BaseServlet {
    UserService userService = new UserServiceImpl();
    VideoService videoService = new VideoServiceImpl();
    ContentService contentService = new ContentServiceImpl();
    ChapterService chapterService = new ChapterServiceImpl();
    CourseService courseService = new CourseServiceImpl();
    public void addContent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain;charset=UTF-8");
        HttpSession session = req.getSession();
        PrintWriter writer = resp.getWriter();
        String checkC = req.getParameter("checkC");
        User user = (User) req.getSession().getAttribute("user");
        Content content = new Content();
        Integer videoId = Integer.parseInt(req.getParameter("videoId"));
        String content1 = req.getParameter("content");
        if(session.getAttribute("checkCode").toString().equalsIgnoreCase(checkC)) {
            content.setUser(user);
            content.setVideo(videoService.findById(videoId));
            content.setContent(content1);
            content.setCreateTime(new Date());
            boolean flag = contentService.save(content);
            if (flag) {
                writer.write("true");
            }else {
                writer.write("false");
            }
        }else {
            writer.write("false");
        }
    }
    public void deleteContent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain;charset=UTF-8");
        HttpSession session = req.getSession();
        PrintWriter writer = resp.getWriter();
        Integer contentId = Integer.parseInt(req.getParameter("contentId"));
        Content content = (Content) contentService.findById(contentId);
        boolean flag = contentService.delete(content);
        if(flag){
            writer.write("true");
        }else{
            writer.write("false");
        }
    }
    public void contentList(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        HttpSession session = req.getSession();
        PrintWriter writer = resp.getWriter();
        Page<Content> page = new Page();

        int chapterId = 1;
        if(req.getParameter("id")!=null){
            chapterId = Integer.valueOf(req.getParameter("id"));
        }



        Chapter chapter = chapterService.findById(chapterId);
        Video video = videoService.findByType("chapterId",chapterId).get(0);
        chapter.setVideo(video);
        Course course = courseService.findById(chapter.getCourse().getId());
        chapter.setCourse(course);

        Integer pNumber = 1;
        if(req.getParameter("pNumber")!=null){
            pNumber = Integer.parseInt(req.getParameter("pNumber"));
        }
        page = contentService.findContentsByNowpage(pNumber,video.getId());
        List<Content> list = page.getList();
        for(Content content:list){
            User user = userService.findById(content.getUser().getId());
            content.setUser(user);
        }
        page.setList(list);

        List<Chapter> chapterList = chapterService.findByType("courseId",course.getId());
        for(Chapter li:chapterList){
            li.setCourse(courseService.findById(course.getId()));
        }
        session.setAttribute("chapterByCourseId",chapterList);
        session.setAttribute("chapterById",chapter);
        session.setAttribute("page",page);
        resp.sendRedirect("admin/video.jsp");
    }
}
