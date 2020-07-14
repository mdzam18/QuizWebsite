<<<<<<< HEAD

/*
=======
=======
>>>>>>> 2a06156c4c583d1d41a42ba5695d7932222e59f0
package Quiz;

import java.util.*;

public class Question {

    private String question;
    private Set<String> answers;

    public Question(String question, Set<String> answers) {
        this.question = question;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public Set<String> getAnswerSet() {
        return answers;
    }
}