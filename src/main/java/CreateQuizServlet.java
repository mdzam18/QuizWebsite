import Quiz.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

public class CreateQuizServlet extends HttpServlet {

    private int questionCounter = 1;

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        PrintStream out = System.out;

        Map<String, String[]> pars = new HashMap<>(httpServletRequest.getParameterMap());
        Map<Integer, HashMap<String, String[]>> data = new HashMap<>();

        out.println("Quiz Name: " + pars.get("quiz_name")[0]);
        pars.remove("quiz_name");

        boolean questionRandomSequenceBool = (pars.get("questionRandomSequence") != null);
        out.println("Random Sequence: " + String.valueOf(questionRandomSequenceBool));
        if(questionRandomSequenceBool) {
            pars.remove("questionRandomSequence");
        }

        boolean oneQuestionPerPageBool = (pars.get("oneQuestionPerPage") != null);
        out.println("One Question Per Page: " + String.valueOf(oneQuestionPerPageBool));
        if(oneQuestionPerPageBool) {
            pars.remove("oneQuestionPerPage");
        }

        boolean hasPracticeModeBool = (pars.get("hasPracticeMode") != null);
        out.println("Has Practice Mode: " + String.valueOf(hasPracticeModeBool));
        if(hasPracticeModeBool) {
            pars.remove("hasPracticeMode");
        }

        for(String key : pars.keySet()) {
            int n = getNumber(key);
            String str = removeExtra(key);
            if(data.containsKey(n)) {
                data.get(n).put(str, pars.get(key));
            } else {
                HashMap<String, String[]> mp = new HashMap<>();
                mp.put(str, pars.get(key));
                data.put(n, mp);
            }
        }

        if(data.isEmpty()) {
            out.print("No Questions");
            return;
        }

        questionCounter = 1;

        List<Question> questions = new ArrayList<>();
        for(Integer k : data.keySet()) {
            printByType(data.get(k));
            questions.add(createQuestion(data.get(k)));
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
        } else if(type == QuestionType.MULTIPLE_CHOICE_AND_ANSWER_QUESTION) {
        }

        if(question != null) {
            question.setScore(score);
        }
        return question;
    }

    private void printByType(Map<String, String[]> arg) {
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

    private int parseAnswerInput(String str) {
        str = str.substring(8);
        str = str.substring(0, str.length() - 6);
        return Integer.parseInt(str);
    }

}
