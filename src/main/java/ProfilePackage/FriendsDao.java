package ProfilePackage;

import java.sql.SQLException;
import java.util.List;

public interface FriendsDao {
    boolean sendFriendRequest(User from, User to);

    boolean confirmFriendRequest(User from , User to) throws SQLException;

    boolean deleteFriend(User from, User to) throws SQLException;

    List<User> getSentRequests(User user) throws SQLException;

    List<User> getReceivedRequests(User user) throws SQLException;

}
