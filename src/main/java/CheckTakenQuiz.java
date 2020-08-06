import HistoryPackage.*;
import AchievementsPackage.*;
import Quiz.*;
import ServletContextPackage.*;
import UserPackage.*;
import Statistics.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.sql.SQLException;
import java.util.*;

@WebServlet("/CheckTakenQuiz")
public class CheckTakenQuiz extends HttpServlet {

    private static final String ANSWER = "answer";
    private static final String ANSWER_ARRAY = "answers";
    private final static String currentUser = "currentUser";

    private final static String QUIZ_ATR_NAME = "QUIZ";

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String quizIdStr = httpServletRequest.getParameter("quizId");
        int quizId = Integer.parseInt(quizIdStr);
        ServletContext context = getServletContext();
        QuizDao quizDao = (QuizDao) context.getAttribute(ContextDataNames.QUIZ_DAO);
        QuestionDao questionDao = (QuestionDao) context.getAttribute(ContextDataNames.QUESTION_DAO);

        try {
            Quiz quiz = quizDao.getQuiz(quizId);
            List<Question> questions = quiz.getQuestionSet();
            if(quiz.isRandom()) {
                questions = QuestionToHTML.shuffleList(questions);
                quiz.getQuestionSet().clear();
                quiz.setQuestionSet(questions);
            }

            httpServletRequest.setAttribute(QUIZ_ATR_NAME, quiz);

            RequestDispatcher dispatcher;
            if(quiz.isOnePage()) {
                dispatcher = httpServletRequest.getRequestDispatcher("onePageQuiz.jsp");
            } else {
                dispatcher = httpServletRequest.getRequestDispatcher("onePageQuiz.jsp");
            }
            dispatcher.forward(httpServletRequest, httpServletResponse);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        ServletContext context = getServletContext();
        UserDao userDao = (UserDao) context.getAttribute(ContextDataNames.USER_DAO);
        QuestionDao questionDao = (QuestionDao) context.getAttribute(ContextDataNames.QUESTION_DAO);
        HistoryDao historyDao = (HistoryDao) context.getAttribute(ContextDataNames.HISTORY_DAO);

        HttpSession session = httpServletRequest.getSession();
        String username = (String) session.getAttribute(currentUser);
        if(username == null) {
            System.out.println("User Not Found");
            return;
        }

        int userId = 1;
        try {
            userId = userDao.getUserIdByName(username.trim());
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        Map<String, String[]> pars = new HashMap<>(httpServletRequest.getParameterMap());
        Map<Integer, Map<String, String[]>> data = new HashMap<>();

        String startTimeStr = pars.get("startTime")[0];
        pars.remove("startTime");
        long startTime = Long.parseLong(startTimeStr);

        for(String key : pars.keySet()) {
            int n = getNumber(key);
            String str = removeExtra(key);
            if (data.containsKey(n)) {
                data.get(n).put(str, pars.get(key));
            } else {
                HashMap<String, String[]> mp = new HashMap<>();
                mp.put(str, pars.get(key));
                data.put(n, mp);
            }
        }

        Map<Integer, QuestionPassResult> quizPass = new HashMap<>();
        int fullScore = 0;
        int quizId = 1;

        for(Integer que : data.keySet()) {
            Map<String, String[]> dataMap = data.get(que);
            String qIdStr = dataMap.get("qId")[0];
            int qId = Integer.parseInt(qIdStr);

            System.out.println(que + ": " + dataMap.toString());

            Question questionFromBase;
            try {
                questionFromBase = questionDao.getQuestion(qId);
                quizId = questionDao.getQuestionQuizId(questionFromBase.getQuestionId());
            } catch (SQLException e) {
                e.printStackTrace();
                return;
            }

            int type = questionFromBase.getType();

            QuestionPassResult passResult = null;

            if(type == QuestionType.QUESTION_RESPONSE
            || type == QuestionType.PICTURE_RESPONSE_QUESTION) {
                String answer = dataMap.get(ANSWER)[0].trim();
                List<String> uAnswers = new ArrayList<>();
                uAnswers.add(answer);

                int curScore = questionFromBase.getScore();
                passResult = new QuestionPassResult(userId, questionFromBase, uAnswers);
                if(questionFromBase.checkAnswer(answer)) {
                    fullScore += curScore;
                    passResult.setPassType(QuestionPassResult.FULL_QUESTION_PASS);
                    passResult.setUserScore(curScore);
                } else {
                    passResult.setPassType(QuestionPassResult.NOT_QUESTION_PASS);
                    passResult.setUserScore(0);
                }
                quizPass.put(que, passResult);
            } else if (type == QuestionType.MULTIPLE_CHOICE_QUESTION
                    || type == QuestionType.MULTI_ANSWER_QUESTION
                    || type == QuestionType.MULTIPLE_CHOICE_AND_ANSWER_QUESTION) {
                String[] answers = dataMap.get(ANSWER_ARRAY);

                if(type == QuestionType.MULTIPLE_CHOICE_QUESTION) {
                    if(answers.length == 1) {
                        passResult = new QuestionPassResult(userId, questionFromBase, new ArrayList<>());
                        passResult.setUserScore(0);
                        passResult.setPassType(QuestionPassResult.NOT_QUESTION_PASS);
                    } else {
                        String trueAnswer = answers[1];
                        List<String> list = new ArrayList<>();
                        list.add(trueAnswer);
                        if(questionFromBase.checkAnswer(trueAnswer)) {
                            int curScore = questionFromBase.getScore();
                            fullScore += curScore;
                            passResult = new QuestionPassResult(userId, questionFromBase, list);
                            passResult.setUserScore(curScore);
                            passResult.setPassType(QuestionPassResult.FULL_QUESTION_PASS);
                        } else {
                            passResult = new QuestionPassResult(userId, questionFromBase, list);
                            passResult.setUserScore(0);
                            passResult.setPassType(QuestionPassResult.NOT_QUESTION_PASS);
                        }
                    }
                } else if(type == QuestionType.MULTI_ANSWER_QUESTION) {
                    MultipleAnswerQuestion multipleAnswer = (MultipleAnswerQuestion) questionFromBase;
                    int curScore = questionFromBase.getScore();
                    if(multipleAnswer.isOrdered()) {
                        List<String> list = Arrays.asList(answers);
                        int checked = multipleAnswer.checkAnswerList(list);
                        if(checked == 0) {
                            passResult = new QuestionPassResult(userId, questionFromBase, list);
                            passResult.setUserScore(0);
                            passResult.setPassType(QuestionPassResult.NOT_QUESTION_PASS);
                        } else if(checked == multipleAnswer.getAnswerSet().size()) {
                            fullScore += curScore;
                            passResult = new QuestionPassResult(userId, questionFromBase, list);
                            passResult.setUserScore(curScore);
                            passResult.setPassType(QuestionPassResult.FULL_QUESTION_PASS);
                        } else {
                            curScore += (curScore*list.size())/multipleAnswer.getAnswerSet().size();
                            passResult = new QuestionPassResult(userId, questionFromBase, list);
                            passResult.setUserScore((curScore*list.size())/multipleAnswer.getAnswerSet().size());
                            passResult.setPassType(QuestionPassResult.PARTIAL_QUESTION_PASS);
                        }
                    } else {
                        Set<String> set = new HashSet<>(Arrays.asList(answers));
                        int checked = multipleAnswer.checkAnswerSet(set);
                        if(checked == 0) {
                            passResult = new QuestionPassResult(userId, questionFromBase, new ArrayList<>(set));
                            passResult.setUserScore(0);
                            passResult.setPassType(QuestionPassResult.NOT_QUESTION_PASS);
                        } else if(checked == multipleAnswer.getAnswerSet().size()) {
                            fullScore += curScore;
                            passResult = new QuestionPassResult(userId, questionFromBase, new ArrayList<>(set));
                            passResult.setUserScore(curScore);
                            passResult.setPassType(QuestionPassResult.FULL_QUESTION_PASS);
                        } else {
                            curScore += (curScore*set.size())/multipleAnswer.getAnswerSet().size();
                            passResult = new QuestionPassResult(userId, questionFromBase, new ArrayList<>(set));
                            passResult.setUserScore((curScore*set.size())/multipleAnswer.getAnswerSet().size());
                            passResult.setPassType(QuestionPassResult.PARTIAL_QUESTION_PASS);
                        }
                    }
                } else {//type == QuestionType.MULTIPLE_CHOICE_AND_ANSWER_QUESTION
                    if(answers.length == 1) {
                        passResult = new QuestionPassResult(userId, questionFromBase, new ArrayList<>());
                        passResult.setUserScore(0);
                        passResult.setPassType(QuestionPassResult.NOT_QUESTION_PASS);
                    } else {
                        List<String> list = new ArrayList<>();
                        Set<String> set = new HashSet<>();
                        for(int i = 1; i<answers.length; i++) {
                            list.add(answers[i]);
                            set.add(answers[i]);
                        }
                        MultipleChoiceAnswerQuestion multipleChoiceAnswer = (MultipleChoiceAnswerQuestion) questionFromBase;
                        int curScore = questionFromBase.getScore();
                        int checked = multipleChoiceAnswer.checkAnswers(set);

                        if(checked == 0) {
                            passResult = new QuestionPassResult(userId, questionFromBase, list);
                            passResult.setUserScore(0);
                            passResult.setPassType(QuestionPassResult.NOT_QUESTION_PASS);
                        } else if(checked == multipleChoiceAnswer.getChoices().size()) {
                            fullScore += curScore;
                            passResult = new QuestionPassResult(userId, questionFromBase, list);
                            passResult.setUserScore(curScore);
                            passResult.setPassType(QuestionPassResult.FULL_QUESTION_PASS);
                        } else {
                            fullScore += (curScore*set.size())/multipleChoiceAnswer.getChoices().size();
                            passResult = new QuestionPassResult(userId, questionFromBase, list);
                            passResult.setUserScore((curScore*set.size())/multipleChoiceAnswer.getChoices().size());
                            passResult.setPassType(QuestionPassResult.PARTIAL_QUESTION_PASS);
                        }
                    }
                }
                quizPass.put(que, passResult);
            } else {
                return;
            }
        }

        // TODO
        /*for(Integer i : data.keySet()) {
            System.out.println("Question " + i);
            Map<String, String[]> mp = data.get(i);
            for (String s : mp.keySet()) {
                System.out.print("\t" + s + ": ");
                String[] arr = mp.get(s);
                for (int j = 0; j < arr.length; j++) {
                    if (j != 0) {
                        System.out.print(", ");
                    }
                    System.out.print(arr[j]);
                }
                System.out.println(";");
            }
            System.out.println();
        }*/

        try {
            History history = new History(userId, quizId, fullScore, new Date(startTime), new Date());
            historyDao.addToHistory(history);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        AchievementsSqlDao aDao = null;
        HistorySqlDao hDao = null;
        try {
            aDao = new AchievementsSqlDao();
            hDao = new HistorySqlDao();
            List<History> histories = hDao.getHistories(userId);
            if (histories.size() == 10 && !aDao.hasAchievement(userId, AchievementsSqlDao.MACHINE)){
                aDao.addAchievement(userId, AchievementsSqlDao.MACHINE);
            }
            StatisticsSqlDao sDao = new StatisticsSqlDao();
            if (sDao.getBestPlayer(quizId).getUserId() == userId && !aDao.hasAchievement(userId, AchievementsSqlDao.GREATEST)){
                aDao.addAchievement(userId, AchievementsSqlDao.GREATEST);
            }
            QuizSqlDao qDao = new QuizSqlDao();
            if (qDao.getQuiz(quizId).isInPracticeMode() && !aDao.hasAchievement(userId, AchievementsSqlDao.PRACTICE)){
                aDao.addAchievement(userId, AchievementsSqlDao.PRACTICE);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        httpServletRequest.setAttribute("RESULTS", quizPass);
        try {
            List<History> histories = historyDao.getHistories(userId);
            httpServletRequest.setAttribute("HISTORY", HistorySqlDao.sortByEndDate(histories));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        httpServletRequest.getRequestDispatcher("quizResults.jsp").forward(httpServletRequest, httpServletResponse);
    }

    private int getNumber(String str) {
        str = str.substring(3);
        String number = str.substring(0, str.indexOf("_"));
        return Integer.parseInt(number);
    }

    private String removeExtra(String str) {
        str = str.substring(3);
        return str.substring(str.indexOf("_") + 1);
    }

}
