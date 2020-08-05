package UserPackage;

import ProfilePackage.CreateTablesForTests;
import ProfilePackage.ProfileDataSrc;
import Quiz.QuizSqlDao;
import org.junit.jupiter.api.*;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.jws.soap.SOAPBinding;
import java.sql.Date;


import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserSqlDaoTest {

    private UserDao uDao;
    private Connection con;
    private CreateTablesForTests tables;

    @BeforeAll
    public void getConnection() throws SQLException, ClassNotFoundException {
        con = ProfileDataSrc.getConnection();
        tables = new CreateTablesForTests();
    }

    @BeforeEach
    public void setUp() throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
        init();
        uDao = new UserSqlDao();
        createTables();
    }

    private void createTables() throws SQLException, ClassNotFoundException {
        assertEquals(tables.createUserTable(), true);
        assertEquals(tables.createQuizTable(), true);
        assertEquals(tables.createFriendsTable(), true);
        assertEquals(tables.createMailsTable(), true);
        assertEquals(tables.createHistoryTable(), true);
        assertEquals(tables.createQuestionTable(), true);
        assertEquals(tables.createAchievementsTable(), true);
        assertEquals(tables.createQuizTagTable(), true);
    }

    private void init() {
        CreateTablesForTests.UsersTable = CreateTablesForTests.UsersTableTest;
        CreateTablesForTests.FriendsTable = CreateTablesForTests.FriendsTableTest;
        CreateTablesForTests.MailsTable = CreateTablesForTests.MailsTableTest;
        CreateTablesForTests.HistoryTable = CreateTablesForTests.HistoryTableTest;
        CreateTablesForTests.AchievementsTable = CreateTablesForTests.AchievementsTableTest;
        CreateTablesForTests.QuizTable = CreateTablesForTests.QuizTableTest;
        CreateTablesForTests.QuizTagTable = CreateTablesForTests.QuizTagTableTest;
        CreateTablesForTests.QuestionTable = CreateTablesForTests.QuestionTableTest;
    }

    @AfterEach
    public void tearDown() throws SQLException {
        dropTables();
        CreateTablesForTests.UsersTable = "Users";
        CreateTablesForTests.FriendsTable = "Friends";
        CreateTablesForTests.MailsTable = "Mails";
        CreateTablesForTests.HistoryTable = "History";
        CreateTablesForTests.AchievementsTable = "Achievements";
        CreateTablesForTests.QuizTable = "Quiz";
        CreateTablesForTests.QuizTagTable = "QuizTag";
        CreateTablesForTests.QuestionTable = "Question";
    }

    private void dropTables() throws SQLException {
        assertEquals(tables.dropTable(CreateTablesForTests.FriendsTableTest), true);
        assertEquals(tables.dropTable(CreateTablesForTests.MailsTableTest), true);
        assertEquals(tables.dropTable(CreateTablesForTests.HistoryTableTest), true);
        assertEquals(tables.dropTable(CreateTablesForTests.QuestionTableTest), true);
        assertEquals(tables.dropTable(CreateTablesForTests.AchievementsTableTest), true);
        assertEquals(tables.dropTable(CreateTablesForTests.QuizTagTableTest), true);
        assertEquals(tables.dropTable(CreateTablesForTests.QuizTableTest), true);
        assertEquals(tables.dropTable(CreateTablesForTests.UsersTableTest), true);
    }


    @Test
    public void testAddAndGet1() throws SQLException {
        assertEquals(uDao.addUser("ChandlerTheBest", "friends", false).equals(new User("ChandlerTheBest", 1, uDao.findHashCode("friends" + uDao.getSalt(1)))), true);
        assertEquals(uDao.addProfile(1, "Chandler", "Bing", "USA", "working"), true);
        User user = uDao.getUser(1);
        assertEquals(user.getUserName(), "ChandlerTheBest");
        assertEquals(user.getPassword(), uDao.findHashCode("friends" + uDao.getSalt(1)));
        assertEquals(user.getName(), "Chandler");
        assertEquals(user.getSurname(), "Bing");
        assertEquals(user.isAdministrator(), false);
        assertEquals(user.getBirthPlace(), "USA");
        assertEquals(user.getStatus(), "working");
        assertEquals(1, uDao.getUserIdByName("ChandlerTheBest"));

        assertEquals(-1, uDao.getUserIdByName("Chandler"));
        user = uDao.getUser(2);
        assertEquals(user, null);
    }

    @Test
    public void testAddAndGet2() throws SQLException {
        assertEquals(uDao.addUser("ChandlerTheBest", "friends", false).equals(new User("ChandlerTheBest", 1, uDao.findHashCode("friends" + uDao.getSalt(1)))), true);
        User user = uDao.getUser(1);
        assertEquals(user.getUserName(), "ChandlerTheBest");
        assertEquals(user.getPassword(), uDao.findHashCode("friends" + uDao.getSalt(1)));
        assertEquals(user.getName(), "");
        assertEquals(user.getSurname(), "");
        assertEquals(user.isAdministrator(), false);
        assertEquals(user.getBirthPlace(), "");
        assertEquals(user.getStatus(), "");
        assertEquals(1, uDao.getUserIdByName("ChandlerTheBest"));

        assertEquals(-1, uDao.getUserIdByName("Chandler"));
        user = uDao.getUser(2);
        assertEquals(user, null);
    }

    @Test
    public void testAddSameUserName() throws SQLException {
        assertEquals(uDao.addUser("ChandlerTheBest", "friends", false).equals(new User("ChandlerTheBest", 1, uDao.findHashCode("friends" + uDao.getSalt(1)))), true);
        assertEquals(uDao.addUser("ChandlerTheBest", "", false), null);
    }

    @Test
    public void testGetAll() throws SQLException {
        assertEquals(uDao.getAllUsers().size(), 0);
        Date date = new Date(2000, 12, 12);
        for (int i = 0; i < 10; i++) {
            if (i == 0) {
                assertEquals(uDao.addUser("ChandlerTheBest", "friends", false).equals(new User("ChandlerTheBest", 1, uDao.findHashCode("friends" + uDao.getSalt(1)))), true);
            }
            assertEquals(uDao.addUser("ChandlerTheBest", "friends", false), null);
            assertEquals(uDao.getAllUsers().size(), 1);
        }
    }

    @Test
    public void testGetAll2() throws SQLException {
        assertEquals(uDao.getAllUsers().size(), 0);
        Date date = new Date(2000, 12, 12);
        for (int i = 0; i < 10; i++) {
            String str = "";
            str = str + String.valueOf(i);
            assertEquals(uDao.addUser(str, "friends", false).equals(new User(str, i + 1, uDao.findHashCode("friends" + uDao.getSalt(i + 1)))), true);
            assertEquals(uDao.getAllUsers().size(), i + 1);
        }
    }

    @Test
    public void testDelete() throws SQLException, ClassNotFoundException {
        assertEquals(uDao.addUser("ChandlerTheBest", "friends", false).equals(new User("ChandlerTheBest", 1, uDao.findHashCode("friends" + uDao.getSalt(1)))), true);
        User user = uDao.getUser(1);
        QuizSqlDao qDao = new QuizSqlDao();
        qDao.addQuiz(1, false, false, false, false, 1, "quiz", "category", null);
        assertEquals(uDao.deleteUser(user), true);
        assertEquals(uDao.getAllUsers().size(), 0);
    }

    @Test
    public void testIsCorrectPassword() throws SQLException {
        assertEquals(uDao.addUser("ChandlerTheBest", "friends", false).equals(new User("ChandlerTheBest", 1, uDao.findHashCode("friends" + uDao.getSalt(1)))), true);
        assertEquals(uDao.isCorrectPassword("ChandlerTheBest", "friends"), true);
        assertEquals(uDao.isCorrectPassword("ChandlerTheBest", "frieds"), false);
    }

    @Test
    public void testContainsUser() throws SQLException {
        assertEquals(uDao.addUser("ChandlerTheBest", "friends", false).equals(new User("ChandlerTheBest", 1, uDao.findHashCode("friends" + uDao.getSalt(1)))), true);
        assertEquals(uDao.containsUserName("ChandlerTheBest"), true);
        assertEquals(uDao.containsUserName("ChandlerTheBest2"), false);
    }
}

