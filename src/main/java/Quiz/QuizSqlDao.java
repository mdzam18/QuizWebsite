package Quiz;

import ProfilePackage.CreateTablesForTests;
import ProfilePackage.ProfileDataSrc;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class QuizSqlDao implements QuizDao{

    private Connection con;
    private String quizTable;

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


    public QuizSqlDao() throws SQLException, ClassNotFoundException {
        con = ProfileDataSrc.getConnection();
        quizTable = CreateTablesForTests.QuizTable;
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

        stm = con.prepareStatement("INSERT INTO " + quizTable + "  VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");
        stm.setInt(QUIZ_ID, quizId);
        stm.setBoolean(IS_RANDOM, false);
        stm.setBoolean(IS_ONE_PAGE, false);
        stm.setBoolean(IS_IMMEDIATE, false);
        stm.setBoolean(IN_PRACTICE_MODE, false);
        stm.setInt(NUMBER_QUESTIONS, 0);
        stm.setString(DESCRIPTION, null);
        stm.setString(CATEGORY, null);
        stm.setInt(CREATOR_ID, creatorId);
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
        Quiz quiz = new Quiz(quizId, rs.getInt(9));
        return quiz;
    }

    @Override
    public boolean deleteQuiz(Quiz quiz) throws SQLException {
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
        PreparedStatement stm = con.prepareStatement("select * from " + CreateTablesForTests.HistoryTable + ";");
        ResultSet rs = stm.executeQuery();
        HashMap<Integer, Integer> mp = new HashMap<>();
        TreeMap<Integer, List<Integer>> mp2 = new TreeMap<>();
        while (rs.next()) {
            if (mp.containsKey(rs.getInt(2))) {
                int value = mp.get(rs.getInt(2));
                value++;
                mp.put(rs.getInt(2), value);
            } else {
                mp.put(rs.getInt(2), 1);
            }
        }
        List<Integer> ids = new ArrayList<>();
        for (Integer i : mp.keySet()) {
            if (mp2.containsKey(mp.get(i))) {
                mp2.get(mp.get(i)).add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                mp2.put(mp.get(i), list);
                ids.add(mp.get(i));
            }
        }
        ids.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        for (int i = ids.size() - 1; i >= 0; i--) {
            List<Integer> current = mp2.get(ids.get(i));
            for (int j = 0; j < current.size(); j++) {
                res.add(getQuiz(current.get(j)));
            }
        }
        return res;
    }

    @Override
    public List<Quiz> recentlyCreatedQuizzes() throws SQLException {
        List<Quiz> res = new ArrayList<>();
        PreparedStatement stm = con.prepareStatement("select * from " + CreateTablesForTests.QuizTable + " where CreateDate >= ?;");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        Date date = cal.getTime();
        stm.setDate(1, (java.sql.Date) date);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            Quiz quiz = new Quiz(rs.getInt(1), rs.getInt(9));
            quiz.setCategory(rs.getString(8));
            res.add(quiz);
        }
        return res;
    }

    @Override
    public List<Quiz> recentlyTakenQuizzes() throws SQLException {
        List<Quiz> res = new ArrayList<>();
        PreparedStatement stm = con.prepareStatement("select * from " + CreateTablesForTests.HistoryTable + " where EndDate >= ?;");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        Timestamp date = (Timestamp) cal.getTime();
        stm.setTimestamp(1, date);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            res.add(getQuiz(rs.getInt(2)));
        }
        return res;
    }

}