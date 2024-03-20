package se.ju23.typespeeder.menu;

import se.ju23.typespeeder.Messagable;

public enum EnglishMenu implements Messagable {

    CREATE("create useer"),
    DELETE("delete user"),
    CHANGE_LANGUAGE("change language"),
    EXIT("stop program");



    private String message;

    EnglishMenu(String message) {
        this.message = message;
    }

    @Override
    public String getMassage() {
        return message;
    }
}
