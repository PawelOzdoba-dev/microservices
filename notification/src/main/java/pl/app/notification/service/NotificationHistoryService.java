package pl.app.notification.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.app.notification.model.NotificationHistory;
import pl.app.notification.repository.NotificationHistoryRepository;

@Service
@RequiredArgsConstructor
public class NotificationHistoryService {

    private final NotificationHistoryRepository historyRepository;

    public NotificationHistory save(NotificationHistory notificationHistory) {
        return historyRepository.save(notificationHistory);
    }
}
