package pl.app.notification.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.app.notification.model.Template;

import java.util.Optional;

public interface TemplateRepository extends JpaRepository<Template, Long> {
    Optional<Template> findByName(String name);
}
