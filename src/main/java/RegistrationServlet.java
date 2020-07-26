import ServletContextPackage.ContextDataNames;
import UserPackage.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class RegistrationServlet extends HttpServlet {

    private final static String USERNAME_BUSY = "USERNAME_IS_BUSY";
    private final static String SUCCESS = "SUCCESS";
    private UserDao userDao;

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

            httpServletRequest.getRequestDispatcher("homepage.jsp").forward(httpServletRequest, httpServletResponse);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
