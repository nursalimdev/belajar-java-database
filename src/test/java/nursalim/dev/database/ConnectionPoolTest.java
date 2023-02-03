package nursalim.dev.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPoolTest {

    @Test
    void testHikariCP(){
        // konfigurasi database
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/belajar_java_database");
        config.setUsername("root");
        config.setPassword("");

        // konfigurasi pool
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setIdleTimeout(60_000);
        config.setMaxLifetime(10 * 60_000);

        HikariDataSource dataSource = new HikariDataSource(config);
        try (Connection connection = dataSource.getConnection()) {

            // mengembalikan koneksi ke pool
            connection.close();

            // close datasource
            dataSource.close();

        } catch (SQLException e) {
            Assertions.fail(e);
        }

    }

    @Test
    void testUtil(){
        try (Connection connection = ConnectionUtil.getDataSource().getConnection()) {
            System.out.println("Connection success");
        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }
}
