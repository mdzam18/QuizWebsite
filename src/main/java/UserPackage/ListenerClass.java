package UserPackage;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebListener
public class ListenerClass implements ServletContextListener {
    private UserSqlDao uDao;
    public static final String uDaoAttribute = "UserSql";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            uDao = new UserSqlDao();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute(uDaoAttribute, uDao);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
