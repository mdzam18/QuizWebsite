package UserPackage;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class UserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        UserSqlDao uDao = (UserSqlDao) servletContext.getAttribute(ListenerClass.uDaoAttribute);
        String name = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");
        String webPageName = "";
      /*  if (accountManager.containsName(name)) {
            webPageName = "nameIsAlreadyUsedPage.jsp";
        } else {
            Account account = new Account(name, password);
            accountManager.createAccount(account);
            webPageName = "welcome.jsp";
        } */
        httpServletRequest.getRequestDispatcher(webPageName).forward(httpServletRequest, httpServletResponse);
    }
}
