package ServletContextPackage;

import Administrator.dao.*;
import FriendsPackage.FriendsDao;
import FriendsPackage.FriendsSqlDao;
import HistoryPackage.*;
import MailPackage.MailDao;
import MailPackage.MailSqlDao;
import ProfilePackage.*;
import Quiz.*;
import Statistics.*;
import UserPackage.*;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebListener
public class ContextDataListener implements ServletContextListener, HttpSessionListener {

    private AdministratorDao administratorDao;
    private HistoryDao historyDao;
    private FriendsDao friendsDao;
    private MailDao mailDao;
    private QuizDao quizDao;
    private StatisticsDao statisticsDao;
    private UserDao userDao;
    private QuestionDao questionDao;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            //administratorDao = new AdministratorSqlDao();
            historyDao = new HistorySqlDao();
            friendsDao = new FriendsSqlDao();
            mailDao = new MailSqlDao();
            quizDao = new QuizSqlDao();
            //statisticsDao = new StatisticsSqlDao();
            userDao = new UserSqlDao();
            questionDao = new QuestionDao();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        ServletContext servletContext = servletContextEvent.getServletContext();

        servletContext.setAttribute(ContextDataNames.ADMINISTRATOR_DAO, administratorDao);
        servletContext.setAttribute(ContextDataNames.HISTORY_DAO, historyDao);
        servletContext.setAttribute(ContextDataNames.FRIENDS_DAO, friendsDao);
        servletContext.setAttribute(ContextDataNames.MAIL_DAO, mailDao);
        servletContext.setAttribute(ContextDataNames.QUIZ_DAO, quizDao);
        servletContext.setAttribute(ContextDataNames.STATISTICS_DAO, statisticsDao);
        servletContext.setAttribute(ContextDataNames.USER_DAO, userDao);
        servletContext.setAttribute(ContextDataNames.QUESTION_DAO, questionDao);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        // PASS
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        // PASS
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        // PASS
    }

}
