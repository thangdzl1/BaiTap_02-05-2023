package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

//urlPattern: Khi người dùng gọi link được qui định trong urlPattern thì url sẽ được kích hoạt
//@WebServlet(urlPatterns = {"/login"})
public class CustomFilter implements Filter {
    @Override
    public void destroy() {
//        Filter.super.destroy();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //Nơi qui định rule
        System.out.println("Filter đã được kích hoạt!");
        //Cho phép đi vào link mà người dùng request
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
