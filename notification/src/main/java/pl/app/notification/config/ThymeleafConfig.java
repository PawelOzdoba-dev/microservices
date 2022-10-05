package pl.app.notification.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.StringTemplateResolver;

@Configuration
public class ThymeleafConfig {

    @Bean
    public TemplateEngine templateEngine() {
        StringTemplateResolver resolver = new StringTemplateResolver();//dzięki temu generujemy html
        resolver.setTemplateMode(TemplateMode.HTML);
        TemplateEngine engine = new TemplateEngine();
        engine.setTemplateResolver(resolver);
        return engine;
    }
}
