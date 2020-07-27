import ServletContextPackage.ContextDataNames;
import UserPackage.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.io.PrintWriter;
import java.sql.SQLException;

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
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute(currentUser, httpServletRequest.getParameter("username").trim());

        httpServletRequest.getRequestDispatcher("homepage.jsp").forward(httpServletRequest, httpServletResponse);
    }

}
