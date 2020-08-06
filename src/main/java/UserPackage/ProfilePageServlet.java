package UserPackage;

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


@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
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
        int id = Integer.parseInt(httpServletRequest.getParameter("id"));
        User user = null;
        try {
            user = uDao.getUser(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setAttributes(user , httpServletRequest);
        String webPage = pageAddress + "ProfilePage.jsp";
        httpServletRequest.getRequestDispatcher(webPage).forward(httpServletRequest, httpServletResponse);
    }

    private void setAttributes(User user, HttpServletRequest httpServletRequest){
        httpServletRequest.setAttribute("name" , user.getName());
        httpServletRequest.setAttribute("surname" , user.getSurname());
        httpServletRequest.setAttribute("birthPlace" , user.getBirthPlace());
        httpServletRequest.setAttribute("status" , user.getStatus());
    }
}