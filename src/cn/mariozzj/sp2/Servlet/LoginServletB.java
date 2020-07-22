package cn.mariozzj.sp2.Servlet;

import cn.mariozzj.sp2.bean.User;
import cn.mariozzj.sp2.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/LoginServletB")
public class LoginServletB extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        System.out.println();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String token = request.getParameter("token");
        PrintWriter pw = response.getWriter();
//        pw.write(username+";"+password+";"+email+";"+token+";");
        if(!"".equals(email)){
            //注册操作
            //插入
            User user = new User();
            user.username = username;
            user.password = password;
            user.email = email;
            user.userType = 2;
            UserDAO dao = new UserDAO();
            try{
                dao.add(user);
            }catch (Exception e){
                pw.write(username+"注册失败!<br />错误："+e.toString());
            }finally {
                pw.write(username + ",注册成功！请重新登录！");
            }
            response.setHeader("refresh", "3;url=/pmicu/login.html");
        }
        else{
            //登录操作
            User user = new User();
            user.username = username;
            user.password = password;
            UserDAO dao = new UserDAO();
            if(dao.logincheck(user)){
                pw.write(username+"登录成功！3秒内将为您跳转至首页。");
                request.getSession().setAttribute("user", user);
                pw.write("如果没有自动跳转，请点击<a href='/pmicu/index.jsp'>此处</a>。");
                response.setHeader("refresh", "3;url=/pmicu/index.jsp");
            }else{
                pw.write("登录失败！请检查您输入的用户名和密码，重新登录");
                pw.write("如果没有自动跳转，请点击<a href='/pmicu/login.html'>此处</a>。");
                response.setHeader("refresh", "3;url=/pmicu/login.html");
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
