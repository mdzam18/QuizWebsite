package ProfilePackage;

import HistoryPackage.HistorySqlDao;
import ProfilePackage.Mail;
import ProfilePackage.MailSqlDao;
import Quiz.QuizSqlDao;
import ServletContextPackage.ContextDataNames;
import UserPackage.UserSqlDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Calendar;
import java.sql.Date;

@WebServlet(name = "MailServlet", urlPatterns = {"/MailServlet"})
public class MailServlet extends HttpServlet {
    private MailSqlDao mailDao;
    private UserSqlDao userDao;
    private HistorySqlDao historyDao;
    private QuizSqlDao quizSqlDao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        mailDao = (MailSqlDao) getServletContext().getAttribute(ContextDataNames.MAIL_DAO);
        userDao = (UserSqlDao) getServletContext().getAttribute(ContextDataNames.USER_DAO);

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        mailDao = (MailSqlDao) getServletContext().getAttribute(ContextDataNames.MAIL_DAO);
        userDao = (UserSqlDao) getServletContext().getAttribute(ContextDataNames.USER_DAO);
        historyDao = (HistorySqlDao) getServletContext().getAttribute(ContextDataNames.HISTORY_DAO);
        quizSqlDao = (QuizSqlDao) getServletContext().getAttribute(ContextDataNames.QUIZ_DAO);

        int senderId = 2;
        int receiverId = 0;
        String message  = "";
        // Date date = new Date();

        String type = request.getParameter("button");


        try {
            PrintWriter out = response.getWriter();
            if(type.equals("send")) {
                String to = request.getParameter("username");
                receiverId = userDao.getUserIdByName(to);
                if (receiverId == -1) {
                    out.println("user doesn't exist");
                } else {
                    message = request.getParameter("message");
                    //int senderId, int receiverId, String noteType, String message, Date date, boolean isSeen
                    mailDao.sendMail(senderId, receiverId, Mail.noteType, message, new Date(System.currentTimeMillis()), false);
                    out.println("message successfully sent");
                }
            } else if(type.equals("challenge")){
                String desc = request.getParameter("description");
                int quizId = quizSqlDao.getQuizIdByName(desc);
                int score = historyDao.getMaxScore(senderId, quizId);
                if(score == -1){
                    out.println("you have never written thi quiz");
                } else {
                    mailDao.sendMail(senderId,receiverId,Mail.challengeType, quizId + "", new Date(System.currentTimeMillis()), false);
                    out.println("challenge successfully sent");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
