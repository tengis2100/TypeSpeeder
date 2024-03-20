package se.ju23.typespeeder.menu;

import se.ju23.typespeeder.Messagable;

public enum LoginMenu  implements Messagable {
    create("create user"),
    delete("delete user"),
    exit ("exit");

    private String message;

    LoginMenu(String message) {
        this.message = message;
    }

    @Override
    public String getMassage() {
        return message;
    }
}