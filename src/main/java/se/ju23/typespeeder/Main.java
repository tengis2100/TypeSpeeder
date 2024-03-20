package se.ju23.typespeeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.db.Leaderboard;
import se.ju23.typespeeder.db.LeaderboardRepo;
import se.ju23.typespeeder.logic.Controller;
import se.ju23.typespeeder.logic.Game;

@Component
public class Main implements CommandLineRunner {

    @Autowired
    Controller controller;

    @Override
    public void run(String... args) throws Exception {

     controller.run();
    }
}
