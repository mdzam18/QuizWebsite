import Quiz.*;
import ServletContextPackage.ContextDataNames;
import UserPackage.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.SQLException;
import java.util.*;

@WebServlet("/CreateQuizServlet")
public class CreateQuizServlet extends HttpServlet {

    private final static String currentUser = "currentUser";
    private final static String SUCCESS = "SUCCESS";
    private final static String ALREADY_HAS = "FAILURE";

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();

        ServletContext context = getServletContext();
        UserDao userDao = (UserDao) context.getAttribute(ContextDataNames.USER_DAO);
        QuizDao quizDao = (QuizDao) context.getAttribute(ContextDataNames.QUIZ_DAO);

        String sessionUserName = (String) session.getAttribute(currentUser);
        try {
            int userId = userDao.getUserIdByName(sessionUserName);
            String quizName = httpServletRequest.getParameter("quizName");
            PrintWriter out = httpServletResponse.getWriter();
            if(quizDao.userHasQuizByName(userId, quizName)) {
                out.print(ALREADY_HAS);
            } else {
                out.print(SUCCESS);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        Map<String, String[]> pars = new HashMap<>(httpServletRequest.getParameterMap());
        Map<Integer, HashMap<String, String[]>> data = new HashMap<>();

        HttpSession session = httpServletRequest.getSession();

        ServletContext context = getServletContext();
        UserDao userDao = (UserDao) context.getAttribute(ContextDataNames.USER_DAO);
        QuizDao quizDao = (QuizDao) context.getAttribute(ContextDataNames.QUIZ_DAO);
        QuestionDao questionDao = (QuestionDao) context.getAttribute(ContextDataNames.QUESTION_DAO);

        String userName = (String) session.getAttribute(currentUser);
        int userId = 1;
        try {
            userId = userDao.getUserIdByName(userName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String quizName = pars.get("quiz_name")[0].trim();
        pars.remove("quiz_name");

        String category = "";
        if(pars.get("category") != null) {
            category = pars.get("category")[0].trim();
            pars.remove("category");
        }

        boolean questionRandomSequence = (pars.get("questionRandomSequence") != null);
        if(questionRandomSequence) {
            pars.remove("questionRandomSequence");
        }

        boolean oneQuestionPerPage = (pars.get("oneQuestionPerPage") != null);
        if(oneQuestionPerPage) {
            pars.remove("oneQuestionPerPage");
        }

        boolean hasPracticeMode = (pars.get("hasPracticeMode") != null);
        if(hasPracticeMode) {
            pars.remove("hasPracticeMode");
        }

        boolean isImmediate = (pars.get("ImmediateAnswer") != null);
        if(isImmediate) {
            pars.remove("ImmediateAnswer");
        }

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

        List<Question> questions = new ArrayList<>();
        for(Integer k : data.keySet()) {
            questions.add(createQuestion(data.get(k)));
        }

        Quiz quiz = null;
        try {
            quiz = quizDao.addQuiz(
                    userId,
                    questionRandomSequence, oneQuestionPerPage, isImmediate, hasPracticeMode,
                    questions.size(),
                    quizName,
                    category,
                    new java.sql.Date(System.currentTimeMillis()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(quiz != null) {
            int quizId = quiz.getQuizId();
            for(Question question : questions) {
                appendQuestion(questionDao, question, quizId);
            }

            String path = "quizInfo.jsp?id=" + quizId;
            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher(path);
            dispatcher.forward(httpServletRequest, httpServletResponse);
        }
    }

    private void appendQuestion(QuestionDao questionDao, Question question, int quizId) {
        if(question == null) return;

        int type = question.getType();
        String questionText = "";
        String answer = "";

        if(type == QuestionType.QUESTION_RESPONSE) {
            questionText = question.getQuestion();
            answer = AnswerDelimiter.mergeAnswers(question.getAnswerSet());
        } else if(type == QuestionType.MULTIPLE_CHOICE_QUESTION) {
            MultipleChoiceQuestion questionType = (MultipleChoiceQuestion) question;
            questionText = question.getQuestion();
            answer = AnswerDelimiter.mergeFewAnswers(question.getAnswerSet(), questionType.getChoices());
        } else if(type == QuestionType.PICTURE_RESPONSE_QUESTION) {
            PictureResponseQuestion questionType = (PictureResponseQuestion) question;
            questionText = AnswerDelimiter.mergeImg(question.getQuestion(), questionType.getImage());
            answer = AnswerDelimiter.mergeAnswers(question.getAnswerSet());
        } else if(type == QuestionType.MULTI_ANSWER_QUESTION) {
            MultipleAnswerQuestion questionType = (MultipleAnswerQuestion) question;
            questionText = AnswerDelimiter.mergeKeepOrderBool(question.getQuestion(), questionType.isOrdered());
            answer = AnswerDelimiter.mergeAnswers(questionType.getOrderedAnswers());
        } else if(type == QuestionType.MULTIPLE_CHOICE_AND_ANSWER_QUESTION) {
            MultipleChoiceAnswerQuestion questionType = (MultipleChoiceAnswerQuestion) question;
            questionText = question.getQuestion();
            answer = AnswerDelimiter.mergeFewAnswers(questionType.getAnswerSet(), questionType.getChoices());
        }

        try {
            Question q = questionDao.addQuestion(questionText, answer, type, question.getScore(), quizId);
            question.setQuestionId(q.getQuestionId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Question createQuestion(Map<String, String[]> arg) {
        Map<String, String[]> map = new HashMap<>(arg);
        Question question = null;

        String questionText = map.get("questionText")[0].trim();
        int type, score;
        type = Integer.parseInt(map.get("type")[0].trim());
        score = Integer.parseInt(map.get("score")[0].trim());

        map.remove("questionText");
        map.remove("type");
        map.remove("score");

        if(type == QuestionType.QUESTION_RESPONSE
        || type == QuestionType.MULTI_ANSWER_QUESTION
        || type == QuestionType.PICTURE_RESPONSE_QUESTION) {
            String[] answers = map.get("answers").clone();
            for(int i = 0; i<answers.length; i++) {
                answers[i] = answers[i].trim();
            }
            Set<String> answersSet = new HashSet<>(Arrays.asList(answers));

            if(type == QuestionType.QUESTION_RESPONSE) {
                question = new QuestionResponse(questionText, answersSet);
            } else if(type == QuestionType.MULTI_ANSWER_QUESTION) {
                boolean keepOrder = (map.get("keepOrder") != null);
                question = new MultipleAnswerQuestion(questionText, Arrays.asList(answers), keepOrder);
            } else {
                String urlImg = map.get("imgURL")[0].trim();
                question = new PictureResponseQuestion(questionText, answersSet, urlImg);
            }
        } else if(type == QuestionType.MULTIPLE_CHOICE_QUESTION) {
            String answerTrue = removeExtra(map.get("answer_true")[0]);
            map.remove("answer_true");

            String rightAnswer = null;

            Set<String> answersSet = new HashSet<>();
            for(String key : map.keySet()) {
                String curAnswer = map.get(key)[0].trim();
                answersSet.add(curAnswer);
                if(parseAnswerInput(answerTrue) == parseAnswerInput(key)) {
                    rightAnswer = curAnswer;
                }
            }
            if(rightAnswer == null) {
                rightAnswer = answersSet.iterator().next();
            }
            question = new MultipleChoiceQuestion(questionText, answersSet, rightAnswer);
        } else if(type == QuestionType.MULTIPLE_CHOICE_AND_ANSWER_QUESTION) {
            Set<String> answers = new HashSet<>();
            Set<String> choices = new HashSet<>();

            for(String key : map.keySet()) {
                String[] values = map.get(key);
                if(values.length == 1) {
                    answers.add(values[0].trim());
                } else {
                    String curAnswer;
                    if(values[0].equalsIgnoreCase("on")) {
                        curAnswer = values[1].trim();
                    } else {
                        curAnswer = values[0].trim();
                    }
                    answers.add(curAnswer);
                    choices.add(curAnswer);
                }
            }
            if(choices.size() == 0) {
                choices.add(answers.iterator().next());
            }
            question = new MultipleChoiceAnswerQuestion(questionText, answers, choices);
        }
        if(question != null) {
            question.setScore(score);
        }
        return question;
    }

    /* Some Important Methods */
    private int getNumber(String str) {
        str = str.substring(3);
        String number = str.substring(0, str.indexOf("_"));
        return Integer.parseInt(number);
    }

    private String removeExtra(String str) {
        str = str.substring(3);
        return str.substring(str.indexOf("_") + 1);
    }

    private int parseAnswerInput(String str) {
        str = str.substring(8);
        str = str.substring(0, str.length() - 6);
        return Integer.parseInt(str);
    }

    /* TODO
    private int questionCounter = 1;

    private void printByType(Map<String, String[]> arg) {
        questionCounter = 1;

        Map<String, String[]> map = new HashMap<>(arg);

        String questionText = map.get("questionText")[0];
        int type, score;
        type = Integer.parseInt(map.get("type")[0]);
        score = Integer.parseInt(map.get("score")[0]);

        map.remove("questionText");
        map.remove("type");
        map.remove("score");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(questionCounter++);
        stringBuilder.append(") ");
        stringBuilder.append(questionText);
        if(score != 0) {
            stringBuilder.append(" [Score: ");
            stringBuilder.append(score);
            stringBuilder.append("]");
        }
        stringBuilder.append('\n');

        if(type == QuestionType.QUESTION_RESPONSE
                || type == QuestionType.MULTI_ANSWER_QUESTION
                || type == QuestionType.PICTURE_RESPONSE_QUESTION) {
            if(type == QuestionType.PICTURE_RESPONSE_QUESTION) {
                stringBuilder.append("URL: ");
                stringBuilder.append(map.get("imgURL")[0]);
                stringBuilder.append('\n');
            }

            stringBuilder.append("Answers");
            if(map.get("keepOrder") != null) {
                stringBuilder.append(" (Keep Order)");
            }
            stringBuilder.append(":\n");

            String[] answers = map.get("answers");
            Arrays.sort(answers);
            for(int i = 0; i<answers.length; i++) {
                stringBuilder.append('\t');
                stringBuilder.append(answers[i].trim());
                stringBuilder.append('\n');
            }
        } else if(type == QuestionType.MULTIPLE_CHOICE_QUESTION) {
            int answerCounter = 1;
            String answerTrue = removeExtra(map.get("answer_true")[0]);
            map.remove("answer_true");
            stringBuilder.append("Answer True: ");
            stringBuilder.append(answerTrue);
            stringBuilder.append('\n');
            for(String key : map.keySet()) {
                stringBuilder.append(answerCounter++);
                stringBuilder.append(") ");
                stringBuilder.append(map.get(key)[0]);
                if(parseAnswerInput(answerTrue) == parseAnswerInput(key)) {
                    stringBuilder.append(" [TRUE]");
                }
                stringBuilder.append('\n');
            }
        } else if(type == QuestionType.MULTIPLE_CHOICE_AND_ANSWER_QUESTION) {
            int answerCounter = 1;
            for(String key : map.keySet()) {
                String[] values = map.get(key);
                stringBuilder.append(answerCounter++);
                stringBuilder.append(") ");
                if(values.length == 1) {
                    stringBuilder.append(values[0]);
                } else {
                    if(values[0].equalsIgnoreCase("on")) {
                        stringBuilder.append(values[1]);
                    } else {
                        stringBuilder.append(values[0]);
                    }
                    stringBuilder.append(" [TRUE]");
                }
                stringBuilder.append('\n');
            }
        } else {
            stringBuilder.append("Invalid Question Type\n");
        }

        System.out.println(stringBuilder.toString());
    }*/

}
