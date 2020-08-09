package Administrator.controller;

import HistoryPackage.HistoryDao;
import HistoryPackage.HistorySqlDao;
import Quiz.Quiz;
import Quiz.QuizDao;
import Quiz.QuizSqlDao;
import ServletContextPackage.ContextDataNames;
import Statistics.StatisticsDao;
import Statistics.StatisticsSqlDao;
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
import java.util.List;

@WebServlet(urlPatterns = "/admin/statistics")
public class StatisticsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        QuizDao quizDao = (QuizSqlDao) servletContext.getAttribute(ContextDataNames.QUIZ_DAO);
        UserDao userDao = (UserSqlDao) servletContext.getAttribute(ContextDataNames.USER_DAO);
        HistoryDao historyDao = (HistorySqlDao) servletContext.getAttribute(ContextDataNames.HISTORY_DAO);
        StatisticsDao statisticsDao = (StatisticsSqlDao) servletContext.getAttribute(ContextDataNames.STATISTICS_DAO);

        String userId1 = req.getParameter("field1");
        String userId2 = req.getParameter("field2");
        String quizId2 = req.getParameter("field3");
        String quizId3 = req.getParameter("field4");
        String quizId4 = req.getParameter("field5");

        req.setAttribute("field1",userId1);
        req.setAttribute("field2",userId2);
        req.setAttribute("field3",quizId2);
        req.setAttribute("field4",quizId3);
        req.setAttribute("field5",quizId4);

        if (userId1 != null && !userId1.isEmpty()) {
            try {
                List<Quiz> quizzes = statisticsDao.getAllQuizzes(Integer.valueOf(userId1));
                req.setAttribute("quizzes", quizzes);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if(userId2 != null && quizId2 != null && !userId2.isEmpty() && !quizId2.isEmpty()){
            try {
                List<Integer> scores = statisticsDao.getPastPerformances(Integer.valueOf(userId2),
                        Integer.valueOf(quizId2));
                req.setAttribute("scores", scores);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if(quizId3 != null && !quizId3.isEmpty()){
            try {
                User user = statisticsDao.getBestPlayer(Integer.valueOf(quizId3));
                req.setAttribute("user", user);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if(quizId4 != null && !quizId4.isEmpty()){
            try {
                double avg = statisticsDao.getAverageScore(Integer.valueOf(quizId4));
                req.setAttribute("avg", avg);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/statistics.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}