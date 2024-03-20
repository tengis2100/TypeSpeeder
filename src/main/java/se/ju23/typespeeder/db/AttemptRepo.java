package se.ju23.typespeeder.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.ju23.typespeeder.logic.Attempt;

@Repository
public interface AttemptRepo extends JpaRepository <Attempt, Long> {

}
