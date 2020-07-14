<<<<<<< HEAD
/*
=======
package Quiz;

>>>>>>> f4fd8e79be1dd4bd2706adbd83ee1ce56cb1aa70
import java.util.*;

public class QuestionResponse extends Question {

	private String question;
	private Set<String> answers;

	private static final int TYPE = QuestionType.QUESTION_RESPONSE;

	public QuestionResponse(String question, Set<String> answers) {
		super(question, answers);
	}

	public boolean checkAnswer(String answer) {
		return (answers.contains(answer));
<<<<<<< HEAD
 	}
=======
	}
<<<<<<< HEAD
}*/
=======
>>>>>>> 58c11f94a70e57bbd2621ac50df6483211d08a7d
}
>>>>>>> f4fd8e79be1dd4bd2706adbd83ee1ce56cb1aa70
