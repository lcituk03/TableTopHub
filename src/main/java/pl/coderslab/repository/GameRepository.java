package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.coderslab.entity.Game;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {

    // wybiera gry na dana ilosc graczy
    @Query("SELECT g FROM Game g WHERE g.minPlayers <= :count AND g.maxPlayers >= :count")
    List<Game> findByPlayerCount(@Param("count") int count);
}
