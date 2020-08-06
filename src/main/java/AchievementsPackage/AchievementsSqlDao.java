package AchievementsPackage;

import ProfilePackage.CreateTablesForTests;
import ProfilePackage.ProfileDataSrc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AchievementsSqlDao implements AchievementsDao {

    private Connection con;
    private String achievementTable;

    private static final int USER_ID = 1;
    private static final int ACHIEVEMENT = 2;

    public static final String AMATEUR = "Amateur Author - You created your first quiz.";
    public static final String PROLIFIC = "Prolific Author - You created five quizzes.";
    public static final String PRODIGIOUS = "Prodigious Author - You created ten quizzes.";
    public static final String MACHINE = "Quiz Machine — You took ten quizzes.";
    public static final String GREATEST = "I am the greatest - You currently hold (or have held) the highest score on a quiz.";
    public static final String PRACTICE = "Practice makes perfect - You took a quiz on practice mode.";

    public AchievementsSqlDao() throws SQLException, ClassNotFoundException {
        con = ProfileDataSrc.getConnection();
        achievementTable = CreateTablesForTests.AchievementsTable;
    }

    @Override
    public void addAchievement(int userId, String achievement) throws SQLException {
        PreparedStatement stm = null;
        stm = con.prepareStatement("INSERT INTO " + achievementTable + " VALUES (?, ?);");
        stm.setInt(USER_ID, userId);
        stm.setString(ACHIEVEMENT, achievement);
        stm.executeUpdate();
    }

    @Override
    public List<String> getAchievements(int userId) throws SQLException {
        PreparedStatement stm = null;
        stm = con.prepareStatement("SELECT * FROM " + achievementTable + " WHERE UserId = ?;");
        stm.setInt(USER_ID, userId);
        ResultSet rs = stm.executeQuery();

        List<String> achievements = new ArrayList<String>();
        while (rs.next()){
            achievements.add(rs.getString(ACHIEVEMENT));
        }

        return achievements;
    }

    @Override
    public boolean hasAchievement(int userId, String achievement) throws SQLException {
        PreparedStatement stm = null;
        stm = con.prepareStatement("SELECT * FROM " + achievementTable + " WHERE UserId = ? AND Achievement = ?;");
        stm.setInt(USER_ID, userId);
        stm.setString(ACHIEVEMENT, achievement);
        ResultSet rs = stm.executeQuery();

        if (rs.next())
            return true;
        return false;
    }
}
