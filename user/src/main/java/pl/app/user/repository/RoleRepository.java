package pl.app.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.app.user.domain.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
