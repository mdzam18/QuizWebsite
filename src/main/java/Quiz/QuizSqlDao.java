package Quiz;

import HistoryPackage.HistorySqlDao;
import ProfilePackage.CreateTablesForTests;
import ProfilePackage.ProfileDataSrc;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class QuizSqlDao implements QuizDao{

    private Connection con;
    private String quizTable;
    private String quizTagTable;
    private QuestionDao questionDao;
    private HistorySqlDao historyDao;

    public final static String TABLE_NAME  = "oop_base.Quiz";

    private static final int QUIZ_ID = 1;
    private static final int IS_RANDOM = 2;
    private static final int IS_ONE_PAGE = 3;
    private static final int IS_IMMEDIATE = 4;
    private static final int IN_PRACTICE_MODE = 5;
    private static final int NUMBER_QUESTIONS = 6;
    private static final int DESCRIPTION = 7;
    private static final int CATEGORY = 8;
    private static final int CREATOR_ID = 9;
    private static final int CREATE_DATE = 10;


    public QuizSqlDao() throws SQLException, ClassNotFoundException {
        con = ProfileDataSrc.getConnection();
        quizTable = CreateTablesForTests.QuizTable;
        questionDao = new QuestionDao();
        historyDao = new HistorySqlDao();
    }
    @Override
    public int getQuizId(int authorId,String description) throws SQLException{
        PreparedStatement stm = null;
        stm = con.prepareStatement(
                "SELECT * FROM " + quizTable + " WHERE CreatorId = ? and Description = ?;");
        stm.setInt(1, authorId);
        stm.setString(2, description);
        ResultSet res = stm.executeQuery();
        if (!res.next()) return -1;
        return res.getInt("QuizId");
    }

    @Override
    public int getQuizIdByName(String description) throws SQLException{
        PreparedStatement stm = null;
        stm = con.prepareStatement(
                "SELECT * FROM " + quizTable + " WHERE Description = ?;");
        stm.setString(1, description);
        ResultSet res = stm.executeQuery();
        if (!res.next()) return -1;
        return res.getInt("QuizId");
    }

    @Override
    public boolean userHasQuizByName(int userId, String quizName) throws SQLException {
        String sql = "SELECT * FROM " + quizTable + " WHERE CreatorId = ? AND Description = ?;";
        PreparedStatement prepState = con.prepareStatement(sql);
        prepState.setInt(1, userId);
        prepState.setString(2, quizName.trim());
        ResultSet resultSet = prepState.executeQuery();
        return resultSet.next();
    }

    @Override
    public Quiz getQuizByCreatorAndName(int userId, String quizName) throws SQLException {
        String sql = "SELECT * FROM " + quizTable + " WHERE CreatorId = ? AND Description = ?;";
        PreparedStatement prepState = con.prepareStatement(sql);
        prepState.setInt(1, userId);
        prepState.setString(2, quizName.trim());
        ResultSet resultSet = prepState.executeQuery();
        if(resultSet.next()) {
            int quizId = resultSet.getInt(QUIZ_ID);
            Quiz quiz = new Quiz(quizId, userId);
            quiz.setIsRandom(resultSet.getBoolean(IS_RANDOM));
            quiz.setIsOnePage(resultSet.getBoolean(IS_ONE_PAGE));
            quiz.setIsImmediate(resultSet.getBoolean(IS_IMMEDIATE));
            quiz.setInPracticeMode(resultSet.getBoolean(IN_PRACTICE_MODE));
            quiz.setDescription(resultSet.getString(DESCRIPTION));
            quiz.setCategory(resultSet.getString(CATEGORY));
            quiz.setCreateDate(resultSet.getDate(CREATE_DATE));
            return quiz;
        }
        return null;
    }

    @Override
    public Quiz addQuiz(int creatorId) throws SQLException {
        Quiz quiz = null;
        PreparedStatement stm =
                con.prepareStatement("SELECT max(QuizId) FROM " + quizTable);
        ResultSet rs = stm.executeQuery();
        int quizId = 0;
        if (rs.next()) {
            quizId = rs.getInt(1);
        }
        quizId++;

        stm = con.prepareStatement("INSERT INTO " + quizTable + "  VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
        stm.setInt(QUIZ_ID, quizId);
        stm.setBoolean(IS_RANDOM, false);
        stm.setBoolean(IS_ONE_PAGE, false);
        stm.setBoolean(IS_IMMEDIATE, false);
        stm.setBoolean(IN_PRACTICE_MODE, false);
        stm.setInt(NUMBER_QUESTIONS, 0);
        stm.setString(DESCRIPTION, null);
        stm.setString(CATEGORY, null);
        stm.setInt(CREATOR_ID, creatorId);
        stm.setDate(CREATE_DATE, new java.sql.Date(System.currentTimeMillis()));
        stm.executeUpdate();
        quiz = new Quiz(quizId, creatorId);
        return quiz;
    }

    @Override
    public Quiz getQuiz(int quizId) throws SQLException {
        PreparedStatement stm =
                con.prepareStatement("SELECT * FROM " + quizTable + " WHERE QuizId = ?;");
        stm.setInt(1, quizId);
        ResultSet rs = stm.executeQuery();
        if(!rs.next()) return null;
        Quiz quiz = new Quiz(quizId, rs.getInt(CREATOR_ID));
        quiz.setIsRandom(rs.getBoolean(IS_RANDOM));
        quiz.setIsOnePage(rs.getBoolean(IS_ONE_PAGE));
        quiz.setIsImmediate(rs.getBoolean(IS_IMMEDIATE));
        quiz.setIsRandom(rs.getBoolean(IS_RANDOM));
        quiz.setInPracticeMode(rs.getBoolean(IN_PRACTICE_MODE));
        quiz.setDescription(rs.getString(DESCRIPTION));
        quiz.setCategory(rs.getString(CATEGORY));
        quiz.setCreateDate(rs.getDate(CREATE_DATE));

        List<Question> questions = questionDao.getQuizQuestions(quizId);
        quiz.setQuestionSet(questions);
        return quiz;
    }

    @Override
    public boolean deleteQuiz(Quiz quiz) throws SQLException {
        questionDao.deleteQuestionsByQuizId(quiz.getQuizId());
        historyDao.removeFromHistories(quiz.getQuizId());
        PreparedStatement stm =
                con.prepareStatement("DELETE FROM " + quizTable + " WHERE QuizId = ?;");
        stm.setInt(1, quiz.getQuizId());
        int n = stm.executeUpdate();
        if(n == 1) return true;
        return false;
    }

    @Override
    public Connection getConnection(){
        return con;
    }

    @Override
    public List<Quiz> getPopularQuizzes() throws SQLException {
        List<Quiz> res = new ArrayList<>();
        PreparedStatement stm = con.prepareStatement("select * from " + CreateTablesForTests.HistoryTable + " group by QuizId order by count(*) DESC limit 5;");
        ResultSet rs = stm.executeQuery();
        while (rs.next()){
            int quizId = rs.getInt(2);
            Quiz q = getQuiz(quizId);
            res.add(q);
        }
        return res;
    }

    @Override
    public List<Quiz> getRecentlyCreatedQuizzes() throws SQLException {
        List<Quiz> res = new ArrayList<>();
        PreparedStatement stm = con.prepareStatement("select * from " + CreateTablesForTests.QuizTable + " order by QuizId DESC LIMIT 5;");
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            Quiz quiz = new Quiz(rs.getInt(1), rs.getInt(9));
            quiz.setDescription(rs.getString(7));
            res.add(quiz);
        }
        return res;
    }

    public List<Quiz> getRecentlyCreatedQuizzesByUser(int userId) throws SQLException {
        List<Quiz> res = new ArrayList<>();
        PreparedStatement stm = con.prepareStatement("select * from " + CreateTablesForTests.QuizTable + " where UserId = ? order by QuizId DESC LIMIT 5;");
        stm.setInt(1, userId);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            Quiz quiz = new Quiz(rs.getInt(1), rs.getInt(9));
            quiz.setDescription(rs.getString(7));
            res.add(quiz);
        }
        return res;
    }


    @Override
    public Quiz addQuiz(int creatorId, boolean isRandom, boolean isOnePage, boolean isImmediate, boolean hasPracticeMode, int questionNum, String quizName, String category, java.sql.Date createDate) throws SQLException
    {
        Statement state = con.createStatement();
        ResultSet maxIdSet = state.executeQuery("SELECT max(QuizId) FROM " + quizTable);
        int maxId = 1;
        if(maxIdSet.next()) {
            maxId = maxIdSet.getInt(1);
            maxId++;
        }

        String sql = "INSERT INTO " + quizTable + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement preState = con.prepareStatement(sql);
        preState.setInt(QUIZ_ID, maxId);
        preState.setBoolean(IS_RANDOM, isRandom);
        preState.setBoolean(IS_ONE_PAGE, isOnePage);
        preState.setBoolean(IS_IMMEDIATE, isImmediate);
        preState.setBoolean(IN_PRACTICE_MODE, hasPracticeMode);
        preState.setInt(NUMBER_QUESTIONS, questionNum);
        preState.setString(DESCRIPTION, quizName);
        preState.setString(CATEGORY, category);
        preState.setInt(CREATOR_ID, creatorId);
        preState.setDate(CREATE_DATE, createDate);
        preState.executeUpdate();

        Quiz quiz = new Quiz(maxId, creatorId);
        quiz.setIsRandom(isRandom);
        quiz.setIsOnePage(isOnePage);
        quiz.setIsImmediate(isImmediate);
        quiz.setInPracticeMode(hasPracticeMode);
        quiz.setDescription(quizName);
        quiz.setCategory(category);
        quiz.setCreateDate(createDate);

        return quiz;
    }

    @Override
    public List<Quiz> getQuizzesForUser(int userId) throws SQLException {
        String sql = "SELECT * FROM " + quizTable + " WHERE CreatorId = ?;";
        PreparedStatement prepState = con.prepareStatement(sql);
        prepState.setInt(1, userId);
        ResultSet results = prepState.executeQuery();
        List<Quiz> quizzes = new ArrayList<>();
        while(results.next()) {
            Quiz curQuiz = new Quiz(results.getInt(QUIZ_ID), results.getInt(CREATOR_ID));
            curQuiz.setIsRandom(results.getBoolean(IS_RANDOM));
            curQuiz.setIsOnePage(results.getBoolean(IS_ONE_PAGE));
            curQuiz.setIsImmediate(results.getBoolean(IS_IMMEDIATE));
            curQuiz.setInPracticeMode(results.getBoolean(IN_PRACTICE_MODE));
            curQuiz.setDescription(results.getString(DESCRIPTION));
            curQuiz.setCategory(results.getString(CATEGORY));
            curQuiz.setCreateDate(results.getDate(CREATE_DATE));

            List<Question> questions = questionDao.getQuizQuestions(results.getInt(QUIZ_ID));
            curQuiz.setQuestionSet(questions);

            quizzes.add(curQuiz);
        }
        return quizzes;
    }

    @Override
    public List<Quiz> getAllQuizzes() throws SQLException {
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM " + quizTable + ";");
        ResultSet results = stmt.executeQuery();

        List<Quiz> quizzes = new ArrayList<>();

        while(results.next()) {
            Quiz curQuiz = new Quiz(results.getInt(QUIZ_ID), results.getInt(CREATOR_ID));
            curQuiz.setIsRandom(results.getBoolean(IS_RANDOM));
            curQuiz.setIsOnePage(results.getBoolean(IS_ONE_PAGE));
            curQuiz.setIsImmediate(results.getBoolean(IS_IMMEDIATE));
            curQuiz.setInPracticeMode(results.getBoolean(IN_PRACTICE_MODE));
            curQuiz.setDescription(results.getString(DESCRIPTION));
            curQuiz.setCategory(results.getString(CATEGORY));
            curQuiz.setCreateDate(results.getDate(CREATE_DATE));

            List<Question> questions = questionDao.getQuizQuestions(results.getInt(QUIZ_ID));
            curQuiz.setQuestionSet(questions);

            quizzes.add(curQuiz);
        }

        return quizzes;
    }

}
