package se.ju23.typespeeder.menu;

import se.ju23.typespeeder.Messagable;

public enum MainMenu implements Messagable {
    LOGIN("to login"),
    REGISTRATION("register new user"),
    EXIT("stop program");


    private String message;

    MainMenu(String message) {
        this.message = message;
    }

    @Override
    public String getMassage() {
        return message;
    }
}
