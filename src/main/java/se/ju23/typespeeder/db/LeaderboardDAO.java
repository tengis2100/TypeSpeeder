package se.ju23.typespeeder.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LeaderboardDAO {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/type_speeder";
    private static final String JDBC_USERNAME = "tgs";
    private static final String JDBC_PASSWORD = "Iths2001";

    private static final String SQL_INSERT_LEADERBOARD = "INSERT INTO leaderboard (playerid, speed, mostrights, mostright_inorder, average) VALUES (?, ?, ?, ?, ?)";

    public boolean insertLeaderboardEntry(Leaderboard leaderboard) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT_LEADERBOARD)) {

            statement.setLong(1, leaderboard.getPlayerid());
            statement.setDouble(2, leaderboard.getSpeed());
            statement.setInt(3, leaderboard.getMostrights());
            statement.setString(4, leaderboard.getMostrightInorder());
            statement.setDouble(5, leaderboard.getAverage());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
