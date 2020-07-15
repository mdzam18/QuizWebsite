package Quiz;

import ProfilePackage.CreateTablesForTests;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuizSqlDaoTest {

    private Quiz quiz;
    private QuizSqlDao database;
    private Connection con;
    private String quizTable = CreateTablesForTests.QuizTableTest;

    private static final int CREATOR = 123;
    private static final int QUIZ = 456;

    @BeforeAll
    public void init() throws SQLException, ClassNotFoundException {
        quiz = new Quiz(QUIZ, CREATOR);
        database = new QuizSqlDao();
        con = database.getConnection();
    }

    // DASAMTAVREBELI
//    @Test
//    public void testGetQuiz() throws SQLException {
//        PreparedStatement stm =
//                con.prepareStatement("SELECT max(QuizId) FROM " + quizTable);
//        ResultSet rs = stm.executeQuery();
//
//        int quizId = 0;
//        if (rs.next()) {
//            quizId = rs.getInt(1);
//        }
//
//        database.addQuiz(CREATOR);
//
//        stm = con.prepareStatement("SELECT max(QuizId) FROM " + quizTable);
//        rs = stm.executeQuery();
//
//        int nextQuizId = 0;
//        if (rs.next()) {
//            quizId = rs.getInt(1);
//        }
//
//    }
}
