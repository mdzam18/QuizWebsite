package ProfilePackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ProfileDataSrc {
    private static Connection con;
    private String tableName;

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if (con != null) {
            return con;
        }
        Class.forName("com.mysql" +
                ".jdbc.Driver");
        con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/", "", "");
        return con;
    }

    //Returns name of the table.
    public String getTableName() {
        return "";
    }

    //Sets table name.
    public void setTableName(String str) {
        tableName = str;
    }

}
