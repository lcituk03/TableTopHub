package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Rental;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    // sprawdza czy user ma nieoddane gry
    List<Rental> findAllByUserIdAndReturnDateIsNull(Long userId);
}