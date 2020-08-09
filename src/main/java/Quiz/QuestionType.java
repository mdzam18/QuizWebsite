package Quiz;

public final class QuestionType {

	public static final int QUESTION_RESPONSE = 1;
	public static final int MULTIPLE_CHOICE_QUESTION = 2;
	public static final int PICTURE_RESPONSE_QUESTION = 3;
	public static final int MULTI_ANSWER_QUESTION = 4;
	public static final int MULTIPLE_CHOICE_AND_ANSWER_QUESTION = 5;

	public static String getTypeName(int type) {
		String typeName = "";
		if(type == QUESTION_RESPONSE) {
			typeName = "Question-Response";
		} else if(type == MULTIPLE_CHOICE_QUESTION) {
			typeName = "Multiple Choice";
		} else if(type == PICTURE_RESPONSE_QUESTION) {
			typeName = "Picture-Response Questions";
		} else if(type == MULTI_ANSWER_QUESTION) {
			typeName = "Multi-Answer Questions";
		} else if(type == MULTIPLE_CHOICE_AND_ANSWER_QUESTION) {
			typeName = "Multiple Choice with Multiple Answers";
		}
		return typeName;
	}

}
