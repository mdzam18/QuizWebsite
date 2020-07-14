package ProfilePackage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTablesForTests {
    public static final String FriendsTableTest = "test.Friends2";
    public static final String UsersTableTest = "test.Users2";
    public static String UsersTable = "test.Users";
    public static String FriendsTable = "test.Friends";
    public static String MailsTableTest = "test.Mails2";
    public static String MailsTable = "test.Mails";
    public static final String HistoryTableTest = "History2";
    public static final String QuizTableTest = "Quiz2";
    public static final String QuestionTableTest = "Question2";

    private Connection con;

    public CreateTablesForTests() throws SQLException, ClassNotFoundException {
        con = ProfileDataSrc.getConnection();
        //con = ProfileDataSrc.getConnection("oop_base", "root", "01234567");
    }

    public boolean createUserTable() throws SQLException, ClassNotFoundException {
        Statement s = con.createStatement();

        s.executeUpdate("CREATE TABLE " + UsersTableTest + " (\n" + "UserId int primary key, \n" +
                "UserName varchar(255),\n" +
                "Password varchar(255),\n" +
                "IsAdministrator boolean,\n" +
                "Salt varchar (255),\n" +
                "Name varchar(255),\n" +
                "Surname varchar(255),\n" +
                "Birth_Date Date,\n" +
                "Birth_Place varchar(255),\n" +
                "Status varchar(255)" + ");");
        return true;
    }


    public boolean createFriendsTable() throws SQLException, ClassNotFoundException {
        Statement s = con.createStatement();
        s.executeUpdate("CREATE TABLE " + FriendsTableTest + " (\n" + "SenderId int ,\n" +
                "ReceiverId int ,\n" +
                "Confirmed boolean,\n" +
                "DateSent Date,\n" +
                "foreign key (SenderId) references Users(UserId),\n" +
                "foreign key (ReceiverId) references Users(UserId));");
        return true;
    }


    public boolean dropTable(String tableName) throws SQLException {
        Statement stm = null;
        stm = con.createStatement();
        stm.executeUpdate("drop table " + tableName);
        return true;
    }


    public boolean createMailsTable() throws SQLException, ClassNotFoundException {
        Statement s = con.createStatement();
        String query = "CREATE TABLE " + MailsTableTest +
                " (\n" + "MailId int primary key ,\n" +
                "SenderId int ,\n" +
                "ReceiverId int ,\n" +
                "Type varchar(255) ,\n" +
                "Message varchar(255) ,\n" +
                "Date Date ,\n" +
                "Seen int);";
                //"foreign key (SenderId) references Users(UserId),\n" +
                //"foreign key (ReceiverId) references Users(UserId));";
        s.executeUpdate(query);
        return true;
    }

    public boolean createHistoryTable() throws SQLException {
        Statement state = con.createStatement();
        state.executeUpdate("CREATE TABLE " + HistoryTableTest + " (\n" +
                "   UserId int ,\n" +
                "   QuizId int ,\n" +
                "   Score int,\n" +
                "   Date Date,\n" +
                "   Time Time,\n" +
                "   foreign key (UserId) references Users(UserId),\n" +
                "   foreign key (QuizId) references Quiz(QuizId)\n" +
                ");");
        return true;
    }

}
