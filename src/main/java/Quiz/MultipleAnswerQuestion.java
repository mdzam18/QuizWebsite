<<<<<<< HEAD
/*
=======

package Quiz;

>>>>>>> 2a06156c4c583d1d41a42ba5695d7932222e59f0
import java.util.*;

public class MultipleAnswerQuestion extends Question {

<<<<<<< HEAD
	private String question;
	private Set<String> answers;
	private Set<String> choices;

	private static final int TYPE = QuestionType.MULTI_ANSWER_QUESTION;

	public MultipleAnswerQuestion(String question, Set<String> answers, Set<String> choices) {
		super(question, answers);
		this.choices = choices;
	}

	public boolean checkAnswer(String answer) {
		return (answers.contains(answer));
=======
		return (choices.contains(answer));

	}

	public Set<String> getChoices() {
		return choices;
	}
}*/
=======
    private String question;
    private Set<String> answers;
    private Set<String> choices;

    private static final int TYPE = QuestionType.MULTI_ANSWER_QUESTION;

    public MultipleAnswerQuestion(String question, Set<String> answers, Set<String> choices) {
        super(question, answers);
        this.choices = choices;
    }

    public boolean checkAnswer(String answer) {
        return (answers.contains(answer));
    }

    public Set<String> getChoices() {
        return choices;
    }
}

>>>>>>> 2a06156c4c583d1d41a42ba5695d7932222e59f0
