package Administrator.dao;

import HistoryPackage.History;
import HistoryPackage.HistoryDao;
import HistoryPackage.HistorySqlDao;
import ProfilePackage.*;
import Quiz.Quiz;
import Quiz.QuizDao;
import Quiz.QuizSqlDao;
import UserPackage.User;
import UserPackage.UserDao;
import UserPackage.UserSqlDao;
import org.junit.jupiter.api.*;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AdministratorSqlDaoTest {
	private AdministratorDao adminDao;
	private UserDao userDao;
	private HistoryDao historyDao;
	private QuizDao quizDao;
	private Connection con;
	private CreateTablesForTests tables;

	@BeforeAll
	public void init() throws SQLException, ClassNotFoundException{
		con = ProfileDataSrc.getConnection();
		tables = new CreateTablesForTests();
	}

	@BeforeEach
	public void setUp() throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
		CreateTablesForTests.UsersTable = CreateTablesForTests.UsersTableTest;
		CreateTablesForTests.FriendsTable = CreateTablesForTests.FriendsTableTest;
		CreateTablesForTests.MailsTable = CreateTablesForTests.MailsTableTest;
		CreateTablesForTests.AchievementsTable = CreateTablesForTests.AchievementsTableTest;
		CreateTablesForTests.HistoryTable = CreateTablesForTests.HistoryTableTest;
		CreateTablesForTests.QuizTable = CreateTablesForTests.QuizTableTest;
		adminDao = new AdministratorSqlDao();
		userDao = new UserSqlDao();
		historyDao = new HistorySqlDao();
		quizDao = new QuizSqlDao();
		assertEquals(true, tables.createUserTable());
		assertEquals(true, tables.createFriendsTable());
		assertEquals(true, tables.createMailsTable());
		assertEquals(true, tables.createAchievementsTable());
		assertEquals(true, tables.createQuizTable());
		assertEquals(true, tables.createHistoryTable());
	}

	@AfterEach
	public void tearDown() throws SQLException {
		assertEquals(true, tables.dropTable(CreateTablesForTests.HistoryTableTest));
		assertEquals(true, tables.dropTable(CreateTablesForTests.FriendsTableTest));
		assertEquals(true, tables.dropTable(CreateTablesForTests.MailsTableTest));
		assertEquals(true, tables.dropTable(CreateTablesForTests.AchievementsTableTest));
		assertEquals(true, tables.dropTable(CreateTablesForTests.QuizTableTest));
		assertEquals(true, tables.dropTable(CreateTablesForTests.UsersTableTest));
		CreateTablesForTests.UsersTable = "Users";
		CreateTablesForTests.FriendsTable = "Friends";
		CreateTablesForTests.MailsTable = "Mails";
		CreateTablesForTests.AchievementsTable = "Achievements";
		CreateTablesForTests.HistoryTable = "History";
		CreateTablesForTests.QuizTable = "Quiz";
	}

	@Test
	public void testGetAllAdmins() throws SQLException {
		assertEquals(0, adminDao.getAllAdmins().size());
		HashSet<User> answer = new HashSet<>();

		User admin1 = adminDao.addAdmin("adminN1","adminN1pass");
		User admin2 = adminDao.addAdmin("adminN2","adminN2pass");
		User admin3 = adminDao.addAdmin("adminN3", "adminN3pass");

		answer.add(admin1);
		answer.add(admin2);
		answer.add(admin3);

		assertEquals(3, adminDao.getAllAdmins().size());

		HashSet<User> result = new HashSet<>(adminDao.getAllAdmins());

		assertEquals(answer,result);
	}

	@Test
	public void testDeleteUser() throws SQLException, ClassNotFoundException {
		HashSet<User> answer = new HashSet<>();

		User admin1 = adminDao.addAdmin("adminN1","adminN1pass");
		User admin2 = adminDao.addAdmin("adminN2","adminN2pass");
		User admin3 = adminDao.addAdmin("adminN3", "adminN3pass");

		answer.add(admin1);
		answer.add(admin3);

		int admin2Id = admin2.getUserId();

		assertEquals(true, adminDao.deleteUser(admin2));
		assertEquals(null, adminDao.getAdmin(admin2Id));

		HashSet<User> result = new HashSet<>(adminDao.getAllAdmins());

		assertEquals(answer,result);
	}

	@Test
	public void testDeleteQuiz() throws SQLException, ClassNotFoundException {
		User user = userDao.addUser("MikeWheeler","Eleven11", false);
		System.out.println(user.getUserId());
		Quiz quiz = quizDao.addQuiz(1, false, false, false,
				false, 2, "quiz", "hard", new java.sql.Date(10, 12, 12));
		assertEquals(true, adminDao.deleteQuiz(quiz));
		assertNull(quizDao.getQuiz(1));
	}

	@Test
	public void testDeleteHistory() throws SQLException {
		List<History> answer = new ArrayList<>();
		User user = userDao.addUser("Dustin","Suzie23", false);
		Quiz quiz = quizDao.addQuiz(1, false, false, false,
				false, 2, "quiz", "hard", new java.sql.Date(10, 12, 12));
		historyDao.addToHistory(1,1,25, new Date(), new Date());
		assertEquals(true, adminDao.deleteHistory(quiz));
		assertEquals(answer, historyDao.getHistoriesByQuizId(1));
	}

	@Test
	public void testPromoteUser() throws SQLException {
		User user = userDao.addUser("userN1", "userN1pass", true);
		int userId = user.getUserId();
		assertEquals(true, adminDao.promoteUser(user));
		assertEquals(true, userDao.getUser(userId).isAdministrator());
	}
}