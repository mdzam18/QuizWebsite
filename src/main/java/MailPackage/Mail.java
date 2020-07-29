package MailPackage;

import java.sql.Date;

public class Mail {
    public static String requestType = "Request";
    public static String challengeType = "Challenge";
    public static String noteType = "Note";

    private int mailId;
    private int senderId;
    private int receiverId;
    private String type;
    private String message;
    private Date date;
    private boolean seen;

    public Mail(int mailId, int senderId, int receiverId, String type, String message, Date date, int seen) {
        this.mailId = mailId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.type = type;
        this.message = message;
        this.date = date;
        this.seen = seen == 1 ? true : false;
    }

    public Mail() {

    }

    public int getMailId() {
        return mailId;
    }

    public void setMailId(int mailId) {
        this.mailId = mailId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public boolean equals(Object mail) {
        if (this == mail) return true;
        if (mail == null || getClass() != mail.getClass()) return false;
        return mailId == ((Mail) mail).getMailId();
    }
}
