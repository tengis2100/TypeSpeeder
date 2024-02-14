package se.ju23.typespeeder;

import java.util.ArrayList;
import java.util.List;

public class Menu implements MenuService {

    @Override
    public List<String> getMenuOptions() {
        List<String> options = new ArrayList<>();
        options.add("Option 1");
        options.add("Option 2");
        options.add("Option 3");
        options.add("Option 4");
        options.add("Option 5");
        return options;
    }

    @Override
    public void displayMenu() {
        List<String> options = getMenuOptions();
        System.out.println("Menu:");
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }
}

