package controller;

import model.*;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "user-table", urlPatterns = {"/user-table", "/user-table/delete", "/user-table/add", "/user-table/adjust"
        , "/user-table/details"})
public class UserTableController extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/user-table":
                getAllUser(req, resp);
                break;
            case "/user-table/delete":
                deleteUser(req, resp);
                break;
            case "/user-table/add":
                addUser(req, resp);
                break;
            case "/user-table/adjust":
                adjustUser(req, resp);
                break;
            case "/user-table/details":
                userDetails(req, resp);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/user-table":
                getAllUser(req, resp);
                break;
            case "/user-table/delete":
                deleteUser(req, resp);
                break;
            case "/user-table/add":
                addUser(req, resp);
                break;
            case "/user-table/adjust":
                adjustUser(req, resp);
                break;
//            case "/user-table/details":
//                userDetails(req,resp);
//                break;
            default:
                break;
        }
    }

    private void getAllUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getMethod().toLowerCase().equals("get")) {
            List<RoleModel> roleModelList = userService.getAllModels();
            List<UserModel> list = userService.getAllUser();
            req.setAttribute("listUsers", list);
            req.setAttribute("listRole", roleModelList);
            req.getRequestDispatcher("user-table.jsp").forward(req, resp);
        }
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        userService.deleteUser(id);
        req.getRequestDispatcher("/user-table.jsp").forward(req, resp);
    }

    private void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<RoleModel> roleModelList = userService.getAllModels();
        if (req.getMethod().equalsIgnoreCase("post")) {
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String fullname = req.getParameter("fullname");
            int role_id = Integer.parseInt(req.getParameter("role"));
            userService.addUser(email, password, fullname, role_id);
        }
        req.setAttribute("roleModelList", roleModelList);
        req.getRequestDispatcher("/user-add.jsp").forward(req, resp);
    }

    private void adjustUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<RoleModel> roleModelList = userService.getAllModels();
        if (req.getMethod().equalsIgnoreCase("post")) {
            int id = Integer.parseInt(req.getParameter("userid"));
            UserModel userModel = userService.findById(id);
            if (userModel != null) {
                String email = req.getParameter("email");
                String password = req.getParameter("password");
                String fullname = req.getParameter("fullname");
                int role_id = Integer.parseInt(req.getParameter("role"));
                userService.adjustUser(email, password, fullname, role_id, id);
                req.setAttribute("alert", "Adjust success!");
            } else {
                req.setAttribute("alert", "Adjust failed!");
            }
        }
        String contextPath = req.getServletPath();
        System.out.println(contextPath);
        req.setAttribute("roleModelList", roleModelList);
        req.getRequestDispatcher("/user-adjust.jsp").forward(req, resp);
    }

    private void userDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userid = Integer.parseInt(req.getParameter("id"));
        UserModel userModel = userService.findById(userid);
        if (userModel != null) {
            List<TaskModel> taskList = userService.findTaskByUserId(userid);
            List<StatusModel> statusList = new ArrayList<>();
            for (int i = 0; i < taskList.size(); i++) {
                statusList.add(userService.findStatusById((taskList.get(i)).getStatus_id()));
            }
            DecimalFormat df = new DecimalFormat("#.##");
            double statusType1 = ((double) userService.findNumberOfTaskByStatusId(1) / taskList.size() * 100);
            double statusType2 = ((double) userService.findNumberOfTaskByStatusId(2) / taskList.size() * 100);
            double statusType3 = ((double) userService.findNumberOfTaskByStatusId(3) / taskList.size() * 100);
            df.format(statusType1);
            df.format(statusType2);
            df.format(statusType3);
            req.setAttribute("statusType1", statusType1);
            req.setAttribute("statusType2", statusType2);
            req.setAttribute("statusType3", statusType3);
            req.setAttribute("user", userModel);
            req.setAttribute("statusList", statusList);
            req.setAttribute("taskList", taskList);
        }
        req.getRequestDispatcher("/user-details.jsp").forward(req, resp);
    }
}
