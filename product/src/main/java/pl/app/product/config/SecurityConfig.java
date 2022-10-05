package pl.app.product.config;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import pl.app.product.security.JwtAuthorizationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

//Aby przekopiować security potrzeba skopiować: SecurityConfig.java
//Aby przekopiować security potrzeba skopiować: JwtAuthorizationFilter.java

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//umożliwia nam to włączenie adnotacji preAuthority
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .ignoringAntMatchers("/**")//na wszystkich endpoint
                .and()
                .cors()//jeżeli aplikacje są na różnych serwerach to pozwalamy na połączenie między nimi
                .and()
                .addFilter(new JwtAuthorizationFilter(authenticationManager()))//sprawdzamy co to za user
                .sessionManagement()//potrzebna do ustawienia sessionCreationPolicy
                .sessionCreationPolicy(STATELESS);//tylko w czasie przetwarzania requestu jest przechowywana sesja (dla zalogowanego usera).
//
//        W SecurityContextHolder przetrzymywane są dane o zalogowanym użytkowniku w kontekście requestu.
//         Ustawiamy securityContext w JwtAuthorizationFilter.

//        Przekopiować folder z wygenerowanym mikroserwisem i zapisać to w głównym pom.
    }
}
