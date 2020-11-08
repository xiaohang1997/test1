package filter;

import model.User;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter("/admin/*")
public class LoginSaveFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        UserService userService = new UserServiceImpl();
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;
        HttpSession session = req.getSession();
        Cookie[] cookies = req.getCookies();
        User user = new User();
        if(session.getAttribute("user")!=null) {
            user = (User) session.getAttribute("user");
        }
        for(Cookie cookie:cookies){
            String value = cookie.getValue();
            String[] login = value.split("==");
            if(login.length==3){
                String loginName = login[0];
                String loginPwd = login[1];
                int identify = Integer.valueOf(login[2]);
                user.setLoginName(loginName);
                user.setLoginPwd(loginPwd);
                user.setIdentify(identify);

                if(userService.login(user)){
                    user=userService.findByType("loginName", loginName).get(0);
                    session.setAttribute("user",user);
                }else {
                    user = new User();
                }
            }
        }
        String url = null;
        if(req.getRequestURI().length()<13){
            url = req.getRequestURI().substring(7);
        }else {
            url = req.getRequestURI().substring(13);
        }
        System.out.println(url);
        if(user.getLoginName()!=null||url.equals("index.jsp")||url==null){
            filterChain.doFilter(req,resp);
        }else{
            resp.sendRedirect("/learn/admin/index.jsp");
        }
    }

    @Override
    public void destroy() {

    }
}
