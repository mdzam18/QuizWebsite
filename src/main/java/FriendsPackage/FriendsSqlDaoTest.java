package FriendsPackage;

import ProfilePackage.CreateTablesForTests;
import ProfilePackage.ProfileDataSrc;
import UserPackage.User;
import UserPackage.UserDao;
import UserPackage.UserSqlDao;
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
        uDao = new UserSqlDao();
        fDao = new FriendsSqlDao();
        assertEquals(tables.createUserTable(), true);
        assertEquals(tables.createFriendsTable(), true);
    }

    @AfterEach
    public void tearDown() throws SQLException {
        assertEquals(tables.dropTable(CreateTablesForTests.FriendsTableTest), true);
        assertEquals(tables.dropTable(CreateTablesForTests.UsersTableTest), true);
        CreateTablesForTests.UsersTable = "Users"; //chemtvis test unda mivawero.
        CreateTablesForTests.FriendsTable = "Friends"; //aqac igive.
    }

    @Test
    public void testSendRequest() throws SQLException {
        User user1 = uDao.addUser("Captain America", "Peggy", false);
        User user2 = uDao.addUser("IronMan", "Tony Stark", false);
        assertEquals(fDao.sendFriendRequest(user1.getUserId(), user2.getUserId()), true);
        assertEquals(fDao.getSentRequests(user1).size(), 1);
        assertEquals(fDao.getSentRequests(user2).size(), 0);
        assertEquals(fDao.getReceivedRequests(user2).size(), 1);
        assertEquals(fDao.getReceivedRequests(user2).get(0).equals(user1), true);

        assertEquals(fDao.sendFriendRequest(user1.getUserId(), user2.getUserId()), false); //is already sent.
        assertEquals(fDao.sendFriendRequest(user2.getUserId(), user1.getUserId()), false); //is already requested.
        assertEquals(fDao.sendFriendRequest(user1.getUserId(), user1.getUserId()), false); //can't send request to user1.
    }

    @Test
    public void testGetFriends() throws SQLException {
        User user1 = uDao.addUser("Captain America", "Peggy", false);
        User user2 = uDao.addUser("IronMan", "Tony Stark", false);
        assertEquals(fDao.sendFriendRequest(user1.getUserId(), user2.getUserId()), true);
        assertEquals(fDao.getFriends(user1).size(), 0);
        assertEquals(fDao.getFriends(user2).size(), 0);
        assertEquals(fDao.confirmFriendRequest(user2.getUserId(), user1.getUserId()), false); //user2 can't confirm request.
        assertEquals(fDao.confirmFriendRequest(user1.getUserId(), user2.getUserId()), true);
        assertEquals(fDao.confirmFriendRequest(user2.getUserId(), user1.getUserId()), false); //they are already friends.

        assertEquals(fDao.getFriends(user1).size(), 1);
        assertEquals(fDao.getFriends(user2).size(), 1);
        assertEquals(fDao.getFriends(user1).get(0).equals(user2), true);
        assertEquals(fDao.getFriends(user2).get(0).equals(user1), true);

        assertEquals(fDao.sendFriendRequest(user1.getUserId(), user2.getUserId()), false); //they are already friends.
    }

    @Test
    public void testAreFriendsAndIsRequested() throws SQLException {
        User user1 = uDao.addUser("Captain America", "Peggy", false);
        User user2 = uDao.addUser("IronMan", "Tony Stark", false);
        assertEquals(fDao.sendFriendRequest(user1.getUserId(), user2.getUserId()), true);
        assertEquals(fDao.isRequested(user1.getUserId(), user2.getUserId()), true);
        assertEquals(fDao.isRequested(user2.getUserId(), user1.getUserId()), false);

        assertEquals(fDao.areFriends(user1.getUserId(), user2.getUserId()), false);
        assertEquals(fDao.areFriends(user2.getUserId(), user1.getUserId()), false);

        assertEquals(fDao.confirmFriendRequest(user1.getUserId(), user2.getUserId()), true);
        assertEquals(fDao.areFriends(user1.getUserId(), user2.getUserId()), true);
        assertEquals(fDao.areFriends(user2.getUserId(), user1.getUserId()), true);
    }

    @Test
    public void testDeleteFriend() throws SQLException {
        User user1 = uDao.addUser("Captain America", "Peggy", false);
        User user2 = uDao.addUser("IronMan", "Tony Stark", false);
        fDao.sendFriendRequest(user1.getUserId(), user2.getUserId());
        assertEquals(fDao.confirmFriendRequest(user1.getUserId(), user2.getUserId()), true);
        assertEquals(fDao.deleteFriend(user1.getUserId(), user2.getUserId()), true);
        assertEquals(fDao.getFriends(user1).size(), 0);
        assertEquals(fDao.getFriends(user2).size(), 0);
        assertEquals(fDao.deleteFriend(user1.getUserId(), user2.getUserId()), false);
    }

}