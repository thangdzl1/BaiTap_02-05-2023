package controller;

import com.mysql.cj.Session;
import config.MysqlConfig;
import model.UserModel;
import service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "loginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    private LoginService loginService = new LoginService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Tao object cookie
//        Cookie cookie = new Cookie("username", "nguyenvana");
//        //Yeu cau tao cookie tren may
//        resp.addCookie(cookie);
//        Cookie[] cookies = req.getCookies();
//        for (Cookie item : cookies) {
//            if (item.getName().equals("username")){
//                System.out.println("Kiem tra: " + item.getValue());
//            }
//        }

        HttpSession session = req.getSession();
        Object username = session.getAttribute("username");
        Object password = session.getAttribute("password");
        req.setAttribute("username", username);
        req.setAttribute("password", password);
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String save = req.getParameter("save");

        boolean isSuccess = loginService.checkLogin(username, password);
        if (isSuccess) {
            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/user-table");
            if (save != null){
                HttpSession session = req.getSession();
                session.setAttribute("username", username);
                session.setAttribute("password", password);
            }
        } else {
            PrintWriter writer = resp.getWriter();
            writer.println("Login Fail !");
            writer.close();
        }
    }
}
