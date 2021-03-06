package Quiz;

import java.util.*;

public class QuestionToHTML {

    public static List<Question> shuffleList(List<Question> original) {
        List<Question> copy = new ArrayList<>(original);
        Collections.shuffle(copy);
        return copy;
    }

    public static List<Question> shuffleSet(Set<Question> original) {
        return shuffleList(new ArrayList<>(original));
    }

    public static List<String> shuffleStringList(List<String> original) {
        List<String> copy = new ArrayList<>(original);
        Collections.shuffle(copy);
        return copy;
    }

    public static List<String> shuffleStringSet(Set<String> original) {
        return shuffleStringList(new ArrayList<>(original));
    }

    public static String convertQuestion(Question question, int questionN) {
        switch(question.getType()) {
            case QuestionType.QUESTION_RESPONSE:
                return questionResponse(question, questionN);
            case QuestionType.MULTIPLE_CHOICE_QUESTION:
                return multipleChoiceQuestion(question, questionN);
            case QuestionType.PICTURE_RESPONSE_QUESTION:
                return pictureResponseQuestion(question, questionN);
            case QuestionType.MULTI_ANSWER_QUESTION:
                return multiAnswerQuestion(question, questionN);
            case QuestionType.MULTIPLE_CHOICE_AND_ANSWER_QUESTION:
                return multipleChoiceAndAnswerQuestion(question, questionN);
        }
        return null;
    }

    private static String questionResponse(Question question, int questionN) {
        QuestionResponse questionResponse = (QuestionResponse) question;
        StringBuilder builder = new StringBuilder();
        builder.append("<div class=\"questionDivStyle\">\n");
        builder.append("\t<input type=\"hidden\" name=\"ID_" + questionN + "_qId\" ");
        builder.append("value=\"" + question.getQuestionId() + "\">");
        builder.append("\t<p>" + questionN + ") ");
        builder.append(questionResponse.getQuestion());
        builder.append(" [Score: " + questionResponse.getScore());
        builder.append("]</p>\n");
        builder.append("\t<p>Your Answer: ");
        builder.append("\t<input name=\"ID_" + questionN + "_answer" + "\" type=\"text\"></p>\n");
        builder.append("</div>\n");
        return builder.toString();
    }

    private static String multipleChoiceQuestion(Question question, int questionN) {
        MultipleChoiceQuestion multipleChoice = (MultipleChoiceQuestion) question;
        StringBuilder builder = new StringBuilder();
        builder.append("<div class=\"questionDivStyle\">\n");
        builder.append("\t<input type=\"hidden\" name=\"ID_" + questionN + "_qId\" ");
        builder.append("value=\"" + question.getQuestionId() + "\">");
        builder.append("\t<p>" + questionN + ") ");
        builder.append(multipleChoice.getQuestion());
        builder.append(" [Score: " + multipleChoice.getScore());
        builder.append("]</p>\n");
        builder.append("Choose one answer:");
        builder.append("\t<ul class=\"notListTypeUl\">\n");

        String name = "ID_" + questionN + "_answers";
        builder.append("\t<input type=\"hidden\" name=\"");
        builder.append(name + "\" value=\"start\">");
        List<String> answerList = shuffleStringSet(multipleChoice.getAnswerSet());
        for(String answer : answerList) {
            builder.append("<li>");
            builder.append("<input type=\"radio\" name=\"" + name);
            builder.append("\" value=\"" + answer.trim() + "\">");
            builder.append(answer.trim() + "</li>");
        }
        builder.append("</ul>");
        builder.append("</div>\n");
        return builder.toString();
    }

    private static String pictureResponseQuestion(Question question, int questionN) {
        PictureResponseQuestion pictureResponse = (PictureResponseQuestion) question;
        StringBuilder builder = new StringBuilder();
        builder.append("<div class=\"questionDivStyle\">\n");
        builder.append("\t<input type=\"hidden\" name=\"ID_" + questionN + "_qId\" ");
        builder.append("value=\"" + question.getQuestionId() + "\">");
        builder.append("\t<div style=\"border-bottom: 0.5px solid black;\">");
        builder.append("\t\t<img src=\"" + pictureResponse.getImage() + "\"");
        builder.append(" class=\"questionDivImgStyle\" \\>\n");
        builder.append("\t</div>\n");
        builder.append("\t<p>" + questionN + ") ");
        builder.append(pictureResponse.getQuestion());
        builder.append(" [Score: " + pictureResponse.getScore());
        builder.append("]</p>\n");
        builder.append("\t<p>Your Answer: ");
        builder.append("\t<input name=\"ID_" + questionN + "_answer" + "\" type=\"text\"></p>\n");
        builder.append("</div>\n");
        return builder.toString();
    }

    private static String multiAnswerQuestion(Question question, int questionN) {
        MultipleAnswerQuestion multipleAnswer = (MultipleAnswerQuestion) question;
        StringBuilder builder = new StringBuilder();
        builder.append("<div class=\"questionDivStyle\">\n");
        builder.append("\t<input type=\"hidden\" name=\"ID_" + questionN + "_qId\" ");
        builder.append("value=\"" + question.getQuestionId() + "\">");
        builder.append("\t<p>" + questionN + ") ");
        builder.append(multipleAnswer.getQuestion());
        builder.append(" [Score: " + multipleAnswer.getScore());
        builder.append("]</p>\n");
        builder.append("\t<p>Your Answers: </p>");
        builder.append("\t<strong>Keep");
        if(!multipleAnswer.isOrdered()) {
            builder.append("ing");
        }
        builder.append(" order of Answers");
        if(!multipleAnswer.isOrdered()) {
            builder.append(" is not necessary");
        }
        builder.append("</strong>\n");
        builder.append("\t<ul class=\"notListTypeUl\">\n");
        String name = "name=\"ID_" + questionN + "_answers\"";
        for(int i = 0; i<multipleAnswer.getAnswerSet().size(); i++) {
            builder.append("\t\t<li>");
            builder.append("<input " + name + " type=\"text\">");
            builder.append("<li>");
        }
        builder.append("\t<ul>");
        builder.append("</div>\n");
        return builder.toString();
    }

    private static String multipleChoiceAndAnswerQuestion(Question question, int questionN) {
        MultipleChoiceAnswerQuestion multipleChoiceAnswer = (MultipleChoiceAnswerQuestion) question;
        StringBuilder builder = new StringBuilder();
        builder.append("<div class=\"questionDivStyle\">\n");
        builder.append("\t<input type=\"hidden\" name=\"ID_" + questionN + "_qId\" ");
        builder.append("value=\"" + question.getQuestionId() + "\">");
        builder.append("\t<p>" + questionN + ") ");
        builder.append(multipleChoiceAnswer.getQuestion());
        builder.append(" [Score: " + multipleChoiceAnswer.getScore());
        builder.append("]</p>\n");
        builder.append("\t<p>Choose One or More Answer:</p>");
        builder.append("\t<ul class=\"notListTypeUl\">\n");

        String name = "ID_" + questionN + "_answers";
        builder.append("\t<input type=\"hidden\" name=\"");
        builder.append(name + "\" value=\"start\">");
        List<String> answerList = shuffleStringSet(multipleChoiceAnswer.getAnswerSet());
        for(String answer : answerList) {
            builder.append("\t<li>");
            builder.append("<input type=\"checkbox\" name=\"" + name + "\" value=\"");
            builder.append(answer.trim() + "\">" + answer.trim() + "</li>");
        }
        builder.append("\t</ul>");
        builder.append("</div>\n");
        return builder.toString();
    }

}
