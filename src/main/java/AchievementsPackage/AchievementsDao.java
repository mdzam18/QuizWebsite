package AchievementsPackage;

import java.sql.SQLException;
import java.util.List;

public interface AchievementsDao {

    void addAchievement(int userId, String achievement) throws SQLException;

    List<String> getAchievements(int userId) throws SQLException;

    boolean hasAchievement(int userId, String achievement) throws SQLException;

}
