package ProfilePackage;

//import Quiz.*;

import Quiz.Quiz;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface AdministratorDao {
	
	List<User> getAllAdmins() throws SQLException;
	
	User addAdmin(String username, String password) throws SQLException;
	
	User getAdmin(int userId) throws SQLException;
	
	boolean deleteUser(User user) throws SQLException;
	
//	boolean deleteQuiz(Quiz quiz) throws SQLException;
	
	boolean deleteHistory(Quiz quiz) throws SQLException;
	
	boolean promoteUser(User user) throws SQLException;
	
}

