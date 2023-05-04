package filter;

import service.LoginService;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

//urlPattern: Khi người dùng gọi link được qui định trong urlPattern thì url sẽ được kích hoạt
@WebServlet(urlPatterns = {"/dashboard"})
public class AuthFilter implements Filter {
    LoginService loginService = new LoginService();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //Nơi qui định rule
        boolean LoggedIn = loginService.checkLogin();
        System.out.println("Filter đã được kích hoạt!");
        //Cho phép đi vào link mà người dùng request
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
