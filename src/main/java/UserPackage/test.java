package UserPackage;

import ProfilePackage.CreateTablesForTests;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class test {
    public static void main(String [] args) throws SQLException, ClassNotFoundException {
        CreateTablesForTests tables = new CreateTablesForTests();
        //assertEquals(tables.dropTable(CreateTablesForTests.FriendsTableTest), true);
       // assertEquals(tables.dropTable(CreateTablesForTests.MailsTableTest), true);
       // assertEquals(tables.dropTable(CreateTablesForTests.HistoryTableTest), true);
       // assertEquals(tables.dropTable(CreateTablesForTests.QuestionTableTest), true);
       // assertEquals(tables.dropTable(CreateTablesForTests.AchievementsTableTest), true);
       // assertEquals(tables.dropTable(CreateTablesForTests.QuizTagTableTest), true);
      //  assertEquals(tables.dropTable(CreateTablesForTests.QuizTableTest), true);
        assertEquals(tables.dropTable(CreateTablesForTests.UsersTableTest), true);
    }
}
