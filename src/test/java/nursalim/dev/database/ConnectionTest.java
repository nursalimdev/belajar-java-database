package nursalim.dev.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {
    @BeforeAll
    static void beforeAll(){
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testConnection(){
        String jdbcUrl = "jdbc:mysql://localhost:3306/belajar_java_database";
        String user = "root";
        String password = "";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
            System.out.println("Connection success");
        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }

    @Test
    void testCloseConnection(){
        String jdbcUrl = "jdbc:mysql://localhost:3306/belajar_java_database";
        String user = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, user, password)){
            System.out.println("Connection success");
            System.out.println("Connection closed");
        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }

}
