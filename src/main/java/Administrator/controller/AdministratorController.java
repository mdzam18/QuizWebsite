package Administrator.controller;

import Administrator.dao.AdministratorDao;
import Administrator.dao.AdministratorSqlDao;
import ServletContextPackage.ContextDataNames;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin")
public class AdministratorController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        AdministratorDao adminDao = (AdministratorSqlDao) servletContext.getAttribute(ContextDataNames.ADMINISTRATOR_DAO);

        RequestDispatcher dispatcher= req.getRequestDispatcher("/WEB-INF/views/admin.jsp");
        dispatcher.forward(req,resp);
    }

}