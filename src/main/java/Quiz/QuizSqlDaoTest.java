package Quiz;

import ProfilePackage.CreateTablesForTests;
import ProfilePackage.ProfileDataSrc;
import UserPackage.UserDao;
import UserPackage.UserSqlDao;
import org.junit.jupiter.api.*;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class QuizSqlDaoTest {

    private Quiz quiz;
    private QuizSqlDao database;
    private UserDao userDao;
    private Connection con;
    private String quizTable = CreateTablesForTests.QuizTableTest;
    private CreateTablesForTests tables;

    private static final int CREATOR = 123;
    private static final int QUIZ = 456;

    @BeforeAll
    public void init() throws SQLException, ClassNotFoundException {
        con = ProfileDataSrc.getConnection();
    }

    @BeforeEach
    public void setUp() throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
        CreateTablesForTests.QuizTable = CreateTablesForTests.QuizTableTest;
        CreateTablesForTests.UsersTable = CreateTablesForTests.UsersTableTest;
        database = new QuizSqlDao();
        userDao = new UserSqlDao();
        tables = new CreateTablesForTests();
        assertTrue(tables.createUserTable());
        assertTrue(tables.createQuizTable());
        addData();
    }

    @AfterEach
    public void finishUp() throws SQLException {
        assertEquals(true, tables.dropTable(CreateTablesForTests.QuizTableTest));
        assertEquals(true, tables.dropTable(CreateTablesForTests.UsersTableTest));
        CreateTablesForTests.QuizTable = "Quiz";
        CreateTablesForTests.UsersTable = "Users";
    }

    @Test
    public void testGetQuiz() throws SQLException {
        Quiz q1 = database.getQuiz(1);
        Quiz q2 = database.getQuiz(2);
        Quiz q3 = database.getQuiz(3);

        assertEquals(q1.getCreator(), 1);
        assertEquals(q2.getCreator(), 2);
        assertEquals(q3.getCreator(), 3);

        assertFalse(q1.isRandom());
        assertFalse(q1.isOnePage());
        assertFalse(q1.isImmediate());
        assertFalse(q1.isInPracticeMode());
        assertEquals(q1.getQuestionCount(), 0);
        assertEquals(q1.getDescription(), null);
        assertEquals(q1.getCategory(), null);
    }

    @Test
    public void testDeleteQuiz() throws SQLException {
        Quiz q = new Quiz(1, 1);
        boolean deleted = database.deleteQuiz(q);

        assertTrue(deleted);
        assertEquals(database.getQuiz(1), null);
        assertNotEquals(database.getQuiz(2), null);

        q = new Quiz(2, 2);
        deleted = database.deleteQuiz(q);

        assertTrue(deleted);
        assertEquals(database.getQuiz(2), null);
    }

    @Test
    public void testGetQuizzesForUser() throws SQLException {
        List<Quiz> user1 = database.getQuizzesForUser(1);

        assertEquals(user1.size(), 1);
        assertEquals(user1.get(0).getCreator(), 1);
        assertEquals(user1.get(0).getQuizId(), 1);
    }

    private void addData() throws SQLException {
        database.addQuiz(1);
        database.addQuiz(2);
        database.addQuiz(3);
    }
}
