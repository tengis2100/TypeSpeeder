package se.ju23.typespeeder.logic;

import java.sql.*;
import se.ju23.typespeeder.logic.Gametask;

public class GametaskDAO {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/your_database_name";
    private static final String JDBC_USERNAME = "tgs";
    private static final String JDBC_PASSWORD = "Iths2001";

    public Gametask getRandomGametask() {
        String sql = "SELECT * FROM gametask ORDER BY RAND() LIMIT 1";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            if (resultSet.next()) {
                return mapResultSetToGametask(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Gametask mapResultSetToGametask(ResultSet resultSet) throws SQLException {
        Gametask gametask = new Gametask();
        gametask.setTaskId(resultSet.getLong("task_id"));
        gametask.setLanguage(resultSet.getObject("language"));
        gametask.setTaskType(resultSet.getInt("task_type"));
        gametask.setCreatedTimestamp(resultSet.getDate("created_timestamp"));
        gametask.setSolution(resultSet.getString("solution"));
        return gametask;
    }
}
