package ProfilePackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ProfileDataSrc {
    private static Connection con;

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if (con != null) {
            return con;
        }
        Class.forName("com.mysql.jdbc.Driver");
        con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "itachi!");
        //con = (Connection) getConnection("test", "root", "01234567");
        return con;
    }

    public static Connection getConnection(String database, String user, String password) throws SQLException, ClassNotFoundException {
        //Class.forName("com.mysql.jdbc.Driver");
        Connection connection = (Connection) DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/" + database + "?serverTimezone=GMT%2B4&characterEncoding=UTF8",
                user,
                password);
        return connection;
    }

}
