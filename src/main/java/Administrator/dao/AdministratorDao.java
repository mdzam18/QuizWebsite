package Administrator.dao;

import Quiz.Quiz;
import UserPackage.User;

import java.sql.SQLException;
import java.util.List;

public interface AdministratorDao {
	
	List<User> getAllAdmins() throws SQLException;
	
	User addAdmin(String username, String password) throws SQLException;
	
	User getAdmin(int userId) throws SQLException;
	
	boolean deleteUser(User user) throws SQLException, ClassNotFoundException;
	
	boolean deleteQuiz(Quiz quiz) throws SQLException, ClassNotFoundException;
	
	boolean deleteHistory(Quiz quiz) throws SQLException;
	
	boolean promoteUser(User user) throws SQLException;
	
}

