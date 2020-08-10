package LoginPackage;

import ProfilePackage.CookieManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Map;

@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {

    private static final String address = "/WEB-INF/views/";
    private final static String currentUser = "currentUser";

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        Cookie[] cookies = httpServletRequest.getCookies();
        Map<String, Cookie> map = CookieManager.getData(cookies);
        if(map == null) {
            toWelcomePage(httpServletRequest, httpServletResponse);
        } else {
            String username = map.get(CookieManager.USERNAME_KEY).getValue().trim();

            HttpSession session = httpServletRequest.getSession();
            session.setAttribute(currentUser, username);

            httpServletResponse.sendRedirect("/UserServlet");
           // httpServletRequest.getRequestDispatcher(address + "UserPage.jsp").forward(httpServletRequest, httpServletResponse);
        }
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        Cookie[] cookies = httpServletRequest.getCookies();
        CookieManager.deleteMyCookies(httpServletResponse, cookies);
        HttpSession session = httpServletRequest.getSession();
        session.invalidate();
        toWelcomePage(httpServletRequest, httpServletResponse);
    }

    private void toWelcomePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("welcome.jsp").forward(request, response);
    }

}
