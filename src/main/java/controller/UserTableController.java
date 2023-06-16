package controller;

import model.RoleModel;
import model.UserModel;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "user-table", urlPatterns = {"/user-table", "/user-table/delete", "/user-table/add", "/user-table/adjust"
                                                ,"/user-table/details"})
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
                adjustUser(req,resp);
                break;
            case "/user-table/details":
                userDetails(req,resp);
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
                adjustUser(req,resp);
                break;
            case "/user-table/details":
                userDetails(req,resp);
                break;
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
        if (req.getMethod().equalsIgnoreCase("post")){
            int id = Integer.parseInt(req.getParameter("userid"));
            UserModel userModel = userService.findById(id);
            if(userModel!= null){
                String email = req.getParameter("email");
                String password = req.getParameter("password");
                String fullname = req.getParameter("fullname");
                int role_id = Integer.parseInt(req.getParameter("role"));
                userService.adjustUser(email, password, fullname, role_id, id);
                req.setAttribute("alert","Adjust success!");
            }
            else{
                req.setAttribute("alert","Adjust failed!");
            }
        }
        String contextPath = req.getServletPath();
        System.out.println(contextPath);
        req.setAttribute("roleModelList", roleModelList);
        req.getRequestDispatcher("/user-adjust.jsp").forward(req, resp);
    }

    private void userDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<RoleModel> roleModelList = userService.getAllModels();
        req.getRequestDispatcher("/user-details.jsp").forward(req, resp);
    }
}
