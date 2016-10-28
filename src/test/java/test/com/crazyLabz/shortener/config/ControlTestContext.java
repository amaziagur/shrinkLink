package test.com.crazyLabz.shortener.config;


import com.crazyLabz.shortener.service.RedirectService;
import com.crazyLabz.shortener.service.ShortenerService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {
        "com.crazyLabz.shortener.rest"})
public class ControlTestContext {

    @Bean
    public ShortenerService getShortenerService() {
        return Mockito.mock(ShortenerService.class);
    }

    @Bean
    public RedirectService getRedirectService() {
        return Mockito.mock(RedirectService.class);
    }
}
