package se.ju23.typespeeder.menu;

import se.ju23.typespeeder.Messagable;

public enum SwedishMenu implements Messagable {

    CREATE("skapa user"),
    DELETE("radera user"),
    CHANGE_LANGUAGE("byta språk"),
    EXIT("stop program");


    private String message;

    SwedishMenu(String message) {
        this.message = message;
    }

    @Override
    public String getMassage() {
        return message;
    }
}
