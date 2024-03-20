package se.ju23.typespeeder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NewsLetter {
    private String content;
    private LocalDateTime publishDateTime;

    public NewsLetter(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String preFormatedData = dateTime.toString().replace('T', ' ');
        preFormatedData = preFormatedData.substring(0,19);
        this.publishDateTime = LocalDateTime.parse(preFormatedData,formatter);
    }

    public NewsLetter () {
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getPublishDateTime() {
        return publishDateTime;
    }

    public void setPublishDateTime(LocalDateTime publishDateTime) {
        this.publishDateTime = publishDateTime;
    }
}
