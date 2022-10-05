package pl.app.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableFeignClients
@EnableSwagger2
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

}

//zrobić pakiet config w której zrobię RestTemplate klasę w której zrobię bean która zwraca restTemplate i bean ma być oznaczony adnotacją: @LoadBalanced
//w linku muszę wstawić nazwę aplikacji czyli (url) PRODUCT-SERVICE
//zrobić security user
//endpoiinty przenieść z monolitu z usera i produktu.

//elasticsearch co to ? https://smartbees.pl/blog/elasticsearch-czyli-wszystko-o-wyszukiwaniu-pelnotekstowym
//product maven projekt ten jest drugą instancją mikroserwisu PRODUCT-SERVICE - odpalany na porcie 8084.
