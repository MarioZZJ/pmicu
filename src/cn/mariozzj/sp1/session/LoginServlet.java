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
        String email = request.getParameter("email");
        String token = request.getParameter("token");
        PrintWriter pw = response.getWriter();
        if("".equals(email)) {
            DBLoginCheck dblc = new DBLoginCheck();
            if (dblc.logincheck(username, password)) {
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                request.getSession().setAttribute("user", user);
                request.getSession().setAttribute("email", email);
                response.sendRedirect("/pmicu/IndexServlet");
            } else {
                pw.write("登录错误，请检查用户名和密码是否输入正确。");
            }
        }
        else{(new DBRegister()).register(username,password,email,token);
            response.sendRedirect("/pmicu/login.html");}
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
