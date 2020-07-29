package Administrator.controller;

import Administrator.dao.AdministratorDao;
import Administrator.dao.AdministratorSqlDao;
import Quiz.Quiz;
import Quiz.QuizDao;
import Quiz.QuizSqlDao;
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

@WebServlet(urlPatterns = "/admin/quizzes",
            initParams = {@WebInitParam(name = "id", value = "{id}")})
public class QuizzesController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        QuizDao quizDao = (QuizSqlDao) servletContext.getAttribute("quizDao");

        String id = req.getParameter("id");
        if (id != null) {
            try {
                Quiz quiz = quizDao.getQuiz(Integer.valueOf(id));
                quizDao.deleteQuiz(quiz);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        try {
            req.setAttribute("quizzes", quizDao.getAllQuizzes());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/quizzes.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}