package cn.mariozzj.sp1.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        PrintWriter pw = response.getWriter();

        DBLoginCheck dblc = new DBLoginCheck();
        if(dblc.logincheck(username,password)){
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            request.getSession().setAttribute("user",user);
            pw.write("登录成功，牛逼啊！");
            response.sendRedirect("/pmicu/IndexServlet");
        }else {
            pw.write("错误！");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
