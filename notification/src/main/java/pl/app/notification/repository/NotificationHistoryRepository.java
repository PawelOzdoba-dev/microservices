package pl.app.notification.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.app.notification.model.MailStatus;
import pl.app.notification.model.NotificationHistory;
import pl.app.notification.model.Template;

import java.util.List;
import java.util.Optional;

public interface NotificationHistoryRepository extends JpaRepository<NotificationHistory, Long> {

    List<NotificationHistory> findByStatusAndCounterLessThan(MailStatus status, int counter);
}