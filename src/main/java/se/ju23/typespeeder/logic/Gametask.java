package se.ju23.typespeeder.logic;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Gametask implements GametaskInterface {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "task_id", nullable = false)
    private Long taskId;
    @Basic
    @Column(name = "language", nullable = false)
    private String language;
    @Basic
    @Column(name = "task_type", nullable = false)
    private int taskType;
    @Basic
    @Column(name = "created_timestamp", nullable = false)
    private Date createdTimestamp;
    @Basic
    @Column(name = "solution", nullable = false, length = -1)
    private String solution;

    public long getTaskId() {
        return taskId;
    }

    public Gametask() {
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
    @Transient
    private String userInput;

    public String getUserInput() {
        return this.userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }
@Transient
    private long startTime;
    @Transient
    private long endTime;

    public void startGame() {
        this.startTime = System.currentTimeMillis();
    }

    public void endGame() {
        this.endTime = System.currentTimeMillis();
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Gametask gametask = (Gametask) o;

        if (taskId != gametask.taskId) return false;
        if (taskType != gametask.taskType) return false;
        if (language != null ? !language.equals(gametask.language) : gametask.language != null) return false;
        if (createdTimestamp != null ? !createdTimestamp.equals(gametask.createdTimestamp) : gametask.createdTimestamp != null)
            return false;
        if (solution != null ? !solution.equals(gametask.solution) : gametask.solution != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (taskId ^ (taskId >>> 32));
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + taskType;
        result = 31 * result + (createdTimestamp != null ? createdTimestamp.hashCode() : 0);
        result = 31 * result + (solution != null ? solution.hashCode() : 0);
        return result;
    }
}
