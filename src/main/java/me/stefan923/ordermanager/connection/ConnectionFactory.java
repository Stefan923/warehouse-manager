package me.stefan923.ordermanager.connection;
import java.sql.*;
import java.util.logging.Logger;

public class ConnectionFactory {

    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/warehousedb";
    private static final String USER = "root";
    private static final String PASS = "DButcnMySQL";

    private static ConnectionFactory singleInstance = new ConnectionFactory();

    private ConnectionFactory() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private Connection createConnection() {
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASS);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Connection getConnection() {
        return singleInstance.createConnection();
    }
    public static void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void close(Statement statement) {
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void close(ResultSet resultset) {
        try {
            resultset.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}