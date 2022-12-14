package pl.app.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.app.user.domain.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    //ctrl + o - nadpisz
//ctrl + i - zaimplementuj
    //Delete bez dodatkowego selecta (zamiast 2 zapytań)
    @Override
    @Modifying
    @Query(value = "delete from user where id = ?1", nativeQuery = true)
    void deleteById(Long id);

    Optional<User> findByEmail(String email);

    Optional<User> findByToken(String token);
}
