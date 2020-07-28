package UserPackage;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import ProfilePackage.FriendsDao;
import ProfilePackage.FriendsSqlDao;
import ServletContextPackage.ContextDataNames;

@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class UserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        UserSqlDao uDao = (UserSqlDao) servletContext.getAttribute(ContextDataNames.USER_DAO);
        String name = httpServletRequest.getParameter("username");
        String webPageName = "ProfilePage.jsp";
        try {
            if (uDao.containsUserName(name)) {
                httpServletRequest.getRequestDispatcher(webPageName).forward(httpServletRequest, httpServletResponse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
            servletContext.setAttribute(ContextDataNames.FRIENDS_DAO, fDao.getFriends(uDao.getUser(uDao.getUserIdByName(name))));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        httpServletRequest.getRequestDispatcher(webPageName).forward(httpServletRequest, httpServletResponse);
    }
}
