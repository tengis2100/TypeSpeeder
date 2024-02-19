package se.ju23.typespeeder.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class AttemptDAO {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/your_database_name";
    private static final String JDBC_USERNAME = "tgs";
    private static final String JDBC_PASSWORD = "Iths2001";

    public boolean recordAttempt(long userId, long taskId, String outcome, Timestamp endTime) {
        String sql = "INSERT INTO attempt (user_id, task_id, outcome, end_time) VALUES (?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, userId);
            statement.setLong(2, taskId);
            statement.setString(3, outcome);
            statement.setTimestamp(4, endTime);
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int countAttemptsForUserAndTask(long userId, long taskId) {
        String sql = "SELECT COUNT(*) FROM attempt WHERE user_id = ? AND task_id = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, userId);
            statement.setLong(2, taskId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Return 0 if there's an error or no attempts found
    }
}
