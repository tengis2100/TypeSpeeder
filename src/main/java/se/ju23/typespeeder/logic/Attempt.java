package se.ju23.typespeeder.logic;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class Attempt {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "attempt_id", nullable = false)
    private long attemptId;
    @Basic
    @Column(name = "user_id", nullable = false)
    private long userId;
    @Basic
    @Column(name = "task_id", nullable = false)
    private long taskId;
    @Basic
    @Column(name = "outcome", nullable = false, length = 100)
    private String outcome;
    @Basic
    @Column(name = "end_time", nullable = false)
    private Timestamp endTime;

    public long getAttemptId() {
        return attemptId;
    }

    public void setAttemptId(long attemptId) {
        this.attemptId = attemptId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attempt attempt = (Attempt) o;

        if (attemptId != attempt.attemptId) return false;
        if (userId != attempt.userId) return false;
        if (taskId != attempt.taskId) return false;
        if (outcome != null ? !outcome.equals(attempt.outcome) : attempt.outcome != null) return false;
        if (endTime != null ? !endTime.equals(attempt.endTime) : attempt.endTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (attemptId ^ (attemptId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) (taskId ^ (taskId >>> 32));
        result = 31 * result + (outcome != null ? outcome.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        return result;
    }
}
