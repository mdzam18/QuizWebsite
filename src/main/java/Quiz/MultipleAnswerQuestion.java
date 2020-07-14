/*
import java.util.*;

public class MultipleAnswerQuestion extends Question {

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
