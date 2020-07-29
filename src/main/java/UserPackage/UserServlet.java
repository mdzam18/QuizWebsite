package UserPackage;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import FriendsPackage.FriendsDao;
import FriendsPackage.FriendsSqlDao;
import ServletContextPackage.ContextDataNames;

@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class UserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        UserSqlDao uDao = (UserSqlDao) servletContext.getAttribute(ContextDataNames.USER_DAO);
        String name = httpServletRequest.getParameter("username");
        int id = 0;
        try {
            id = uDao.getUserIdByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(httpServletRequest.getParameter("button").equals("delete")){
            try {
                if(uDao.deleteUser(uDao.getUser(id))) {
                    System.out.println("sasuke");
                } else {
                    System.out.println("naruto");
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            String webPageName = "ProfilePage.jsp?id=" + id;
            try {
                if (uDao.containsUserName(name)) {
                    httpServletRequest.getRequestDispatcher(webPageName).forward(httpServletRequest, httpServletResponse);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        UserSqlDao uDao = (UserSqlDao) servletContext.getAttribute(ContextDataNames.USER_DAO);
        FriendsDao fDao = null;
        try {
            fDao = new FriendsSqlDao();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String name = httpServletRequest.getParameter("username");
        String webPageName = "FriendsPage.jsp";
        try {
            assert fDao != null;
            User user = uDao.getUser(uDao.getUserIdByName(name));
            servletContext.setAttribute(ContextDataNames.FRIENDS_DAO, fDao.getFriends(user));
            servletContext.setAttribute(ContextDataNames.Received_Requests, fDao.getReceivedRequests(user));
            servletContext.setAttribute(ContextDataNames.Sent_Requests, fDao.getSentRequests(user));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        httpServletRequest.getRequestDispatcher(webPageName).forward(httpServletRequest, httpServletResponse);
    }
}
