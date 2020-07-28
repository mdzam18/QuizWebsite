package Quiz;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuizTest {

    private static Quiz quiz;

    private static final int CREATOR = 123;
    private static final int QUIZ = 456;

    @BeforeAll
    public static void init(){
        quiz = new Quiz(QUIZ, CREATOR);
    }

    @Test
    public void testCheckCreator(){
        assert(quiz.getCreator() == CREATOR);

        quiz.setCreator(0);
        assert(quiz.getCreator() == 0);
    }

    @Test
    public void testCheckQuizID(){
        assert(quiz.getQuizId() == QUIZ);

        quiz.setQuizId(0);
        assert(quiz.getQuizId() == 0);
    }

    @Test
    public void testSetBooleans(){
        quiz.setIsRandom(true);
        quiz.setIsImmediate(true);
        quiz.setIsOnePage(true);
        quiz.setInPracticeMode(true);

        assertTrue(quiz.isRandom());
        assertTrue(quiz.isImmediate());
        assertTrue(quiz.isOnePage());
        assertTrue(quiz.isInPracticeMode());

        quiz.setIsRandom(false);
        quiz.setIsImmediate(false);
        quiz.setIsOnePage(false);
        quiz.setInPracticeMode(false);

        assertFalse(quiz.isRandom());
        assertFalse(quiz.isImmediate());
        assertFalse(quiz.isOnePage());
        assertFalse(quiz.isInPracticeMode());
    }

    @Test
    public void testSetCategory(){
        quiz.setCategory("category");
        assert(quiz.getCategory().equals("category"));
    }

    @Test
    public void testSetDescription(){
        quiz.setDescription("category");
        assert(quiz.getDescription().equals("category"));
    }

    @Test
    public void testQuestionSet(){
        List<Question> questions = new ArrayList<Question>();

        questions.add(new QuestionResponse("1", new HashSet<String>()));
        questions.add(new MultipleChoiceQuestion("2", new HashSet<String>(), ""));
        questions.add(new MultipleAnswerQuestion("3", new ArrayList<String>(), false));
        questions.add(new PictureResponseQuestion("4", new HashSet<String>(), "URL"));
        questions.add(new MultipleChoiceAnswerQuestion("5", new HashSet<String>(), new HashSet<String>()));

        for(int i = 0; i<questions.size(); i++) {
            questions.get(i).setScore(i*10);
        }

        quiz.setQuestionSet(questions);

        assert(quiz.getQuestionCount() == 5);

        List<Question> result = quiz.getQuestionSet();
        for (int i = 0; i < 5; i++){
            assert(result.get(i).getQuestion().equals("" + (i + 1)));
        }

        assert(result.get(0).getType() == QuestionType.QUESTION_RESPONSE);
        assert(result.get(1).getType() == QuestionType.MULTIPLE_CHOICE_QUESTION);
        assert(result.get(2).getType() == QuestionType.MULTI_ANSWER_QUESTION);
        assert(result.get(3).getType() == QuestionType.PICTURE_RESPONSE_QUESTION);
        assert(result.get(4).getType() == QuestionType.MULTIPLE_CHOICE_AND_ANSWER_QUESTION);

        for(int i = 0; i<result.size(); i++) {
            assert(result.get(i).getScore() == i*10);
        }
    }
}
