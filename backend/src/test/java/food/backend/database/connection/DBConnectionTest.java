package food.backend.database.connection;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import static org.springframework.test.util.AssertionErrors.assertNotNull;


@SpringBootTest
public class DBConnectionTest {

    @Autowired
    private DataSource dataSource;

    @Value("${spring.datasource.url}")
    private String databaseUrl;
    @Test
    public void testDbConnection() {
        try (Connection connection = dataSource.getConnection()) {

            DatabaseMetaData metaData = connection.getMetaData();

            assertNotNull("DB 연결 되나", connection);
            Assertions.assertThat(databaseUrl.equals(metaData.getURL()));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
