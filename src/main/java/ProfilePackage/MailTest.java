package ProfilePackage;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class MailTest {
    private static Mail mail;

    @BeforeAll
    public static void init() {
        mail = new Mail();
    }

    @Test
    void checkMailId() {
        mail.setMailId(1);
        assertEquals(mail.getMailId(),1);
        mail.setMailId(2);
        assertEquals(mail.getMailId(),2);
    }

    @Test
    void checkSenderId() {
        mail.setSenderId(1);
        assertEquals(mail.getSenderId(),1);
        mail.setSenderId(2);
        assertEquals(mail.getSenderId(),2);
    }

    @Test
    void checkReceiverId() {
        mail.setReceiverId(1);
        assertEquals(mail.getReceiverId(),1);
        mail.setReceiverId(2);
        assertEquals(mail.getReceiverId(),2);
    }


    @Test
    void checkType() {
        mail.setType(Mail.requestType);
        assertEquals(mail.getType(), "Request");
        mail.setType(Mail.noteType);
        assertEquals(mail.getType(),"Note");
        mail.setType(Mail.challengeType);
        assertEquals(mail.getType(),"Challenge");
    }

    @Test
    void checkMessage() {
        mail.setMessage("message");
        assertEquals(mail.getMessage(),"message");
        mail.setMessage("someMessage");
        assertEquals(mail.getMessage(),"someMessage");
    }

    @Test
    void checkTime() {
        //Date date = new Date();
        mail.setDate(new Date(2000, 12, 12));
        assertEquals(mail.getDate(), new Date(2000,12,12));
        mail.setDate(new Date(1955, 1, 12));
        assertEquals(mail.getDate(), new Date(1955,1,12));
    }
    @Test
    void checkSeen() {
        mail.setSeen(true);
        assertEquals(mail.isSeen(),true);
        mail.setSeen(false);
        assertEquals(mail.isSeen(),false);
    }

    @Test
    void checkEquals(){
        Mail mail1 = new Mail(1,1,1,Mail.challengeType,"message", null,1);
        Mail mail2 = new Mail(1,2,2,Mail.noteType,"message", null,0);
        Mail mail3 = new Mail(2,2,2,Mail.noteType,"message", null,0);
        assertTrue(mail1.equals(mail2));

        assertEquals(mail1.equals(null), false);
        assertEquals(mail1.equals(mail1), true);
        assertEquals(mail1.equals("bla"), false);
        assertEquals(mail1.equals(mail3), false);
    }
}

