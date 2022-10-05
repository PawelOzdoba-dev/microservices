package pl.app.notification.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import pl.app.notification.model.MailStatus;
import pl.app.notification.model.NotificationHistory;
import pl.app.notification.model.Template;

import java.util.Locale;
import java.util.Map;

import static pl.app.notification.model.MailStatus.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {

    private final TemplateService templateService;
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;
    private final NotificationHistoryService notificationHistoryService;

    @Async
    public void resendEmail(NotificationHistory notificationHistory) {
        send(notificationHistory.getReceiver(), notificationHistory.getSubject(), notificationHistory.getBody(), notificationHistory);
    }

    @Async
    public void sendEmail(String emailReceiver, Map<String, Object> variables, String templateName) {

        log.info("{}", variables);
        Template template = templateService.findTemplateByName(templateName);
        Context context = new Context(new Locale("pl"), variables);
        String body = templateEngine.process(template.getBody(), context); //przeprocesowany template zostanie zwrócony
        NotificationHistory notificationHistory = notificationHistoryService.save(NotificationHistory.builder()
                .receiver(emailReceiver)
                .body(body)
                .subject(template.getSubject())
                .status(PENDING)
                .build());
        send(emailReceiver, template.getSubject(), body, notificationHistory);
    }

    private void send(String emailReceiver, String subject, String body, NotificationHistory notificationHistory) {
        try {
            javaMailSender.send(message -> {
                MimeMessageHelper helper = new MimeMessageHelper(message);//helper do ustawienia wartości
                helper.setTo(emailReceiver);
                helper.setSubject(subject);
                helper.setText(body, true);//true ponieważ wysyłamy html a nie tekst
                helper.setFrom("NotificationSpring@gmail.com");
            });
            notificationHistory.setStatus(SUCCESS);

        } catch (Exception e) {
            log.error("Error during sendin email ", e);
            notificationHistory.setCounter(notificationHistory.getCounter() + 1);
            notificationHistory.setStatus(ERROR);
        } finally {
            notificationHistoryService.save(notificationHistory);
        }
    }
}
