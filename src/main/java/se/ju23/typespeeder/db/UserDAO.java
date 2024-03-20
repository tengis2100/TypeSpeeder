package se.ju23.typespeeder.db;

import org.springframework.stereotype.Component;
import se.ju23.typespeeder.db.UserEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class UserDAO {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/type_speeder";
    private static final String JDBC_USERNAME = "tgs";
    private static final String JDBC_PASSWORD = "Iths2001";

    // SQL queries for user management
    private static final String SQL_SELECT_USER_BY_USERNAME_AND_PASSWORD =
            "SELECT * FROM user WHERE username = ? AND password = ?";
    private static final String SQL_INSERT_USER =
            "INSERT INTO user (username, password, gamelevel, gamename) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE_USER =
            "UPDATE user SET password = ?, gamelevel = ?, gamename = ? WHERE userid = ?";
    private static final String SQL_DELETE_USER_BY_ID = "DELETE FROM user WHERE userid = ?";


    public UserEntity getUserByUsernameAndPassword(String username, String password) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USER_BY_USERNAME_AND_PASSWORD)) {

            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    UserEntity user = new UserEntity();
                    user.setUserid(resultSet.getLong("userid"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    user.setGamelevel(resultSet.getInt("gamelevel"));
                    user.setGamename(resultSet.getString("gamename"));
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }
        return null;
    }

    // Method to create a new user
    public boolean createUser(String username, String password, int gameLevel, String gameName) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT_USER)) {

            statement.setString(1, username);
            statement.setString(2, password);
            statement.setInt(3, gameLevel);
            statement.setString(4, gameName);

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as needed
            return false;
        }
    }

    // Method to update user details
    public boolean updateUser(UserEntity user) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER)) {

            statement.setString(1, user.getPassword());
            statement.setInt(2, user.getGamelevel());
            statement.setString(3, user.getGamename());
            statement.setLong(4, user.getUserid());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as needed
            return false;
        }
    }

    public boolean deleteUser(long userId) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_USER_BY_ID)) {

            statement.setLong(1, userId);

            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as needed
            return false;
        }
    }
}
