package ProfilePackage;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public interface FriendsDao {
    boolean sendFriendRequest(User from, User to) throws SQLException;

    boolean confirmFriendRequest(User from, User to) throws SQLException;

    boolean deleteFriend(User from, User to) throws SQLException;

    List<User> getSentRequests(User user) throws SQLException;

    List<User> getReceivedRequests(User user) throws SQLException;

    List<User> getFriends(User user) throws SQLException;

    public boolean requested(int SenderId, int ReceiverId) throws SQLException;

    public boolean areFriends(int user1, int user2) throws SQLException;

    public boolean confirmFriendRequest(int senderId, int receiverId) throws SQLException;

    public void addFriendship(int user1, int user2, Timestamp time) throws SQLException;
}
