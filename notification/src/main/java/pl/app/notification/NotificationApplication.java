package pl.app.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAsync
@EnableSwagger2
@EnableJpaAuditing
@EnableScheduling
public class NotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationApplication.class, args);
	}

//	TODO
//	1, Stworzyć Template do zmiany hasła: imię nazwisko i link do zmiany hasła -> zmienna przesyłąna z user service do notification service.
//	Z user service wysyłany jest email, nazwa template i zmienne potrzebne do wygenerowania maila.
}
