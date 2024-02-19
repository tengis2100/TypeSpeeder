package se.ju23.typespeeder.db;

import java.sql.*;

public class AttemptDAO {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/your_database_name";
    private static final String JDBC_USERNAME = "tgs";
    private static final String JDBC_PASSWORD = "Iths2001";

    public int countAttemptsForUserAndTask(long userId, long taskId) {
        String sql = "SELECT COUNT(*) FROM attempt WHERE user_id = ? AND task_id = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, userId);
            statement.setLong(2, taskId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1); // count of attempts
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Return 0 if there's an error or no attempts found
    }
}
