package pl.app.notification.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.app.notification.repository.NotificationHistoryRepository;
import pl.app.notification.service.MailService;

import static pl.app.notification.model.MailStatus.ERROR;

@Component
@RequiredArgsConstructor
public class EmailScheduler {

    private final NotificationHistoryRepository historyRepository;
    private final MailService mailService;

    @Scheduled(fixedDelay = 10000)
    public void resendEmailScheduler() {
        historyRepository.findByStatusAndCounterLessThan(ERROR, 6).forEach(mailService::resendEmail);

    }
}
