package pl.app.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer //właczenie serwera eureka
public class DiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscoveryApplication.class, args);
	}

}
//Discovery jest serwerem eureka. Odpalanie jar zipkina w termianlu za pomocą komendy:

// java -jar zipkin.jar

//Discovery odpala się na porcie: http://localhost:8761/

//Kolejność odpalania:
//- zipkin
//- discovery
//- później bez znaczenia jaka kolejność.

//Odpalenei elasticsearch: http://localhost:9200/ elasticsearch.bat