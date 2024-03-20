package se.ju23.typespeeder.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.db.UserDAO;
@Component
public class UserService {
    @Autowired
    UserDAO userDAO;
    public void createUser(String username, String password, int gameLevel, String gameName) {
        boolean success = userDAO.createUser(username, password, gameLevel, gameName);
        if (success) {
            System.out.println("User created");
        } else {
            System.out.println("Failed to create user. Please try again.");
        }
    }

    public void deleteUser(long userId) {
        System.out.println("vilken id vill du ta bort");
        boolean success = userDAO.deleteUser(userId);
        if (success) {
            System.out.println("User deleted");
        } else {
            System.out.println("Failed to delete user. Please try again.");
        }
    }
}
