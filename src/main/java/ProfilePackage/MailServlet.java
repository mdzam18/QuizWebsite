package ProfilePackage;

import ServletContextPackage.ContextDataNames;
import UserPackage.UserSqlDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet(name = "MailServlet", urlPatterns = {"/MailServlet"})
public class MailServlet extends HttpServlet {
    private MailSqlDao mailDao;
    private UserSqlDao userDao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        mailDao = (MailSqlDao) getServletContext().getAttribute(ContextDataNames.MAIL_DAO);
        userDao = (UserSqlDao) getServletContext().getAttribute(ContextDataNames.USER_DAO);

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        mailDao = (MailSqlDao) getServletContext().getAttribute(ContextDataNames.MAIL_DAO);
        userDao = (UserSqlDao) getServletContext().getAttribute(ContextDataNames.USER_DAO);

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
            } else if(type.equals("read")){

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
