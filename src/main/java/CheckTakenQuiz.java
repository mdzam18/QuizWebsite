import HistoryPackage.*;
import Quiz.*;
import ServletContextPackage.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.sql.SQLException;
import java.util.*;

@WebServlet("/CheckTakenQuiz")
public class CheckTakenQuiz extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        ServletContext context = getServletContext();
        QuizDao quizdao = (QuizDao) context.getAttribute(ContextDataNames.QUIZ_DAO);
        QuestionDao questionDao = (QuestionDao) context.getAttribute(ContextDataNames.QUESTION_DAO);
        HistoryDao historyDao = (HistoryDao) context.getAttribute(ContextDataNames.HISTORY_DAO);

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

        int fullScore = 0;

        for(Integer i : data.keySet()) {
            Map<String, String[]> mp = data.get(i);
            String qIdStr = mp.get("qId")[0];
            int qId = Integer.parseInt(qIdStr);
            try {
                Question questionFromBase = questionDao.getQuestion(qId);
                int type = questionFromBase.getType();
                if(type == QuestionType.QUESTION_RESPONSE) {

                } else if(type == QuestionType.MULTIPLE_CHOICE_QUESTION) {

                } else if(type == QuestionType.PICTURE_RESPONSE_QUESTION) {

                } else if(type == QuestionType.MULTI_ANSWER_QUESTION) {

                } else if(type == QuestionType.MULTIPLE_CHOICE_AND_ANSWER_QUESTION) {

                } else {
                    break;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        /*for(Integer i : data.keySet()) {
            System.out.println("Question" + i);
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

        //History history = new History();
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
