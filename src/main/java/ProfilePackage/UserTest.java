package ProfilePackage;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testGetAndSet1() {
        User user = new User("ZukoTheFireLord", 1, "CatchTheAvatar");
        assertEquals(user.getUserName(), "ZukoTheFireLord");
        assertEquals(user.getUserId(), 1);
        assertEquals(user.getPassword(), "CatchTheAvatar");
        assertEquals(user.isAdministrator(), false);

        user.setName("Zuko");
        user.setBirthPlace("FireNation");
        user.setAdministrator(true);
        assertEquals(user.isAdministrator(), true);
        Date date = new Date(12, 12, 12);
        user.setBirthDate(date);
        user.setStatus("working");
        assertEquals(user.getName(), "Zuko");
        assertEquals(user.getSurname(), null);
        assertEquals(user.getBirthPlace(), "FireNation");
        assertEquals(user.getBirthDate(), date);
        assertEquals(user.getStatus(), "working");
        user.setUserName("ZukoZuko");
        assertEquals(user.getUserName(), "ZukoZuko");
        user.setSurname("FireLord");
        assertEquals(user.getSurname(), "FireLord");

        assertEquals(user.hashCode(), user.hashCode());
        User user2 = new User("GeneralAiroh", 2, "jasmineTea");
        assertNotEquals(user.hashCode(), user2.hashCode());
    }

    @Test
    public void testEquals(){
        User user1 = new User("Aragorn" , 1, "Gondor");
        User user2 = new User("Frodo" , 2, "Ring");
        assertEquals(user1.equals(user2), false);
        assertEquals(user1.equals(user1), true);
        assertEquals(user1.equals(null), false);
        User user3 = new User("Aragorn" , 1, "Gondor");
        assertEquals(user1.equals(user3), true);
        assertEquals(user1.equals("frodo"), false);

        user3 = new User("Boromir", 1, "Gondor");
        assertEquals(user1.equals(user3), false);

        user3 = new User("Aragorn", 2, "Gondor");
        assertEquals(user1.equals(user3), false);

        user3 = new User("Aragorn", 1, "Ring");
        assertEquals(user1.equals(user3), false);
    }

}