package ProfilePackage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTablesForTests {
    public static final String FriendsTableTest = "Friends2";
    public static final String UsersTableTest = "Users2";
    public static String UsersTable = "Users";
    public static String FriendsTable = "Friends";
    public static String MailsTableTest = "Mails2";
    public static String MailsTable = "Mails2";
    public static final String HistoryTableTest = "History2";
    public static final String QuizTableTest = "Quiz2";
    public static final String QuestionTableTest = "Question2";
    
    private Connection con;
    
    public CreateTablesForTests() throws SQLException, ClassNotFoundException {
        con = ProfileDataSrc.getConnection();
        //con = NanukaDatabase.getConnection();
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
                "foreign key (SenderId) references Users2(UserId),\n" +
                "foreign key (ReceiverId) references Users2(UserId));");
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
        //con.createStatement().executeQuery("drop table Mails2;");
        String query = "CREATE TABLE " + MailsTableTest +
                " (\n" + "MailId int primary key ,\n" +
                "SenderId int ,\n" +
                "ReceiverId int ,\n" +
                "Type varchar(255) ,\n" +
                "Message varchar(255) ,\n" +
                "Date Date ,\n" +
                "Seen int, \n" +
                "foreign key (SenderId) references Users2(UserId),\n" +
                "foreign key (ReceiverId) references Users2(UserId));";
        s.executeQuery(query);
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
                "   foreign key (UserId) references Users2(UserId),\n" +
                "   foreign key (QuizId) references Quiz2(QuizId)\n" +
                ");");
        return true;
    }
    
    public boolean createQuizTable() throws SQLException{
        Statement stm = con.createStatement();
        stm.executeUpdate("CREATE TABLE " + QuizTableTest + " (\n" +
                "QuizId int primary key,\n" +
                "IsRandom boolean,\n" +
                "IsOnePage boolean,\n" +
                "IsImmediate boolean,\n" +
                "InPracticeMode boolean,\n" +
                "NumberOfQuestions int,\n" +
                "Description varchar(255),\n" +
                "Category varchar(255),\n" +
                "CreatorId int,\n" +
                "FOREIGN KEY (CreatorId) REFERENCES Users2 (UserId)\n" +
                ");");
        return true;
    }
}
