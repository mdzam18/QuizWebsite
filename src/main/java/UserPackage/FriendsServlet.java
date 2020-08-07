package UserPackage;

import FriendsPackage.FriendsSqlDao;
import ServletContextPackage.ContextDataNames;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "FriendsServlet", urlPatterns = {"/Friends"})
public class FriendsServlet extends HttpServlet {
    private static final String pageAddress = "/WEB-INF/views/";

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        String currentUser = (String) session.getAttribute("currentUser");
        if (currentUser == null) {
            httpServletResponse.sendRedirect("/");
            return;
        }
        ServletContext servletContext = getServletContext();
        UserSqlDao uDao = (UserSqlDao) servletContext.getAttribute(ContextDataNames.USER_DAO);
        int id = 0;
        try {
            id = uDao.getUserIdByName(currentUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        User user = null;
        try {
            user = uDao.getUser(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        FriendsSqlDao fDao = (FriendsSqlDao) servletContext.getAttribute(ContextDataNames.FRIENDS_DAO);
        try {
            httpServletRequest.setAttribute("friends", fDao.getFriends(user));
            httpServletRequest.setAttribute("sentRequests" , fDao.getSentRequests(user));
            httpServletRequest.setAttribute("receivedRequests", fDao.getReceivedRequests(user));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String webPage = pageAddress + "Friends.jsp";
        httpServletRequest.getRequestDispatcher(webPage).forward(httpServletRequest, httpServletResponse);
    }
}
