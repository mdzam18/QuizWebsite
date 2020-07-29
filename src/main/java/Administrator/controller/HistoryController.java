package Administrator.controller;

import Administrator.dao.AdministratorDao;
import Administrator.dao.AdministratorSqlDao;
import HistoryPackage.History;
import HistoryPackage.HistoryDao;
import HistoryPackage.HistorySqlDao;
import UserPackage.User;

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
import java.util.List;

@WebServlet(urlPatterns = "/admin/history",
        initParams = {@WebInitParam(name = "userId", value = "{userId}"),
                @WebInitParam(name = "quizId", value = "{quizId}")})
public class HistoryController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        HistoryDao historyDao = (HistorySqlDao) servletContext.getAttribute("historyDao");

        String userId = req.getParameter("userId");
        String quizId = req.getParameter("quizId");

        if (userId != null && quizId != null) {
            try {
                List<History> histories = historyDao.getHistories(Integer.valueOf(userId));
                /** Add id to historyDao (for deletion) **/
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        try {
            req.setAttribute("histories", historyDao.getAllHistories());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/history.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
