package Administrator.controller;

import Administrator.dao.AdministratorDao;
import Administrator.dao.AdministratorSqlDao;
import ServletContextPackage.ContextDataNames;
import UserPackage.User;
import UserPackage.UserDao;
import UserPackage.UserSqlDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/admin/admins",
        initParams = {@WebInitParam(name = "id", value = "{id}")})
public class AdministratorsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        AdministratorDao adminDao = (AdministratorSqlDao) servletContext.getAttribute(ContextDataNames.ADMINISTRATOR_DAO);
        UserDao userDao = (UserSqlDao) servletContext.getAttribute(ContextDataNames.USER_DAO);

        String id = req.getParameter("id");
        if (id != null) {
            try {
                User user = userDao.getUser(Integer.valueOf(id));
                adminDao.promoteUser(user);
                req.setAttribute("users", userDao.getAllUsers());
                RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/users.jsp");
                dispatcher.forward(req,resp);
                return;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        try {
            req.setAttribute("admins", adminDao.getAllAdmins());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admins.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}