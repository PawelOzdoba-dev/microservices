package pl.app.user.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.app.common.SendEmailDto;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name ="NOTIFICATION-SERVICE")
@RequestMapping("/api/mail")
public interface NotificationClient {

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    <T> void sendEmail(@RequestBody SendEmailDto<T> sendEmailDto);
//1. Porównać z klientem Feign w basket.
}
