package se.ju23.typespeeder.logic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GametaskRepo extends JpaRepository <Gametask, Long>{
    List<Gametask> findBySolution(String solution);

}
