package pl.app.basket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients// włączenie FeignClients
public class BasketApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasketApplication.class, args);
	}


}
