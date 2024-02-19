package se.ju23.typespeeder.logic;

import se.ju23.typespeeder.db.AttemptDAO;

public class AttemptService {

    private AttemptDAO attemptDAO;

    public AttemptService() {
        this.attemptDAO = new AttemptDAO();
    }

    public int countAttemptsForUser(long userId, long taskId) {
        return attemptDAO.countAttemptsForUserAndTask(userId, taskId);
    }
}
