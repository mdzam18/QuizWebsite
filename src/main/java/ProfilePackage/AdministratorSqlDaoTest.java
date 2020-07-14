package ProfilePackage;

import Quiz.*;

import org.junit.jupiter.api.*;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AdministratorSqlDaoTest {
	private AdministratorDao adminDao;
	private UserDao userDao;
	private HistoryDao historyDao;
	//	private Quiz.QuizDAO quizDao;
	private Connection con;
	private CreateTablesForTests tables;
	
	@BeforeAll
	public void init() throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
		con = ProfileDataSrc.getConnection();
		adminDao = new AdministratorSqlDao();
		userDao = new UserSqlDao();
		historyDao = new HistorySqlDao(false);
		tables = new CreateTablesForTests();
	}
	
	@BeforeEach
	public void setUp() throws SQLException, ClassNotFoundException {
		assertEquals(true, tables.createUserTable());
		assertEquals(true, tables.createQuizTable());
		assertEquals(true, tables.createHistoryTable());
	}
	
	@AfterEach
	public void tearDown() throws SQLException {
		assertEquals(true, tables.dropTable("History2"));
		assertEquals(true, tables.dropTable("Quiz2"));
		assertEquals(true, tables.dropTable("Users2"));
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
	public void testDeleteUser() throws SQLException {
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
	public void testDeleteQuiz() throws SQLException {
//		Quiz quiz = quizDao.addQuiz(); //not yet implemented
//		assertEquals(true, adminDao.deleteQuiz(quiz));
//		assertEquals(null, quizDao.getQuiz(quiz_id));
	}
	
	@Test
	public void testDeleteHistory() throws SQLException {
		Quiz quiz = new Quiz(1,1); //signature needs to be modified
		quiz.setQuizId(1);
		historyDao.addToHistory(1,1,20,new Date(),new Date());
		assertEquals(true, adminDao.deleteHistory(quiz));
		assertEquals(null, historyDao.getHistoriesByQuizId(1));
	}
	
	@Test
	public void testPromoteUser() throws SQLException {
		User user = userDao.addUser("userN1", "userN1pass");
		int userId = user.getUserId();
		assertEquals(true, adminDao.promoteUser(user));
		assertEquals(true, userDao.getUser(userId).isAdministrator());
	}
}