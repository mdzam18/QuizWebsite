package UserPackage;

import ServletContextPackage.ContextDataNames;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "ProfilePageServlet", urlPatterns = {"/ProfilePage"}, initParams = {@WebInitParam(name = "id", value = "{id}")})
public class ProfilePageServlet extends HttpServlet {
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
       // int id = (Integer)(httpServletRequest.getAttribute("id"));
        String id = httpServletRequest.getParameter("id");
        if (id != null) {
            try {
                User user = uDao.getUser(Integer.valueOf(id));
                setAttributes(user , httpServletRequest);
                String webPage = pageAddress + "ProfilePage.jsp";
                httpServletRequest.getRequestDispatcher(webPage).forward(httpServletRequest, httpServletResponse);
                return;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private void setAttributes(User user, HttpServletRequest httpServletRequest){
        httpServletRequest.setAttribute("name" , user.getName());
        httpServletRequest.setAttribute("username" , user.getUserName());
        httpServletRequest.setAttribute("surname" , user.getSurname());
        httpServletRequest.setAttribute("birthPlace" , user.getBirthPlace());
        httpServletRequest.setAttribute("status" , user.getStatus());
    }
}