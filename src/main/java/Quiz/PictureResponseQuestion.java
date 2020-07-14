package Quiz;
<<<<<<< HEAD
import java.util.*;
=======

import java.util.Set;
>>>>>>> 58c11f94a70e57bbd2621ac50df6483211d08a7d

public class PictureResponseQuestion extends Question {

	private String question;
	private String imgURL;
	private Set<String> answers;

<<<<<<< HEAD
	private static final int sTYPE = QuestionType.PICTURE_RESPONSE_QUESTION;
=======
	private static final int TYPE = QuestionType.PICTURE_RESPONSE_QUESTION;
>>>>>>> 58c11f94a70e57bbd2621ac50df6483211d08a7d

	public PictureResponseQuestion(String question, Set<String> answers, String imgURL) {
		super(question, answers);
		this.imgURL = imgURL;
	}

	public boolean checkAnswer(String answer) {
		return (answers.contains(answer));
	}

	public String getImage(){
		return imgURL;
	}

	
}