package se.ju23.typespeeder.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaderboardRepo extends JpaRepository<Leaderboard ,Long > {



}
