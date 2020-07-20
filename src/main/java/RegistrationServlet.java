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

    /* // Testing
    private Map<String, String> data = new HashMap<>();
    private void runBefore() {
        data.put("blabla", "blabla");
        data.put("giorgi", "1234");
        data.put("levka", "java");
    }
    */

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
        httpServletRequest.getRequestDispatcher("homepage.jsp").forward(httpServletRequest, httpServletResponse);
    }

}
