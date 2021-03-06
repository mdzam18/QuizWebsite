package Quiz;

import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Quiz {

	private int quizId;
	private boolean isRandom;
	private boolean isOnePage;
	private boolean isImmediate;
	private boolean inPracticeMode;
	private String description;
	private List<Question> questions;
	private String category;
	private int creatorId;
	private Date createDate;
	private int numberOfQuestions;

	public Quiz(int quizId, int creatorId)
	{
		this.quizId = quizId;
		this.creatorId = creatorId;
		this.questions = new ArrayList<>();
	}

	public int getNumberOfQuestions() {
		return numberOfQuestions;
	}

	public void setNumberOfQuestions(int numberOfQuestions) {
		this.numberOfQuestions = numberOfQuestions;
	}

	public void setQuizId(int quizId){
		this.quizId = quizId;
	}

	public void setIsRandom(boolean isRandom){
		this.isRandom = isRandom;
	}

	public void setIsOnePage(boolean isOnePage){
		this.isOnePage = isOnePage;
	}

	public void setIsImmediate(boolean isImmediate){
		this.isImmediate = isImmediate;
	}

	public void setInPracticeMode(boolean inPracticeMode) { this.inPracticeMode = inPracticeMode; }

	public void setCreatorId(int creatorId){
		this.creatorId = creatorId;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public void setQuestionSet(List<Question> questions){
		this.questions.addAll(questions);
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getQuizId(){
		return quizId;
	}

	public boolean isRandom(){
		return isRandom;
	}

	public boolean isOnePage(){
		return isOnePage;
	}

	public boolean isImmediate(){
		return isImmediate;
	}

	public boolean isInPracticeMode() { return inPracticeMode; }

	public int getQuestionCount(){
		return questions.size();
	}

	public int getCreatorId(){
		return creatorId;
	}

	public String getDescription(){
		return description;
	}

	public List<Question> getQuestionSet(){
		return questions;
	}

	public String getCategory() { return category; }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Quiz quiz = (Quiz) o;
		return quizId == quiz.quizId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(quizId, creatorId);
	}


	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
