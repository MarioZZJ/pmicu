package cn.mariozzj.sp2.filter;

import cn.mariozzj.sp2.bean.VisitRecord;
import cn.mariozzj.sp2.dao.VisitRecordDAO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebFilter(urlPatterns = "/*")
public class GlobalFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
//        chain.doFilter(req, resp); //doFilter代码重复可能导致jsp页面内容重复
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        request.setCharacterEncoding("UTF-8");//在全局Filter中对中文进行适配
        String url = String.valueOf(((HttpServletRequest) req).getRequestURL());
        if(url.contains("/js") || url.contains("/image") || url.contains("/css") || url.contains("/fonts")) {}
        else{
            VisitRecord record = new VisitRecord();
            record.ipaddr = request.getRemoteAddr();
            record.location = (new Location()).GetLocation(record.ipaddr);
            record.url = request.getRequestURL().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d = new Date();
            record.date = sdf.format(d);
            VisitRecordDAO vdao = new VisitRecordDAO();
            vdao.add(record);
            System.out.printf("[P.M.I.C.U-INFO]%s %s has just requested %s%n", record.date, record.ipaddr, record.url);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
