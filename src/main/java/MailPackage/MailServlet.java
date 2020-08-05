package MailPackage;

import FriendsPackage.FriendsSqlDao;
import HistoryPackage.HistorySqlDao;
import MailPackage.Mail;
import MailPackage.MailSqlDao;
import Quiz.QuizSqlDao;
import ServletContextPackage.ContextDataNames;
import UserPackage.UserSqlDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

@WebServlet(name = "MailServlet", urlPatterns = {"/MailServlet"})
public class MailServlet extends HttpServlet {
    private MailSqlDao mailDao;
    private UserSqlDao userDao;
    private HistorySqlDao historyDao;
    private QuizSqlDao quizSqlDao;
    private FriendsSqlDao friendsDao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        mailDao = (MailSqlDao) getServletContext().getAttribute(ContextDataNames.MAIL_DAO);
        userDao = (UserSqlDao) getServletContext().getAttribute(ContextDataNames.USER_DAO);
        //historyDao = (HistorySqlDao) getServletContext().getAttribute(ContextDataNames.HISTORY_DAO);
        //quizSqlDao = (QuizSqlDao) getServletContext().getAttribute(ContextDataNames.QUIZ_DAO);
        friendsDao = (FriendsSqlDao) getServletContext().getAttribute(ContextDataNames.FRIENDS_DAO);


        String current = String.valueOf(request.getSession().getAttribute("currentUser"));
        int senderId = 0;
        try {
            senderId = userDao.getUserIdByName(current);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int receiverId = 0;
        String message = "";
        String type = request.getParameter("button");


        try {
            PrintWriter out = response.getWriter();
            if (type.equals("send")) {
                String to = request.getParameter("username");
                receiverId = userDao.getUserIdByName(to);
                if (receiverId == -1) {
                    //out.println("user doesn't exist");
                    request.getRequestDispatcher("UserPage.jsp").forward(request, response);
                } else {
                    message = request.getParameter("message");
                    mailDao.sendMail(senderId, receiverId, Mail.noteType, message, new java.sql.Date(System.currentTimeMillis()), false);
                    //out.println("message successfully sent");
                    request.getRequestDispatcher("UserPage.jsp").forward(request, response);
                }
            } else if (type.equals("challenge")) {
                String[] desc = request.getParameter("quizzes").split(" - ");
                String author = desc[0];
                String description = desc[1];
                receiverId = userDao.getUserIdByName(request.getParameter("username"));

                int authorId = userDao.getUserIdByName(author);
                int quizId = quizSqlDao.getQuizId(authorId, description);
                int score = historyDao.getMaxScore(senderId, quizId);
                if (score == -1) {
                    //out.println("you have never written this quiz");
                    request.getRequestDispatcher("UserPage.jsp").forward(request, response);
                } else {
                    mailDao.sendMail(senderId, receiverId, Mail.challengeType, quizId + "", new java.sql.Date(System.currentTimeMillis()), false);
                   //out.println("challenge successfully sent");
                    request.getRequestDispatcher("UserPage.jsp").forward(request, response);
                }
            } else if (type.equals("sendRequest") || type.equals("sendRequestFromProfile")) {
                receiverId = userDao.getUserIdByName(request.getParameter("username"));
                checkRequestIds(receiverId, senderId, request, out);
                request.getRequestDispatcher("UserPage.jsp").forward(request, response);
            } else if (type.equals("confirmRequest")) {
                receiverId = Integer.parseInt(request.getParameter("username"));
                friendsDao.confirmFriendRequest(receiverId, senderId);
                request.getRequestDispatcher("UserPage.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }

    }

    //Checks if friend request can be sent;
    private void checkRequestIds(int receiverId, int senderId, HttpServletRequest request, PrintWriter out) throws SQLException {
        if (receiverId == -1) {
            out.println("user doesn't exist");
        } else if (friendsDao.areFriends(senderId, receiverId)) {
            out.println("you are already friends");
        } else if (friendsDao.isRequested(receiverId, senderId)) {
            out.println(request.getParameter("username") + " sent you friend request, you can confirm");
        } else if (friendsDao.isRequested(senderId, receiverId)) {
            out.println("You have already sent request.");
        } else if (senderId == receiverId) {
            out.println("Invalid User.");
        } else {
            friendsDao.sendFriendRequest(senderId, receiverId);
            out.println("friend request sent successfully");
        }
    }
}
