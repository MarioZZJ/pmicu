package cn.mariozzj.sp2.Servlet;

import cn.mariozzj.sp2.dao.TokenDAO;
import cn.mariozzj.sp2.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/RegisCheckServlet")
public class RegisCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String checkName = req.getParameter("checkName");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter pw = resp.getWriter();
        System.out.println("post");
        if("username".equals(checkName)){
            String userName = req.getParameter("username");
            System.out.println("checkusername");
            boolean flag = new UserDAO().exist(userName);
            pw.write(flag?"该用户名已存在":"");
        }else if("token".equals(checkName)){
            String tokenname = req.getParameter("token");
            System.out.println("checktoken");
            boolean flag = new TokenDAO().hasUser(tokenname);
            pw.write(flag?"该Token已被使用":"");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
