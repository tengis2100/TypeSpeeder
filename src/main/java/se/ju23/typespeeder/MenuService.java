package se.ju23.typespeeder;

import java.util.List;

public interface MenuService {

    List<String> getMenuOptions();

    <T extends Enum<T> & Messagable> void displayMenu(Class<T> menu);

    <T extends Messagable> T getUserChoise(T[] options);

    void createUserFromMenu ();

    void deleteUserFromMenu ();
}