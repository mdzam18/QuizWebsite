import ProfilePackage.CookieManager;
import ServletContextPackage.ContextDataNames;
import UserPackage.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {

    private final static String USERNAME_BUSY = "USERNAME_IS_BUSY";
    private final static String SUCCESS = "SUCCESS";
    private UserDao userDao;

    private final static String currentUser = "currentUser";

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletResponse.setContentType("text/plain");
        userDao = (UserDao) getServletContext().getAttribute(ContextDataNames.USER_DAO);

        String username = httpServletRequest.getParameter("username").trim();

        PrintWriter out = httpServletResponse.getWriter();
        try {
            if(userDao.containsUserName(username)) {
                out.print(USERNAME_BUSY);
            } else {
                out.print(SUCCESS);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String username = httpServletRequest.getParameter("username").trim();
        String password = httpServletRequest.getParameter("password").trim();
        userDao = (UserDao) getServletContext().getAttribute(ContextDataNames.USER_DAO);

        try {
            userDao.addUser(username, password, false);

            HttpSession session = httpServletRequest.getSession();
            session.setAttribute(currentUser, username);

            String path = httpServletRequest.getContextPath();
            Cookie[] newCookies = CookieManager.convertToCookies(path, username, password);
            CookieManager.cookiesToResponse(httpServletResponse, newCookies);

            httpServletRequest.getRequestDispatcher("UserPage.jsp").forward(httpServletRequest, httpServletResponse);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
