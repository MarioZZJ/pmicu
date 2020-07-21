package cn.mariozzj.sp1.session;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;

@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user==null){
            response.getWriter().print("您还没有登录！请<a href='/pmicu/login.jsp'>登录</a>");
        }else {
            response.getWriter().print("您已登录！欢迎" + user.getUsername());
            response.getWriter().print("<a href=/pmicu/LogoutServlet>退出</a>");

            // 创建Cookie存放Session的标识号
            Cookie cookie = new Cookie("JSESSIONID", session.getId());
            cookie.setMaxAge(30 * 60);
            cookie.setPath("/pmicu");
            response.addCookie(cookie);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);

    }
}
