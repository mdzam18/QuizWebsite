package Quiz;
<<<<<<< HEAD
=======

>>>>>>> 58c11f94a70e57bbd2621ac50df6483211d08a7d
import java.util.*;

public class Quiz {

	private int id;
	private boolean isRandom;
	private boolean isOnePage;
	private boolean isImmediate;
	private int questionCount;
	private String creator;
	private String description;
	private List<Question> questions;
	private String category;
	private Set<String> tags;

	public Quiz(int id, boolean isRandom, 
				boolean isOnePage, boolean isImmedate,
				int questionCount, String creator, 
				String description, List<Question> questions,
				String category, Set<String> tags) 
	{
		this.id = id;
		this.isRandom = isRandom;
		this.isOnePage = isOnePage;
		this.isImmediate = isImmedate;
		this.questionCount = questionCount;
		this.creator = creator;
		this.description = description;
		this.questions = questions;
		this.category = category;
		this.tags = tags;
	}

	public void setId(int id){
		this.id = id;
	}

	public void setIsRandom(boolean isRandom){
		this.isRandom = isRandom();
	}

	public void isOnePage(boolean isOnePage){
		this.isOnePage = isOnePage;
	}

	public void setIsImmediate(boolean isImmediate){
		this.isImmediate = isImmediate;
	}

	public void setQuestionCount(int questionCount){
		this.questionCount = questionCount;
	}

	public void setCreator(String creator){
		this.creator = creator;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public void setQuestionSet(List<Question> questions){
		this.questions = questions;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	public int getId(){
		return id;
	}

	public boolean isRandom(){
		return isRandom;
	}

	public boolean isOnePage(){
		return isOnePage;
	}

<<<<<<< HEAD
	public boolean isImmediate(){
=======
	public boolean isImmedate(){
>>>>>>> 58c11f94a70e57bbd2621ac50df6483211d08a7d
		return isImmediate;
	}

	public int getQuestionCount(){
		return questionCount;
	}

	public String getCreator(){
		return creator;
	}

	public String description(){
		return description;
	}

	public List<Question> getQuestionSet(){
		return questions;
	}

	public String getCategory() { return category; }

	public Set<String> getTags() { return tags; }

}