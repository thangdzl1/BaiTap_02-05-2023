package controller;

import model.UserModel;
import repository.UserRepository;
import service.DashboardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "dashboardController", urlPatterns = {"/dashboard"})
public class DashboardController extends HttpServlet {
    private DashboardService dashboardService = new DashboardService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserModel> list = dashboardService.getAllUser();
        req.setAttribute("listUser",list);
        req.getRequestDispatcher("user-table.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
