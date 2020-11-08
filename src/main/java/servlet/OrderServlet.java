package servlet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import model.Course;
import model.Order;
import model.User;
import service.CourseService;
import service.OrderService;
import service.UserService;
import service.impl.CourseServiceImpl;
import service.impl.OrderServiceImpl;
import service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@WebServlet("/order.do")
public class OrderServlet  extends HttpServlet {
    //调用业务层
    UserService userService=new UserServiceImpl();
    CourseService courseService=new CourseServiceImpl();
    OrderService orderService=new OrderServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //设置请求编码
        req.setCharacterEncoding("UTF-8");
        //获取请求参数
        String lname = req.getParameter("lname");
        String op=req.getParameter("op");
        if("list".equals(op)){
            //显示订单列表
            list(req,resp);
            
        }else if("add".equals(op)){
            
            //添加订单
            add(req,resp);
        }else if("del".equals(op)){
            delete(req,resp);

        }else if("update".equals(op)){
            //修改订单状态
            update(req,resp);

        }else if("findByType".equals(op)){

            //按状态查询  分为4种状态
            findByType(req,resp);

        }else if("findCourse".equals(op)){

            findCourse(req,resp);
        }else if("fastAdd".equals(op)){

            fastAdd(req,resp);
        }else{

            finishOrder(req,resp);
        }


    }

    private void finishOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out=resp.getWriter();
        HttpSession session=req.getSession();
        Order order1= (Order) session.getAttribute("order");
        order1.setStatus(1);
        orderService.update(order1);

      //  RequestDispatcher requestDispatcher=req.getRequestDispatcher("orderIndex.jsp");
       // requestDispatcher.forward(req,resp);
        resp.sendRedirect("orderIndex.jsp");


    }

    private void fastAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plian;charset=UTF-8");
        int id=Integer.parseInt(req.getParameter("orderId"));
        PrintWriter out=resp.getWriter();
        //查询此订单
        Order order=orderService.findById(id);
        HttpSession session=req.getSession();
        session.setAttribute("order",order);
        Course course=courseService.findById(order.getCourse().getId());

        session.setAttribute("course",course);
        out.write("true");

        out.close();


    }
    //课程付费解锁
    private void findCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        int id=Integer.parseInt(req.getParameter("id"));
        Course course=courseService.findById(id);


        HttpSession session=req.getSession();
        session.setAttribute("course",course);
        //User user = (User) session.getAttribute("user");
//        int uid=user.getId();
//        User user=userService.findById(uid);
//        session.setAttribute("user",user);
        req.setAttribute("course",course);
        RequestDispatcher requestDispatcher=req.getRequestDispatcher("order_sure.jsp");
        requestDispatcher.forward(req,resp);


    }

    //这个页面进行状态得改变，分别为支付订单， 取消订单，失效等
      private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
           resp.setContentType("text/plain;charset=UTF-8");
           PrintWriter out=resp.getWriter();
           String id=req.getParameter("orderId");
           String status=req.getParameter("status");
          Order order = orderService.findById(Integer.parseInt(id));
            order.setStatus(Integer.parseInt(status));
        boolean flag=orderService.update(order);
        if(flag){
            out.write("true");
        }else {
            out.write("false");
        }


        out.close();

    }

    private void findByType(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        //List<Order> orders=orderService.findAll();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        List<Order> orders = new ArrayList<>();
        orders = orderService.findByType("userId",user.getId());
        Course course = new Course();
        for(Order li:orders){
            course = courseService.findByType("id",li.getCourse().getId()).get(0);
            li.setCourse(course);
        }
        //选择状态,然后循环返回
        int  type= Integer.parseInt(req.getParameter("type"));
        List<Order> order1=new ArrayList<Order>();

            for(Order order:orders){

                if(order.getStatus()==(type-1)){

                    order1.add(order);

                }


        }

        String jsonArrayString = JSONArray.toJSONString(order1);
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out=resp.getWriter();
        out.write(jsonArrayString);
        out.close();


    }
    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("text/plain;charset=UTF-8");
        PrintWriter out=resp.getWriter();
        String id=req.getParameter("orderId");
        Order order=orderService.findById(Integer.parseInt(id));
        boolean flag=orderService.delete(order);
        if(flag){

            out.write("true");
        }else{
            out.write("false");
        }
        out.close();
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
       resp.setContentType("text/plain;charset=UTF-8");
       PrintWriter out=resp.getWriter();

       String form=req.getParameter("form");
       Order order = JSONObject.parseObject(form,Order.class);
        Date date = new Date();
//        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyy-MM-dd");
//        String orderNum=simpleDateFormat.format(date);
        Calendar cal=Calendar.getInstance();
        cal.setTime(date);
        String month=String.valueOf(cal.get(Calendar.MONTH));
        String year=String.valueOf(cal.get(Calendar.YEAR));
        String day=String.valueOf(cal.get(Calendar.DATE));
        String hour=String.valueOf(cal.get(Calendar.HOUR));
        String minute=String.valueOf(cal.get(Calendar.MINUTE));
        String second=String.valueOf(cal.get(Calendar.SECOND));
        String orderNum=year+month+day+hour+minute+second;
        HttpSession session=req.getSession();
         Course course= (Course) session.getAttribute("course");
        User user= (User) session.getAttribute("user");
        order.setUser(user);
        order.setCourse(course);

        order.setOrderNum(orderNum);

        order.setDate(date);
        order.setStatus(0);

        boolean flag=orderService.save(order);
        List<Order> list = orderService.findByType("orderNum",orderNum);
        if(list!=null){
            order=list.get(0);
        }else {
            order=null;
        }


        session.setAttribute("order",order);
        session.setAttribute("course",course);
       if(flag){

           out.write("true");
       }else{
           out.write("false");
       }
       out.close();

    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //List<Order> orders=orderService.findAll();
        String jsonArrayString = null;
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out=resp.getWriter();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        List<Order> orders = new ArrayList<>();
        if(user!=null){
            orders = orderService.findByType("userId",user.getId());
            Course course = new Course();
            for(Order li:orders){
                course = courseService.findByType("id",li.getCourse().getId()).get(0);
                li.setCourse(course);
            }
            jsonArrayString = JSONArray.toJSONString(orders);
        }else {
            resp.getWriter().write("alert('请登录')");
            resp.sendRedirect("User?method=findCourseDirectionLimit");
        }

        if(orders==null){
            out.write("false");

        }else{

            out.write(jsonArrayString);

        }

        out.close();


    }
}
