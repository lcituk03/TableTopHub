package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Rating;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    // wylicza rating dla gry
    @Query("SELECT AVG(r.score) FROM Rating r WHERE r.game.id = :gameId")
    Double getAverageRating(@Param("gameId") Long gameId);

    //czy juz ocenil dana gre
    boolean existsByGameIdAndUserId(Long gameId, Long userId);

    List<Rating> findAllByGameId(Long gameId);

}