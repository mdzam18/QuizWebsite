package ProfilePackage;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public interface FriendsDao {

    boolean sendFriendRequest(int from, int to) throws SQLException;

    boolean deleteFriend(int from, int to) throws SQLException;

    List<User> getSentRequests(User user) throws SQLException;

    List<User> getReceivedRequests(User user) throws SQLException;

    List<User> getFriends(User user) throws SQLException;

    boolean isRequested(int SenderId, int ReceiverId) throws SQLException;

    boolean areFriends(int user1, int user2) throws SQLException;

    boolean confirmFriendRequest(int senderId, int receiverId) throws SQLException;

    // void addFriendship(int user1, int user2, Timestamp time) throws SQLException; //?
}
