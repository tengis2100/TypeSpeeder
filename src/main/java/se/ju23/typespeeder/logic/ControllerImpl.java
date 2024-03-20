package se.ju23.typespeeder.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.*;
import se.ju23.typespeeder.menu.EnglishMenu;
import se.ju23.typespeeder.menu.LanguageMenu;
import se.ju23.typespeeder.menu.SwedishMenu;

@Component
public class ControllerImpl implements Controller {
    @Autowired
    GameInter game;
    @Autowired
    MenuService menu;

private LanguageMenu language = LanguageMenu.ENGLISH;
    @Override
    public void run(){
        System.out.println(game.getRandomSolution());

        while (true) {
            if(language == LanguageMenu.ENGLISH){
                menu.displayMenu(EnglishMenu.class);
               EnglishMenu choice = menu.getUserChoise(EnglishMenu.values());
                switch (choice){
                    case CHANGE_LANGUAGE -> chooseLanguage();
                    case CREATE -> menu.createUserFromMenu();
                    case DELETE -> menu.deleteUserFromMenu();
                    case EXIT -> System.exit(1);
                    default -> System.out.println("Wrong input");
                }

            }

            if(language == LanguageMenu.SWEDISH){
                menu.displayMenu(SwedishMenu.class);
                SwedishMenu choice = menu.getUserChoise(SwedishMenu.values());
                switch (choice){
                    case CHANGE_LANGUAGE -> chooseLanguage();
                    case CREATE -> menu.createUserFromMenu();
                    case DELETE -> menu.deleteUserFromMenu();
                    default -> System.out.println("Wrong input");
                }
            }

        }
    }

    private void chooseLanguage(){

        menu.displayMenu(LanguageMenu.class);
        LanguageMenu choice = menu.getUserChoise(LanguageMenu.values());
        switch (choice){
            case ENGLISH -> language = LanguageMenu.ENGLISH;
            case SWEDISH -> language = LanguageMenu.SWEDISH;
            default -> System.out.println("Wrong input");
        }
    }

    private void AAAAAAAAAAAAAAAAAAAAAA(){
        for (int i = 0; i < 1000000 ; i++) {
            i++;
        }
    }
}
