import ProfilePackage.CookieManager;
import ServletContextPackage.ContextDataNames;
import UserPackage.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private final static String NO_USERNAME = "USERNAME_NOT_EXISTS";
    private final static String NO_PASSWORD = "INCORRECT_PASSWORD";
    private final static String SUCCESS = "SUCCESS";
    private UserDao userDao;

    private final static String currentUser = "currentUser";

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletResponse.setContentType("text/plain");
        userDao = (UserDao) getServletContext().getAttribute(ContextDataNames.USER_DAO);

        String username = httpServletRequest.getParameter("username").trim();
        String password = httpServletRequest.getParameter("password").trim();

        PrintWriter out = httpServletResponse.getWriter();
        try {
            if(!userDao.containsUserName(username)) {
                out.print(NO_USERNAME);
            } else {
                if(userDao.isCorrectPassword(username, password)) {
                    out.print(SUCCESS);
                } else {
                    out.print(NO_PASSWORD);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String username = httpServletRequest.getParameter("username").trim();
        String password = httpServletRequest.getParameter("password").trim();
        boolean remember = (httpServletRequest.getParameter("remember") != null);

        HttpSession session = httpServletRequest.getSession();
        session.setAttribute(currentUser, username);

        Cookie[] cookies = httpServletRequest.getCookies();
        if(remember) {
            Map<String, Cookie> map = CookieManager.getData(cookies);
            if(map != null) {
                for(Cookie c : map.values()) {
                    c.setMaxAge(CookieManager.THREE_WEEKS);
                }
            } else {
                String path = httpServletRequest.getContextPath();
                Cookie[] newCookies = CookieManager.convertToCookies(path, username, password);
                CookieManager.cookiesToResponse(httpServletResponse, newCookies);
            }
        } else {
            if(cookies != null) {
                CookieManager.deleteMyCookies(httpServletResponse, cookies);
            }
        }

        try {
            if(userDao.getUser(userDao.getUserIdByName(username)).isAdministrator()){
                httpServletRequest.setAttribute("isAdmin", 1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //httpServletRequest.getRequestDispatcher("UserPage.jsp").forward(httpServletRequest, httpServletResponse);
        httpServletResponse.sendRedirect("/MyPage");
    }

}
