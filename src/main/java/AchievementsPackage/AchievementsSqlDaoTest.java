package AchievementsPackage;

import ProfilePackage.CreateTablesForTests;
import ProfilePackage.ProfileDataSrc;
import UserPackage.User;
import UserPackage.UserSqlDao;
import org.junit.jupiter.api.*;

import javax.faces.lifecycle.Lifecycle;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AchievementsSqlDaoTest {

    private Connection con;
    private CreateTablesForTests tables;
    private String achievementsTable;

    private AchievementsSqlDao aDao;
    private UserSqlDao uDao;

    @BeforeAll
    public void getConnection() throws SQLException, ClassNotFoundException {
        con = ProfileDataSrc.getConnection();
    }

    @BeforeEach
    public void setUp() throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
        CreateTablesForTests.AchievementsTable = CreateTablesForTests.AchievementsTableTest;
        CreateTablesForTests.UsersTable = CreateTablesForTests.UsersTableTest;

        tables = new CreateTablesForTests();

        assertTrue(tables.createUserTable());
        assertTrue(tables.createAchievementsTable());

        aDao = new AchievementsSqlDao();
        uDao = new UserSqlDao();

        uDao.addUser("1", "1", false);
        uDao.addUser("2", "1", false);
        uDao.addUser("3", "1", false);

        achievementsTable = CreateTablesForTests.AchievementsTable;
    }

    @AfterEach
    public void finishUp() throws SQLException, ClassNotFoundException {
        assertTrue(tables.dropTable(CreateTablesForTests.AchievementsTable));
        assertTrue(tables.dropTable(CreateTablesForTests.UsersTable));

        CreateTablesForTests.AchievementsTable = "Achievements";
        CreateTablesForTests.UsersTable = "Users";
    }

    @Test
    public void testAddAchievement() throws SQLException {
        aDao.addAchievement(1, AchievementsSqlDao.PRODIGIOUS);
        aDao.addAchievement(2, AchievementsSqlDao.PRODIGIOUS);
        aDao.addAchievement(3, AchievementsSqlDao.PRODIGIOUS);

        PreparedStatement stm = con.prepareStatement("SELECT * FROM " + achievementsTable + " WHERE Achievement = ?;");
        stm.setString(1, AchievementsSqlDao.PRODIGIOUS);
        ResultSet rs = stm.executeQuery();

        int cnt = 1;

        while (rs.next()) {
            assertTrue(rs.getInt(1) == cnt);
            assertTrue(rs.getString(2).equals(AchievementsSqlDao.PRODIGIOUS));
            cnt++;
        }
    }

    @Test
    public void testGetAchievements() throws SQLException {
        aDao.addAchievement(1, AchievementsSqlDao.PRODIGIOUS);
        aDao.addAchievement(2, AchievementsSqlDao.PRODIGIOUS);
        aDao.addAchievement(3, AchievementsSqlDao.PRODIGIOUS);

        PreparedStatement stm = con.prepareStatement("SELECT * FROM " + achievementsTable + " WHERE Achievement = ?;");
        stm.setString(1, AchievementsSqlDao.PRODIGIOUS);
        ResultSet rs = stm.executeQuery();

        List<String> achievements;

        achievements = aDao.getAchievements(1);

        while (rs.next()) {
            assert (achievements.size() == 1);
            assert (achievements.get(0).equals(rs.getString(2)));
        }
    }

    @Test
    public void testHasAchievement() throws SQLException {
        assertFalse(aDao.hasAchievement(1, AchievementsSqlDao.PRODIGIOUS));
        assertFalse(aDao.hasAchievement(2, AchievementsSqlDao.PRODIGIOUS));
        assertFalse(aDao.hasAchievement(3, AchievementsSqlDao.PRODIGIOUS));

        aDao.addAchievement(1, AchievementsSqlDao.PRODIGIOUS);
        aDao.addAchievement(2, AchievementsSqlDao.PRODIGIOUS);
        aDao.addAchievement(3, AchievementsSqlDao.PRODIGIOUS);

        assertTrue(aDao.hasAchievement(1, AchievementsSqlDao.PRODIGIOUS));
        assertTrue(aDao.hasAchievement(1, AchievementsSqlDao.PRODIGIOUS));
        assertTrue(aDao.hasAchievement(1, AchievementsSqlDao.PRODIGIOUS));
    }

}
