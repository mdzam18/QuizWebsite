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