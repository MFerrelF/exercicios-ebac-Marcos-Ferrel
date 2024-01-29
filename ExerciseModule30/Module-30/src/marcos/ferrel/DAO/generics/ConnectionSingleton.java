package marcos.ferrel.DAO.generics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author marcos.ferrel
 */
public class ConnectionSingleton {

    private static Connection connection;

    private ConnectionSingleton (Connection connection) {

    }

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = initConnection();
            return connection;
        } else if (connection != null && connection.isClosed()) {
            connection = initConnection();
            return connection;
        } else {
            return connection;
        }
    }

    private static Connection initConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:15432/module_30", "postgres", "admin");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
