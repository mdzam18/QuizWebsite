package ProfilePackage;


import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public interface MailDao {
    Mail getMailById(int mailId) throws SQLException;

    boolean sendMail(int senderId, int receiverId, String noteType, String message, Date date, boolean isSeen) throws SQLException;

    ArrayList<Mail> getMails(int userId) throws SQLException;

    boolean deleteMailById(int userId) throws SQLException;

}
