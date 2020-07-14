package Quiz;

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
<<<<<<< HEAD
		return (answers.contains(answer));
=======
		return (choices.contains(answer));
>>>>>>> 58c11f94a70e57bbd2621ac50df6483211d08a7d
	}

	public Set<String> getChoices() {
		return choices;
	}
}