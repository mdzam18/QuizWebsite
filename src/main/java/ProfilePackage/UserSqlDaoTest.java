package ProfilePackage;

import org.junit.jupiter.api.*;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        CreateTablesForTests.UsersTable = CreateTablesForTests.UsersTableTest;
        uDao = new UserSqlDao();
        assertEquals(tables.createUserTable(), true);
    }

    @AfterEach
    public void tearDown() throws SQLException {
        assertEquals(tables.dropTable("test.Users2"), true);
        CreateTablesForTests.UsersTable = "test.Users";
    }


    @Test
    public void testAddAndGet1() throws SQLException {
        Date date = new Date(2000, 12, 12);
        assertEquals(uDao.addUser("ChandlerTheBest", "friends").equals(new User("ChandlerTheBest", 1, uDao.findHashCode("friends" + uDao.getSalt(1)))), true);
        assertEquals(uDao.addProfile(1, "Chandler", "Bing", date, "USA", "working"), true);
        User user = uDao.getUser(1);
        assertEquals(user.getUserName(), "ChandlerTheBest");
        assertEquals(user.getPassword(), uDao.findHashCode("friends" + uDao.getSalt(1)));
        assertEquals(user.getName(), "Chandler");
        assertEquals(user.getSurname(), "Bing");
        assertEquals(user.isAdministrator(), false);
        assertEquals(user.getBirthDate(), date);
        assertEquals(user.getBirthPlace(), "USA");
        assertEquals(user.getStatus(), "working");

        user = uDao.getUser(2);
        assertEquals(user, null);
    }

    @Test
    public void testAddSameUserName() throws SQLException {
        Date date = new Date(2000, 12, 12);
        assertEquals(uDao.addUser("ChandlerTheBest", "friends").equals(new User("ChandlerTheBest", 1, uDao.findHashCode("friends" + uDao.getSalt(1)))), true);
        assertEquals(uDao.addUser("ChandlerTheBest", ""), null);
    }

    @Test
    public void testGetAll() throws SQLException {
        assertEquals(uDao.getAllUsers().size(), 0);
        Date date = new Date(2000, 12, 12);
        for (int i = 0; i < 10; i++) {
            if (i == 0) {
                assertEquals(uDao.addUser("ChandlerTheBest", "friends").equals(new User("ChandlerTheBest", 1, uDao.findHashCode("friends" + uDao.getSalt(1)))), true);
            }
            assertEquals(uDao.addUser("ChandlerTheBest", "friends"), null);
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
            assertEquals(uDao.addUser(str, "friends").equals(new User(str, i + 1, uDao.findHashCode("friends" + uDao.getSalt(i + 1)))), true);
            assertEquals(uDao.getAllUsers().size(), i + 1);
        }
    }

    @Test
    public void testDelete() throws SQLException {
        Date date = new Date(2000, 12, 12);
        assertEquals(uDao.addUser("ChandlerTheBest", "friends").equals(new User("ChandlerTheBest", 1, uDao.findHashCode("friends" + uDao.getSalt(1)))), true);
        User user = uDao.getUser(1);
        assertEquals(uDao.deleteUser(user), true);
        assertEquals(uDao.getAllUsers().size(), 0);
    }

    @Test
    public void testIsCorrectPassword() throws SQLException {
        Date date = new Date(2000, 12, 12);
        assertEquals(uDao.addUser("ChandlerTheBest", "friends").equals(new User("ChandlerTheBest", 1, uDao.findHashCode("friends" + uDao.getSalt(1)))), true);
        assertEquals(uDao.isCorrectPassword("ChandlerTheBest", "friends"), true);
        assertEquals(uDao.isCorrectPassword("ChandlerTheBest", "frieds"), false);
    }
}
