package com.bjsxt.servlet;

import com.bjsxt.entity.User;
import com.bjsxt.service.UserService;
import com.bjsxt.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "UserServlet",urlPatterns = "/servlet/UserServlet")
public class UserServlet extends BaseServlet {


    /**
     * 登录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取表单的数据
        String username = request.getParameter("username");

        //解决get请求的乱码问题
//        byte [] bytes=username.getBytes("iso-8859-1");//string-- byte[]
//        username = new String(bytes,"utf-8");//byte []---string
        String password = request.getParameter("pwd");
        String rememberme = request.getParameter("rememberme");

        //增加服务器端的格式验证
        if(username == null || "".equals(username)){
            request.setAttribute("nameerror","用户名不能为空（JSP）");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return; //不加return，后面的语句还会执行
        }
        if(password == null || password.length()<=6){
            request.setAttribute("pwderror","密码长度要大于6(JSP)");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }

        //调用数据库判断登录是否成功
       /* boolean flag = false;
        if(username.indexOf("sxt")>=0 || username.indexOf("尚学堂")>=0){
            flag = true;
        }*/
        /*UserDao userDao = new UserDaoImpl();
        User user = userDao.find(username, password);*/
        UserService userService = new UserServiceImpl();
        User user = userService.login(username, password);
        //页面的跳转
        if(user!=null){
            //借助Cookie实现十天免登录
            //1.办理会员卡
            String username1 = URLEncoder.encode(username,"utf-8");
            Cookie cookie1 = new Cookie("username",username1);
            Cookie cookie2 = new Cookie("pwd",password);
            //2.指定作用范围  默认的作用于当前目录
            cookie1.setPath(request.getContextPath()); //作用范围当前项目   / 作用于当前服务器
            cookie2.setPath(request.getContextPath());
            System.out.println("路径："+request.getContextPath());
            //3.指定作用时间  默认时间是关闭浏览器
            if("yes".equals(rememberme)){
                cookie1.setMaxAge(60*60*24*10);
                cookie2.setMaxAge(60*60*24*10);
            }else{
                cookie1.setMaxAge(0);
                cookie2.setMaxAge(0);
            }

            //4.将会员卡带回家
            response.addCookie(cookie1);
            response.addCookie(cookie2);

            // 记录网站访问人数
            ServletContext context = this.getServletContext();
            Object contextObj = context.getAttribute("count");
            int count = 1;
            if (contextObj != null){
                count = (Integer)contextObj;
                count++;
            }
            context.setAttribute("count", count);

            //跳到成功页面
            request.setAttribute("uname",username);
            //response.sendRedirect("/myservlet1/admin/success.jsp");//重定向Redirect
            //response.sendRedirect("http://www.bjsxt.com");
            //response.sendRedirect("/myservlet1/admin/success.jsp");
            //response.sendRedirect(request.getContextPath()+"/admin/success.jsp");

            //http://localhost:8080/myservlet1/servlet/LoginServlet
            //http://localhost:8080/myservlet1/admin/success.jsp
            //http://localhost:8080/myservlet1/servlet/success.jsp
            HttpSession hs = request.getSession();
            hs.setAttribute("user", user);

            response.sendRedirect("../admin/success.jsp");
        }else{
            //跳回登录页面
            request.setAttribute("error","用户名或者密码错误(JSP)");
            //request.getRequestDispatcher("/admin/login.jsp").forward(request,response);//转发
            //request.getRequestDispatcher("http://www.bjsxt.com").forward(request,response);
            RequestDispatcher rd = request.getRequestDispatcher("../admin/login.jsp");
            rd.forward(request,response);
            //return;
        }

    }

    /**
     * 注册
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取数据
        Object userIdObj = req.getParameter("uname");
        String userId = null;
        if (userIdObj!=null) {
            userId = (String)userIdObj;
        }
        Object realnameObj = req.getParameter("realname");
        String realname = null;
        if (realnameObj!=null){
            realname = (String)realnameObj;
        }
        Object passwordObj = req.getParameter("pwd");
        String password = null;
        if (passwordObj!=null){
            password = (String)passwordObj;
        }
        // 年龄
        Object ageObj = Integer.parseInt(req.getParameter("age"));
        int age = 0;
        if (ageObj!=null) {
            age = (Integer)ageObj;
        }
        // 兴趣
        Object[] hobbyObj = req.getParameterValues("hobby");
        String hobby = "";
        if (hobbyObj!=null){
            for (Object o:hobbyObj) {
                hobby += (String) o+"#";
            }
        }
        hobby = hobby.substring(0, hobby.length()-2);

        // 日期
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String enterDateStr = format.format(new Date());
        Date enterDate = null;
        try {
            enterDate =  format.parse(enterDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        User user = new User(userId, realname, password, age, hobby, enterDate);
        UserService userService = new UserServiceImpl();
        boolean isSuccess = userService.register(user);
        if (isSuccess){
            // 注册成功，跳转到登录界面

            resp.sendRedirect("/remember/login.jsp");
        }else {
            // 注册失败，返回源界面，给出错误提示

            /*Cookie cookie = new Cookie("registererror", "用户名已被占用，请重新输入");
            resp.addCookie(cookie);*/
            //resp.sendRedirect("/remember/admin/register.jsp");
            req.setAttribute("registererror", "用户名已被占用，请重新输入");
            req.getRequestDispatcher("/admin/register.jsp").forward(req, resp);
        }

    }

    /**
     * 注销
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 重定向
        resp.sendRedirect("/remember/login.jsp");
        // 设置session失效
        req.getSession().invalidate();

    }

    /**
     *
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    public void findAll(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
        // 调用
        UserService userService = new UserServiceImpl();
        List<User> userList = userService.findAll();
        // 返回
        req.setAttribute("userList", userList);
        req.getRequestDispatcher("/admin/show.jsp").forward(req, res);
    }

    public void find(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{

        // 获取数据
        Object userIdObj = req.getParameter("userId");
        String userId = "";
        if (userIdObj == null || "".equals(userIdObj)){
            userIdObj = "";
        }else {
            userId = (String) userIdObj;
        }

        Object minAgeObj = req.getParameter("minAge");
        int minAge = 0;
        if (minAgeObj == null || "".equals(minAgeObj)){
            minAge = 0;
        }else {
            minAge = Integer.parseInt((String)minAgeObj);
        }

        // 处理数据
        UserService userService = new UserServiceImpl();
        List<User> userList = userService.find(userId, minAge);

        // 返回数据
        req.setAttribute("userList", userList);
        req.setAttribute("userId", userIdObj);
        req.setAttribute("minAge", minAgeObj);
        req.getRequestDispatcher("/admin/show.jsp").forward(req, res);

    }

}
