package servlet;

import com.qiniu.common.Zone;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import model.Chapter;
import model.Course;
import model.Video;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.ChapterService;
import service.CourseService;
import service.VideoService;
import service.impl.ChapterServiceImpl;
import service.impl.CourseServiceImpl;
import service.impl.VideoServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@WebServlet("/YunUploadServlet")
public class YunUploadServlet extends HttpServlet {
            private ChapterService chapterService = new ChapterServiceImpl();
            private CourseService courseService = new CourseServiceImpl();
            private VideoService videoService = new VideoServiceImpl();
            //设置好账号的ACCESS_KEY和SECRET_KEY
            private static String ACCESS_KEY = "2RYFb4MlvRpmIOFGb-zRe-26ffeCg3XWl4ytDHJ-";
            //这两个登录七牛 账号里面可以找到
            private static String SECRET_KEY = "3L0VUEanD7poBtE2-1Lhjq4z-rMrFVgdRH7_TC6t";
            //要上传的空间
            private static String bucketname = "learn-video";
            private static final long serialVersionUID = 1L;
            // 重写doPost方法，处理事件识别请求
            protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                request.setCharacterEncoding("utf-8");
                response.setCharacterEncoding("utf-8");
                Chapter chapter = new Chapter();
                Course course = new Course();
                Video video = new Video();
                // 密钥配置
                Auth auth = Auth.create(ACCESS_KEY,SECRET_KEY);
                //创建上传对象
                Configuration configuration = new Configuration(Zone.zone2());
                UploadManager uploadManager = new UploadManager(configuration);

                //1、创建一个DiskFileItemFactory工厂
                DiskFileItemFactory factory = new DiskFileItemFactory();
                //2、创建一个文件上传解析器
                ServletFileUpload upload = new ServletFileUpload(factory);
                //解决上传文件名的中文乱码
                upload.setHeaderEncoding("UTF-8");
                upload.setSizeMax(1024 * 1024 * 500);//设置上传的文件总的大小不能超过500M
                try {
                    // 1. 得到 FileItem 的集合 items
                    List<FileItem> /* FileItem */items = upload.parseRequest(request);
                    // 2. 遍历 items:
                    for (FileItem item : items) {
                        if (item.isFormField()) {
                            String name = item.getFieldName();
                            String value = item.getString("utf-8");
                            switch (name){
                                case "name":
                                    chapter.setName(value);break;
                                case "courseId":
                                    course = courseService.findById(Integer.valueOf(value));
                                    chapter.setCourse(course);break;
                                default:break;
                            }

                        }else {
                            String fileName = item.getName();
                            InputStream in = item.getInputStream();
                            uploadManager.put(in,fileName,auth.uploadToken(bucketname),null, null);
                            in.close();
                            video.setUrl(fileName);
                            chapter.setVideo(video);
                            //System.out.println("上传完成");
                        }
                    }
                    chapterService.save(chapter);
                    chapter=chapterService.findByType("name",chapter.getName()).get(0);
                    video.setChapter(chapter);
                    videoService.save(video);
                    video=videoService.findByType("chapterId",chapter.getId()).get(0);
                    chapter.setVideo(video);
                    chapterService.update(chapter);

                    request.getRequestDispatcher("Teacher?method=selectCourse").forward(request,response);
                } catch (FileUploadException e) {
                    e.printStackTrace();
                }
            }
}