// DatabaseConnector.java Author: Vural Bilgin ID: 22095034
// Provides database connection( POSTGRESQL)
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/Linkedn";
    private static final String USER = "postgres";
    private static final String PASSWORD = "0539";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }
}
