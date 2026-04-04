package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {

    // zeby sprawdzac czy email i username sa unikalne
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);


    // tu tez sprawdzenie unikalnosci ale do update
    boolean existsByUsernameAndIdNot(String username, Long id);
    boolean existsByEmailAndIdNot(String email, Long id);
}
