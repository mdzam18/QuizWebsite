package Quiz;

import java.util.HashSet;
import java.util.Set;

public class AnswerDelimiter {

    public static final String DELIMITER = "<-|::|->";

    public static Set<String> splitAnswer(String answer){
        Set<String> answers = new HashSet<String>();
        String[] array = answer.split(DELIMITER);
        for (int i = 0; i < array.length; i++) {
            answers.add(array[i]);
        }
        return answers;
    }
}
