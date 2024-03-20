package se.ju23.typespeeder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Patch {
    private String patchVersion;
    private LocalDateTime realeaseDateTime;



    public Patch(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String preFormatedData = dateTime.toString().replace('T', ' ');
        preFormatedData = preFormatedData.substring(0,19);
        this.realeaseDateTime = LocalDateTime.parse(preFormatedData,formatter);
    }


    public String getPatchVersion() {
        return patchVersion;
    }

    public void setPatchVersion(String patchVersion) {
        this.patchVersion = patchVersion;
    }

    public String getRealeaseDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return realeaseDateTime.format(formatter);
    }


    public void setRealeaseDateTime(LocalDateTime releaseDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        releaseDateTime.format(formatter);
        this.realeaseDateTime = releaseDateTime;
    }
}
