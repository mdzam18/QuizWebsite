package UserPackage;

import ProfilePackage.CreateTablesForTests;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class test {
    public static void main(String [] args) throws SQLException, ClassNotFoundException {
        CreateTablesForTests tables = new CreateTablesForTests();
        assertEquals(tables.dropTable(CreateTablesForTests.QuizTable), true);
        assertEquals(tables.dropTable(CreateTablesForTests.UsersTable), true);
    }
}
