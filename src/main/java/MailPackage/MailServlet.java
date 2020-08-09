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
    private static final String pageAddress = "/WEB-INF/views/";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        mailDao = (MailSqlDao) getServletContext().getAttribute(ContextDataNames.MAIL_DAO);
        userDao = (UserSqlDao) getServletContext().getAttribute(ContextDataNames.USER_DAO);
        historyDao = (HistorySqlDao) getServletContext().getAttribute(ContextDataNames.HISTORY_DAO);
        quizSqlDao = (QuizSqlDao) getServletContext().getAttribute(ContextDataNames.QUIZ_DAO);
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
                    out.println("user doesn't exist");
                } else {
                    message = request.getParameter("message");
                    mailDao.sendMail(senderId, receiverId, Mail.noteType, message, new java.sql.Date(System.currentTimeMillis()), false);
                    out.println("message was sent successfully");
                }
            } else if (type.equals("challenge")) {
                receiverId = userDao.getUserIdByName(request.getParameter("username"));
                if (receiverId == -1) {
                    out.println("user doesn't exist");
                    return;
                }
                String[] desc = request.getParameter("quizzes").split(" - ");
                String author;
                String description;
                if(desc.length != 2){
                    out.println("invalid format");
                    return;
                } else {
                    author = desc[0];
                    description = desc[1];
                }

                int authorId = userDao.getUserIdByName(author);
                int quizId = quizSqlDao.getQuizId(authorId, description);
                int score = historyDao.getMaxScore(senderId, quizId);
                if (score == -1) {
                    out.println("you have never written this quiz");
                } else {
                    mailDao.sendMail(senderId, receiverId, Mail.challengeType, quizId + "", new java.sql.Date(System.currentTimeMillis()), false);
                    out.println("challenge was sent successfully");
                }
            } else if (type.equals("sendRequest") || type.equals("sendRequestFromProfile")) {
                String bla = request.getParameter("username");
                receiverId = userDao.getUserIdByName(bla);
                checkRequestIds(receiverId, senderId, request, out);
            } else if (type.equals("confirmRequest")) {
                receiverId = Integer.parseInt(request.getParameter("username"));
                friendsDao.confirmFriendRequest(receiverId, senderId);
                response.sendRedirect("/UserServlet");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
