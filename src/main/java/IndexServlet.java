import ProfilePackage.CookieManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Map;

@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {

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

            httpServletRequest.getRequestDispatcher("UserPage.jsp").forward(httpServletRequest, httpServletResponse);
        }
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        Cookie[] cookies = httpServletRequest.getCookies();
        CookieManager.deleteMyCookies(httpServletResponse, cookies);
        toWelcomePage(httpServletRequest, httpServletResponse);
    }

    private void toWelcomePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("welcome.jsp").forward(request, response);
    }

}
