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
    public static String MailsTable = "Mails";
    public static final String HistoryTableTest = "History2";
    public static String HistoryTable = "History";
    public static final String QuizTableTest = "Quiz2";
    public static String QuizTable = "Quiz";
    public static String QuestionTable = "Questions";
    public static final String QuestionTableTest = "Questions2";
    public static final String AchievementsTableTest = "Achievements2";
    public static String AchievementsTable = "Achievements";
    public static String QuizTagTable = "QuizTag";
    public static final String QuizTagTableTest = "QuizTag2";

    private Connection con;


    public boolean createQuizTagTable() throws SQLException {
        Statement s = con.createStatement();
        s.executeUpdate("CREATE TABLE " + QuizTagTableTest + " (QuizId int ,\n" +
                " Tag varchar(255) ,\n" +
                "foreign key (QuizId) references " + QuizTableTest + "(QuizId));");
        return true;
    }


    public boolean createAchievementsTable() throws SQLException {
        Statement s = con.createStatement();
        s.executeUpdate("CREATE TABLE " + AchievementsTableTest + " (UserId int ,\n" +
                "Achievement varchar(255) ,\n" +
                "foreign key (UserId) references " + UsersTableTest + "(UserId));");
        return true;
    }

    public CreateTablesForTests() throws SQLException, ClassNotFoundException {
        con = ProfileDataSrc.getConnection();
        //con = NanukaDatabase.getConnection();
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
                "Birth_Place varchar(255),\n" +
                "Status varchar(255)" + ");");
        return true;
    }

    public boolean createQuestionTable() throws SQLException {
        Statement s = con.createStatement();
        s.executeUpdate("CREATE TABLE " + QuestionTableTest + " (" +
                " QuestionId int primary key ,\n" +
                " Question varchar(512) ,\n" +
                "Answer varchar(2048) ,\n" +
                "Type int ,\n" +
                "Score int ,\n" +
                "QuizId int ,\n" +
                "foreign key (QuizId) references " + CreateTablesForTests.QuizTableTest + "(QuizId)\n" + ");");
        return true;
    }


    public boolean createFriendsTable() throws SQLException, ClassNotFoundException {
        Statement s = con.createStatement();
        s.executeUpdate("CREATE TABLE " + FriendsTableTest + " (\n" + "SenderId int ,\n" +
                "ReceiverId int ,\n" +
                "Confirmed boolean,\n" +
                "DateSent Date,\n" +
                "foreign key (SenderId) references " + UsersTableTest + "(UserId),\n" +
                "foreign key (ReceiverId) references " + UsersTableTest + "(UserId));");
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
                "foreign key (SenderId) references " + UsersTableTest + "(UserId),\n" +
                "foreign key (ReceiverId) references " + UsersTableTest + "(UserId));";
        s.executeUpdate(query);
        return true;
    }

    public boolean createHistoryTable() throws SQLException {
        Statement state = con.createStatement();
        state.executeUpdate("CREATE TABLE " + HistoryTableTest + " (\n" +
                "   UserId int ,\n" +
                "   QuizId int ,\n" +
                "   Score int,\n" +
                "   StartDate TIMESTAMP,\n" +
                "   EndDate TIMESTAMP,\n" +
                "   foreign key (UserId) references " + UsersTableTest + "(UserId),\n" +
                "   foreign key (QuizId) references " + QuizTableTest + "(QuizId)\n" +
                ");");
        return true;
    }

    public boolean createQuizTable() throws SQLException {
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
                "CreateDate Date,\n" +
                "FOREIGN KEY (CreatorId) REFERENCES " + UsersTableTest + "(UserId)\n" +
                ");");
        return true;
    }
}
