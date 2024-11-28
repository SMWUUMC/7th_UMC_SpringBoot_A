package study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import study.validation.validator.CheckPageResolver;
import java.util.*;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public CheckPageResolver checkPagePageableResolver() {
        return new CheckPageResolver();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(checkPagePageableResolver());
    }
}
