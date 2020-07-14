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
public class FriendsSqlDaoTest {

    private FriendsDao fDao;
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
        CreateTablesForTests.FriendsTable = CreateTablesForTests.FriendsTableTest;
        CreateTablesForTests.UsersTable = CreateTablesForTests.UsersTableTest;
      //  fDao = new FriendsSqlDao();
        uDao = new UserSqlDao();
        assertEquals(tables.createUserTable(), true);
        assertEquals(tables.createFriendsTable(), true);
    }

    @AfterEach
    public void tearDown() throws SQLException {
        assertEquals(tables.dropTable("test.Friends2"), true);
        assertEquals(tables.dropTable("test.Users2"), true);
        CreateTablesForTests.UsersTable = "test.Users";
        CreateTablesForTests.FriendsTable = "test.Friends";
    }

   /* @Test
    public void testSendRequest() throws SQLException {
        User user1 = uDao.addUser("Captain America", "Peggy");
        User user2 = uDao.addUser("IronMan", "Tony Stark");
        fDao.sendFriendRequest(user1, user2);
        assertEquals(fDao.getSentRequests(user1).size(), 1);
        assertEquals(fDao.getSentRequests(user2).size(), 0);
        assertEquals(fDao.getReceivedRequests(user2).size(), 1);
        assertEquals(fDao.getReceivedRequests(user1).get(0).equals(user2), true);
    }
    */
}