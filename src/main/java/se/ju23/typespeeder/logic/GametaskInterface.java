package se.ju23.typespeeder.logic;

import java.sql.Date;

public interface GametaskInterface {

    long getTaskId();

    void setTaskId(long taskId);

    String getLanguage();

    void setLanguage(String language);

    int getTaskType();

    void setTaskType(int taskType);

    Date getCreatedTimestamp();

    void setCreatedTimestamp(Date createdTimestamp);

    String getSolution();

    void setSolution(String solution);
}
