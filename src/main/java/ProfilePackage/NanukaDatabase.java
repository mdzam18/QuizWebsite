package ProfilePackage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NanukaDatabase {
    private static Connection conn;

    public static Connection getConnection() {
        // SQLite connection string
        if(conn != null) {
            return conn;
        }
        String url = "jdbc:sqlite:C:/Users/Nanuka/Desktop/SQLite/mydatabase.db";
        conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
