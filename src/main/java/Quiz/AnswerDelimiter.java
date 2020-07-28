package Quiz;

import java.util.*;

public class AnswerDelimiter {

    public static final String ANSWER_DELIMITER = "<-|::|->";
    public static final String IMAGE_DELIMITER = "<-|:IMG:|->";
    public static final String ANSWER_TRUE = "[_TRUE]";
    public static final String ANSWER_FALSE = "[FALSE]";
    public static final int ANSWER_T_F_LEN = 7;

    public static List<String> splitAnswers(String answer){
        List<String> answers = new ArrayList<>();
        String[] array = answer.split(ANSWER_DELIMITER);
        for (int i = 0; i < array.length; i++) {
            answers.add(array[i]);
        }
        return answers;
    }

    public static String[] splitImage(String question) {
        return question.trim().split(IMAGE_DELIMITER);
    }

    public static String mergeAnswers(List<String> answers) {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i<answers.size(); i++) {
            if(i != 0) {
                builder.append(ANSWER_DELIMITER);
            }
            builder.append(answers.get(i));
        }
        return builder.toString();
    }

    public static String mergeImg(String question, String imgUrl) {
        StringBuilder builder = new StringBuilder();
        builder.append(question);
        builder.append(IMAGE_DELIMITER);
        builder.append(imgUrl);
        return builder.toString();
    }

    public static String mergeFewAnswers(Set<String> answers, String rightAnswer) {
        Set<String> set = new HashSet<>();
        set.add(rightAnswer);
        return mergeFewAnswers(answers, set);
    }

    public static String mergeFewAnswers(Set<String> answers, Set<String> choices) {
        StringBuilder builder = new StringBuilder();
        boolean append = false;
        for(String answer : answers) {
            if(append) {
                builder.append(ANSWER_DELIMITER);
            }
            append = true;
            builder.append(answer);
            if(setContains(choices, answer)) {
                builder.append(ANSWER_TRUE);
            } else {
                builder.append(ANSWER_FALSE);
            }
        }
        return builder.toString();
    }

    public static void splitFewAnswers(String answer, Set<String> allAnswerOutput, Set<String> trueAnswerOutput) {
        List<String> splits = splitAnswers(answer);
        for(String cur : splits) {
            int endIndex = cur.length() - ANSWER_T_F_LEN;
            String T_F = cur.substring(endIndex);
            String curAnswer = cur.substring(0, endIndex);

            allAnswerOutput.add(curAnswer);
            if(T_F.equals(ANSWER_TRUE)) {
                trueAnswerOutput.add(curAnswer);
            }
        }
    }

    private static boolean setContains(Set<String> set, String toFind) {
        for(String elem : set) {
            if(elem.trim().equalsIgnoreCase(toFind.trim())) {
                return true;
            }
        }
        return false;
    }

}
