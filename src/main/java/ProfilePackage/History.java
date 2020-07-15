package ProfilePackage;

import java.text.SimpleDateFormat;
import java.util.*;

public class History {

    private int userId;
    private int quizId;
    private int score;
    private Date startDate;
    private Date endDate;

    public History(int userId, int quizId, int score, Date startDate, Date endDate) {
        this.userId = userId;
        this.quizId = quizId;
        this.score = score;
        this.startDate = moduloDate(startDate);
        this.endDate = moduloDate(endDate);
    }

    private Date moduloDate(Date date) {
        long longValue = date.getTime();
        return new Date(longValue - longValue%1000);
    }

    public int getUserId() {
        return userId;
    }

    public int getQuizId() {
        return quizId;
    }

    public int getScore() {
        return score;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setStartDate(Date startDate) {
        this.startDate = moduloDate(startDate);
    }

    public void setEndDate(Date endDate) {
        this.endDate = moduloDate(endDate);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!(obj instanceof History)) return false;
        History other = (History) obj;
        return this.userId == other.userId
                && this.quizId == other.quizId
                && this.score == other.score
                && this.startDate.equals(other.startDate)
                && this.endDate.equals(other.endDate);
    }

    @Override
    public String toString() {
        StringBuilder strb = new StringBuilder();
        strb.append("UserId: ");
        strb.append(userId);
        strb.append(", QuizId: ");
        strb.append(quizId);
        strb.append(", Score: ");
        strb.append(score);
        SimpleDateFormat datefmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        strb.append(", StartDate: ");
        strb.append(datefmt.format(startDate));
        strb.append(", EndDate: ");
        strb.append(datefmt.format(endDate));
        strb.append(';');
        return strb.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, quizId, score, startDate, endDate);
    }

}
