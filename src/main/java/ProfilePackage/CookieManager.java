package ProfilePackage;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class CookieManager {

    public static final int ONE_WEEK = 1000*60*60*24*7;
    public static final int THREE_WEEKS = 3*ONE_WEEK;
    public static final int DELETE_COOKIE = 0;

    public static final String USERNAME_NAME_COOKIE = "QUIZ_APP_COOKIE_USERNAME";
    public static final String PASSWORD_NAME_COOKIE = "QUIZ_APP_COOKIE_PASSWORD";
    public static final String USERNAME_KEY = "USERNAME_KEY";
    public static final String PASSWORD_KEY = "PASSWORD_KEY";

    public static Map<String, Cookie> getData(Cookie[] cookies) {
        if(cookies == null) return null;

        Cookie userName = null, password = null;
        for(int i = 0; i<cookies.length; i++) {
            Cookie cookie = cookies[i];
            if(cookie.getName().equals(USERNAME_NAME_COOKIE)) {
                userName = cookie;
            } else if(cookie.getName().equals(PASSWORD_NAME_COOKIE)) {
                password = cookie;
            }
            if(userName != null && password != null) {
                Map<String, Cookie> map = new HashMap<>();
                map.put(USERNAME_KEY, userName);
                map.put(PASSWORD_KEY, password);
                return map;
            }
        }
        return null;
    }

    public static void deleteMyCookies(HttpServletResponse response, Cookie[] cookies) {
        if(cookies == null) return;

        for(int i = 0; i<cookies.length; i++) {
            Cookie cookie = cookies[i];
            if(cookie.getName().equals(USERNAME_NAME_COOKIE)
            || cookie.getName().equals(PASSWORD_NAME_COOKIE)) {
                cookie.setMaxAge(DELETE_COOKIE);
                cookie.setValue(null);
                response.addCookie(cookie);
            }
        }
    }

    public static Cookie[] convertToCookies(String path, String user, String password) {
        Cookie[] cookies = new Cookie[2];

        cookies[0] = new Cookie(USERNAME_NAME_COOKIE, user);
        cookies[0].setComment("User Name");
        cookies[0].setPath(path);

        cookies[1] = new Cookie(PASSWORD_NAME_COOKIE, password);
        cookies[1].setComment("Password");
        cookies[1].setPath(path);

        return cookies;
    }

    public static void cookiesToResponse(HttpServletResponse response, Cookie[] cookies) {
        if(response != null && cookies != null) {
            for(int i = 0; i<cookies.length; i++) {
                response.addCookie(cookies[i]);
            }
        }
    }

}
