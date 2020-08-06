package UserPackage;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import ProfilePackage.CookieManager;
import ServletContextPackage.ContextDataNames;

@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class UserServlet extends HttpServlet {
    private static final String pageAddress = "/WEB-INF/views/";

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        HttpSession session = httpServletRequest.getSession();
        Cookie[] cookies = httpServletRequest.getCookies();
        UserSqlDao uDao = (UserSqlDao) servletContext.getAttribute(ContextDataNames.USER_DAO);
        String userName = (String) session.getAttribute("currentUser");
        if (userName == null) {
            httpServletResponse.sendRedirect("/");
            return;
        }
        if (httpServletRequest.getParameter("button").equals("delete")) {
            int id = 0;
            try {
                id = uDao.getUserIdByName(userName);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                uDao.deleteUser(uDao.getUser(id));
                session.invalidate();
                CookieManager.deleteMyCookies(httpServletResponse, cookies);
                httpServletResponse.sendRedirect("/");
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else if (httpServletRequest.getParameter("button").equals("edit profile")) {
            httpServletResponse.sendRedirect("/EditProfile");
        } else {
            try {
                String searchName = httpServletRequest.getParameter("username");
                int id = 0;
                try {
                    id = uDao.getUserIdByName(searchName);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (uDao.containsUserName(searchName)) {
                    httpServletRequest.setAttribute("id", id);
                   // httpServletResponse.sendRedirect("/ProfilePage");
                    httpServletRequest.getRequestDispatcher(pageAddress + "ProfilePage.jsp").forward(httpServletRequest , httpServletResponse);
                } else {
                    httpServletRequest.setAttribute("error", "Username does not exist!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        String currentUser = (String) session.getAttribute("currentUser");
        String webPage = "";
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
        setAttributes(user , httpServletRequest);
        webPage = pageAddress + "UserPage.jsp";
        httpServletRequest.getRequestDispatcher(webPage).forward(httpServletRequest, httpServletResponse);
    }

    private void setAttributes(User user, HttpServletRequest httpServletRequest){
        httpServletRequest.setAttribute("name" , user.getName());
        httpServletRequest.setAttribute("surname" , user.getSurname());
        httpServletRequest.setAttribute("birthPlace" , user.getBirthPlace());
        httpServletRequest.setAttribute("status" , user.getStatus());
    }
}
